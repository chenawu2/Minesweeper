package minesweeper;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

// frame that surrounds the window

public class MineWindow extends JFrame {

	private MineBoard gameBoard;
	private JPanel content;
	private String difficulty = "easy";

	private int numRows;
	private int numCols;
	private int numMines;

	// initilizes the frame
	public MineWindow() {

		// sets initial conditions of window
		this.setTitle("Minesweeper!");
		this.setResizable(false);

		// create the menu bar to be used on window
		JMenuBar menu = new MineSweeperMenuBar(this);

		// add menu bar to window
		this.setJMenuBar(menu);

		// creates a new game
		this.newGame();

	}

	// quits the game
	public void kill() {

		setVisible(false);
		dispose();
		System.exit(0);

	}

	// creates a new game
	public void newGame() {

		if (this.content != null) {
			this.remove(this.content);
		}

		if (this.difficulty.equals("hard")) {
			this.setBoardFill(16, 30, 99);

		} else if (this.difficulty.equals("medium")) {
			this.setBoardFill(16, 16, 40);

		} else {
			this.setBoardFill(9, 9, 10);
		}

		// creates a panel to hold stats panel and board panel
		this.content = new JPanel();
		this.content
				.setLayout(new BoxLayout(this.content, BoxLayout.PAGE_AXIS));

		// creates a game board
		this.gameBoard = new MineBoard(this.numRows, this.numCols,
				this.numMines, this);
		// this.gameBoard = new MineBoard(10, 10, 2);

		// creates a stats panel then adds it to the content
		StatsPanel statsPan = new StatsPanel(this);

		// creates a mine panel then adds it to the content
		MinePanel minePan = new MinePanel(this.gameBoard);

		// adds items to content
		this.content.add(statsPan);
		this.content.add(minePan);

		// adds the content to the minewindow
		this.add(this.content);

		this.pack();

	}

	// setes the difficulty
	public void setDifficulty(String string) {
		this.difficulty = string;
	}

	// returns the gameboard
	public MineBoard getMinePanel() {
		return this.gameBoard;
	}

	// sets the correct rows, columns, and number of mines
	public void setBoardFill(int numRows, int numCols, int numMines) {
		this.numRows = numRows;
		this.numCols = numCols;
		this.numMines = numMines;
	}

	// returns the game board
	public MineBoard getMineBoard() {
		return this.gameBoard;
	}
}
