package sortingvisualizer.sorts;
import java.util.*;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({})
public class sortsTests {

	public void testInsertion(){
		ArrayList<Integer> l = new ArrayList<> ();
		l.add(5);
		l.add(2);
		l.add(0);
		ArrayList<Integer> j = new ArrayList<> ();
		l.add(0);
		l.add(2);
		l.add(5);
		assertTrue(Sorts.Insertion(l), j);
	}
}
