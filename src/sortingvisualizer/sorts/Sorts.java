package sortingvisualizer.sorts;

import java.util.*;

import sortingvisualizer.events.CompareEvent;
import sortingvisualizer.events.CopyEvent;
import sortingvisualizer.events.SortEvent;
import sortingvisualizer.events.SwapEvent;

public class Sorts {
	/**
	 * 
	 * @param l ArrayList to be sorted
	 * @return ret a List<Sortevent<T>>
	 */
	public static <T extends Comparable<T>> List<SortEvent<T>> selectionSort(ArrayList<T> l) {
		List<SortEvent<T>> ret = new ArrayList<SortEvent<T>>();
		for (int i = 0; i < l.size(); i++) {
			for (int j = i + 1; j < l.size(); j++) {
				if (l.get(j).compareTo(l.get(i)) < 0) {
					ret.add(new CompareEvent<T>(j, i));
					SortEvent<T> c = new SwapEvent<T>(j, i);
					c.apply(l);
					// swap(l, i, j);
					ret.add(c);

				}
			}
		}
		return ret;
	}

	/**
	 * 
	 * @param l an ArrayList that will be modified
	 * @param i an index that will be swapped
	 * @param j an index that will be swapped
	 */
	public static <T> void swap(ArrayList<T> l, int i, int j) {
		T t = l.get(i);
		l.set(i, l.get(j));
		l.set(j, t);
	}

	/**
	 * 
	 * @param l an ArrayList that will be modified
	 * @return ret a List<Sortevent<T>>
	 */
	public static <T extends Comparable<T>> List<SortEvent<T>> insertionSort(ArrayList<T> l) {
		List<SortEvent<T>> ret = new ArrayList<SortEvent<T>>();
		for (int i = 1; i < l.size(); i++) {
			for (int j = i; j > 0 && l.get(j - 1).compareTo(l.get(j)) > 0; j--) {
				ret.add(new CompareEvent<T>(j - 1, j));
				swap(l, j - 1, j);
				ret.add(new SwapEvent<T>(j - 1, j));
			}
		}
		return ret;
	}

	/**
	 * 
	 * @param l  an ArrayList that will be modified
	 * @return ret a List<Sortevent<T>>
	 */
	public static <T extends Comparable<T>> List<SortEvent<T>> bubbleSort(ArrayList<T> l) {
		List<SortEvent<T>> ret = new ArrayList<SortEvent<T>>();
		boolean swapped = true;
		while (swapped) {
			swapped = false;
			for (int i = 1; i < l.size(); i++) {
				ret.add(new CompareEvent<T>(i - 1, i));
				if (l.get(i - 1).compareTo(l.get(i)) > 0) {
					swap(l, i - 1, i);
					ret.add(new SwapEvent<T>(i - 1, i));
					swapped = true;
				}
			}
		}
		return ret;
	}


