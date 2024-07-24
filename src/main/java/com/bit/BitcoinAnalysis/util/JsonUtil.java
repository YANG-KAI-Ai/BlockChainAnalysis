package com.bit.BitcoinAnalysis.util;

import com.bit.BitcoinAnalysis.entity.CoinPrice;
import com.bit.BitcoinAnalysis.entity.Derivatives;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.StringWriter;
import java.util.*;

public class JsonUtil {

    public String sortDerivativesData(String json) throws Exception {
        // Parse JSON string into a list of JsonNode objects
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        List<JsonNode> jsonList = new ArrayList<>();
        jsonNode.iterator().forEachRemaining(jsonList::add);

        // Sort the list by the "price" attribute
        Comparator<JsonNode> comparator = Comparator.comparing(jsonNode1 -> jsonNode1.get("price").asInt());
        Collections.sort(jsonList, comparator.reversed());

        // Convert the sorted list back to a JSON string
        String sortedJsonString = objectMapper.writeValueAsString(jsonList);

        JsonNode replacementJsonNode = objectMapper.readTree(sortedJsonString);

        // Convert the modified JsonNode back to a JSON string
        String modifiedJsonString = objectMapper.writeValueAsString(replacementJsonNode);
        return modifiedJsonString;
    }

    public String sortGlobalData(String json) throws Exception {
        // Parse JSON string into a list of JsonNode objects
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(json).get("data").get("total_volume");
        // 遍历每个币种，构造对应的CoinPrice对象
        List<CoinPrice> coinPrices = new ArrayList<>();
        Iterator<String> iterator = rootNode.fieldNames();
        while (iterator.hasNext()) {
            String coinName = iterator.next();
            Long price = rootNode.get(coinName).asLong();
            coinPrices.add(new CoinPrice(coinName, price));
        }

        // 将CoinPrice对象转换为对应的JSON字符串
        StringWriter stringWriter = new StringWriter();
        JsonGenerator generator = objectMapper.getFactory().createGenerator(stringWriter);
        generator.writeStartArray();
        for (CoinPrice coinPrice : coinPrices) {
            generator.writeStartObject();
            generator.writeStringField("name", coinPrice.getName());
            generator.writeNumberField("price", coinPrice.getPrice());
            generator.writeEndObject();
        }
        generator.writeEndArray();
        generator.flush();
        String result = stringWriter.toString();
        return sortGlobalDataNew(result);
    }

    public String sortApyData(String json) throws Exception {
        // Parse JSON string into a list of JsonNode objects
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        List<JsonNode> jsonList = new ArrayList<>();
        jsonNode.iterator().forEachRemaining(jsonList::add);

        // Sort the list by the "price" attribute
        Comparator<JsonNode> comparator = Comparator.comparing(jsonNode1 -> jsonNode1.get("apy").get("net_apy").asDouble());
        Collections.sort(jsonList, comparator.reversed());

        // Convert the sorted list back to a JSON string
        String sortedJsonString = objectMapper.writeValueAsString(jsonList);

        JsonNode replacementJsonNode = objectMapper.readTree(sortedJsonString);

        // Convert the modified JsonNode back to a JSON string
        String modifiedJsonString = objectMapper.writeValueAsString(replacementJsonNode);

        List<String> list = objectMapper.readValue(modifiedJsonString, List.class);
        list.remove(0);
        String newJson = objectMapper.writeValueAsString(list);
        return newJson;
    }

    public String sortTvlData(String json) throws Exception {
        // Parse JSON string into a list of JsonNode objects
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        List<JsonNode> jsonList = new ArrayList<>();
        jsonNode.iterator().forEachRemaining(jsonList::add);

        // Sort the list by the "price" attribute
        Comparator<JsonNode> comparator = Comparator.comparing(jsonNode1 -> jsonNode1.get("tvl").get("tvl").asLong());
        Collections.sort(jsonList, comparator.reversed());

        // Convert the sorted list back to a JSON string
        String sortedJsonString = objectMapper.writeValueAsString(jsonList);

        JsonNode replacementJsonNode = objectMapper.readTree(sortedJsonString);

        // Convert the modified JsonNode back to a JSON string
        String modifiedJsonString = objectMapper.writeValueAsString(replacementJsonNode);
        return modifiedJsonString;
    }

    public String sortGlobalDataNew(String json) throws Exception {
        // Parse JSON string into a list of JsonNode objects
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        List<JsonNode> jsonList = new ArrayList<>();
        jsonNode.iterator().forEachRemaining(jsonList::add);

        // Sort the list by the "price" attribute
        Comparator<JsonNode> comparator = Comparator.comparing(jsonNode1 -> jsonNode1.get("price").asLong());
        Collections.sort(jsonList, comparator.reversed());

        // Convert the sorted list back to a JSON string
        String sortedJsonString = objectMapper.writeValueAsString(jsonList);

        JsonNode replacementJsonNode = objectMapper.readTree(sortedJsonString);

        // Convert the modified JsonNode back to a JSON string
        String modifiedJsonString = objectMapper.writeValueAsString(replacementJsonNode);
        return modifiedJsonString;
    }


