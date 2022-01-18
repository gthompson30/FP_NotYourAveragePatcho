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


	public static void print2( int[][] a ){
	    for(int[] i : a) {
     		 for(int j : i){
         		 System.out.print(j);
     		 }
     	    	 System.out.println();
   	    }

  	}

	public void displayBoard() {
		int topNumbers [][] = new int[2][width];
		int remainder = (width % 10) - 2;
		int numtens = width / 10  ;
		int i;
		int ifdk = 0;
		int p;
		int ifdk2 = 0;
		int r;
		int v;

		System.out.println(remainder);
		System.out.println(numtens);

		for (i = 0; i < numtens; i++){
			for (int j = i*10; j < i * 10 + 10; j++){
				topNumbers[0][j] = i;
				ifdk++;
			}
		}

//		System.out.println(ifdk);

		for ( r = ifdk; r < (ifdk + remainder); r++){
			topNumbers[0][r]= numtens;
		}

		for (p = 0; p < numtens; p++){
	              for (int q = p*10; q < p * 10 + 10; q++){
                                topNumbers[1][q] = q % 10;
				ifdk2++;
                        }
                }


		for ( v = ifdk2; v < (ifdk2 + remainder); v++){
                        topNumbers[1][v]= v % 10;
                }

		print2(topNumbers);

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
