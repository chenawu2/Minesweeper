package minesweeper;

// this class is an Empty tile that knows the number of mines surrounding itself

public class EmptyTile extends Tile {
	private int numMinesSurrounding;

	// constructor for tiles
	public EmptyTile(MineBoard board, int row, int col, int numMinesSurr) {
		super(board, row, col);
		this.numMinesSurrounding = numMinesSurr;

	}

	// EmptyTile objects are never mines, so this always returns false
	public boolean isMine() {
		return false;
	}

	// returns the number of surrounding mines
	public int getNumMinesSurrounding() {
		return this.numMinesSurrounding;
	}

	// performs a leftClick on the tile
	public void leftClick() {
		if (super.state.equals("revealed") || super.state.equals("flag")
				|| super.state.equals("question")) {
			return;
		} else {
			super.state = "revealed";
			this.setEnabled(false);
			// if no mines surrounding a tile, calls leftClick on surrounding
			// tiles
			if (this.numMinesSurrounding == 0) {
				for (int i = -1; i < 2; ++i) {
					for (int k = -1; k < 2; ++k) {
						Tile update = this.board.getTile(super.row + i,
								super.col + k);
						if (update != null) {
							update.leftClick();
						}
					}
				}
			} else {
				this.setText(this.numMinesSurrounding + "");
				return;
			}
		}
	}

	// performs shift left click on a revealed tile.
	public void shiftLeftClick() {

		int flagCount = 0;
		for (int i = -1; i < 2; ++i) {
			for (int k = -1; k < 2; ++k) {
				Tile update = this.board.getTile(super.row + i, super.col + k);
				if (update != null) {
					if (update.state.equals("flag")) {
						++flagCount;
					}
				}
			}
		}
		if (flagCount == this.numMinesSurrounding) {
			for (int i = -1; i < 2; ++i) {
				for (int k = -1; k < 2; ++k) {
					Tile update = this.board.getTile(super.row + i, super.col
							+ k);
					if (update != null) {
						update.leftClick();
					}
				}
			}
		}
	}

}