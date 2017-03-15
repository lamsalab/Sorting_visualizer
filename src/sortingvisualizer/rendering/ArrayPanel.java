package sortingvisualizer.rendering;

import java.awt.Color;
import java.util.*;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import sortingvisualizer.audio.NoteIndices;

@SuppressWarnings("serial")
public class ArrayPanel extends JPanel {

	private NoteIndices notes;
	private int width;
	private int height;
	

	/**
	 * Constructs a new ArrayPanel that renders the given note indices to the
	 * screen.
	 * 
	 * @param notes
	 *            the indices to render
	 * @param width
	 *            the width of the panel
	 * @param height
	 *            the height of the panel
	 */
	public ArrayPanel(NoteIndices notes, int width, int height) {
		this.notes = notes;
		this.setPreferredSize(new Dimension(width, height));
		this.width= width;
		this.height= height;
	}

	@Override
	public void paintComponent(Graphics g) {
		ArrayList<Integer> indices= notes.getNotes();
		int rectWidth =  width/(indices.size());
		int rectHeight= height/(indices.size());
		//g.drawRect(0, 0, rectWidth, rectHeight);
		//g.fillRect(0, 0, rectWidth, rectHeight);
		//g.setColor(Color.white);
		for (int i= 0; i < notes.getNotes().size(); i++) {			
			if (notes.isHighlighted(i)){
				g.setColor(Color.red);
			}
			g.fillRect(rectWidth*i, 300-rectHeight*indices.get(i), rectWidth,rectHeight*(1 + indices.get(i)));	
		}
		
	}	
}