package com.bit.BitcoinAnalysis.demo;
import java.util.Arrays;
import java.util.Random;

public class ShellSortDemo {
    public static void main(String[] args) {
        int[] arr = generateRandomArray(1000000);
        System.out.println("Original array：" + Arrays.toString(arr));
        long startTime = System.currentTimeMillis();
        shellSort(arr);
        long endTime = System.currentTimeMillis();
        System.out.println("Sorted Arrays：" + Arrays.toString(arr));
        System.out.println("Shell sort the time required for sorting：" + (endTime - startTime) + "ms");
    }

    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(1000000);
        }
        return arr;
    }

    public static void shellSort(int[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j = i;
                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
        }
    }
}

