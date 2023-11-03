/*

    Description: This file contains the static method `sort` which
    runs Merge Sort algorithm on the called array.

    Author: Beyhan Kandemir
    Contact: kandemirbeyhan1999@gmail.com

    Last Update: 03.11.2023 23:10

 */

import java.util.Arrays;

public class MergeSort {
    public static <E> void sort(Comparable<E>[]  arr) {

        divide(arr, arr.length);
    }

    private static <E> void divide(Comparable<E>[] arr, int base){
        if (arr.length < 2) {
            return ;
        }
        int middle = arr.length / 2;

        Comparable<E> [] leftSide = Arrays.copyOfRange(arr, 0, middle);
        Comparable<E> [] rightSide = Arrays.copyOfRange(arr, middle, arr.length);
        divide(leftSide,middle);
        divide(rightSide, base-middle);
        mergeSorted(arr, leftSide,rightSide);
    }
    public static <E> void mergeSorted(Comparable<E>[] oldArray, Comparable<E> [] leftSide, Comparable<E> [] rightSide) {
        int i1 = 0;
        int i2 = 0;
        int k = 0;

        //Comparable<E> []  lastArray  =  new Comparable[leftSide.length + rightSide.length];

        while (i1 < leftSide.length && i2 < rightSide.length) {
            if (leftSide[i1].compareTo((E)rightSide[i2]) < 0) {
                 //lastArray[k] =  leftSide[i1];
                oldArray[k] =  leftSide[i1];
                i1++;
            } else {
                 //lastArray[k] =  rightSide[i2];
                oldArray[k] =  rightSide[i2];
                i2++;
            }
            k++;
        }
        while (i1 < leftSide.length) {
            //lastArray[k] = leftSide[i1];
            oldArray[k] =  leftSide[i1];
            i1++;
            k++;
        }
        while (i2 < rightSide.length) {
            //lastArray[k] = rightSide[i2];
            oldArray[k] =  rightSide[i2];
            i2++;
            k++;
        }

    }

}
