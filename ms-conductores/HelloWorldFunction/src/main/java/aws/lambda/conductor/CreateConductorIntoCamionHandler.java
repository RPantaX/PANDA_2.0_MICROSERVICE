package aws.lambda.conductor;

import aws.lambda.GlobalException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class CreateConductorIntoCamionHandler implements RequestHandler <Map<String, String>, Map<String, String>>  {
    private final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    private final DynamoDB dynamoDB = new DynamoDB(client);
    private final Table table = dynamoDB.getTable(System.getenv("TABLE_NAME"));
    @Override
    public Map<String, String> handleRequest(final Map<String, String> input, final Context context) {
        Map<String, String> response = new HashMap<>();
        String id = input.get("id");
        try {
            Map<String, String> expressionAttributeNames = new HashMap<>();
            Map<String, Object> expressionAttributeValues = new HashMap<>();
            StringBuilder updateExpression = new StringBuilder("SET ");

            // Excluir DNI de los atributos a actualizar
            input.remove("id");

            for (Map.Entry<String, String> entry : input.entrySet()) {
                String attributeName = entry.getKey();
                String placeholderName = "#" + attributeName;
                String placeholderValue = ":" + attributeName;

                expressionAttributeNames.put(placeholderName, attributeName);
                expressionAttributeValues.put(placeholderValue, entry.getValue());

                updateExpression.append(placeholderName).append(" = ").append(placeholderValue).append(", ");
            }

            // Agregar modificadoEn
            String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
            expressionAttributeNames.put("#modificadoEn", "modificadoEn");
            expressionAttributeValues.put(":modificadoEn", currentDateTime);
            updateExpression.append("#modificadoEn = :modificadoEn");

            UpdateItemSpec updateItemSpec = new UpdateItemSpec()
                    .withPrimaryKey("id", id)
                    .withUpdateExpression(updateExpression.toString())
                    .withNameMap(expressionAttributeNames)
                    .withValueMap(expressionAttributeValues)
                    .withReturnValues(ReturnValue.ALL_NEW);

            UpdateItemOutcome outcome = table.updateItem(updateItemSpec);

            response.put("message", "Conductor updated successfully");
            response.put("updatedItem", outcome.getItem().asMap().toString());

        } catch (AmazonDynamoDBException e) {
            throw new GlobalException("DynamoDB Exception: " + e.getMessage(), e.getCause());
        } catch (Exception e) {
            throw new GlobalException("Exception: " + e.getMessage(), e.getCause());
        }

        return response;
    }
}
