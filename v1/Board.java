import java.util.*;

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


	public static void print2( int[][] a ){
	    for(int[] i : a) {
     		 for(int j : i){
         		 System.out.print(j);
     		 }
     	    	 System.out.println();
   	    }

  	}

	public void displayBoard() {
		//column labelling
		// top row
		int onesCtr = 0;
		int firstDig = 0;
		Text.go(1, 3);
		//System.out.println("This is the real start");
		for (int i = 0; i < this.width - 2; i++){
			if ( onesCtr > 9){
				onesCtr = 0;
				firstDig++;
			}
			System.out.print(firstDig);
			onesCtr++;
		}
		System.out.print("\n");

		// bottom row
		Text.go(2, 3);
		onesCtr = 0; //resetting to zero
		for (int i = 0; i < this.width - 2; i++){
			if (onesCtr > 9){
				onesCtr = 0;
			}
			System.out.print(onesCtr);
			onesCtr++;
		}

System.out.print("\n");

		for (int row = 1; row < this.height - 1; row++) {
			Text.go(2 + row, 1);

			if (row < 11){
				System.out.print("0");
			}

			System.out.print(row - 1);
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
