package aws.lambda.conductor;

import aws.lambda.GlobalException;
import aws.lambda.entity.ConductorTable;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Index;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetConductorByDNIHandler implements RequestHandler<Map<String, String>, Map<String, String>> {

    private final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    private final DynamoDB dynamoDB = new DynamoDB(client);
    private final Table table = dynamoDB.getTable(System.getenv("TABLE_NAME"));
    @Override
    public Map<String, String> handleRequest(Map<String, String> input, Context context) {
        Map<String, String> response = new HashMap<>();
        String camionPlaca = input.get("camionPlaca");

        try {
            // Obtiene el índice secundario para camionPlaca
            Index index = table.getIndex(System.getenv("INDEX_DNI_NAME"));
            QuerySpec spec = new QuerySpec()
                    .withKeyConditionExpression("DNI = :v_DNI")
                    .withValueMap(new ValueMap().withString(":v_DNI", camionPlaca));

            // Ejecuta la consulta
            List<ConductorTable> conductores = new ArrayList<>();
            for (Item item : index.query(spec)) {
                ConductorTable conductorTable = new ConductorTable();
                conductorTable.setCamionFechMantenimiento(item.getString("camionFechMantenimiento"));
                conductorTable.setCamionPlaca(item.getString("camionPlaca"));
                conductorTable.setDNI(item.getString("DNI"));
                conductorTable.setNombres(item.getString("nombres"));
                conductorTable.setApellidos(item.getString("apellidos"));
                conductorTable.setNroLicencia(item.getString("nroLicencia"));
                conductorTable.setIdUser(item.getInt("idUser"));
                conductorTable.setCertPsicofisico(item.getBoolean("certPsicofisico"));
                conductorTable.setCertMecanicaBasica(item.getBoolean("certMecanicaBasica"));
                conductorTable.setCertPrimerosAux(item.getBoolean("certPrimerosAux"));
                conductorTable.setCertSeguridadVial(item.getBoolean("certSeguridadVial"));
                conductorTable.setCertBuquesPortuaria(item.getBoolean("certBuquesPortuaria"));
                conductorTable.setCamionMarca(item.getString("camionMarca"));
                conductorTable.setCamionModelo(item.getString("camionModelo"));
                conductorTable.setCamionAnioFabricacion(item.getString("camionAnioFabricacion"));
                conductorTable.setCamionCertHabVehicular(item.getBoolean("camionCertHabVehicular"));
                conductorTable.setCamionSOAT(item.getString("camionSOAT"));
                conductorTable.setCamionRevTecnica(item.getBoolean("camionRevTecnica"));
                conductorTable.setCarretaPlaca(item.getString("carretaPlaca"));
                conductorTable.setCarretaCapacidad(item.getDouble("carretaCapacidad"));
                conductorTable.setCarretaCargaUtil(item.getDouble("carretaCargaUtil"));
                conductorTable.setCarretaConfVehicular(item.getString("carretaConfVehicular"));
                conductorTable.setEstado(item.getBoolean("estado"));
                conductorTable.setUsuarioModificador(item.getString("usuarioModificador"));
                conductorTable.setCreadoEn(item.getString("creadoEn"));
                conductorTable.setModificadoEn(item.getString("modificadoEn"));
                conductorTable.setEliminadoEn(item.getString("eliminadoEn"));

                conductores.add(conductorTable);
            }

            // Convierte la lista de conductores a JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(conductores);

            response.put("message", "Conductores found successfully");
            response.put("conductores", jsonResponse);

        } catch (AmazonDynamoDBException e) {
            throw new GlobalException("DynamoDB Exception: " + e.getMessage(), e.getCause());
        } catch (Exception e) {
            throw new GlobalException("Exception: " + e.getMessage(), e.getCause());
        }

        return response;
    }
}
