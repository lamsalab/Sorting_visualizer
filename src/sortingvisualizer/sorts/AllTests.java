package sortingvisualizer.sorts;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BubbleSortTest.class, CustomSortTest.class, InsertionSortTest.class, MergeSortTest.class,
		QuickSortTest.class, SelectionSortTest.class })
public class AllTests {

}
