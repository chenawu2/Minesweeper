package minesweeper;

import java.awt.GridLayout;
import javax.swing.JPanel;


// This is a JPanel that we draw the mines on.
public class MinePanel extends JPanel {

	public MinePanel(MineBoard board) {
		
		int numRows = board.getNumRows();
		int numCols = board.getNumCols();
		
		// sets the layout of this panel
		this.setLayout(new GridLayout(numRows, numCols));
		
		// fills the panel with all of the tiles (buttons)
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {	
				this.add(board.getTile(row, col));
			}			
		}
	}	
}
