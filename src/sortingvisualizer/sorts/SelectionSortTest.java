package sortingvisualizer.sorts;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class SelectionSortTest {

	@Test
	//testing selection sort on an arbitrary list
	public void testselection1(){
		ArrayList<Integer> l = new ArrayList<>();
		l.add(5);
		l.add(2);
		l.add(0);
		l.add(10);
		l.add(15);
		l.add(-3);
		ArrayList<Integer> l2 = new ArrayList<>();
		l2.add(-3);
		l2.add(0);
		l2.add(2);
		l2.add(5);
		l2.add(10);
		l2.add(15);
		Sorts.selectionSort(l);
		assertEquals(l2, l);
	}
	@Test
	//testing selection sort on an empty list
	public void testselection2(){
		ArrayList<Integer> l = new ArrayList<>();

		ArrayList<Integer> l2 = new ArrayList<>();

		Sorts.selectionSort(l);
		assertEquals(l2, l);
	}
	@Test
	//testing selection sort on a sorted list
	public void testselection3(){
		ArrayList<Integer> l = new ArrayList<>();
		l.add(1);
		l.add(2);
		l.add(3);
		l.add(4);
		l.add(5);
		l.add(6);
		ArrayList<Integer> l2 = new ArrayList<>();
		l2.add(1);
		l2.add(2);
		l2.add(3);
		l2.add(4);
		l2.add(5);
		l2.add(6);
		Sorts.selectionSort(l);
		assertEquals(l2, l);
	}
	@Test
	//Testing selection sort on a list sorted from greatest to smallest
	public void testselection4(){
		ArrayList<Integer> l = new ArrayList<>();
		l.add(6);
		l.add(5);
		l.add(4);
		l.add(3);
		l.add(2);
		l.add(1);
		ArrayList<Integer> l2 = new ArrayList<>();
		l2.add(1);
		l2.add(2);
		l2.add(3);
		l2.add(4);
		l2.add(5);
		l2.add(6);
		Sorts.selectionSort(l);
		assertEquals(l2, l);
	}
	@Test
	//Testing selection sort on an arbitrary list of letters (to demonstrate that the sorting does not only work for integers)
	public void testselection5(){
		ArrayList<String> l = new ArrayList<>();
		l.add("a");
		l.add("t");
		l.add("u");
		l.add("q");
		l.add("l");
		l.add("x");
		ArrayList<String> l2 = new ArrayList<>();
		l2.add("a");
		l2.add("l");
		l2.add("q");
		l2.add("t");
		l2.add("u");
		l2.add("x");
		Sorts.selectionSort(l);
		assertEquals(l2, l);
	}
	
}
