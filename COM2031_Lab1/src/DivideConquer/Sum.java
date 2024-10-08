package org.com2031.labs.dac.css1ss;

/**
 * Sum the values in an array
 * 
 * COM2031: Divide and Conquer Labs
 * Autumn Semester 2020
 * Steve Schneider
 */

public class Sum {

	/**
	 * Sum the values of an array
	 * 
	 * @param arr array of integers
	 * @return the sum of the values in arr
	 */
	public static int sum(int[] arr) {
		return dac_sum(arr, 0, arr.length);
	}

	/**
	 * Recursive method for Divide and Conquer approach to find sum of values in a
	 * section of an array
	 * 
	 * @param arr array of integers
	 * @param i   an index of the array
	 * @param j   such that j-1 is an index of the array >= i
	 * @return the sum of values in arr between i (inclusive) and j (exclusive)
	 */
	private static int dac_sum(int[] arr, int i, int j) {
		if (i == j) {
			return 0; // If the range is empty, return 0
		} else if (i == j - 1) {
			return arr[i]; // Base case: If there's only one element, return it
		} else {
			// DIVIDE
			int m = (i + j) / 2;

			// CONQUER
			int sum1 = dac_sum(arr, i, m); // Sum of the first half
			int sum2 = dac_sum(arr, m, j); // Sum of the second half

			// COMBINE
			return sum1 + sum2; // Combine the results from both halves
		}
	}

	// *************************************

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

	public static void testSum(int[] a, int expected) {
		int n = sum(a);
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
		System.out.print("Tests for sum:\n\n");
		testSum(new int[] { 3 }, 3);
		testSum(new int[] { 1, 2, 3, 4 }, 10);
		testSum(new int[] { 3, -3, 2, -2, 1, -1, 0 }, 0);
		testSum(new int[] { 31, 2, 3, 4, 5, 10, 9, 8, 7, 6, 20 }, 105);
	}
}
