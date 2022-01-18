import java.util.Scanner;

public class Woo {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Board b = new Board(30, 20);
		b.displayBoard();

		System.out.print("Enter row and col to select separated by space: ");
		int row = sc.nextInt();
		int col = sc.nextInt();
		System.out.println(row + ", " + col);
	}

}
