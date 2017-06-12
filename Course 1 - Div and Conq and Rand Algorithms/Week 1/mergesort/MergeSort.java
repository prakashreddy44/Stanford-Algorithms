package mergesort;

public class MergeSort {

	// This class should not be instantiated.
	private MergeSort() {}

	// Stable merging of a[lo ... mid] with a[mid + 1 ... hi] by copying to aux[lo .. hi]
	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid) a[k] = aux[j++];
			else if (j > hi) a[k] = aux[i++];
			else if (aux[i].compareTo(aux[j]) <= 0) a[k] = aux[i++];
			else a[k] = aux[j++];
		}
	}
	
	// recursively call sort until the lo is greater than or equal to hi (array of size 0)
	// uses an auxiliary array to copy elements for merging 
	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		if (lo >= hi) return;
		int mid = lo + (hi - lo) / 2; // to prevent integer overflow
		sort(a, aux, lo, mid);
		sort(a, aux, mid + 1, hi);
		merge(a, aux, lo, mid, hi);
	}
	
	/**
	 * Sorts the array in ascending order, using the natural order
	 * @param a the array to be sorted
	 */
	public static void sort(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length - 1);
	}
}
