import java.util.Scanner;
import java.util.ArrayList;

public class Woo {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		long finalScoreTime;
		int score;
		Board b = new Board(420, 69);
		ArrayList scoreboard = new ArrayList<Integer>();
		boolean playing = true;
		boolean onFirstMove = true;
		boolean running = true;

		Scanner sc = new Scanner(System.in);
		while(running){
		System.out.print("Which of the following difficulties would you like?\n" +
				 "  1. Easy\n" +
				 "  2. Medium\n" +
				 "  3. Difficult\n" +
				 "Type the number here: ");
		int difficulty = sc.nextInt();
		if (difficulty == 1)
			b = new EasyBoard();
		else if (difficulty == 2)
			b = new MediumBoard();
		else if (difficulty == 3)
			b = new DifficultBoard();
		else {
			System.out.println("\u001b[91;1mInput a real number you idiot. I will now self-destruct\u001b[0m");
			System.out.println(1/0);
		}

		while (playing) {
			b.displayBoard();

			System.out.print("Enter row and col to select, separated by space: ");
			int row = sc.nextInt();
			int col = sc.nextInt();

			if (onFirstMove) {
				b.generateMines(row, col);
				onFirstMove = false;
			}

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
					scoreboard.add(score);
				//	System.out.println("Scoredboard: " + scoreboard);
				 	playing = false;

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
				System.out.print("\nYAY! You did it. I knew you could :) \n Score = " + score);
				scoreboard.add(score);
				System.out.println();
			//	System.out.println("Scoredboard: " + scoreboard);
				playing = false;

			}
			System.out.print("Would you like to play again? (y/n)\n" +
					 "Type your answer here: ");
			


		}
		}
	}

}
