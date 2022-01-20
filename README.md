# FP_NotYourAveragePatcho

## DA TEAM (in order of lsat nombre)
 - [Faiza Huda](https://www.github.com/FeiFiFoFaiza)
     - duckies: TruthfulTom, Hubert
 - [Jacob Ng](https://www.github.com/jng20)
     - duckies: PreGuac
 - [Gabriel Thompson](https://www.github.com/gthompson30)
     - duckies: Iggy

## Project Description
We created a version of the classic game Minesweeper that runs in the terminal.
 Our implementation is essentially the same game as the original Minesweeper, however, instead of tapping on tiles with your mouse, you type in the row and column of the mine.

**Notable features**:
- We use ANSI escape codes to color tiles appropriately, and Unicode characters to draw the boundary of the board
- We use the flood fill algorithm to clear open spaces, implemented recursively

**Topics from Semester Included (so far):
- Classes and instances
- Arrays
- ArrayLists
- Recursion, Iteration
- .equals()
- Scanner
- currentTimeMillis()

**Topics We Will Include (and how):
- Inheritance (different classes for difficulties)
- Interfaces (interface for all the difficulties)
- Sorting algorithms (to sort high scores)

## How-to-run
From the root directory, run:

`javac *java`

This will compile all required files. Then, to run, type:

`java Woo`

In each turn of the gameplay, a the board will displayed in the terminal, and you will be asked to choose a row and column, and either flag or reveal it. This process will repeat until either you are done the game, or you opened a mine. At this point, your score will be shown and added to the leaderboard.
