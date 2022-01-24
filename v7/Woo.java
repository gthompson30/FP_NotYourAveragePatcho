import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import javax.sound.midi.*;

public class Woo extends TerminallyIll {

                private long startTime;
		private long finalScoreTime;
               	private int score;
		private String playerName;
                private Board b;
                private Scoreboard scboard = new Scoreboard();
		private static final int BACKGROUND = background(BLACK);
                private boolean onFirstMove;
                private Scanner sc = new Scanner(System.in);
		public boolean playing;

		private static Sequence sequence;
		private static Sequencer sequencer;
		private static File song = new File("song.mid");

		public Woo() {
			playing = true;
                	Scanner sc = new Scanner(System.in);
                	newSession();
        	}
  
                
                public void newSession() {
                        onFirstMove = true;
                        b = new Board(60, 60);

			System.out.print(go(20, 50) + color(WHITE, BACKGROUND));
                        System.out.print("Which of the following difficulties would you like?");
			System.out.print(go(21, 50) + color(WHITE, BACKGROUND, ITALICS));
			System.out.print("  1. Easy");
			System.out.print(go(22, 50));
			System.out.print("  2. Medium");
			System.out.print(go(23, 50));
			System.out.print("  3. Difficult");
			System.out.print(RESET + go(24, 50) + color(WHITE, BACKGROUND));
			System.out.print("Type the number here: ");
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

                	startTime = System.currentTimeMillis();
                }

                
                public boolean playGame() {
                	b.displayBoard();

                        System.out.print("Enter row and col to select, separated by space: ");
                        int row = sc.nextInt();
                        int col = sc.nextInt();

                        if (onFirstMove) {
                                b.generateMines(row, col);
                                onFirstMove = false;
                        }

			while (true) {
                        	System.out.print("Would you like to open, or flag this tile (Type open/flag)? ");
                        	String moveType = sc.next();
                        	moveType = moveType.toLowerCase();

                      		if (moveType.equals("open")) {
                                	return b.selectTile(row, col);
                        	} else if (moveType.equals("flag")) {
                                        b.flag(row, col);
                                        return true;
                        	} else {
                        		System.out.println("Invalid Option.\n");
                        	}
                	}
                }
                

                public void endGame() {	

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

			System.out.print("\nTime to own up to your score. \nWhat's your name? (10 chars max, USE UNDERSCORES NOT SPACES)\n > ");
			while (true) {
				playerName = sc.next();
				if (playerName.length() < 10){
					break;
				}
				System.out.println("\n\u001b[91;1mI SAID 10 CHARACTERS.\u001b[0m \nNow let's try this again:");

                        }
			scboard.add(playerName, score);
                        System.out.println("High Scores:\n " + scboard.topFive());
                        
                        System.out.print("Would you like to play again? (y/n) " );
                        String answer = sc.next().toLowerCase();
                        
                        if (answer.equals("y")) {
                                newSession();
                        } else if (answer.equals("n")) { 
				playing = false;
			} else {
                          	System.out.println("\u001b[91;1mI take that as a no. Goodbye\u001b[0m");
				playing = false;
                        }
		}

		public static void intro() {
			String start = "                                        ";
			for (int i = 0; i < 8; i++) {System.out.println();}
			System.out.println(color(RED, BACKGROUND));
			System.out.println(start+"███╗   ███╗ ██╗███╗   ██╗██████╗ ███████╗██╗    ██╗██████╗ ██████╗ ██████╗ ██████╗ ██████╗ ");
                        System.out.println(start+"████╗ ████║███║████╗  ██║╚════██╗██╔════╝██║    ██║╚════██╗╚════██╗██╔══██╗╚════██╗██╔══██╗");
                        System.out.println(start+"██╔████╔██║╚██║██╔██╗ ██║ █████╔╝███████╗██║ █╗ ██║ █████╔╝ █████╔╝██████╔╝ █████╔╝██████╔╝");
                        System.out.println(start+"██║╚██╔╝██║ ██║██║╚██╗██║ ╚═══██╗╚════██║██║███╗██║ ╚═══██╗ ╚═══██╗██╔═══╝  ╚═══██╗██╔══██╗");
                        System.out.println(start+"██║ ╚═╝ ██║ ██║██║ ╚████║██████╔╝███████║╚███╔███╔╝██████╔╝██████╔╝██║     ██████╔╝██║  ██║");
                        System.out.println(start+"╚═╝     ╚═╝ ╚═╝╚═╝  ╚═══╝╚═════╝ ╚══════╝ ╚══╝╚══╝ ╚═════╝ ╚═════╝ ╚═╝     ╚═════╝ ╚═╝  ╚═╝");
			System.out.println(RESET);
		}
                
		public static void load() throws Exception {
			sequence = MidiSystem.getSequence(song);
			sequencer = MidiSystem.getSequencer();
			sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
		}

                public static void main( String[] args ){
			try {
				load();
				sequencer.open();
				sequencer.setSequence(sequence);
				sequencer.start();
			} catch (Exception e) { System.out.println("Loading error!"); }

			System.out.print(color(WHITE, BACKGROUND));
			System.out.println(CLEAR_SCREEN);
			intro();
                	Woo game = new Woo();
                        while (game.playing ){
				if (!game.playGame() ) {
                        		game.endGame();
				}
			}
                        System.out.println( "Thanks for playing!" );
			sequencer.stop();
			sequencer.close();
                }
        }
