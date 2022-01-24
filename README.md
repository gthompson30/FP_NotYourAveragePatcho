# FP_NotYourAveragePatcho

## DA TEAM (in order of lsat nombre)
 - [Faiza Huda](https://www.github.com/FeiFiFoFaiza)
     - duckies: TruthfulTom, Hubert
 - [Jacob Ng](https://www.github.com/jng20)
     - duckies: PreGuac
 - [Gabriel Thompson](https://www.github.com/gthompson30)
     - duckies: Iggy

### Major thanks
 - [Team FrogHats](https://github.com/afaruque30/FrogHats_FP) for their MidiPlayer implementation
 - [Perrey and Kingsley](https://en.m.wikipedia.org/wiki/Perrey_and_Kingsley) for their song "The Little Man from Mars" (MIDI arrangement by Gabriel)

## Project Description
We created a version of the classic game Minesweeper that runs in the terminal.
 Our implementation is essentially the same game as the original Minesweeper, however, instead of tapping on tiles with your mouse, you type in the row and column of the mine.

**Notable features**:
- We used ANSI escape codes to color tiles appropriately and format and refresh the screen, and we used Unicode characters to draw the boundary of the board
- We used the flood fill algorithm to clear open spaces, implemented recursively
- We used Java's MidiPlayer to play music throughout the game (see "thanks" section for music credits)

**Topics from Semester Included**:
- Classes and instances
- Arrays
- ArrayLists
- Recursion, Iteration
- .equals()
- Scanner
- currentTimeMillis()
- Inheritance (different classes for difficulties)
- Sorting algorithms (to sort high scores)

## How-to-run
From the root directory, run:

`javac *java`

This will compile all required files. Before running, it is highly recommended that you use full screen for your terminal, or have your resolution set to >=170x80. To run, type:

`java Woo`

First, the user will be prompted to select a difficulty. Beware failure to comply may result in self destruct sequence. After selecting a difficulty, a board shall be generated.

In each turn of the gameplay, a the board will be displayed in the terminal, and you will be asked to choose a row and column, and either flag or reveal it. This process will repeat until either you are done with the game, or you opened a mine. At this point, you will be prompted to input a name the follows our requirements. Then, your score will be shown and added to the leaderboard. Finally, you will be asked to play again.

For those who don't know the rules to Minesweeper, check out this fantastic explanation: [*Minesweeper Rules*](https://www.ducksters.com/games/minesweeper.php#:~:text=The%20numbers%20on%20the%20board,empty%20spaces%20to%20win%20Minesweeper.)
