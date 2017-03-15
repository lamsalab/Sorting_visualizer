package sortingvisualizer.sorts;

import java.util.*;

import sortingvisualizer.events.CompareEvent;
import sortingvisualizer.events.CopyEvent;
import sortingvisualizer.events.SortEvent;
import sortingvisualizer.events.SwapEvent;

public class Sorts {
	public static <T extends Comparable<T>> List<SortEvent<T>> selectionSort(ArrayList<T> l) {
		List<SortEvent<T>> ret = new ArrayList<SortEvent<T>>();
		for (int i = 0; i < l.size(); i++) {
			for (int j = i + 1; j < l.size(); j++) {
				if (l.get(j).compareTo(l.get(i)) < 0) {
					ret.add(new CompareEvent<T>(j, i));
					SortEvent<T> c= new SwapEvent<T>(j, i);
					c.apply(l);
					//swap(l, i, j);
					ret.add(c);
					
				}
			}
		}
		return ret;
	}

	public static <T> void swap(ArrayList<T> l, int i, int j) {
		T t = l.get(i);
		l.set(i, l.get(j));
		l.set(j, t);
	}

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
	 * Shellsort, using Shell’s (poor) increments.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 *            http://stackoverflow.com/questions/4833423/shell-sort-java-
	 *            example
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

	private static <T extends Comparable<T>> void mergeSortHelper(ArrayList<T> l, int lo, int hi,
			List<SortEvent<T>> ret) {
		if (lo != hi) {
			int middle = (hi + lo) / 2;
			mergeSortHelper(l, lo, middle, ret);
			mergeSortHelper(l, middle + 1, hi, ret);
			merge(l, lo, middle, hi, ret);
		}
	}

	public static <T extends Comparable<T>> List<SortEvent<T>> mergeSort(ArrayList<T> l) {
		List<SortEvent<T>> ret = new ArrayList<SortEvent<T>>();
		if (l.size() <= 1) {
			return ret;
		}
		mergeSortHelper(l, 0, l.size() - 1, ret);
		return ret;
	}
	// http://www.cs.mcgill.ca/~dprecup/courses/IntroCS/Examples/Sorting/MergeSort.java

	public static <T extends Comparable<T>> void partition(ArrayList<T> l, int low, int hi, int pivotIndex) {
		/*
		 * swap(l, pivotIndex, l.size()); while (low != hi) { if
		 * (l.get(low).compareTo(l.get(l.size())) > 0) { low++; } if
		 * (l.get(hi).compareTo(l.get(l.size())) < 0) { hi--; } if
		 * ((l.get(low).compareTo(l.get(l.size())) < 0) &&
		 * (l.get(hi).compareTo(l.get(l.size())) > 0)) { swap(l, low, hi); } }
		 * swap(l, l.size(), low);
		 */
		/*
		 * int i = low-1; for (int j = low; j < hi - 1; j++) { if
		 * (l.get(j).compareTo(l.get(hi)) <= 0) { i++; swap(l, i, j); } }
		 * swap(l, i + 1, hi);
		 */
		T pivot = l.get(l.size()-1);
		while (low != hi) {
			while ((l.get(low).compareTo(pivot) <= 0) && (hi != low)) {
				low++;
			}
			while ((l.get(hi).compareTo(pivot) >= 0) && (hi != low)) {
				hi--;
			}
			swap(l, low, hi);
		}
		swap(l, low, l.size()-1);
	}

	private static <T extends Comparable<T>> void quickSortHelper(ArrayList<T> l, int lo, int hi) {
		if (lo < hi) {
			int mid = (hi - lo) / 2;
			int median = 0;
			ArrayList<T> temp = new ArrayList<T>();
			temp.add(l.get(lo));
			temp.add(l.get(mid));
			temp.add(l.get(hi));
			selectionSort(temp);
			if (temp.get(1) == l.get(lo))
				median = lo;
			if (temp.get(1) == l.get(mid))
				median = mid;
			if (temp.get(1) == l.get(hi))
				median = hi;
			swap(l, median, l.size()-1);
			partition(l, lo, hi, median);
			//swap(l, median, hi);
			quickSortHelper(l, lo, median);
			quickSortHelper(l, median + 1, hi);
		}
	}

	public static <T extends Comparable<T>> List<SortEvent<T>> quickSort(ArrayList<T> l) {
		quickSortHelper(l, 0, l.size() - 1);
		return null;
	}

	public static <T> void eventSort(ArrayList<T> l, List<SortEvent<T>> events) {
		for(int i=0; i< events.size(); i++){
			events.get(i).apply(l);
		}
	}

	public static void main(String[] args) {
		ArrayList<Integer> l = new ArrayList<>();
		l.add(5);
		l.add(2);
		l.add(0);
		l.add(10);
		l.add(15);
		l.add(-3);
		ArrayList<Integer> l2 = (ArrayList<Integer>)l.clone();
		List<SortEvent<Integer>> list = selectionSort(l);
		eventSort(l2, list);
		
		System.out.print(l.toString());
		System.out.print(l2.toString());
	}
}
