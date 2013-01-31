package minesweeper;

import javax.swing.ImageIcon;

// A class for mine tiles
public class MineTile extends Tile {

	// creates a tile that is a mine
	public MineTile(MineBoard board, int row, int col) {
		super(board, row, col);

	}

	// MineTiles are always mines, so this method always returns true
	public boolean isMine() {
		return true;
	}

	// calls the method when a tile is left-clicked on
	public void leftClick() {
		if (super.state.equals("flag") || super.state.equals("question")) {

			return;
		}
		this.setIcon(new ImageIcon("mine.png"));
		this.setEnabled(false);
		this.state = "revealed";
		super.board.endGame();
	}

	// calls this method when user performs a shift left click
	public void shiftLeftClick() {
		if (super.state.equals("flag") || super.state.equals("question")) {
			return;
		}
		this.setIcon(new ImageIcon("mine.png"));
		this.setEnabled(false);
		super.board.endGame();
	}

	// Reveals the mine at the end of the game, disables it
	public void end() {
		this.setIcon(new ImageIcon("mine.png"));
		this.setEnabled(false);
		this.state = "revealed";
	}

}