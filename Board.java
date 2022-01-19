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
		for (int i = 0 ; i < 10 ; i++) { System.out.println(); }
		System.out.print("     ");
		for (int i = 0; i < this.width; i++)
			System.out.print((int) (i / 10));
		System.out.print("\n     ");
		for (int i = 0; i < this.width; i++)
			System.out.print(i % 10);
                System.out.print("\n   ╔ ");
		for (int i = 0; i < this.width; i++)
			System.out.print("═");
		System.out.println();
		for (int row = 0; row < this.height; row++) {
			if (row < 10) {
				System.out.print(0);
			}
			System.out.print(row + " ║ ");
			for (int col = 0; col < this.width; col++) {
				int surroundingCount = this.getSurroundingCount(row, col);
				if (this.viewed[row][col]) {
					System.out.print(surroundingCount);
				} else {
					System.out.print("-");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	public int getSurroundingCount(int row, int col) {
		int count = 0;
		int minX = col - 1;
		int maxX = col + 1;
		int minY = row - 1;
		int maxY = row + 1;

		if (minY == -1) { minY = 0; }
		if (minX == -1) { minX = 0; }
		if (maxX == this.width) { maxX--; }
		if (maxY == this.height) { maxY--; }

		for (int dx = minX; dx <= maxX; dx++) {
			for (int dy = minY; dy <= maxY; dy++) {
				if (this.mines[dy][dx]) {
					count++;
				}
			}
		}
		return count;
	}

	public void selectTile(int row, int col) {
		this.viewed[row][col] = true;
	}

}
