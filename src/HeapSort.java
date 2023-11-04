/*

    Description: This file contains the static method `sort` which
    runs Heap Sort algorithm on the called array.

    Author: Zehra İnöz
    Contact: zehrainozz@gmail.com

    Last Update: 04.11.2023 16.16

 */

public class HeapSort {

    private static int heapSize;

    private static int left(int index) {
        return index * 2 + 1;
    }

    private static int right(int index) {
        return index * 2 + 2;
    }

    public static <E> void sort(Comparable<E>[] arr) {
        heapSort(arr);
    }

    private static <E> void heapSort(Comparable<E>[] arr) {

        // creates a max heap from given array
        buildMaxHeap(arr, arr.length);

        // sorts by pushing the maximum to the correct position and reducing heap
        for(int i = arr.length - 1; i > 0; i--){

            swap(arr, 0, i);

            heapSize -= 1;

            maxHeapify(arr, 0);
        }

    }

    private static <E> void buildMaxHeap(Comparable<E>[] arr, int length) {

        heapSize = length;

        // creates max heap starting from last parent
        for (int i = (length - 1) / 2; i >= 0; i--) {

            maxHeapify(arr, i);
        }
    }

    //compares with left and right children and exchanges if required
    private static <E> void maxHeapify(Comparable<E>[] arr, int index) {

        int largest = index;
        int left = left(index);
        int right = right(index);

        if (left < heapSize && arr[left].compareTo((E) arr[index]) > 0) {

            largest = left;
        }

        if (right < heapSize && arr[right].compareTo((E) arr[largest]) > 0) {

            largest = right;
        }

        if (largest != index) {

            swap(arr, index, largest);

            maxHeapify(arr, largest);
        }
    }

    private static <E> void swap(Comparable<E>[] arr, int first, int second) {

        Comparable<E> temp = arr[first];

        arr[first] = arr[second];
        arr[second] = temp;

    }



















}
