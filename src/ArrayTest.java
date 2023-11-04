import java.lang.Math;
import java.util.Arrays;


public class ArrayTest {

	public static void main(String[] args) {

		Integer[] arr = new Integer[20];

		for (int i = 0; i < 20; i++) {

			arr[i] = (int)(Math.random() * 1000);

		}

		// run your sorting algorithm here
		//MergeSort.sort(arr);


		System.out.println(Arrays.toString(arr));

	}

}
