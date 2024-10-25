package lab03;

/**
 * Sorted Table Search
 * 
 * COM2031: Divide and Conquer Labs
 * 
 */

public class SortedTableSearch {

	public static int tableSearch(int[][] table, int x) {
		if (table.length == 0 || table[0].length == 0) {
			return 0;
		}
		return dac_TableSearch(table, 0, 0, table.length, table[0].length, x);
	}

	private static int dac_TableSearch(int[][] table, int i, int j, int k, int l, int x) {
		// BASE CASE: if bounds are invalid
		if (i >= k || j >= l) {
			return 0;
		}

		// Divide: get the middle element
		int midValue = table[i][j];

		if (midValue == x) {
			return 1; // found
		} else if (midValue > x) {
			// If midValue is greater than x, eliminate the current column
			return dac_TableSearch(table, i, j, k, j, x); // Search in the same row, left of midValue
		} else {
			// If midValue is less than x, eliminate the current row
			return dac_TableSearch(table, i + 1, j, k, l, x); // Search in the same column, below midValue
		}
	}

	public static void testSearch(String name, int[][] table, int x, int expected) {
		int n = tableSearch(table, x);
		if (n == expected) {
			System.out.print(name + "  Pass");
		} else {
			System.out.println("*********************");
			System.out.print(name + "  Fail:  ");
			System.out.print("correct output is " + expected + "\n");
			System.out.print("*********************");
		}
		System.out.print("\n");
	}

	public static void main(String[] args) {
		int[][] table1 = {
				{ 1, 5, 9 },
				{ 2, 6, 10 },
				{ 3, 7, 11 }
		};
		testSearch("test1", table1, 11, 1);
		testSearch("test2", table1, 1, 1);
		testSearch("test3", table1, 2, 1);
		testSearch("test4", table1, 3, 1);
		testSearch("test5", table1, 6, 1);
		testSearch("test6", table1, 9, 1);
		testSearch("test7", table1, 0, 0);
		testSearch("test8", table1, 66, 0);
		testSearch("test9", table1, 8, 0);
		int[][] table2 = {
				{ 1, 5, 9, 15 },
				{ 2, 6, 10, 18 },
				{ 3, 7, 11, 21 },
				{ 4, 8, 22, 24 } };
		testSearch("test10", table2, 18, 1);
		testSearch("test11", table2, 22, 1);
		testSearch("test12", table2, 17, 0);
		testSearch("test13", table2, 0, 0);
		int[][] table3 = { {} };
		testSearch("test14", table3, 3, 0);
		int[][] table4 = {};
		testSearch("test15", table4, 3, 0);
	}
}
