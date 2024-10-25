package COM2031_Lab1.src.dnc;

/**
 * Count number of a particular value in an array, using Divide and Conquer
 * 
 * COM2031: Divide and Conquer Labs
 * Autumn Semester 2020
 * 
 */

public class Count {

	/**
	 * Count the number of occurrences of a value in an array
	 * 
	 * @param arr an array of integers
	 * @param k   an integer value
	 * @return the number of occurrences of k in arr
	 */

	public static int count(int[] arr, int k) {
		return recur_count(arr, k, 0, arr.length);
	}

	/**
	 * Recursive method for Divide and Conquer approach
	 * to find number of occurrences of a value in an array
	 * 
	 * @param arr array of integers
	 * @param k   integer
	 * @param i   an index of arr
	 * @param j   an integer so that j-1 is an index of arr
	 * @return the number of occurrences of k in arr between i (inclusive) and j
	 *         (exclusive)
	 */

	private static int recur_count(int[] arr, int k, int i, int j) {
		// Base case: If there is only one element
		if (i == j - 1) {
			return arr[i] == k ? 1 : 0; // Return 1 if it matches, otherwise return 0
		} else if (i == j) {
			return 0; // If the range is empty, return 0
		} else {
			// Divide: Calculate the midpoint
			int mid = (i + j) / 2;

			// Conquer: Recursively count occurrences in both halves
			int r1 = recur_count(arr, k, i, mid); // Count in the left half
			int r2 = recur_count(arr, k, mid, j); // Count in the right half

			// Combine: Sum the counts from both halves
			return r1 + r2; // Total occurrences
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

	public static void testCount(int[] a, int k, int expected) {
		int n = count(a, k);
		if (n == expected) {
			System.out.print("  Pass:   ");
		}
		if (n != expected) {
			System.out.println("*********************");
			System.out.print("  Fail:   ");
		}
		System.out.print("count(");
		printArray(a);
		System.out.print(", " + k + ") is " + n);
		if (n != expected) {
			System.out.print(", correct value is " + expected + "\n");
			System.out.print("*********************");
		}
		System.out.print("\n");
	}

	public static void main(String[] args) {
		testCount(new int[] { 1, 4, 3, 3, 2, 5, 3, 4, 3 }, 3, 4);
		testCount(new int[] { 1, 4, 3, 3, 2, 5, 3, 4, 3 }, 4, 2);
		testCount(new int[] { 1, 4, 3, 3, 2, 5, 3, 4, 3 }, 6, 0);
		testCount(new int[] {}, 5, 0);
	}

}
