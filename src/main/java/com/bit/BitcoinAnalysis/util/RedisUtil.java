package com.bit.BitcoinAnalysis.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static com.bit.BitcoinAnalysis.controller.MyTask.*;

@Component
public class RedisUtil {


    public void getDerivativesData(StringRedisTemplate redisTemplate){
        String okxStr = redisTemplate.opsForValue().get("okx");
        String binanceStr = redisTemplate.opsForValue().get("binance");
        String byBitStr = redisTemplate.opsForValue().get("byBit");
        if(okxStr != null && binanceStr != null && byBitStr != null && !okxStr.equals("") && !binanceStr.equals("") && !byBitStr.equals("")) {
            ArrayList list = new ArrayList();
            String[] strArray = okxStr.substring(1, okxStr.length() - 1).split(",");
            for (String str : strArray) {
                list.add(str.trim());
            }
            okxList = list;
            list = new ArrayList();
            strArray = binanceStr.substring(1, binanceStr.length() - 1).split(",");
            for (String str : strArray) {
                list.add(str.trim());
            }
            binanceList = list;
            list = new ArrayList();
            strArray = byBitStr.substring(1, byBitStr.length() - 1).split(",");
            for (String str : strArray) {
                list.add(str.trim());
            }
            byBitList = list;
        }
    }

    public void getGlobalData(StringRedisTemplate redisTemplate){
        String vndStr = redisTemplate.opsForValue().get("vnd");
        String idrStr = redisTemplate.opsForValue().get("idr");
        String satsStr = redisTemplate.opsForValue().get("sats");
        String mmkStr = redisTemplate.opsForValue().get("mmk");
        String krwStr = redisTemplate.opsForValue().get("krw");
        if (vndStr !=null && !vndStr.equals("") ){
            ArrayList list = new ArrayList();
            String[] strArray = vndStr.substring(1, vndStr.length() - 1).split(",");
            for (String str : strArray) {
                list.add(str.trim());
            }
            vndList = list;
        }
        if (idrStr !=null && !idrStr.equals("") ){
            ArrayList list = new ArrayList();
            String[] strArray = idrStr.substring(1, idrStr.length() - 1).split(",");
            for (String str : strArray) {
                list.add(str.trim());
            }
            idrList = list;
        }
        if (satsStr !=null && !satsStr.equals("") ){
            ArrayList list = new ArrayList();
            String[] strArray = satsStr.substring(1, satsStr.length() - 1).split(",");
            for (String str : strArray) {
                list.add(str.trim());
            }
            satsList = list;
        }
        if (mmkStr !=null && !mmkStr.equals("") ){
            ArrayList list = new ArrayList();
            String[] strArray = mmkStr.substring(1, mmkStr.length() - 1).split(",");
            for (String str : strArray) {
                list.add(str.trim());
            }
            mmkList = list;
        }
        if (krwStr !=null && !krwStr.equals("") ){
            ArrayList list = new ArrayList();
            String[] strArray = krwStr.substring(1, krwStr.length() - 1).split(",");
            for (String str : strArray) {
                list.add(str.trim());
            }
            krwList = list;
        }

    }

    public void initRedis(StringRedisTemplate redisTemplate) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        if(!redisTemplate.hasKey("vnd")){
            redisTemplate.opsForValue().set("vnd",vndList.toString());
        }
        if(!redisTemplate.hasKey("idr")){
            redisTemplate.opsForValue().set("idr",idrList.toString());
        }
        if(!redisTemplate.hasKey("sats")){
            redisTemplate.opsForValue().set("sats",satsList.toString());
        }
        if(!redisTemplate.hasKey("mmk")){
            redisTemplate.opsForValue().set("mmk",mmkList.toString());
        }
        if(!redisTemplate.hasKey("krw")){
            redisTemplate.opsForValue().set("krw",krwList.toString());
        }
        if(!redisTemplate.hasKey("okx")){
            redisTemplate.opsForValue().set("okx",okxList.toString());
        }
        if(!redisTemplate.hasKey("binance")){
            redisTemplate.opsForValue().set("binance",binanceList.toString());
        }
        if(!redisTemplate.hasKey("byBit")){
            redisTemplate.opsForValue().set("byBit",byBitList.toString());
        }
        if(!redisTemplate.hasKey("tokenList")){
            redisTemplate.opsForValue().set("tokenList", mapper.writeValueAsString(tokenList));
        }
        if(!redisTemplate.hasKey("poolsList")){
            redisTemplate.opsForValue().set("poolsList",mapper.writeValueAsString(poolsList));
        }

        tokenList = mapper.readValue(redisTemplate.opsForValue().get("tokenList"), new TypeReference<ArrayList<String>>() {});
        poolsList = mapper.readValue(redisTemplate.opsForValue().get("poolsList"), new TypeReference<ArrayList<String>>() {});

    }


}
