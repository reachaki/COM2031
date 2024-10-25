package lab02;

import java.util.Arrays;
import java.util.ArrayList;

public class MedianFinder {

    public static int[] FindKPair(final int k, final ArrayList<Integer> S) {
        if (S.isEmpty() || k < 1 || k > S.size()) {
            throw new IllegalArgumentException("Array cannot be empty and k must be valid.");
        }
        return dac_FindKPair(k, S);
    }

    public static int[] dac_FindKPair(int k, ArrayList<Integer> arr) {
        // BASE CASE: If there's only one element in the array, return it
        if (arr.size() == 1) {
            return new int[] { arr.get(0), arr.get(0) }; // Return the same element
        }

        // Choose a pivot (last element)
        int pivotIndex = arr.size() - 1;
        int pivot = arr.get(pivotIndex);

        // Create leftarr and rightarr for partitioning
        ArrayList<Integer> leftarr = new ArrayList<>();
        ArrayList<Integer> rightarr = new ArrayList<>();

        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i) < pivot) {
                leftarr.add(arr.get(i));
            } else {
                rightarr.add(arr.get(i));
            }
        }

        int sl = leftarr.size();

        // Debug output for tracking values
        System.out.println("Array: " + arr);
        System.out.println("Pivot: " + pivot);
        System.out.println("Left size: " + sl + ", Right size: " + rightarr.size());

        // Determine which part to recurse into
        if (sl >= k) {
            return dac_FindKPair(k, leftarr); // Both k-th and (k+1)-th in left
        } else if (sl == k - 1) {
            // If sl is exactly k - 1, then pivot is the k-th smallest
            return new int[] { pivot, rightarr.isEmpty() ? pivot : dac_FindKPair(1, rightarr)[0] };
        } else {
            // Check to avoid negative index
            if (k - sl - 1 < 0 || rightarr.isEmpty()) {
                throw new IllegalArgumentException("Invalid k value for right array: " + (k - sl - 1));
            }
            return dac_FindKPair(k - sl - 1, rightarr);
        }
    }

    public static double findMedian(ArrayList<Integer> arr) {
        int n = arr.size();
        if (n % 2 == 1) {
            // Odd size: find the middle element
            return FindKPair(n / 2 + 1, arr)[0]; // n/2 + 1 for 1-based index
        } else {
            // Even size: find the two middle elements and calculate the average
            int[] kPair = FindKPair(n / 2, arr); // k = n/2
            return (kPair[0] + kPair[1]) / 2.0; // Average of two middle elements
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr1 = new ArrayList<>(Arrays.asList(7, 1, 3, 4, 5));
        System.out.println("Median: " + findMedian(arr1)); // Should print 4

        ArrayList<Integer> arr2 = new ArrayList<>(Arrays.asList(9, 3, 1, 5, 6, 2));
        System.out.println("Median: " + findMedian(arr2)); // Should print 4.5
    }
}
