package minesweeper;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

// This class holds all the tiles and acts as the board of the game
public class MineBoard {

	private Tile[][] mineBoard;
	private int numRows;
	private int numCols;
	private int numMines;
	private String gameState;
	final private Timer gameTimer;
	private boolean isLocked = false;
	private MineWindow window;

	public MineBoard(int numRows, int numCols, int numMines, MineWindow window) {

		this.numRows = numRows;
		this.numCols = numCols;
		this.numMines = numMines;
		this.mineBoard = new Tile[numRows][numCols];

		Random rand = new Random();

		for (int i = 0; i < numMines; i++) {

			int row = rand.nextInt(numRows);
			int col = rand.nextInt(numCols);

			if (this.mineBoard[row][col] == null) {
				this.mineBoard[row][col] = new MineTile(this, row, col);
			} else {
				i--;
			}
		}

		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				if (this.mineBoard[row][col] == null) {

					this.mineBoard[row][col] = new EmptyTile(this, row, col,
							this.getNumSurroundingMines(row, col));
				}
			}
		}

		this.gameState = "newgame";
		this.window = window;

		// creates an action listener update game
		ActionListener updateGameChecker = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				MineBoard.this.updateGame();
			}
		};

		// creates update game timer
		this.gameTimer = new Timer(100, updateGameChecker);
		this.gameTimer.start();

	}

	// returns the tile at a specific spot on the mineBoard

	public Tile getTile(int row, int col) {

		if (row < 0 || row >= this.numRows || col < 0 || col >= this.numCols) {
			return null;
		} else {
			return this.mineBoard[row][col];
		}

	}

	// returns the staate of the game
	public String getGameState() {
		return this.gameState;
	}

	// returns the number of mines remaining
	public int getMinesRemaining() {

		int numMines = this.numMines;

		for (int row = 0; row < this.numRows; row++) {
			for (int col = 0; col < this.numCols; col++) {
				if (mineBoard[row][col].getState() == "flag") {
					numMines--;
				}
			}
		}
		return numMines;
	}

	// ends game and displays whether you won or not
	public void endGame() {
		for (int row = 0; row < this.numRows; row++) {
			for (int col = 0; col < this.numCols; col++) {
				if (mineBoard[row][col].isMine()) {
					((MineTile) mineBoard[row][col]).end();
				}
			}
		}

		this.setBoardLock(true);

		if (!this.gameState.equals("wongame")) {
			this.gameState = "lostgame";
			final JFrame youLose = new JFrame("You Lost");
			youLose.setSize(new Dimension(200, 75));

			JLabel loser = new JLabel("You lost", JLabel.CENTER);

			JButton playAgain = new JButton("Play Again?");
			ActionListener play = new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					newGame();
					youLose.dispose();
				}
			};
			playAgain.addActionListener(play);

			JButton quit = new JButton("Quit");
			ActionListener quiter = new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					kill();
					youLose.dispose();
				}
			};
			quit.addActionListener(quiter);

			JPanel container = new JPanel();
			container.add(playAgain, BorderLayout.NORTH);
			container.add(quit, BorderLayout.SOUTH);

			youLose.add(loser, BorderLayout.CENTER);
			youLose.add(container, BorderLayout.SOUTH);
			youLose.setVisible(true);
			youLose.setResizable(false);
		}

	}

	// kills window
	protected void kill() {
		this.window.kill();
	}

	// returns number of tiles surrounding the mines
	public int getNumSurroundingMines(int rowPos, int colPos) {

		int numSurrMines = 0;

		for (int row = rowPos - 1; row <= rowPos + 1; row++) {
			for (int col = colPos - 1; col <= colPos + 1; col++) {
				if (!(row == rowPos && col == colPos)) {

					if (this.getTile(row, col) != null) {
						if (this.getTile(row, col).isMine()) {
							numSurrMines++;
						}
					}
				}
			}
		}

		return numSurrMines;

	}

	// updates the game and chekcs its current state
	public void updateGame() {

		int numberTilesShown = 0;
		for (int row = 0; row < this.numRows; row++) {
			for (int col = 0; col < this.numCols; col++) {
				if (!mineBoard[row][col].isMine()
						&& mineBoard[row][col].getState().equals("revealed")) {
					numberTilesShown++;
				}
			}
		}
		if (numberTilesShown == (this.numCols * this.numRows - this.numMines)) {
			this.gameState = "wongame";
			this.gameTimer.stop();
			this.endGame();

			final JFrame youWin = new JFrame("You Win!");
			youWin.setSize(new Dimension(200, 75));

			JLabel winner = new JLabel("You Won!",
					JLabel.CENTER);

			JButton playAgain = new JButton("Play Again?");
			ActionListener play = new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					newGame();
					youWin.dispose();
				}
			};
			playAgain.addActionListener(play);

			JButton quit = new JButton("Quit");
			ActionListener quiter = new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					kill();
					youWin.dispose();
				}
			};
			quit.addActionListener(quiter);

			JPanel container = new JPanel();
			container.add(playAgain, BorderLayout.NORTH);
			container.add(quit, BorderLayout.SOUTH);

			youWin.add(winner, BorderLayout.CENTER);
			youWin.add(container, BorderLayout.SOUTH);
			youWin.setVisible(true);
			youWin.setResizable(false);
		}

	}

	// locks the gmae board
	public void setBoardLock(boolean isLocked) {
		this.isLocked = isLocked;
	}

	// starts game
	public void startGame() {

		if (this.gameState.equals("newgame")) {
			this.gameState = "running";
		}
	}

	// returns rows
	public int getNumRows() {
		return this.numRows;
	}

	// returns columns
	public int getNumCols() {
		return this.numCols;
	}

	// returns number of mines
	public int getNumMines() {
		return this.numMines;
	}

	// returns game if is locked
	public boolean getIsLocked() {
		return this.isLocked;
	}

	private void newGame() {
		this.window.newGame();
	}

	// sets the game to pressed
	public void press() {
		this.gameState = "pressed";
	}

	// sets the game to running
	public void release() {
		this.gameState = "running";
	}

}
