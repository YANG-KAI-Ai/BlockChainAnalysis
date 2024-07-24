package com.bit.BitcoinAnalysis.demo;

import java.util.Arrays;
import java.util.Random;

public class TestDemo {

    public static void main(String[] args) {
        int[] arr = generateRandomArray(1000000);
        System.out.println("Original array：" + Arrays.toString(arr));
        long startTime = System.currentTimeMillis();
        countingSort(arr);
        long endTime = System.currentTimeMillis();
        System.out.println("Sorted Arrays：" + Arrays.toString(arr));
        System.out.println("Counting the time required for sorting：" + (endTime - startTime) + "ms");
    }

    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(1000000);
        }
        return arr;
    }

    public static void countingSort(int[] arr) {
        int max = arr[0];
        int min = arr[0];

        // 找到数组中的最大值和最小值
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }

        // 创建计数数组，并将每个元素出现的次数加到对应的计数值上
        int[] count = new int[max - min + 1];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - min]++;
        }

        // 将count数组中的计数值累加，以便确定每个元素在排序后数组中的位置
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // 创建输出数组，并根据count数组中的计数值确定每个元素在输出数组中的位置
        int[] output = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            output[--count[arr[i] - min]] = arr[i];
        }

        // 将输出数组中的元素复制回原始数组
        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }
    }
}
