import java.util.Scanner;
import java.util.ArrayList;

public class Woo {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		long finalScoreTime;
		int score;
		ArrayList scoreboard = new ArrayList<Integer>();
		boolean playing = true;
		Scanner sc = new Scanner(System.in);
		Board b = new Board(10, 6);
		b.generateMines();

		while (playing) {
			b.displayBoard();

			System.out.print("Enter row and col to select, separated by space: ");
			int row = sc.nextInt();
			int col = sc.nextInt();

			System.out.print("Would you like to open, or flag this tile (Type open/flag)? ");
			String moveType = sc.next();
			moveType = moveType.toLowerCase();

			if (moveType.equals("open")) {
				b.selectTile(row, col);

                        	if (b.isAMine(row, col)) {
					finalScoreTime = System.currentTimeMillis() - startTime;
					if (finalScoreTime > 999_999){
						finalScoreTime = 999_999;
					}
					score = ((int)((b.countViewed() * 1_000_000.0) - finalScoreTime));
                                	b.showMines();
                        	        b.displayBoard();
//                	                System.out.print("\nAHA! You stepped on a mine! Game over! \n Score = " + finalScoreTime);
                	                System.out.print("\nAHA! You stepped on a mine! Game over! \n Score = " + score);
					System.out.println();
        	                        playing = false;
					scoreboard.add(score);
					System.out.println("Scoredboard: " + scoreboard);
	                        }

			} else if (moveType.equals("flag")) {
				b.flag(row, col);
			}

			if (b.hasWon()){
				finalScoreTime = System.currentTimeMillis() - startTime;
				if (finalScoreTime > 999_999){
					finalScoreTime = 999_999;
				}
				score = ((int)((b.countViewed() * 1_000_000.0) - finalScoreTime));
//				System.out.print("\nYAY! You did it. I knew you could :) \n Score = " + finalScoreTime);
				System.out.print("\nYAY! You did it. I knew you could :) \n Score = " + score);
				playing = false;
				scoreboard.add(score);
				System.out.println();
				System.out.println("Scoredboard: " + scoreboard);

			}
		}
	}

}
