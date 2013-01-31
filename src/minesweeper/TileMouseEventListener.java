package minesweeper;

import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.plaf.basic.BasicButtonListener;

// this listener for the buttons when the player clicks the tiles

public class TileMouseEventListener extends BasicButtonListener {
	private Tile button;
	private boolean tileIsPressed = false;

	// we create a listener that interacts with the player and the tiles
	public TileMouseEventListener(AbstractButton b) {
		super(b);
		this.button = (Tile) b;
	}

	// override the mousepressed in BasicButtonListener
	public void mousePressed(MouseEvent e) {
		this.tileIsPressed = true;
		if (e.getButton() == MouseEvent.BUTTON1)
			this.button.board.press();
	}

	// override the mousereleased in BasicButtonListener
	public void mouseReleased(MouseEvent e) {
		if (this.button.board.getIsLocked()) {
			return;
		}
		if (!this.button.board.getGameState().equals("running")) {
			if (this.button.board.getGameState().equals("pressed")) {
				this.button.board.release();
			} else {
				this.button.board.startGame();
			}
		}
		if (!this.tileIsPressed) {
			return;
		} else {
			if (this.button.state.equals("revealed")) {
				if (e.isShiftDown()
						&& (e.getButton() == MouseEvent.BUTTON1)
						|| ((e.getButton() == MouseEvent.BUTTON1) && e
								.getModifiers() != MouseEvent.BUTTON1_DOWN_MASK)) {
					this.button.shiftLeftClick();
				} else
					return;
			} else {

				if (e.getButton() == MouseEvent.BUTTON1) {

					this.button.leftClick();

				} else {
					if (e.getModifiers() == MouseEvent.BUTTON1_DOWN_MASK) {
						this.button.shiftLeftClick();
					}

					this.button.toggleIsEnabled();

					if (this.button.state.equals("question")) {
						this.button.state = "hidden";
						this.button.setIcon(null);
					} else if (this.button.state.equals("hidden")) {
						this.button.state = "flag";
						this.button.setIcon(new ImageIcon("flag.png"));
					} else {
						this.button.state = "question";
						this.button.setIcon(new ImageIcon("Question.png"));
					}

				}
			}
		}
	}

	// override the mouse exited method in basicbuttonlistener
	public void mouseExited(MouseEvent e) {
		this.tileIsPressed = false;
	}

}
