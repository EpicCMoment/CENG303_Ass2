/*

    Description: This file contains the static method `sort` which
    runs Merge Sort algorithm on the called array.

    Author: Beyhan Kandemir
    Contact: kandemirbeyhan1999@gmail.com

    Last Update: 03.11.2023 23:10

 */

import java.util.Arrays;

public class MergeSort {
    public static <E extends Comparable<E>> E[] sort(E[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        int middle = arr.length / 2;

        E[] leftSide = Arrays.copyOfRange(arr, 0, middle);
        E[] rightSide = Arrays.copyOfRange(arr, middle, arr.length);

        return mergeSorted(leftSide,rightSide);
    }

    public static <E extends Comparable<E>> E[] mergeSorted(E[] leftSide, E[] rightSide) {
        int i1 = 0;
        int i2 = 0;
        int k = 0;

        E[] lastArray  = (E[]) new Comparable[leftSide.length + rightSide.length];

        while (i1 < leftSide.length && i2 < rightSide.length) {
            if (leftSide[i1].compareTo(rightSide[i2]) < 0) {
                 lastArray[k] =  leftSide[i1];
                i1++;
            } else {
                 lastArray[k] =  rightSide[i2];
                i2++;
            }
            k++;
        }
        while (i1 < leftSide.length) {
             lastArray[k] = leftSide[i1];
            i1++;
            k++;
        }
        while (i2 < rightSide.length) {
            lastArray[k] = rightSide[i2];
            i2++;
            k++;
        }
        return (E[]) lastArray;
    }

}
