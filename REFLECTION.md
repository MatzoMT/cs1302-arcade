# Reflection

Add to this file to satisfy the "Reflections in Deadline Submissions" non-functional
requirement for this project. Please keep this document organized using Markdown. If you
click on this file in your team's GitHub repository website, then you will see
that the Markdown is transformed into nice looking HTML.

Here is a sample entry (delete this line; also: feel free to copy/paste/modify):

## Deadline 1 - TUE 2020-12-01 (Dec 01)

1. Work that has been completed for this deadline included creating a Piece.java class that
   sets up certain attributes found in all chess pieces. Furthermore, each different chess
   piece (ex: King, Rook) has been created and extends the Piece class, in addition to having
   certain variables and methods of their own depending on the type of piece. A Square class was
   also created that represents an individual square of a chess board and its current state. This
   class will be used to help analyze events such as whether the King is in check.
2. For the next deadline, it is imperative that I complete all aspects of the Chess game and make
   my code fully playable, though I am not expecting to completely implement a GUI for the chess
   game yet. It should be possible to play the Chess game through other means, however, such as
   through the terminal as part of a testing phase before I move on to GUI implementation.
3. I anticipate that I will have a lot of branches regarding developing each individual Chess
   Piece. As it is likely that I will encounter bugs not previously addressed or may have forgotten
   a component in one of the chess piece classes, this will likely mean that I will branch a lot in
   this aspect in order to return to previous code to revise them.

## Deadline 2 - TUE 2020-12-08 (Dec 08)

1. Work that has been completed for this deadline included finishing all technical aspect of each
   piece class, as well as creating a tester class to test the movement and capturing ability of
   each piece that has not yet been finished. This deadline has also seen the creation of a
   ChessBoard class representing the state of the board in terms of piece location as well as a
   ChessGame class defining key methods that form the gameplay, such as methods checking for check
   positions. The work for this deadline differed from the first deadline plan in that I was unable
   to complete all aspects of gameplay, and as a result, I have not yet started on GUI programming,
   even though that was the intended goal for this deadline.
2. For the next deadline, it is imperative that all parts of my chess gameplay are complete and that
   I have a working GUI for my chess application.
3. The main issues I have encountered is being able to check whether the king is in check, since the
   only method I can think about has been incredibly difficult to implement. Another issue is dealing
   with JavaFX since I have only experimented very briefly with JavaFX and the starter code since the
   rest of my chess game is incomplete.

    ## Deadline 3 - WED 2020-12-16 (Dec 16)

i. Work completed since deadline 3 largely included setting up all GUI components of the chess game
   and fixing any gameplay methods for the chess game. The last significant change was allowing the
   game to recognize a checkmated scenario. GUI work included setting up pieces, the chess board,
   other user interface items such as scoring, an animated title screen, and other pop up messages
   such as showing which user won a game.
ii. I have been able to fix the check recognition issue mentioned in checkpoint 2, though at the time
   of this writing, I am having trouble getting the program to recognize when a game has a checkmate.
   The last issue was resolved largely by being able to see whether an opposite color piece is
   attacking an enemy king by adding new methods to my ChessGame.java class.
iii. Graders should check for extra credit opportunity #1 where I animated the "Chess" title for my
   application.