package com.bowleu.exam.java;

import java.util.*;

public class QuickSort {

    public static void quickSort(List<Integer> list, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivot = list.get((left + right) / 2);
        int index = partition(list, left, right, pivot);

        quickSort(list, left, index - 1);
        quickSort(list, index, right);
    }

    private static int partition(List<Integer> list, int left, int right, int pivot) {
        while (left <= right) {

            while (list.get(left) < pivot) {
                left++;
            }

            while (list.get(right) > pivot) {
                right--;
            }

            if (left <= right) {
                Collections.swap(list, left, right);
                left++;
                right--;
            }
        }

        return left;
    }
}