    public String sortExchangeData(String json) throws Exception {
        // Parse JSON string into a list of JsonNode objects
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        List<JsonNode> jsonList = new ArrayList<>();
        jsonNode.iterator().forEachRemaining(jsonList::add);

        // Sort the list by the "price" attribute
        Comparator<JsonNode> comparator = Comparator.comparing(jsonNode1 -> jsonNode1.get("price").asInt());
        Collections.sort(jsonList, comparator.reversed());

        // Convert the sorted list back to a JSON string
        String sortedJsonString = objectMapper.writeValueAsString(jsonList);

        JsonNode replacementJsonNode = objectMapper.readTree(sortedJsonString);

        // Convert the modified JsonNode back to a JSON string
        String modifiedJsonString = objectMapper.writeValueAsString(replacementJsonNode);
        return null;
    }

    public String getDerivativesFlowString(Map<String, String> json1, Map<String, String> json2, Map<String, String> json3, Map<String, String> json4) throws Exception {
        // Parse JSON string into a list of JsonNode objects
        ObjectMapper objectMapper = new ObjectMapper();

        // 将Json对象合并到一个新的Java对象中
        ArrayNode mergedNode = objectMapper.createArrayNode();
        mergedNode.add(objectMapper.writeValueAsString(json1));
        mergedNode.add(objectMapper.writeValueAsString(json2));
        mergedNode.add(objectMapper.writeValueAsString(json3));
        mergedNode.add(objectMapper.writeValueAsString(json4));

        return mergedNode.toString();
    }

    public String getGlobalFlowString(Map<String, String> json1, Map<String, String> json2, Map<String, String> json3, Map<String, String> json4, Map<String, String> json5, Map<String, String> json6) throws Exception {
        // Parse JSON string into a list of JsonNode objects
        ObjectMapper objectMapper = new ObjectMapper();

        // 将Json对象合并到一个新的Java对象中
        ArrayNode mergedNode = objectMapper.createArrayNode();
        mergedNode.add(objectMapper.writeValueAsString(json1));
        mergedNode.add(objectMapper.writeValueAsString(json2));
        mergedNode.add(objectMapper.writeValueAsString(json3));
        mergedNode.add(objectMapper.writeValueAsString(json4));
        mergedNode.add(objectMapper.writeValueAsString(json5));
        mergedNode.add(objectMapper.writeValueAsString(json6));

        return mergedNode.toString();
    }



//    public String sortNFTData(String json) throws Exception {
//        // Parse JSON string into a list of JsonNode objects
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode = objectMapper.readTree(json).get("data");
//        List<JsonNode> jsonList = new ArrayList<>();
//        jsonNode.iterator().forEachRemaining(jsonList::add);
//
//        // Sort the list by the "num_market_pairs" attribute
//        Comparator<JsonNode> comparator = Comparator.comparing(jsonNode1 -> jsonNode1.get("num_market_pairs").asInt());
//        Collections.sort(jsonList, comparator.reversed());
//
//        // Convert the sorted list back to a JSON string
//        String sortedJsonString = objectMapper.writeValueAsString(jsonList);
//
//        // Parse the original JSON string and the replacement JSON string into JsonNode objects
//        JsonNode originalJsonNode = objectMapper.readTree(json);
//        JsonNode replacementJsonNode = objectMapper.readTree(sortedJsonString);
//
//        // Replace the "data" node in the original JSON object with the replacement JSON object
//        ((ObjectNode) originalJsonNode).replace("data", replacementJsonNode);
//
//        // Convert the modified JsonNode back to a JSON string
//        String modifiedJsonString = objectMapper.writeValueAsString(originalJsonNode);
//        System.out.println(modifiedJsonString);
//        return modifiedJsonString;
//    }

    public String take(String json, int n) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        List<JsonNode> result = new ArrayList<>();
        Iterator<JsonNode> iterator = jsonNode.elements();
        for (int i = 0; i < n && iterator.hasNext(); i++) {
            result.add(iterator.next());
        }
        String modifiedJsonString = objectMapper.writeValueAsString(result);
        return modifiedJsonString;
    }

    public String takeNFS(String json, int n) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json).get("results");
        List<JsonNode> result = new ArrayList<>();
        List<JsonNode> resultRoot = new ArrayList<>();
        Iterator<JsonNode> iterator = jsonNode.elements();
        for (int i = 0; i < n && iterator.hasNext(); i++) {
            result.add(iterator.next());
        }
        String modifiedJsonString = objectMapper.writeValueAsString(result);
        return modifiedJsonString;
    }

    public String getJsonNew(String json) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String convertedJson = "";
        try {
            Map<String, Integer> data = objectMapper.readValue(json, HashMap.class);
            List<Map<String, String>> convertedData = new ArrayList<>();

            for (Map.Entry<String, Integer> entry : data.entrySet()) {
                String name = entry.getKey().split(" ")[0];
                String value = String.valueOf(entry.getValue());

                Map<String, String> entryMap = new HashMap<>();
                entryMap.put("name", name);
                entryMap.put("value", value);

                convertedData.add(entryMap);
            }
            convertedJson = objectMapper.writeValueAsString(convertedData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return convertedJson;
    }
}
