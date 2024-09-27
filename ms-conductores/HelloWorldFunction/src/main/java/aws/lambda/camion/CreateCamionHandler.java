package aws.lambda.camion;

import aws.lambda.GlobalException;
import aws.lambda.entity.ConductorTable;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CreateCamionHandler implements RequestHandler<Map<String, String>, Map<String, String>> {
    private final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    private final DynamoDB dynamoDB = new DynamoDB(client);
    private final Table table = dynamoDB.getTable(System.getenv("TABLE_NAME"));
    @Override
    public Map<String, String> handleRequest(final Map<String, String> input, final Context context) {
        Map<String, String> response = new HashMap<>();
        String id = UUID.randomUUID().toString();
        try {
            String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
            ConductorTable conductorTable = new ConductorTable();
            conductorTable.setCamionPlaca(input.get("camionPlaca"));
            conductorTable.setCamionMarca(input.get("camionMarca"));
            conductorTable.setCamionModelo(input.get("camionModelo"));
            conductorTable.setCamionAnioFabricacion(input.get("camionAnioFabricacion"));
            conductorTable.setCamionCertHabVehicular(Boolean.valueOf(input.get("camionCertHabVehicular")));
            conductorTable.setCamionSOAT(input.get("camionSOAT"));
            conductorTable.setCamionRevTecnica(Boolean.valueOf(input.get("camionRevTecnica")));
            conductorTable.setCamionFechMantenimiento(input.get("camionFechMantenimiento"));
            conductorTable.setCarretaPlaca(input.get("carretaPlaca"));
            conductorTable.setCarretaCapacidad(Double.valueOf(input.get("carretaCapacidad")));
            conductorTable.setCarretaCargaUtil(Double.valueOf(input.get("carretaCargaUtil")));
            conductorTable.setCarretaConfVehicular(input.get("carretaConfVehicular"));
            conductorTable.setEstado(true);
            conductorTable.setCreadoEn(currentDateTime);
            Item item = new Item().withPrimaryKey("id", id)
                    .withString("camionPlaca", conductorTable.getCamionPlaca())
                    .withString("camionMarca", conductorTable.getCamionMarca())
                    .withString("camionModelo", conductorTable.getCamionModelo())
                    .withString("camionAnioFabricacion", conductorTable.getCamionAnioFabricacion())
                    .withBoolean("camionCertHabVehicular", conductorTable.getCamionCertHabVehicular())
                    .withString("camionSOAT", conductorTable.getCamionSOAT())
                    .withBoolean("camionRevTecnica", conductorTable.getCamionRevTecnica())
                    .withString("camionFechMantenimiento", conductorTable.getCamionFechMantenimiento())
                    .withString("carretaPlaca", conductorTable.getCarretaPlaca())
                    .withDouble("carretaCapacidad", conductorTable.getCarretaCapacidad())
                    .withDouble("carretaCargaUtil", conductorTable.getCarretaCargaUtil())
                    .withString("carretaConfVehicular", conductorTable.getCarretaConfVehicular());

            // Insert the item into DynamoDB
            PutItemOutcome outcome = table.putItem(item);
            System.out.println(outcome);
            // Use ObjectMapper to convert the created item to a JSON string response
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonItem = objectMapper.writeValueAsString(item.asMap());

            // Return the created item as the response
            response.put("message", "Camion created successfully");
            response.put("createdItem", jsonItem);

        } catch (AmazonDynamoDBException e) {
            throw new GlobalException("DynamoDB Exception: " + e.getMessage(), e.getCause());
        } catch (Exception e) {
            throw new GlobalException("Exception: " + e.getMessage(), e.getCause());
        }

        return response;
    }

}
