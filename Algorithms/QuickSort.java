package Algorithms;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    public void quickSort(int[] arr, int left, int right) {
        if(left < right) {
            int partitionIndex = partition(arr, left, right);
            quickSort(arr, left, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, right);
        }
    }

    public int partition(int[] arr, int left, int right) {
        int i = left, j = right;

        while (i < j) {
            while (i < j && arr[j] >= arr[left]) j--;
            while (i < j && arr[i] >= arr[left]) i++;
            swap(arr, i, j);
        }
        swap(arr, i, left);
        return i;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // 主方法来测试排序算法
    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5};
        int n = arr.length;

        QuickSort ob = new QuickSort();
        ob.quickSort(arr, 0, n-1);
        System.out.println(Arrays.toString(arr));
    }
}


class RandomizedQuickSort {

    void quickSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = randomizedPartition(arr, begin, end);

            quickSort(arr, begin, partitionIndex-1);
            quickSort(arr, partitionIndex+1, end);
        }
    }

    private int partition(int[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        int swapTemp = arr[i+1];
        arr[i+1] = arr[end];
        arr[end] = swapTemp;

        return i+1;
    }

    // 随机化分区函数
    private int randomizedPartition(int[] arr, int begin, int end) {
        Random rand = new Random();
        int pivotIndex = rand.nextInt(end - begin + 1) + begin;
        int swapTemp = arr[pivotIndex];
        arr[pivotIndex] = arr[end];
        arr[end] = swapTemp;

        return partition(arr, begin, end);
    }

    // 主方法来测试排序算法
    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5};
        RandomizedQuickSort ob = new RandomizedQuickSort();
        ob.quickSort(arr, 0, arr.length-1);

        System.out.println(Arrays.toString(arr));
    }
}

