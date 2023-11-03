import java.lang.Math;
import java.util.Arrays;


public class ArayTest {

	public static void main(String[] args) {

		Integer[] arr = new Integer[20];

		for (int i = 0; i < 20; i++) {

			arr[i] = (int)(Math.random() * 1000);

		}

		System.out.println(Arrays.toString(arr));




	}

}
