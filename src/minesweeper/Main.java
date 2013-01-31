package minesweeper;

/**
 * @author chenwu2, aduh2, zfei2
 */
import javax.swing.JFrame;
//Welcome to minesweeper!
public class Main {

	public static void main(String[] args) {
		MineWindow frame = new MineWindow();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
