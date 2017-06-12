package assignment_two_count_inversions;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/*
 * This file contains all of the 100,000 integers between 1 and 100,000 (inclusive) in some order, with no integer repeated.
 * 
 * Your task is to compute the number of inversions in the file given, where the ith row of the file indicates the ith entry of an array.
 */
public class CountInversions {
	
	private static long count = 0; // number of inversions
	
	// merges and counts inversions
	private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid) { // no inversions if the lower half is completed
				a[k] = aux[j++];
			} else if (j > hi) { // if the upper half is completed, the number of inversions
			                     // is equal to the size of the upper half
				a[k] = aux[i++];
				count += j - (mid + 1);
			} else if (aux[i] <= aux[j]) { // if the lower half element is smaller, the number of inversions
			                               // is equal to the number of elements taken in the upper half
				a[k] = aux[i++];
				count += j - (mid + 1);
			} else {                       // if the upper half element is smaller, the number of inversions
			                               // is equal to the number of elements not taken in the lower half
				a[k] = aux[j++];
				count += mid + 1 - i;
			}
		}
	}
	
	private static void countInversions(int[] a, int[] aux, int lo, int hi) {
		if (lo >= hi) return; // base case is interval of 1; if lo is greater than or equal to hi,
		                      // then the current interval is less than 1
		int mid = lo + (hi - lo) / 2; // to prevent integer overflow
		// recursively divides into 2 subarrays
		countInversions(a, aux, lo, mid);
		countInversions(a, aux, mid + 1, hi);
		// merges subarrays and counts inversions
		merge(a, aux, lo, mid, hi);
	}
	
	public static long countInversions(int[] a) {
		int[] aux = new int[a.length];
		countInversions(a, aux, 0, a.length - 1);
		return count / 2; // since each inversion is counted twice
	}
	
	private static Reader in;
	private static PrintWriter out;
	
	private static final String INPUT_FILE_NAME = "IntegerArray.txt";
	
	private static final int SIZE = 100000;
	
	public static void main(String[] args) throws IOException {
		String packageName = "";
		try {
			packageName = new CountInversions().getClass().getPackage().toString().split(" ")[1] + "/";
		} catch (NullPointerException e) {}
		in = new Reader("Week 2/" + packageName + INPUT_FILE_NAME);
		// in = new Reader(System.in);
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int[] arr = new int[SIZE];
		for (int i = 0; i < SIZE; i++) {
			arr[i] = in.nextInt();
		}
		out.println(countInversions(arr));
		out.close();
	}
}
