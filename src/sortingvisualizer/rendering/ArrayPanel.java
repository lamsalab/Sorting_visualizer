package sortingvisualizer.rendering;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import sortingvisualizer.audio.NoteIndices;

@SuppressWarnings("serial")
public class ArrayPanel extends JPanel {

	private NoteIndices notes;

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
	}

	@Override
	public void paintComponent(Graphics g) {
		int j= 0;
		for (int i=0; i < notes.getNotes().length-1; i++) {
			g.drawRect(j, j, notes.getNotes().length / notes.getN(), notes.getNotes()[i]);
			g.fillRect(j, j, notes.getNotes().length / notes.getN(), notes.getNotes()[i]);
			j += notes.getNotes().length / notes.getN();
		}
		
		
	}
	
}