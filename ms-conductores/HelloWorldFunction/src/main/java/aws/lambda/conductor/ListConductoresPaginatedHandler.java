package aws.lambda.conductor;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.*;

public class ListConductoresPaginatedHandler implements RequestHandler<Map<String, String>, Map<String, Object>> {

    private final DynamoDB dynamoDB = new DynamoDB(AmazonDynamoDBClientBuilder.standard().build());
    private final Table table = dynamoDB.getTable(System.getenv("TABLE_NAME"));

    @Override
    public Map<String, Object> handleRequest(Map<String, String> input, Context context) {
        Map<String, Object> response = new HashMap<>();
        int pageSize = Integer.parseInt(input.getOrDefault("pageSize", "10")); // Número de elementos por página
        String lastEvaluatedKeyDNI = input.get("lastEvaluatedKey"); // Clave de evaluación anterior para paginación

        try {
            Map<String, AttributeValue> exclusiveStartKey = null;
            if (lastEvaluatedKeyDNI != null) {
                exclusiveStartKey = new HashMap<>();
                exclusiveStartKey.put("DNI", new AttributeValue().withS(lastEvaluatedKeyDNI));
            }

            ScanSpec scanSpec = new ScanSpec()
                    .withMaxResultSize(pageSize)
                    .withExclusiveStartKey((KeyAttribute) exclusiveStartKey);

            ItemCollection<ScanOutcome> items = table.scan(scanSpec);

            List<Map<String, Object>> conductores = new ArrayList<>();
            Iterator<Item> iterator = items.iterator();

            while (iterator.hasNext()) {
                conductores.add(iterator.next().asMap());
            }

            Map<String, AttributeValue> lastKey = items.getLastLowLevelResult().getScanResult().getLastEvaluatedKey();

            response.put("conductores", conductores);
            response.put("numeroPagina", Integer.parseInt(input.getOrDefault("numeroPagina", "1"))); // Agregar número de página
            response.put("medidaPagina", pageSize); // Tamaño de página
            response.put("totalElementos", conductores.size()); // Número de elementos devueltos

            if (lastKey != null && lastKey.containsKey("DNI")) {
                response.put("lastEvaluatedKey", lastKey.get("DNI").getS()); // Clave de evaluación anterior
            } else {
                response.put("lastEvaluatedKey", null); // No hay más elementos
            }

        } catch (Exception e) {
            response.put("error", e.getMessage());
        }

        return response;
    }
}
