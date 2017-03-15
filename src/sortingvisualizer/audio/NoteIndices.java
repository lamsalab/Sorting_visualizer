package sortingvisualizer.audio;

import java.util.Random;
import java.util.*;


/**
 * A collection of indices into a Scale object.
 * These indices are the subject of the various sorting algorithms
 * in the program.
 */
public class NoteIndices {
	private int n;
	private int[] arr;
	private boolean[] highlighted;
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
    	//int[] arr = new int[n];
    	Random rand = new Random();
    	for (int i =0; i < n; i++){
    		arr[i] = i;
    	}
    	for (int j = 0;j < n; j++){
    		int index = rand.nextInt(n);
    		int temp = arr[index];
    		arr[index] = arr[j];
    		arr[j] = temp;
    	}
    }
    
    /** @return the indices of this NoteIndices object */
   /* public Integer[] getNotes() { 
        Integer[] arr2 = new Integer[n];
        for (int i = 0; i < arr.length; i++){
        	arr2[i] = (Integer) arr[i];
        }
        return arr2;
    }*/
    public ArrayList<Integer> getNotes(){
    	ArrayList<Integer> arr2= new ArrayList<Integer>();
    	for (int i=0; i< arr2.size(); i++){
    		arr2.add(arr[i]);
    	}
    	return arr2;
    	
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
   }

