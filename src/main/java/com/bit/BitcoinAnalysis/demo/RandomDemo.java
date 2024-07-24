package com.bit.BitcoinAnalysis.demo;

import java.util.Random;

public class RandomDemo {
    public static void main(String[] args) {
        String result = "";
        Random rand = new Random();

        for (int i = 0; i < 7; i++) {
            long randomNum = rand.nextLong() * 1000000000*10000000;
//            String formattedNum = String.format("%.2f", randomNum);
            if(randomNum < 0){
                randomNum = randomNum * -1;
            }
            result += String.valueOf(randomNum) + ",";
        }

        // 去除最后一个逗号
        result = result.substring(0, result.length() - 1);

        System.out.println(result);
    }
}
