package com.bit.BitcoinAnalysis.controller;

import com.bit.BitcoinAnalysis.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.*;

import static com.bit.BitcoinAnalysis.controller.MyTask.*;

@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("showNFTData")
    @ResponseBody
    public String showNFTData() throws Exception {
        String result = NFTJson;

        ObjectMapper mapper = new ObjectMapper();

        JsonNode rootNode = mapper.readTree(result);

        DecimalFormat decimalFormat = new DecimalFormat("#.##"); // 设置保留两位小数的格式

        for (JsonNode node : rootNode) {
            double oldValue = node.get("volume").asDouble();
            double minValue = oldValue * 0.995;
            double maxValue = oldValue * 1.005;
            double randomValue = minValue + Math.random() * (maxValue - minValue);
            double newValue = randomValue;

            ((ObjectNode) node).put("volume", Double.valueOf(decimalFormat.format(newValue))); // 将生成的随机数保留两位小数

        }


        String newJsonString = mapper.writeValueAsString(rootNode);
        result = newJsonString;


        return result;
    }

    @RequestMapping("showNFTDataList")
    @ResponseBody
    public String showNFTDataList() throws Exception {
        String result = NFTJsonList;

        ObjectMapper mapper = new ObjectMapper();

        JsonNode rootNode = mapper.readTree(result);

        DecimalFormat decimalFormat = new DecimalFormat("#.##"); // 设置保留两位小数的格式

        String str = "volumePercentageChange,avgPrice,avgPricePercentageChange,traders,tradersPercentageChange";
        String[] strArray = str.split(",");

        for (JsonNode node : rootNode) {
            double oldValue = node.get("volume").asDouble();
            double minValue = oldValue * 0.995;
            double maxValue = oldValue * 1.005;
            double randomValue = minValue + Math.random() * (maxValue - minValue);
            double newValue = randomValue;

            ((ObjectNode) node).put("volume", Double.valueOf(decimalFormat.format(newValue))); // 将生成的随机数保留两位小数

        }

        // 使用 for-each 循环遍历字符串数组
        for (String str2 : strArray) {
            for (JsonNode node : rootNode) {
                System.out.println(node.get("VolumePercentageChange"));
                double oldValue = node.get(str2).asDouble();
                double minValue = oldValue + 10;
                double maxValue = oldValue - 10;
                double randomValue = minValue + Math.random() * (maxValue - minValue);
                double newValue = randomValue;

                ((ObjectNode) node).put(str2, Double.valueOf(decimalFormat.format(newValue))); // 将生成的随机数保留两位小数

            }
        }


        String newJsonString = mapper.writeValueAsString(rootNode);
        result = newJsonString;


        return result;
    }

    @RequestMapping("showDerivativesData")
    @ResponseBody
    public String showDerivativesData() throws Exception {
        String result = DerivativesJson;

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(result);

        DecimalFormat decimalFormat = new DecimalFormat("#.##"); // 设置保留两位小数的格式

        for (JsonNode node : rootNode) {
            double oldValue = node.get("price").asDouble();
            double minValue = oldValue * 0.995;
            double maxValue = oldValue * 1.005;
            double randomValue = minValue + Math.random() * (maxValue - minValue);
            double newValue = randomValue;

            ((ObjectNode) node).put("price", Double.valueOf(decimalFormat.format(newValue))); // 将生成的随机数保留两位小数

        }


        String newJsonString = mapper.writeValueAsString(rootNode);
        result = newJsonString;

        return result;
    }

    @RequestMapping("showDerivativesDataForFlow")
    @ResponseBody
    public String showDerivativesDataForFlow() throws Exception {
        JsonUtil jsonUtil = new JsonUtil();
        String[] okxArr = (String[]) okxList.toArray(new String[0]);
        String okxResult = String.join(",", okxArr);
        String[] binanceArr = (String[]) binanceList.toArray(new String[0]);
        String binanceResult = String.join(",", binanceArr);
        String[] byBitArr = (String[]) byBitList.toArray(new String[0]);
        String byBitResult = String.join(",", byBitArr);
        String[] dateArr = (String[]) dateList.toArray(new String[0]);
        String dateResult = String.join(",", dateArr);

        Map<String, String> okxMap = new HashMap<>();
        Map<String, String> binanceMap = new HashMap<>();
        Map<String, String> byBitMap = new HashMap<>();
        Map<String, String> dateMap = new HashMap<>();


        okxMap.put("okx", okxResult);
        binanceMap.put("binance", binanceResult);
        byBitMap.put("byBit", byBitResult);
        dateMap.put("dateList", dateResult);
        String derivativesJson = jsonUtil.getDerivativesFlowString(okxMap, binanceMap, byBitMap, dateMap);

        return derivativesJson;
    }

    @RequestMapping("showApyData")
    @ResponseBody
    public String showApyData() throws Exception {
        String result = ApyJson;

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(result);

        DecimalFormat decimalFormat = new DecimalFormat("#.##"); // 设置保留两位小数的格式

        for (JsonNode node : rootNode) {
            double oldValue = node.get("apy").get("net_apy").asDouble();
            double minValue = oldValue * 0.995;
            double maxValue = oldValue * 1.005;
            double randomValue = minValue + Math.random() * (maxValue - minValue);
            double newValue = randomValue;

            ((ObjectNode) node.get("apy")).put("net_apy", Double.valueOf(decimalFormat.format(newValue))); // 将生成的随机数保留两位小数

        }


        String newJsonString = mapper.writeValueAsString(rootNode);
        result = newJsonString;

        return result;
    }

    @RequestMapping("showApyDataList")
    @ResponseBody
    public String showApyDataList() throws Exception {
        String result = ApyJsonList;

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(result);

        DecimalFormat decimalFormat = new DecimalFormat("#.##"); // 设置保留两位小数的格式

        for (JsonNode node : rootNode) {
            double oldValue = node.get("apy").get("net_apy").asDouble();
            double minValue = oldValue + 1;
            double maxValue = oldValue - 0.5;
            double randomValue = minValue + Math.random() * (maxValue - minValue);
            double newValue = randomValue;

            ((ObjectNode) node.get("apy")).put("net_apy", Double.valueOf(decimalFormat.format(newValue))); // 将生成的随机数保留两位小数

        }


        String newJsonString = mapper.writeValueAsString(rootNode);
        result = newJsonString;
        JsonUtil jsonUtil = new JsonUtil();
        result = jsonUtil.sortApyData(result);

        return result;
    }

    @RequestMapping("showTvlData")
    @ResponseBody
    public String showTvlData() throws Exception {
        String result = TvlJson;

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(result);

        DecimalFormat decimalFormat = new DecimalFormat("#.##"); // 设置保留两位小数的格式

        for (JsonNode node : rootNode) {
            double oldValue = node.get("tvl").get("tvl").asDouble();
            double minValue = oldValue * 0.995;
            double maxValue = oldValue * 1.005;
            double randomValue = minValue + Math.random() * (maxValue - minValue);
            double newValue = randomValue;

            ((ObjectNode) node.get("tvl")).put("tvl", Double.valueOf(decimalFormat.format(newValue))); // 将生成的随机数保留两位小数

        }

        String newJsonString = mapper.writeValueAsString(rootNode);
        result = newJsonString;


        return result;
    }

    @RequestMapping("showNewsData")
    @ResponseBody
    public String showNewsData(String page) throws Exception {
        JsonUtil jsonUtil = new JsonUtil();
        JavaExample javaExample = new JavaExample();
        String result = javaExample.getNewsData(page);

        return result;
    }

    @RequestMapping("showGlobalData")
    @ResponseBody
    public String showGlobalData() throws Exception {
        JsonUtil jsonUtil = new JsonUtil();
        JavaExample javaExample = new JavaExample();
        String result = javaExample.getGlobalData();

        return jsonUtil.take(jsonUtil.sortGlobalData(result),10);
    }

    @RequestMapping("showGlobalDataForFlow")
    @ResponseBody
    public String showGlobalDataForFlow() throws Exception {
        JsonUtil jsonUtil = new JsonUtil();
        String[] vndArr = (String[]) vndList.toArray(new String[0]);
        String vndResult = String.join(",", vndArr);
        String[] idrArr = (String[]) idrList.toArray(new String[0]);
        String idrResult = String.join(",", idrArr);
        String[] satsArr = (String[]) satsList.toArray(new String[0]);
        String satsResult = String.join(",", satsArr);
        String[] mmkArr = (String[]) mmkList.toArray(new String[0]);
        String mmkResult = String.join(",", mmkArr);
        String[] krwArr = (String[]) krwList.toArray(new String[0]);
        String krwResult = String.join(",", krwArr);
        String[] dateArr = (String[]) dateList.toArray(new String[0]);
        String dateResult = String.join(",", dateArr);


        Map<String, String> vndMap = new HashMap<>();
        Map<String, String> idrMap = new HashMap<>();
        Map<String, String> satsMap = new HashMap<>();
        Map<String, String> mmkMap = new HashMap<>();
        Map<String, String> krwMap = new HashMap<>();
        Map<String, String> dateMap = new HashMap<>();
        vndMap.put("VND", vndResult);
        idrMap.put("IDR", idrResult);
        satsMap.put("SATS", satsResult);
        mmkMap.put("MMK", mmkResult);
        krwMap.put("KRW", krwResult);
        dateMap.put("dateList", dateResult);

        String globalJson = jsonUtil.getGlobalFlowString(vndMap, idrMap, satsMap, mmkMap, krwMap, dateMap);

        return globalJson;
    }

    @RequestMapping("showExchangeData")
    @ResponseBody
    public String showExchangeData() throws Exception {
        JsonUtil jsonUtil = new JsonUtil();
        JavaExample javaExample = new JavaExample();
        String result = javaExample.getExchangeData();

        // Create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        Object json = new Object();
        // Convert JSON string to JSON object
        try {
            json = objectMapper.readValue(result, Object.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return jsonUtil.take(jsonUtil.sortGlobalData(result),10);
    }
//
//    @RequestMapping("showNewData")
//    @ResponseBody
//    public String showNewData() throws Exception {
//        JavaExample javaExample = new JavaExample();
//        String result = javaExample.getNewsData();
//
//         // Create ObjectMapper instance
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        Object json = new Object();
//        // Convert JSON string to JSON object
//        try {
//            json = objectMapper.readValue(result, Object.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        return sortNFTData(result);
//    }
    @RequestMapping("showHotBlockChainPools")
    @ResponseBody
    public String showHotBlockChainPools() throws Exception  {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(poolsList);
    }

    @RequestMapping("showHotBlockChainToken")
    @ResponseBody
    public String showHotBlockChainToken() throws Exception  {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(tokenList);
    }

}
