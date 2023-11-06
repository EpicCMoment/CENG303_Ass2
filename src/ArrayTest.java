import java.lang.Math;
import java.util.Arrays;


public class ArrayTest {

	public static void main(String[] args) {

		int testAmount = 100;

		for (int i = 0; i < testAmount; i++) {
			runTest();
		}


	}

	private static void runTest() {
		Integer[] arr = new Integer[20];

		for (int i = 0; i < 20; i++) {

			arr[i] = (int)(Math.random() * 1000);

		}

		// run your sorting algorithm here
		Integer[] dummy_arr = Arrays.copyOfRange(arr, 0, arr.length);
		Integer[] dummy_arr2 = Arrays.copyOfRange(arr, 0, arr.length);

		// replace these sorting algorithms with your algorithm
		AVLTreeSort.sort(dummy_arr);

		// we trust that Arrays.sort always sort correctly
		Arrays.sort(dummy_arr2);

		int compare_result = Arrays.compare(dummy_arr2, dummy_arr);


		// if there is a problem in the sorted array
		if (compare_result != 0) {
			System.out.println("----------------------------------------------------------------------------------------------");
			System.out.println("!! INCORRECT SORTING !!");
			System.out.println("Original array:" + Arrays.toString(arr) + "\n");
			System.out.println("After sorting:" + Arrays.toString(dummy_arr));
			System.out.println("----------------------------------------------------------------------------------------------\n");

		} else {
			System.out.println("$$ CORRECT SORTING $$\n");
		}



	}

}
