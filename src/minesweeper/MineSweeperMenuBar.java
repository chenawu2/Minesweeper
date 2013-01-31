package minesweeper;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;

// Menu bar of the game
public class MineSweeperMenuBar extends JMenuBar {
	// private String difficulty;
	private MineWindow window;

	// INCLUDES FILE AND DIFFICULTY MENU PANEL!!
	public MineSweeperMenuBar(MineWindow window) { // MinePanel panel) {

		this.window = window;

		// add file menu
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		this.add(fileMenu);

		// add newGame item to menu
		JMenuItem newGame = new JMenuItem("New Game");
		fileMenu.add(newGame);

		// adds listener to new game button
		ActionListener newGameAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MineSweeperMenuBar.this.window.newGame();
			}
		};
		newGame.addActionListener(newGameAction);

		// add quit item to menu
		JMenuItem quitButton = new JMenuItem("Quit");
		fileMenu.add(quitButton);

		// adds listener to quit button
		ActionListener quitAction = new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				MineSweeperMenuBar.this.window.kill();
			}
		};
		quitButton.addActionListener(quitAction);

		// add a difficulty menu
		JMenu difficultyMenu = new JMenu("Difficulty");
		difficultyMenu.setMnemonic(KeyEvent.VK_D);

		// add easy item to menu
		JMenuItem easy = new JMenuItem("Easy");
		difficultyMenu.add(easy);
		easy.addActionListener(difficultyActionListener("easy"));

		// add medium item to menu
		JMenuItem medium = new JMenuItem("Medium");
		difficultyMenu.add(medium);
		medium.addActionListener(difficultyActionListener("medium"));

		// add hard item to menu
		JMenuItem hard = new JMenuItem("Hard");
		difficultyMenu.add(hard);
		hard.addActionListener(difficultyActionListener("hard"));

		// adds difficulty menu to menu bar
		this.add(difficultyMenu);

		JMenu aboutMenu = new JMenu("About");
		JMenuItem aboutGame = new JMenuItem("About");
		aboutMenu.add(aboutGame);
		aboutGame.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				final JFrame aboutFrame = new JFrame("About");
				aboutFrame.setSize(new Dimension(600, 200));

				JLabel aboutLabel = new JLabel(
						"This simple game was created with the collaboration of Chen Wu, Alex Duh, and Zhiye Fei.",
						JLabel.CENTER);

				JButton playAgain = new JButton("Close");
				ActionListener play = new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						aboutFrame.dispose();
					}
				};
				playAgain.addActionListener(play);
				aboutFrame.add(playAgain, BorderLayout.SOUTH);
				aboutFrame.add(aboutLabel, BorderLayout.WEST);
				aboutFrame.setVisible(true);
				aboutFrame.setResizable(false);
			}

		});

		this.add(aboutMenu);
	}

	// ACTION LISTENER acts by taking in mouse clicks for the difficulties
	public ActionListener difficultyActionListener(final String difficulty) {

		ActionListener it = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				MineSweeperMenuBar.this.window.setDifficulty(difficulty);
				MineSweeperMenuBar.this.window.newGame();
			}
		};
		return it;
	}

}
