/**********************************************************************
 *  readme template                                                   
 *  Final Project (Game 2048)
 **********************************************************************/

Name: Emily (Yiting) Shen
PennKey: shenyit
Hours to complete assignment (optional): 15

/**********************************************************************
 *  Please list all help, collaboration, and outside resources
 *  you used here. 
 *
 *  If you did not get any help in outside of TA office hours,
 *  and did not use any materials outside of the standard
 *  course materials and piazza, write the following statement below:
 *  "I did not receive any help outside of TA office hours.  I
 *  did not collaborate with anyone, and I did not use any
 *  resources beyond the standard course materials."
 **********************************************************************/

I did not receive any help outside of TA office hours.  I
did not collaborate with anyone, and I did not use any
resources beyond the standard course materials.

/**********************************************************************
 *  Instructions to run program.                 
 **********************************************************************/
 
Compile Board.java, Game2048.java and Tile.java, and then run Game2048.java
using "java Game2048" as the execution. In "View Running Program", a 
blank 2048 board with a single randomly generated Tile should appear. Click and 
start pressing awsd keys to play the game!

/**********************************************************************
 *  Description of each file and its purpose                 
 **********************************************************************/

Tile.java:
This class defines the coordinates and number stored of each Tile of the 2048 game. 
There are four methods in this class. The drawNumber() method draws the number 
stored in the Tile using PennDraw. The getValue() method gets the number stored in 
the Tile. The updateNum(int value) method changes the number stored in the Tile and 
the removeNumber() method removes the number displayed using PennDraw.

Board.java:
This class defines the board of the 2048 game using Tile.java. It creates a 
board consisting of a 4x4 2D array, which can store up to 16 Tiles. This class
contains 11 methods. The createBoard() method creates a new board using PennDraw.
The generateTile() method generates a random Tile in an empty space on the board.
The shiftUp(), shiftDown(), shiftRight() and shiftLeft() methods shift and
combine Tiles with the same number either up, down, right or left. The method 
addNumOfMoves() adds one to numOfMoves and numMoves() gets the current 
numOfMoves. largestScore() gets the current highest number on the board. 
isWon() and isLost() checks to see if the user has won or lost respectively. 

Game2048.java:
This class is the main file that utilises the methods defined in the Board class to 
create and animate the complete 2048 game. Depending on which wasd keys the user 
types, the class will shift the Tiles accordingly. This class also checks whether
the user has won or lost the game after each move. If the user wins (i.e. gets
2048), the program will stop and print a congratulatory message along with the
number of moves. If the user loses (i.e. has no move options left), the program
will stop and print a failure message along with the number of moves.
 
/**********************************************************************
 *  Any additional features you added beyond the assignment specifications                 
 **********************************************************************/
 
I did not include any additional features beyond the assignment 
specifications. However, I did assume that the user's move would always 
move at least one Tile or combine at least one pair of Tiles. Also, 
if there were three Tiles adjacent to each other with the same value,
I would always combine the two Tiles closer to the direction they were
moving (e.g. if Tiles were shifting up, I would combine upper Two tiles).

/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/

I did not encounter any serious problems, but this project was definitely
significantly harder than the previous homeworks, mainly because we 
had to think of the execution without any previous guidance. Thus, I spent
the most time planning this project than actually coding. 

/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/

I loved the assignment! It's definitely my favorite, especially since we
could be creative and think of the execution ourselves!