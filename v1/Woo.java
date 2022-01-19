public class Woo {

	public static void main(String[] args) {
		Text.clear();
		Board b = new Board(31, 20);
		b.generateMines();
		b.displayBoard();
	}

}
