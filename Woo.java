//Modelled after the YoRPG Driver file

import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import javax.sound.midi.*;

public class Woo extends TerminallyIll {

                private long startTime;
		private long finalScoreTime;
               	private int score;
		private int row;
		private int col;
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
	
	        // awesome title card
                public static void intro() {
                        System.out.println(CLEAR_SCREEN);
                        String start = "                                        ";
                        for (int i = 0; i < 8; i++) {System.out.println();}
                        System.out.println(color(RED, BACKGROUND));
                        System.out.println(go(15,41) + "███╗   ███╗ ██╗███╗   ██╗██████╗ ███████╗██╗    ██╗██████╗ ██████╗ ██████╗ ██████╗ ██████╗ ");
                        System.out.println(go(16,41) + "████╗ ████║███║████╗  ██║╚════██╗██╔════╝██║    ██║╚════██╗╚════██╗██╔══██╗╚════██╗██╔══██╗");
                        System.out.println(go(17,41) + "██╔████╔██║╚██║██╔██╗ ██║ █████╔╝███████╗██║ █╗ ██║ █████╔╝ █████╔╝██████╔╝ █████╔╝██████╔╝");
                        System.out.println(go(18,41) + "██║╚██╔╝██║ ██║██║╚██╗██║ ╚═══██╗╚════██║██║███╗██║ ╚═══██╗ ╚═══██╗██╔═══╝  ╚═══██╗██╔══██╗");
                        System.out.println(go(19,41) + "██║ ╚═╝ ██║ ██║██║ ╚████║██████╔╝███████║╚███╔███╔╝██████╔╝██████╔╝██║     ██████╔╝██║  ██║");
                        System.out.println(go(20,41) + "╚═╝     ╚═╝ ╚═╝╚═╝  ╚═══╝╚═════╝ ╚══════╝ ╚══╝╚══╝ ╚═════╝ ╚═════╝ ╚═╝     ╚═════╝ ╚═╝  ╚═╝");
                        System.out.println(RESET);
                }
  
                
		public static void load() throws Exception {
			sequence = MidiSystem.getSequence(song);
			sequencer = MidiSystem.getSequencer();
			sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
		}
                
                // New program when type in java Woo
                public void newSession() {
                        onFirstMove = true;
                        b = new Board(60, 60, 0);

                        System.out.print(go(21, 50) + color(WHITE, BACKGROUND));
                        System.out.print("Which of the following difficulties would you like?");
                        System.out.print(go(22, 50) + color(WHITE, BACKGROUND, ITALICS));
                        System.out.print("  1. Easy");
                        System.out.print(go(23, 50));
                        System.out.print("  2. Medium");
                        System.out.print(go(24, 50));
                        System.out.print("  3. Difficult");
                        System.out.print(RESET + go(25, 50) + color(WHITE, BACKGROUND));
                        System.out.print("Type the number here: ");

			String difficulty = sc.next();
		
			if (difficulty.equals("1"))	//Our way of making sure the user inputs the right input. 
                                b = new EasyBoard();
                        else if (difficulty.equals("2"))
                                b = new MediumBoard();
                        else if (difficulty.equals("3"))
                                b = new DifficultBoard();
                        else {
                                System.out.println("\u001b[91;1mInput a valid option you idiot. I will now self-destruct\u001b[0m");
                                System.out.println(1 / 0);
                        }
                


                        startTime = System.currentTimeMillis();
                }

                
                //New game, NOT a new program
                public boolean playGame() {
                        b.displayBoard();

			while(true){
				System.out.print("Enter row and col to select, separated by space: ");
                        	try {
                                	row = sc.nextInt();
                                	col = sc.nextInt();
					break;
                        	} catch (Exception e){
					row = 0;
					col = 0;
					sc.nextLine();
                        	}

			}
		
			if (onFirstMove) {
                                b.generateMines(row, col);
                                onFirstMove = false;
                       	}


			
                        while (true) {
                                System.out.print("Would you like to open, or flag this tile (Type open/flag)? ");
                                String moveType = sc.next();
                                moveType = moveType.toLowerCase();
				System.out.println(moveType);

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


                
		//Gets triggered once a game is done-- either by winning or losing
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
                                System.out.println(CLEAR_SCREEN);
				newSession();
                        } else if (answer.equals("n")) {
				playing = false;
			} else {
                          	System.out.println("\u001b[91;1mI take that as a no. Goodbye\u001b[0m");
				playing = false;
                        }
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

