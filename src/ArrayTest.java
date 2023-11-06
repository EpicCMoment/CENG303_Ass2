import java.lang.Math;
import java.util.Arrays;


public class ArrayTest {

	public static void main(String[] args) {

		for (int i = 0; i < 1000; i++) {
			System.out.println(runTest());
		}


	}

	private static int runTest() {
		Integer[] arr = new Integer[20];

		for (int i = 0; i < 20; i++) {

			arr[i] = (int)(Math.random() * 1000);

		}

		// run your sorting algorithm here
		Integer[] dummy_arr = Arrays.copyOfRange(arr, 0, arr.length);
		Integer[] dummy_arr2 = Arrays.copyOfRange(arr, 0, arr.length);

		AVLTreeSort.sort(dummy_arr);
		Arrays.sort(dummy_arr2);

		int compare_result = Arrays.compare(dummy_arr2, dummy_arr);

		if (compare_result != 0) {
			System.out.println("Sorted array:" + Arrays.toString(dummy_arr));
			System.out.println("Original array:" + Arrays.toString(arr));
		}

		return compare_result;


	}

}
