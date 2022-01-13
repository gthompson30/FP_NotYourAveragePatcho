import java.util.Arrays;

public class Board {

	int width;
	int height;
	int minesCount;
	boolean[][] mines;
	boolean[][] viewed;

	public Board(int newWidth, int newHeight) {
		this.width = newWidth;
		this.height = newHeight;
		this.mines = new boolean[newHeight][newWidth];
		this.viewed = new boolean[newHeight][newWidth];
		this.minesCount = (int) (newWidth * newHeight * 0.15);
	}

	public void generateMines() {
		int row, col;
		for (int i = 0; i < this.minesCount; ) {
			row = (int) (Math.random() * width);
			col = (int) (Math.random() * height);
			if (!this.mines[col][row]) {
				this.mines[col][row] = true;
				i++;
			}
		}
	}

	public void displayBoard() {
		for (int row = 1; row < this.height - 1; row++) {
			for (int col = 1; col < this.width - 1; col++) {
				int surroundingCount = this.getSurroundingCount(row, col);
				if (surroundingCount > -1) {//this.viewed[row][col]) {
					System.out.print(surroundingCount);
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

	public int getSurroundingCount(int row, int col) {
		int count = 0;
		for (int dx = col-1; dx <= col+1; dx++) {
			for (int dy = row-1; dy <= row+1; dy++) {
				if (this.mines[dy][dx]) {
					count++;
				}
			}
		}
		return count;
	}

}
