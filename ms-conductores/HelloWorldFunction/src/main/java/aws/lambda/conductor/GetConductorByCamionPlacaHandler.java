package aws.lambda.conductor;

import aws.lambda.GlobalException;
import aws.lambda.entity.ConductorTable;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class GetConductorByCamionPlacaHandler implements RequestHandler<Map<String, String>, Map<String, String>> {

    private final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    private final DynamoDB dynamoDB = new DynamoDB(client);
    private final Table table = dynamoDB.getTable(System.getenv("TABLE_NAME"));

    @Override
    public Map<String, String> handleRequest(Map<String, String> input, Context context) {
        Map<String, String> response = new HashMap<>();
        String camionPlaca = input.get("camionPlaca");

        try {
            System.out.println("LOG1: " + camionPlaca);
            // Obtiene el índice secundario para camionPlaca
            Index index = table.getIndex(System.getenv("INDEX_CAMION_PLACA_NAME"));
            QuerySpec spec = new QuerySpec()
                    .withKeyConditionExpression("camionPlaca = :v_placa")
                    .withValueMap(new ValueMap().withString(":v_placa", camionPlaca));

            // Ejecuta la consulta
            //Item item = index.query(spec).iterator().next();  // Solo obtenemos el primer resultado
            Item item = index.query(spec).iterator().next();
            System.out.println("Items disponibles:");
                System.out.println(item.toJSON());  // Imprimir cada ítem en formato JSON

            ConductorTable conductorTable = new ConductorTable();
            if(item.hasAttribute("DNI")) {
                conductorTable.setId(item.getString("id"));
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
            }
            else {
                conductorTable.setId(item.getString("id"));
                conductorTable.setCamionFechMantenimiento(item.getString("camionFechMantenimiento"));
                conductorTable.setCamionPlaca(item.getString("camionPlaca"));
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
            }
//             Mapea el resultado al objeto ConductorTable
//
//
//             Convierte el conductor a JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(conductorTable);

            response.put("message", "Conductor found successfully");
            response.put("conductor", jsonResponse);  // Aquí ya no usamos una lista

        } catch (AmazonDynamoDBException e) {
            throw new GlobalException("DynamoDB Exception: " + e.getMessage(), e.getCause());
        } catch (Exception e) {
            throw new GlobalException("Exception: " + e.getMessage(), e.getCause());
        }

        return response;
    }
}