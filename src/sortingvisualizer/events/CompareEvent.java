package sortingvisualizer.events;

import java.util.ArrayList;
import java.util.List;


public class CompareEvent<T> implements SortEvent<T>{
	public List<Integer> compareList; 
	public int affectedIndex1;
	public int affectedIndex2;
	public boolean isEmphasized;
	
	public CompareEvent(int affectedIndex1, int affectedIndex2){
		this.affectedIndex1= affectedIndex1;
		this.affectedIndex2= affectedIndex2;
		this.isEmphasized= false;
		compareList = new ArrayList<>();
		compareList.add(affectedIndex1);
		compareList.add(affectedIndex2);
	}
	public void apply(ArrayList<T> l){
	}
	
	
	public List<Integer> getAffectedIndices(){
		return compareList;
	}
	public boolean isEmphasized(){
		return isEmphasized;
	}
}
