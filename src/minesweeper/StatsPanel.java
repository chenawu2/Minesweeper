package minesweeper;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Calculate the timer/statistics of the game (including number of mines remaining)

public class StatsPanel extends JPanel {

	private int timePassed = 0;
	protected int minesRemaining;

	// create panel that displays the stats to the users
	public StatsPanel(final MineWindow window) {
		final MineBoard gameBoard = window.getMineBoard();

		// Adds a timer label
		final JLabel timerLabel = new JLabel("Current Time: "
				+ Integer.toString(timePassed));
		this.add(timerLabel, BorderLayout.WEST);

		// adds a "New Game" button
		//uncomment for new game button
		//JButton newGameButton = new JButton("New Game");
		//this.add(newGameButton, BorderLayout.CENTER);

		// adds new game button listener
		ActionListener newGameListener = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetTimer();
				window.newGame();
			}
		};
		//newGameButton.addActionListener(newGameListener);

		// adds a "Mines Remaining" label
		final JLabel minesRemainingLabel = new JLabel("Mines Remaining: "
				+ Integer.toString(gameBoard.getMinesRemaining()));
		this.add(minesRemainingLabel, BorderLayout.EAST);

		final JLabel face = new JLabel();
		face.setIcon(new ImageIcon("good.png"));
		this.add(face);

		// Defines a timerListener Class
		ActionListener timerListener = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StatsPanel.this.minesRemaining = gameBoard.getMinesRemaining();
				StatsPanel.this.timePassed += 1;

				if (StatsPanel.this.timePassed % 10 == 0) {
					timerLabel
							.setText("Current Time: "
									+ Integer
											.toString(StatsPanel.this.timePassed / 10));
				}

				minesRemainingLabel.setText("Mines Remaining: "
						+ Integer.toString(gameBoard.getMinesRemaining()));

			}
		};

		// creates a timer that keeps track of game time elapsed
		final Timer gameTimer = new Timer(100, timerListener);

		// creates an action listener for the state checker timer
		ActionListener stateChecker = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String state = window.getMineBoard().getGameState();

				if (state == "running" || state == "pressed") {
					gameTimer.start();
				}
				if (state == "lostgame" || state == "wongame") {
					gameTimer.stop();
				}

				if (state == "running") {
					face.setIcon(new ImageIcon("good.png"));
				} else if (state == "pressed") {
					face.setIcon(new ImageIcon("pressed1.png"));
				} else if (state == "wongame") {
					face.setIcon(new ImageIcon("winner.png"));
				} else if (state == "lostgame") {
					face.setIcon(new ImageIcon("gameover.png"));
				}

			}
		};

		// creates the state checker timer which always is running checking the state
		final Timer stateTimer = new Timer(100, stateChecker);
		stateTimer.start();

	}

	// resets the game timer
	void resetTimer() {
		this.timePassed = 0;
	}
}
