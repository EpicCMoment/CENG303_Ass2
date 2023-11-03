/*

    Description: This file contains the static method `sort` which
    runs Insertion Sort algorithm on the called array.

    Author: Beyhan Kandemir
    Contact: kandemirbeyhan1999@gmail.com

    Last Update: 03.11.2023 23:10

 */

public class InsertionSort {
    public static <E> void sort(Comparable<E>[] arr){
        int n = arr.length;
        for(int i= 0; i<n;i++){
            Comparable<E> number = arr[i];
            int j = i-1;
            while (j >= 0 && arr[j].compareTo((E) number) > 0) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j+1] = number;
        }
    }
}
