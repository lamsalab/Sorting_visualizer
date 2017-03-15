package sortingvisualizer.rendering;

import java.awt.Color;
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
		int i= 0;
		int width=  notes.getNotes().size() / notes.getN();
		int height= notes.getNotes().get(i);
		for (i= 0; i < notes.getNotes().size(); i++) {
			g.drawRect(j, j, width, height);
			g.fillRect(j, j, width, height);
			g.setColor(Color.WHITE);
			if (notes.isHighlighted(i)){
				g.setColor(Color.RED);
			}
			else{
				if (i%2 == 1){
					g.setColor(Color.BLUE);
				}
				else{
					g.setColor(Color.GREEN);
				}
			}
			
			j += width;
		}						
	}	
}