package divideconquer;

/**
 * Alternately add and subtract values in a list, using Divide and Conquer
 * 
 * COM2031: Divide and Conquer Labs
 * Autumn Semester 2020
 * Steve Schneider
 */

public class PlusMinus {

	/**
	 * Alternately add and subtract the values of an array
	 * 
	 * @param arr array of integers
	 * @return the result of adding and subtracting alternate values of arr
	 */
	public static int plusMinus(int[] arr) {
		return dac_plusMinus(arr, 0, arr.length);
	}

	/**
	 * Recursive method for Divide and Conquer approach to find
	 * alternating adding and subtracting of values in a section of an array
	 * 
	 * @param arr array of integers
	 * @param i   an index of the array
	 * @param j   such that j-1 is an index of the array >= i
	 * @return the result of adding and subtracting alternate values in arr between
	 *         i (inclusive) and j (exclusive)
	 */
	private static int dac_plusMinus(int[] arr, int i, int j) {
		if (i == j) {
			return 0; // If the range is empty, return 0
		} else if (i == j - 1) {
			return arr[i]; // Base case: If there's only one element, return it
		} else {
			// DIVIDE
			int m = (i + j) / 2;

			// CONQUER
			int leftResult = dac_plusMinus(arr, i, m); // Result from the left half
			int rightResult = dac_plusMinus(arr, m, j); // Result from the right half

			// COMBINE
			// Combine the results while respecting the alternating sum/subtract pattern
			if ((m - i) % 2 == 0) {
				// If the left half has an even count of elements, we add the rightResult
				return leftResult + rightResult;
			} else {
				// If the left half has an odd count of elements, we subtract the rightResult
				return leftResult - rightResult;
			}
		}
	}

	// Utilities to assist in testing

	public static void printArray(int[] a) {
		System.out.print("{");
		for (int i = 0; i < a.length - 1; i++) {
			System.out.print(a[i] + ", ");
		}
		if (a.length > 0) {
			System.out.print(a[a.length - 1]);

		}
		System.out.print("}");
	}

	public static void testPlusMinus(int[] a, int expected) {
		int n = plusMinus(a);
		if (n == expected) {
			System.out.print("  Pass:   ");
		}
		if (n != expected) {
			System.out.println("*********************");
			System.out.print("  Fail:   ");
		}
		System.out.print("sum(");
		printArray(a);
		System.out.print(") is " + n);
		if (n != expected) {
			System.out.print(", correct value is " + expected + "\n");
			System.out.print("*********************");
		}
		System.out.print("\n");
	}

	public static void main(String[] args) {
		System.out.print("Tests for plusminus:\n\n");
		testPlusMinus(new int[] { 3 }, 3);
		testPlusMinus(new int[] { 1, 2, 3, 4, 3, 2, 1 }, 0);
		testPlusMinus(new int[] { 3, -3, 2, -2, 1, -1, 0 }, 12);
		testPlusMinus(new int[] { 31, 2, 3, 4, 5, 10, 9, 8, 7, 6, 20 }, 45);
		testPlusMinus(new int[] {}, 0);
	}

}
