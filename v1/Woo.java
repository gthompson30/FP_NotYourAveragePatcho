import java.util.Scanner;

public class Woo {

	public static void main(String[] args) {
		boolean playing = true;
		Scanner sc = new Scanner(System.in);
		Board b = new Board(30, 20);
		b.generateMines();

		while (playing) {
			b.displayBoard();
	
			System.out.print("Enter row and col to select separated by space: ");
			int row = sc.nextInt();
			int col = sc.nextInt();
			b.selectTile(row, col);
			b.displayBoard();
		}
	}

}
