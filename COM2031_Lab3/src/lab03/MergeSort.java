package lab03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MergeSort {

	public static int[] mergeSort(int[] arr) {
		return dac_mergeSort(arr, 0, arr.length);
	}

	public static int[] dac_mergeSort(int[] arr, int start, int end) {
		// BASE CASE: If the subarray has one or no elements
		if (end - start <= 1) {
			return Arrays.copyOfRange(arr, start, end);
		} else {
			// DIVIDE: Find the midpoint
			int mid = (start + end) / 2;

			// CONQUER: Recursively sort both halves
			int[] leftSorted = dac_mergeSort(arr, start, mid);
			int[] rightSorted = dac_mergeSort(arr, mid, end);

			// COMBINE: Merge the two sorted halves
			return merge(leftSorted, rightSorted);
		}
	}

	public static int[] merge(int[] arr1, int[] arr2) {
		int[] merged = new int[arr1.length + arr2.length];
		int i = 0, j = 0, k = 0;

		// Merge the two sorted arrays
		while (i < arr1.length && j < arr2.length) {
			if (arr1[i] <= arr2[j]) {
				merged[k++] = arr1[i++];
			} else {
				merged[k++] = arr2[j++];
			}
		}

		// If there are remaining elements in arr1
		while (i < arr1.length) {
			merged[k++] = arr1[i++];
		}

		// If there are remaining elements in arr2
		while (j < arr2.length) {
			merged[k++] = arr2[j++];
		}

		return merged;
	}

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

	public static void testMergeSort(String name, int[] test, int[] expected) {
		int[] test2 = mergeSort(test);
		if (Arrays.equals(test2, expected)) {
			System.out.println("  Pass:   " + name);
		} else {
			System.out.println("*********************");
			System.out.println("  Fail:   " + name);
			System.out.print("  Input:    ");
			printArray(test);
			System.out.println();
			System.out.print("  Expected: ");
			printArray(expected);
			System.out.println();
			System.out.print("  Actual:   ");
			printArray(test2);
			System.out.println();
			System.out.println("*********************");
		}
	}

	public static void main(String[] args) {
		int[] s1 = { 2, 1, 4, 3, 6, 5 };
		int[] t1 = { 1, 2, 3, 4, 5, 6 };
		testMergeSort("test1", s1, t1);
		int[] s2 = { 8, 6, 8, 6, 4, 4, 2 };
		int[] t2 = { 2, 4, 4, 6, 6, 8, 8 };
		testMergeSort("test2", s2, t2);
		int[] s3 = { 1 };
		int[] t3 = { 1 };
		testMergeSort("test3", s3, t3);
		int[] s4 = { 3, 3, 4, 3, 3, 3 };
		int[] t4 = { 3, 3, 3, 3, 3, 4 };
		testMergeSort("test4", s4, t4);
		int[] s5 = { 9, 7, 5, 3, 1 };
		int[] t5 = { 1, 3, 5, 7, 9 };
		testMergeSort("test5", s5, t5);
		int[] s6 = {};
		int[] t6 = {};
		testMergeSort("test6", s6, t6);
	}
}
