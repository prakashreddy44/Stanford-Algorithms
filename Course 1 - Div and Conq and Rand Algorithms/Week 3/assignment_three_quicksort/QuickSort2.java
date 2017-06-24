package assignment_three_quicksort;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/*
 * The file QuickSort.txt contains all of the integers between 1 and 10,000 (inclusive,
 * with no repeats) in unsorted order. The integer in the ith row of the file gives
 * you the ith entry of an input array.
 * 
 * Your task is to compute the total number of comparisons used to sort the
 * given input file by QuickSort. As you know, the number of comparisons depends
 * on which elements are chosen as pivots, so we'll ask you to explore three
 * different pivoting rules.
 * 
 * You should not count comparisons one-by-one. Rather, when there is a
 * recursive call on a subarray of length m, you should simply add m−1 to your
 * running total of comparisons. (This is because the pivot element is compared
 * to each of the other m−1 elements in the subarray in this recursive call.)
 * 
 * WARNING: The Partition subroutine can be implemented in several different
 * ways, and different implementations can give you differing numbers of
 * comparisons. For this problem, you should implement the Partition subroutine
 * exactly as it is described in the video lectures (otherwise you might get the
 * wrong answer).
 * 
 * Compute the number of comparisons (as in Problem 1), always using the final element
 * of the given array as the pivot element. Again, be sure to implement the Partition
 * subroutine exactly as it is described in the video lectures.
 * 
 * Recall from the lectures that, just before the main Partition subroutine, you should
 * exchange the pivot element (i.e., the last element) with the first element.
 * 
 */
public class QuickSort2 {
    private static long count = 0; // number of comparisons

    public static long countComparisons(int[] a) {
        sort(a);
        assert isSorted(a);
        return count;
    }

    public static void sort(int[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(int[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = partition(a, lo, hi);
        sort(a, lo, mid - 1);
        sort(a, mid + 1, hi);
        count += hi - lo;
    }

    private static int partition(int[] a, int lo, int hi) {
        int i = lo + 1;
        int p = hi; // pivot
        swap(a, p, lo);
        for (int j = lo + 1; j <= hi; j++) {
            if (a[j] < a[lo]) swap(a, i++, j);
        }
        swap(a, i - 1, lo);
        return i - 1;
    }
    
    private static void swap (int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    private static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] > a[i]) return false;
        }
        return true;
    }

    private static Reader in;
    private static PrintWriter out;

    private static final String INPUT_FILE_NAME = "QuickSort.txt";

    private static final int SIZE = 10000;

    public static void main(String[] args) throws IOException {
        String packageName = "";
        try {
            packageName = new QuickSort2().getClass().getPackage().toString().split(" ")[1] + "/";
        } catch (NullPointerException e) {}
        in = new Reader("Week 3/" + packageName + INPUT_FILE_NAME);
        // in = new Reader(System.in);
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int[] arr = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = in.nextInt();
        }
        out.println(countComparisons(arr));
        out.close();
    }
}
