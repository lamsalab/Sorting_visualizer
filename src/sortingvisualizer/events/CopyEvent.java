package sortingvisualizer.events;

import java.util.ArrayList;
import java.util.List;

public class CopyEvent<T> {
	public List<Integer> copyList; 
	public int affectedIndex;
	public T value;
	public boolean isEmphasized;
	
	public CopyEvent(int affectedIndex, T value){
		this.affectedIndex= affectedIndex;
		this.isEmphasized= true;
		this.value = value;
		copyList.add(affectedIndex);
	}
	
	public void apply(ArrayList<T> l){
		l.set(affectedIndex, value);
	}
	public List<Integer> getAffectedIndices(){
		return copyList;
	}
	public boolean isEmphasized(){
		return isEmphasized;
	}

}
