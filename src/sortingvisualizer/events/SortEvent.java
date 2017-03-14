package sortingvisualizer.events;
import java.util.ArrayList;
import java.util.List;

public interface SortEvent<T> {
    
	public void apply(ArrayList<T> l);
	public List<Integer> getAffectedIndices();
	public boolean isEmphasized();
}
