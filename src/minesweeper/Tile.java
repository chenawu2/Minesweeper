package minesweeper;

import java.awt.Dimension;
import java.awt.event.MouseListener;

import javax.swing.JButton;

// A basic tile, can either be a mine or empty. Knows its state, location, and mineBoard. Abstract
public abstract class Tile extends JButton {

	protected boolean isEnabled = true;
	protected String state = "hidden";
	protected MineBoard board;
	protected int row;
	protected int col;

	// sets the fields of row, col and board, also defaults the values of
	// isEnabled and state
	public Tile(MineBoard board, int row, int col) {
		super();
		this.board = board;
		MouseListener listener = new TileMouseEventListener(this);
		this.addMouseListener(listener);
		this.row = row;
		this.col = col;
		this.setPreferredSize(new Dimension(45, 45));

	}

	// returns the current state of the tile
	public String getState() {
		return this.state;
	}

	// abstract method that is implemented by individual classes
	public abstract void leftClick();

	// returns true if tile is a mine
	public abstract boolean isMine();

	// returns true if tile is enabled
	public boolean getIsEnabled() {
		return this.isEnabled;
	}

	// toggles the boolean isEnabled
	public void toggleIsEnabled() {
		this.isEnabled = !this.isEnabled;
	}

	// returns the current state of the tile
	public String getTileState() {
		return this.state;
	}

	// abstract method that occurs when tile is shift clicked (or right + left
	// clicked)
	public abstract void shiftLeftClick();

}