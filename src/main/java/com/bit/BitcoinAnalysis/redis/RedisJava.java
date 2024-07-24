package com.bit.BitcoinAnalysis.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisJava {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/getRedis")
    public String getRedis() {


        String test = redisTemplate.opsForValue().get("fffff");
        return test;
    }
}
