/*

    Description: This file contains the static method `sort` which
    runs Quick Sort algorithm on the called array.

    Author: Arif Fil
    Contact: arif.fil@outlook.com

    Last Update: 28.10.2023 23:10

 */

public class QuickSort {

    public static <E> void sort(Comparable<E>[] arr) {

        // run quicksort algorithm
        quickSort(arr, 0, arr.length-1);

    }

    private static <E> int partition(Comparable<E>[] arr, int left, int right) {

        // we choose pivot as the last element of the array
        Comparable<E> pivot = arr[right];

        // starts from one index before the start of the array
        int left_iterator = left - 1;

        // iterate over the array
        for (int right_iterator = left; right_iterator < (right); right_iterator++) {

            if (pivot.compareTo((E)arr[right_iterator]) >= 0) {
                left_iterator++;
                swap(arr, left_iterator, right_iterator);
            }

        }

        swap(arr, left_iterator + 1, right);
        return left_iterator + 1;

    }

    private static <E> void quickSort(Comparable<E>[] arr, int left, int right) {

        if (left < right) {

            int q = partition(arr, left, right);

            quickSort(arr, left, q - 1);
            quickSort(arr, q + 1, right);

        }

    }

    private static <E> void swap(Comparable<E>[] arr, int first, int second) {

        Comparable<E> temp = arr[first];

        arr[first] = arr[second];
        arr[second] = temp;

    }

}