import java.util.Arrays;

public class Board {

	private int width;
	private int height;
	private int minesCount;
	private boolean[][] mines;
	private boolean[][] viewed;

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
		System.out.print("");
		int first = 0;
		int second = 0;
		for (int i = 0; i < (10*first + second); i++) {
			if (i % 10 == 0 && i != 0) {
				first++;
			}
			System.out.print(first);
		}
		System.out.println();

                for (int i = 0; i < (10*first + second); i++) {
                        System.out.print(second);
			second++;
                }
                System.out.println();

//(546 % (10^2)) / (10^(2-1))
		for (int row = 1; row < this.height - 1; row++) {
			for (int col = 1; col < this.width - 1; col++) {
				int surroundingCount = this.getSurroundingCount(row, col);
				if (this.viewed[row][col]) {
					System.out.print(surroundingCount);
				} else {
					System.out.print("-");
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
