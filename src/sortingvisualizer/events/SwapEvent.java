package sortingvisualizer.events;
import java.util.ArrayList;
import java.util.List;

public class SwapEvent<T> implements SortEvent<T>{
	public List<Integer> swapList; 
	public int affectedIndex1;
	public int affectedIndex2;
	public boolean isEmphasized;
	
	
	public SwapEvent(int first, int second){
		this.affectedIndex1= first;
		this.affectedIndex2= second;
		this.isEmphasized=true;
		swapList = new ArrayList<>();
		swapList.add(affectedIndex1);
		swapList.add(affectedIndex2);
		
	}
	
	public void apply(ArrayList<T> l){
		T temp = l.get(affectedIndex1);
		l.set(affectedIndex1, l.get(affectedIndex1));
		l.set(affectedIndex2, temp);
		
	}
	public List<Integer> getAffectedIndices(){
		return swapList;
	}
	public boolean isEmphasized(){
		return isEmphasized;
	}

}
