package sortingvisualizer.audio;

import java.util.Random;
import java.util.*;


/**
 * A collection of indices into a Scale object.
 * These indices are the subject of the various sorting algorithms
 * in the program.
 */
public class NoteIndices {
	public int n;
	public int[] arr;
	public boolean[] highlighted;
	public ArrayList<Integer> arr2;
    /**
     * @param n the size of the scale object that these indices map into
     */
    public NoteIndices(int n) {
    	
        this.n=n;

    }
    
    /**
     * Reinitializes this collection of indices to map into a new scale object
     * of the given size.  The collection is also shuffled to provide an
     * initial starting point for the sorting process.
     * @param n the size of the scale object that these indices map into
     */
    public void initializeAndShuffle(int n) {
        this.highlighted = new boolean[n];
        for (int i = 0; i < n; i++){
        	this.highlighted[i] = false;
        }
    	int[] arr = new int[n];
    	Random rand = new Random();
    	for (int i =0; i < n-1; i++){
    		arr[i] = i;
    	}
    	for (int j = 0;j < n-1; j++){
    		int index = rand.nextInt(n);
    		int temp = arr[index];
    		arr[index] = arr[j];
    		arr[j] = temp;
    	}
    	 this.arr= new int[n]; 
    	for (int i=0; i<n-1; i++){
    		this.arr[i]= arr[i];
    	}
    }
    
    /** @return the indices of this NoteIndices object */

    public ArrayList<Integer> getNotes(){
    	ArrayList<Integer> arr3= new ArrayList<Integer>();
    	for (int i=0; i< arr.length; i++){
    		arr3.add(arr[i]);
    	}
    	return arr3;
    	
    }
    
    /**
     * Highlights the given index of the note array
     * @param index the index to highlight
     */
    public void highlightNote(int index) {
    	this.highlighted[index] = true;
    }
    
    /** @return true if the given index is highlighted */
    public boolean isHighlighted(int index) {
    	return highlighted[index];
    }
    
    /** Clears all highlighted indices from this collection */
    public void clearAllHighlighted() {
        for (int i = 0; i < highlighted.length; i++){
        	highlighted[i] = false;
        }      
    }
    public int getN(){
    	return n;
    }
    public void update(ArrayList<Integer> l){
    	for (int i=0; i< arr.length; i++){
    		arr[i] = l.get(i);
    	}
    }
   }

