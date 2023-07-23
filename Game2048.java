/**
 * Name: Emily (Yiting) Shen
 * Pennkey: shenyit
 * Execution: java Game2048
 *
 * This class utilises the methods defined in the Board class to create and animate
 * the complete 2048 game. It will always generate a new board, and then, depending
 * on which wasd keys the user types, will shift the Tiles accordingly. 'w' 
 * shifts the Tiles up, 's' shifts the Tiles down, 'a' shifts the Tiles to the
 * left and 'd' shifts the Tiles to the right. This class will also check whether
 * the user has won or lost the game after each move. If the user wins (i.e. gets
 * 2048), the program will stop and print a congratulatory message along with the
 * number of moves. If the user loses (i.e. has no move options left), the program
 * will stop and print a failure message along with the number of moves.
**/
public class Game2048 {

    public static void main(String[] args) {
        // create new 2048 board
        Board x = new Board();
        x.createBoard();
        
        // generate first random tile
        x.generateTile();
        
        PennDraw.enableAnimation(30);
        
        // allow user to input up, down, left or right move using wasd keys
        while (true) {
            
            if (PennDraw.hasNextKeyTyped()) {  
                // shift Tiles up if user types 'w'
                char key = PennDraw.nextKeyTyped();
                if (key == 'w') {
                    x.shiftUp();
                    x.generateTile();
                    x.addNumOfMoves();
                } else if (key == 's') {
                    // shift Tiles down if user types 's'
                    x.shiftDown(); 
                    x.generateTile(); 
                    x.addNumOfMoves();
                } else if (key == 'd') {
                    // shift Tiles to the right if user types 'd'
                    x.shiftRight();
                    x.generateTile(); 
                    x.addNumOfMoves();
                } else if (key == 'a') {
                    // shift Tiles to the left if the user types 'a'
                    x.shiftLeft();
                    x.generateTile(); 
                    x.addNumOfMoves();                   
                }                                 
            }

            /**
            * keep track of the largest score
            * */            
            x.largestScore();
            
            // break out of while loop if user lost or won
            if (x.isWon() == true || x.isLost() == true) {
                break;
            }            

            PennDraw.advance();
        }        
        
        PennDraw.disableAnimation(); 

        /**
         * create victory or defeat message and display the number of moves
         * (subtract 1 to account for extra move counted as the first 
         * generateTile(), where the user did not press any keys)
         * */
        if (x.isWon() == true) {
            // victory message
            PennDraw.clear();
            PennDraw.text(2.5, 3, "Congratulations, you win!");
            PennDraw.text(2.5, 2, "No. of moves: " + 
                          Integer.toString(x.numMoves() - 1));
        } else {
            // defeat message
            PennDraw.clear();
            PennDraw.text(2.5, 3, "Sorry, you lost :(");
            PennDraw.text(2.5, 2, "No. of moves: " + 
                          Integer.toString(x.numMoves() - 1));
        }
    }
    
}