	/**
	 * 
	 * @param l an ArrayList that will be modified
	 * @return ret a List<Sortevent<T>>
	 */
	public static <T extends Comparable<T>> List<SortEvent<T>> customSort(ArrayList<T> l) {
		List<SortEvent<T>> ret = new ArrayList<SortEvent<T>>();
		int j;
		for (int gap = l.size() / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < l.size(); i++) {
				T temp = l.get(i);
				for (j = i; j >= gap && temp.compareTo(l.get(j - gap)) < 0; j -= gap) {
					ret.add(new CompareEvent<T>(i, j - gap));
					l.set(j, l.get(j - gap));
					ret.add(new CopyEvent<T>(j, l.get(j - gap)));
				}
				l.set(j, temp);
				ret.add(new CopyEvent<T>(j, temp));
			}
		}
		return ret;
	}

	/**
	 * 
	 * @param l an ArrayList that will be modified
	 * @param lo low index
	 * @param mid middle index
	 * @param hi  high index
	 * 
	 */
	private static <T extends Comparable<T>> void merge(ArrayList<T> l, int lo, int mid, int hi,
			List<SortEvent<T>> ret) {
		ArrayList<T> temp = new ArrayList<>();
		T filler = null;
		for (int p = 0; p < l.size(); p++) {
			temp.add(p, filler);
		}
		int i = lo;
		int j = mid + 1;
		int k = 0;
		while (i <= mid && j <= hi) {
			ret.add(new CompareEvent<T>(i, j));
			if (l.get(i).compareTo(l.get(j)) <= 0) {
				ret.add(new CopyEvent<T>(k, l.get(i)));
				temp.set(k, l.get(i++));
			} else {
				ret.add(new CopyEvent<T>(k, l.get(j)));
				temp.set(k, l.get(j++));
			}
			k++;
		}
		if (i <= mid && j > hi) {
			while (i <= mid) {
				ret.add(new CopyEvent<T>(k, l.get(i)));
				temp.set(k++, l.get(i++));
			}
		} else {
			while (j <= hi) {
				ret.add(new CopyEvent<T>(k, l.get(j)));
				temp.set(k++, l.get(j++));
			}
		}
		for (k = 0; k + lo <= hi; k++) {
			ret.add(new CopyEvent<T>(lo + k, temp.get(k)));
			l.set(lo + k, temp.get(k));
		}
	}

	/**
	 * 
	 * @param l an ArrayList that will be modified
	 * @param lo low index
	 * @param hi high index
	 * 
	 */
	private static <T extends Comparable<T>> void mergeSortHelper(ArrayList<T> l, int lo, int hi,
			List<SortEvent<T>> ret) {
		if (lo != hi) {
			int middle = (hi + lo) / 2;
			mergeSortHelper(l, lo, middle, ret);
			mergeSortHelper(l, middle + 1, hi, ret);
			merge(l, lo, middle, hi, ret);
		}
	}

	/**
	 * 
	 * @param l an ArrayList that will be modified
	 * @return ret a List<Sortevent<T>>
	 */
	public static <T extends Comparable<T>> List<SortEvent<T>> mergeSort(ArrayList<T> l) {
		List<SortEvent<T>> ret = new ArrayList<SortEvent<T>>();
		if (l.size() <= 1) {
			return ret;
		}
		mergeSortHelper(l, 0, l.size() - 1, ret);
		return ret;
	}

	// http://www.cs.mcgill.ca/~dprecup/courses/IntroCS/Examples/Sorting/MergeSort.java
	// http://www.learntosolveit.com/java/GenericQuickSort.html
	 // http://stackoverflow.com/questions/4833423/shell-sort-java-example
	
	/**
	 * 
	 * @param l an ArrayList that will be modified
	 * @param lo low index
	 * @param hi high index
	 * 
	 */
	private static <T extends Comparable<T>> void quickSortHelper(ArrayList<T> l, int lo, int hi,
			List<SortEvent<T>> ret) {
		if (lo < hi) {
			int i = lo;
			int j = hi;
			T pivot = l.get((hi + lo) / 2);
			do {
				while (l.get(i).compareTo(pivot) < 0) {
					ret.add(new CompareEvent<T>(i, (hi + lo) / 2));
					i++;
				}
				while (l.get(j).compareTo(pivot) > 0) {
					ret.add(new CompareEvent<T>(i, (hi + lo) / 2));
					j--;
				}
				if (i <= j) {
					ret.add(new SwapEvent<T>(i, j));
					swap(l, i, j);
					i++;
					j--;
				}

			} while (i <= j);
			quickSortHelper(l, lo, j, ret);
			quickSortHelper(l, i, hi, ret);
		}
	}

	/**
	 * 
	 * @param l an ArrayList that will be modified
	 * @return ret a List<Sortevent<T>>
	 */
	public static <T extends Comparable<T>> List<SortEvent<T>> quickSort(ArrayList<T> l) {
		List<SortEvent<T>> ret = new ArrayList<SortEvent<T>>();
		if (l.size() > 0)
			quickSortHelper(l, 0, l.size() - 1, ret);
		return ret;
	}

	/**
	 * 
	 * @param l an ArrayList that will be modified
	 * @param events a List<Sortevent<T>>
	 */
	public static <T> void eventSort(ArrayList<T> l, List<SortEvent<T>> events) {
		for (int i = 0; i < events.size(); i++) {
			events.get(i).apply(l);
		}
	}

}
