import java.util.Scanner;
import java.util.ArrayList;

public class Woo {

        public static void main(String[] args) {

                boolean running = true;

                long startTime;
                long finalScoreTime;
                int score;
                Board b;
                Scoreboard scboard;
                boolean playing;
                boolean onFirstMove;

                Scanner sc = new Scanner(System.in);

                while (running) {

                        startTime = System.currentTimeMillis();
                        scboard = new Scoreboard();
                        playing = true;
                        onFirstMove = true;
                        running = true;
                        b = new Board(60, 60);

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
                                System.out.println(1 / 0);
                        }

                        playing = true;

                        while (playing) {

                                b.displayBoard();

                                System.out.print("Enter row and col to select, separated by space: ");
                                int row = sc.nextInt();
                                int col = sc.nextInt();

                                if (onFirstMove) {
                                        b.generateMines(row, col);
                                        System.out.println("generated mines!! " + b.minesCount);
                                        onFirstMove = false;
                                }

                                System.out.print("Would you like to open, or flag this tile (Type open/flag)? ");
                                String moveType = sc.next();
                                moveType = moveType.toLowerCase();

                                if (moveType.equals("open")) {
                                        b.selectTile(row, col);

                                        if (b.isAMine(row, col)) {
                                                playing = false;

                                        }

                                } else if (moveType.equals("flag")) {
                                        b.flag(row, col);
                                }

                        }

                        finalScoreTime = System.currentTimeMillis() - startTime;
                        if (finalScoreTime > 999_999) {
                                finalScoreTime = 999_999;
                        }
                        score = ((int)((b.countViewed() * 1_000_000.0) - finalScoreTime));

                        if (b.hasWon()) {
                                System.out.print("\nYAY! You did it. I knew you could :) \nScore = " + score);
                        } else {
                                System.out.print("\nAHA! You stepped on a mine! Game over! \nScore = " + score);
                        }

                        scboard.add(score);
                        System.out.println();
                        System.out.println("High Score: " + scboard.hiScore());
                        playing = false;
                        System.out.print("Would you like to play again? (y/n)\n" +
                                "Type your answer here: ");
                        String answer = sc.next().toLowerCase();
                        if (answer.equals("y")) {
                                running = true;
                        } else if (answer.equals("n"))
                                running = false;
                        else {
                                System.out.println("\u001b[91;1mI take that as a no. Goodbye\u001b[0m");
                                running = false;
                        }
                }
        }

}
