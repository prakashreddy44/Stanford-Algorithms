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
 * Compute the number of comparisons (as in Problem 1), using the "median-of-three"
 * pivot rule. [The primary motivation behind this rule is to do a little bit of
 * extra work to get much better performance on input arrays that are nearly sorted
 * or reverse sorted.] In more detail, you should choose the pivot as follows.
 * Consider the first, middle, and final elements of the given array. (If the array
 * has odd length it should be clear what the "middle" element is; for an array with
 * even length 2k, use the kth element as the "middle" element. So for the array
 * 4 5 6 7, the "middle" element is the second one ---- 5 and not 6!) Identify which
 * of these three elements is the median (i.e., the one whose value is in between the
 * other two), and use this as your pivot. As discussed in the first and second parts
 * of this programming assignment, be sure to implement Partition exactly as
 * described in the video lectures (including exchanging the pivot element with
 * the first element just before the main Partition subroutine).
 * 
 * EXAMPLE: For the input array 8 2 4 5 7 1 you would consider the first (8),
 * middle (4), and last (1) elements; since 4 is the median of the set {1,4,8},
 * you would use 4 as your pivot element.
 * 
 * SUBTLE POINT: A careful analysis would keep track of the comparisons made in
 * identifying the median of the three candidate elements. You should NOT do this.
 * That is, as in the previous two problems, you should simply add m−1 to your
 * running total of comparisons every time you recurse on a subarray with length m.
 * 
 */
public class QuickSort3 {
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
        int mid = lo + (hi - lo) / 2; // to prevent integer overflow
        int p;
        if ((a[lo] >= a[mid] && a[lo] <= a[hi]) || (a[lo] <= a[mid] && a[lo] >= a[hi])) p = lo;
        else if ((a[mid] >= a[lo] && a[mid] <= a[hi]) || (a[mid] <= a[lo] && a[mid] >= a[hi])) p = mid;
        else p = hi;
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
            packageName = new QuickSort3().getClass().getPackage().toString().split(" ")[1] + "/";
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
