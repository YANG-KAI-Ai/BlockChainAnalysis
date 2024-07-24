package com.bit.BitcoinAnalysis.controller;

import com.bit.BitcoinAnalysis.util.JsonUtil;
import com.bit.BitcoinAnalysis.util.RedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class MyTask {
    /**
     * 定时计算。每天凌晨 01:00 执行一次
     */
//    @Scheduled(cron = "0 0 1 * * *")
//    public void show() {
//        System.out.println("show method 2");
//    }
    @Autowired
    private StringRedisTemplate redisTemplate;

    public static ArrayList okxList = new ArrayList(){
        {
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
        }
    };
    public static ArrayList binanceList = new ArrayList(){
        {
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
        }
    };
    public static ArrayList byBitList = new ArrayList(){
        {
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
        }
    };

    public static ArrayList vndList = new ArrayList(){
        {
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
        }
    };
    public static ArrayList idrList = new ArrayList(){
        {
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
        }
    };
    public static ArrayList satsList = new ArrayList(){
        {
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
        }
    };
    public static ArrayList mmkList = new ArrayList(){
        {
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
        }
    };
    public static ArrayList krwList = new ArrayList(){
        {
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
        }
    };
    public static ArrayList poolsList = new ArrayList(){
        {
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
        }
    };
    public static ArrayList tokenList = new ArrayList(){
        {
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
        }
    };


    public static ArrayList dateList = new ArrayList();

    public static String NFTJson;
    public static String NFTJsonList;
    public static String DerivativesJson;
    public static String ApyJson;
    public static String ApyJsonList;
    public static String TvlJson;
    public static String poolsJson;
    public static String tokenJson;
    /**
     * 启动时执行一次，之后每隔10分钟执行一次
     */
    @Scheduled(fixedRate = 1000*60*10)
    public void modifyDerivativesData() throws Exception {
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.initRedis(redisTemplate);
        redisUtil.getDerivativesData(redisTemplate);

        JavaExample javaExample = new JavaExample();
        String result = javaExample.getDerivativesData();

        // 将JSON字符串解析为Java对象列表
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> dataList = mapper.readValue(result, new TypeReference<List<Map<String, String>>>() {});
        // 指定要查找的symbol列表
        List<String> targetSymbols = Arrays.asList("BTC-USDT-230929", "BTCUSD_230929", "BTCUSDU23");
        String okx = "";
        String binance = "";
        String byBit = "";
        // 遍历数据列表，查找符合条件的对象
        for (Map<String, String> data : dataList) {
            String symbol = data.get("symbol");
            if (targetSymbols.contains(symbol)) {
                // 找到符合条件的对象，可以在这里对对象进行处理
                String price = data.get("price");

                // 扰乱数据
                DecimalFormat decimalFormat = new DecimalFormat("#.##"); // 设置保留两位小数的格式
                double oldValue = Double.valueOf(price);
                double minValue = oldValue * 0.70;
                double maxValue = oldValue * 1.30;
                double randomValue = minValue + Math.random() * (maxValue - minValue);
                String newValue = decimalFormat.format(randomValue);

                // 赋值
                if(symbol.equals("BTC-USDT-230929")){
                    // okx
                    okx = newValue;
                }else if(symbol.equals("BTCUSD_230929")) {
                    // binance
                    binance = newValue;
                }else if(symbol.equals("BTCUSDU23")) {
                    // byBit
                    byBit = newValue;
                }
            }
        }


        while (okxList.size() >= 7 || binanceList.size() >= 7 || byBitList.size() >= 7) {
            okxList.remove(0);
            binanceList.remove(0);
            byBitList.remove(0);
        }

        okxList.add(okx);
        binanceList.add(binance);
        byBitList.add(byBit);


        redisTemplate.opsForValue().set("okx", okxList.toString());
        redisTemplate.opsForValue().set("binance", binanceList.toString());
        redisTemplate.opsForValue().set("byBit", byBitList.toString());

    }

    /**
     * 启动时执行一次，之后每隔10分钟执行一次
     */
    @Scheduled(fixedRate = 1000*60*10)
    public void modifyGlobalData() throws Exception {
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.initRedis(redisTemplate);
        redisUtil.getGlobalData(redisTemplate);

        JsonUtil jsonUtil = new JsonUtil();
        JavaExample javaExample = new JavaExample();
        String result = javaExample.getGlobalData();
        result = jsonUtil.sortGlobalData(result);

        // 将JSON字符串解析为Java对象列表
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> dataList = mapper.readValue(result, new TypeReference<List<Map<String, String>>>() {});
        // 指定要查找的symbol列表
        List<String> targetSymbols = Arrays.asList("vnd", "idr", "sats", "mmk", "krw");

        String vndPrice = "";
        String idrPrice = "";
        String satsPrice = "";
        String mmkPrice = "";
        String krwPrice = "";

        // 遍历数据列表，查找符合条件的对象
        for (Map<String, String> data : dataList) {
            String name = data.get("name");
            if (targetSymbols.contains(name)) {
                // 找到符合条件的对象，可以在这里对对象进行处理
                String price = data.get("price");

                // 扰乱数据
                DecimalFormat decimalFormat = new DecimalFormat("#.##"); // 设置保留两位小数的格式
                double oldValue = Double.valueOf(price);
                double minValue = oldValue * 0.70;
                double maxValue = oldValue * 1.30;
                double randomValue = minValue + Math.random() * (maxValue - minValue);
                String newValue = decimalFormat.format(randomValue);

                // 赋值
                if (name.equals("vnd")) {
                    // vnd
                    vndPrice = newValue;
                } else if (name.equals("idr")) {
                    // idr
                    idrPrice = newValue;
                } else if (name.equals("sats")) {
                    // sats
                    satsPrice = newValue;
                } else if (name.equals("mmk")) {
                    // mmk
                    mmkPrice = newValue;
                } else if (name.equals("krw")) {
                    // krw
                    krwPrice = newValue;
                }
            }
        }


        while (vndList.size() >= 7 || idrList.size() >= 7 || satsList.size() >= 7 || mmkList.size() >= 7 || krwList.size() >= 7 ) {
            vndList.remove(0);
            idrList.remove(0);
            satsList.remove(0);
            mmkList.remove(0);
            krwList.remove(0);
        }

        // vnd
        vndList.add(vndPrice);
        // idr
        idrList.add(idrPrice);
        // sats
        satsList.add(satsPrice);
        // mmk
        mmkList.add(mmkPrice);
        // krw
        krwList.add(krwPrice);


        redisTemplate.opsForValue().set("vnd", vndList.toString());
        redisTemplate.opsForValue().set("idr", idrList.toString());
        redisTemplate.opsForValue().set("sats", satsList.toString());
        redisTemplate.opsForValue().set("mmk", mmkList.toString());
        redisTemplate.opsForValue().set("krw", krwList.toString());

    }

    /**
     * 启动时执行一次，之后每隔两天执行一次
     */
    @Scheduled(fixedRate = 1000*60*60*48)
    public void modifyNFTData() throws Exception {
        JsonUtil jsonUtil = new JsonUtil();
        JavaExample javaExample = new JavaExample();
        String result = javaExample.getNFTData();
        NFTJson = jsonUtil.takeNFS(result,10);;
        NFTJsonList = jsonUtil.takeNFS(result,20);;

    }

    /**
     * 启动时执行一次，之后每隔1分钟执行一次
     */
    @Scheduled(fixedRate = 1000*60*1)
    public void showPreOneMinData() throws Exception {

        // Derivatives
        JsonUtil jsonUtil = new JsonUtil();
        JavaExample javaExample = new JavaExample();
        String result = javaExample.getDerivativesData();
        result = jsonUtil.take(jsonUtil.sortDerivativesData(result),10);

        DerivativesJson = result;

        // Apy
        result = javaExample.getApyAndTvlData();
        ApyJson = jsonUtil.take(jsonUtil.sortApyData(result),10);
        ApyJsonList = jsonUtil.take(jsonUtil.sortApyData(result),20);


        // Tvl
        result = javaExample.getApyAndTvlData();
        result = jsonUtil.take(jsonUtil.sortTvlData(result),10);

        TvlJson = result;

    }

    /**
     * 启动时执行一次，之后每隔10分钟执行一次
     */
    @Scheduled(fixedRate = 1000*60*10)
    public void getDate(){

        // 获取当前时间
        Date currentTime = new Date();

        // 创建 SimpleDateFormat 对象来指定输出格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

        dateList = new ArrayList<>();

        // 循环生成7个时间间隔，每次减去10分钟
        for (int i = 6; i >= 0; i--) {
            // 计算当前时间之前的时间
            Date previousTime = new Date(currentTime.getTime() - (i * 10 * 60 * 1000));
            // 格式化时间
            String formattedTime = dateFormat.format(previousTime);
            // 存入时间
            dateList.add(formattedTime);
        }

        redisTemplate.opsForValue().set("dateList", dateList.toString());

    }

    /**
     * 启动时执行一次，之后每隔2秒执行一次
     */
    @Scheduled(fixedRate = 1000*2)
    public void getBlockchainPools() throws Exception {
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.initRedis(redisTemplate);

        JavaExample javaExample = new JavaExample();
        String result = javaExample.getHotBlockChainPools();


        // 将JSON字符串解析为Java对象列表
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> dataMap = mapper.readValue(result, new TypeReference<Map<String, String>>() {});
        Map<String, String> modifiedMap = new HashMap<>();

        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        modifiedMap.put("time", formattedTime);

        // 遍历数据列表，查找符合条件的对象
        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
            String value = entry.getValue();
            String key = entry.getKey();
            if(!key.equals("time")){
                // 扰乱数据
                DecimalFormat decimalFormat = new DecimalFormat("#.##"); // 设置保留两位小数的格式
                double oldValue = Double.valueOf(value);
                double minValue = oldValue - 2;
                double maxValue = oldValue + 4;
                double randomValue = minValue + Math.random() * (maxValue - minValue);
                String newValue = decimalFormat.format(randomValue);
                String modifiedKey = key.replace(" ", "_").replace(".", "_");
                modifiedMap.put(modifiedKey, newValue);
            }
        }


        while (poolsList.size() >= 20) {
            poolsList.remove(19);
        }

        // toJson
        String jsonStr = mapper.writeValueAsString(modifiedMap);
        poolsList.add(0, jsonStr);

        redisTemplate.opsForValue().set("poolsList", mapper.writeValueAsString(poolsList));
    }

    /**
     * 启动时执行一次，之后每隔2秒执行一次
     */
    @Scheduled(fixedRate = 1000*2)
    public void getBlockchainToken() throws Exception {
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.initRedis(redisTemplate);

        JavaExample javaExample = new JavaExample();
        String result = javaExample.getHotBlockChainToken();


        // 将JSON字符串解析为Java对象列表
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> dataMap = mapper.readValue(result, new TypeReference<Map<String, String>>() {});

        // 遍历数据列表，查找符合条件的对象
        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
            String value = entry.getValue();
            String key = entry.getKey();
            if(!key.equals("timestamp")){
                // 扰乱数据
                DecimalFormat decimalFormat = new DecimalFormat("#.##"); // 设置保留两位小数的格式
                double oldValue = Double.valueOf(value);
                double minValue = oldValue - 2;
                double maxValue = oldValue + 4;
                double randomValue = minValue + Math.random() * (maxValue - minValue);
                String newValue = decimalFormat.format(randomValue);
                entry.setValue(newValue);
            } else {
                LocalTime currentTime = LocalTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                String formattedTime = currentTime.format(formatter);
                entry.setValue(formattedTime);
            }
        }


        while (tokenList.size() >= 20) {
            tokenList.remove(19);
        }

        // toJson
        String jsonStr = mapper.writeValueAsString(dataMap);
        tokenList.add(0,jsonStr);

        redisTemplate.opsForValue().set("tokenList", mapper.writeValueAsString(tokenList));
    }
}
