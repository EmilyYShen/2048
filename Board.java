/**
 * Name: Emily (Yiting) Shen
 * Pennkey: shenyit
 * Execution: java Board
 *
 * This class defines the board of the 2048 game using Tile.java. It creates a 
 * board consisting of a 4x4 2D array, which can store up to 16 Tiles. This class
 * contains 11 methods. The createBoard() method creates a new board using PennDraw.
 * The generateTile() method generates a random Tile in an empty space on the board.
 * The shiftUp(), shiftDown(), shiftRight() and shiftLeft() methods shift and
 * combine Tiles with the same number either up, down, right or left. The method 
 * addNumOfMoves() adds one to numOfMoves and numMoves() gets the current 
 * numOfMoves. largestScore() gets the current highest number on the board. 
 * isWon() and isLost() checks to see if the user has won or lost respectively. 
 * 
**/
public class Board {
    
    // fields: create 2048 board and store current score and number of moves
    private Tile[][] board;
    private int score;
    private int numOfMoves;
    
    /**
    * Constructor: create board of 4x4 dimension and initialize score and 
    * number of moves as 0
    */
    public Board() {
        this.board = new Tile[4][4];
        this.score = 0;
        this.numOfMoves = 0;
    }
    
    /**
     * Inputs: nothing
     * Outputs: creating a blank 4x4 square board that represents the 2048 
     * board (void)
     * 
     * Description: This method creates a 4x4 blank square board using PennDraw
    */
    public void createBoard() {
        PennDraw.setPenColor(PennDraw.BLACK);
        
        // draw the 4x4 blank square board
        PennDraw.setScale(0, 5);
        PennDraw.setPenRadius(0.01);
        PennDraw.square(2.5, 2.5, 2);
        PennDraw.line(1.5, 0.5, 1.5, 4.5);
        PennDraw.line(2.5, 0.5, 2.5, 4.5);
        PennDraw.line(3.5, 0.5, 3.5, 4.5);
        PennDraw.line(0.5, 1.5, 4.5, 1.5);
        PennDraw.line(0.5, 2.5, 4.5, 2.5);
        PennDraw.line(0.5, 3.5, 4.5, 3.5);
        
        // initialise a Tile for each space in the 2D board array
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                board[row][col] = new Tile(row + 1, col + 1, 0);
            }
        }
    }
    
    /**
     * Inputs: nothing
     * Outputs: random number generated in a random empty place on the board (void)
     * 
     * Description: This method generates a random Tile either storing the
     * number 2 or 4 in a random empty space of the 2048 board
    */
    public void generateTile() {
        int number = 0;
        
        // generate either a 2 or 4 randomly
        if (Math.random() < 0.5) {
            number = 2;
        } else {
            number = 4;
        }
        
        // generate the random position on the board to place the number
        int x = (int) (Math.random() * 4);
        int y = (int) (Math.random() * 4);
        
        /**
         * check if the position on the board already contains a number, and if
         * it does, regenerate a random position until that position is an empty
         * space
         * */
        while (board[x][y].getValue() != 0) {
            x = (int) (Math.random() * 4);
            y = (int) (Math.random() * 4);
        }        
        
        // replace the empty Tile with the random number on that Tile
        if (board[x][y].getValue() == 0) {
            board[x][y].updateNum(number);
        }
        
        // draw the randomly generated number in the randomly generated position
        PennDraw.setFontSize(40);
        Tile z = new Tile(x + 1, y + 1, number);
        z.drawNumber();
        numOfMoves++;
    }
    
    /**
     * Inputs: nothing
     * Outputs: shifting all Tiles up and combining Tiles storing the same 
     * number (void)
     * 
     * Description: This method first shifts all the Tiles up to fill empty 
     * spaces, and then combines vertically adjacent Tiles storing the same
     * number. It then checks if there are any blank spaces above filled spaces,
     * and fills those blank spaces in to fully shift all Tiles up, including
     * combined Tiles. 
    */
    public void shiftUp() {       
        // shift all Tiles up without combining values yet
        int rowNum = 3;
        for (int col = 0; col < 4; col++) {
            for (int row = 3; row >= 0; row--) {
                if (board[col][row].getValue() != 0) {
                    
                    // store temporary value of board[row][col] to shift up
                    int temp = board[col][row].getValue();
                    board[col][row].updateNum(0);
                    board[col][row].removeNumber();
                    
                    /**
                     * shift value of board[row][col] up or if cannot shift up,
                     * remain in the same x, y coordinate position
                     * */                    
                    board[col][rowNum].updateNum(temp);
                    board[col][rowNum].drawNumber();
                    rowNum--;
                }                
            }
            // repeat this process for each column
            rowNum = 3;
            
            /** 
             * combine vertically adjacent Tiles that store the same number 
             * (if 3 adjacent tiles with same number, only combine the upper two
             * tiles)
             * */
            for (int row = 2; row >= 0; row--) {
                if (board[col][row].getValue() == 
                    board[col][row + 1].getValue() && 
                    board[col][row].getValue() != 0) { 
                    
                    /**
                     * combine two Tiles at the position of the upper Tile and 
                     * update value by 2 times
                     * */
                    board[col][row + 1].removeNumber();
                    board[col][row + 1].updateNum(
                        board[col][row].getValue() * 2);
                    board[col][row + 1].drawNumber();
                    
                    // remove lower Tile
                    board[col][row].updateNum(0);
                    board[col][row].removeNumber();
                    row++;
                 }
             }
            
            /**
             * check if there are any empty spaces above Tiles storing a number,
             * and shift Tiles up if this is true
             * */
            for (int row = 3; row > 0; row--) {
                if (board[col][row].getValue() == 0 && 
                    board[col][row - 1].getValue() != 0) {
                    
                    // shift Tiles up if space above is empty
                    board[col][row].updateNum(
                        board[col][row - 1].getValue());
                    board[col][row].drawNumber();
                    
                    board[col][row - 1].updateNum(0);
                    board[col][row - 1].removeNumber();
                }
            }
            
        }        
    }
    
    /**
     * Inputs: nothing
     * Outputs: shifting all Tiles down and combining Tiles storing the same 
     * number (void)
     * 
     * Description: This method first shifts all the Tiles down to fill empty 
     * spaces, and then combines vertically adjacent Tiles storing the same
     * number. It then checks if there are any blank spaces below filled spaces,
     * and fills those blank spaces in to fully shift all Tiles down, including
     * combined Tiles. 
    */
    public void shiftDown() {        
        // shift all Tiles down without combining values yet
        int rowNum = 0;
        for (int col = 0; col < 4; col++) {
            for (int row = 0; row < 4; row++) {
                if (board[col][row].getValue() != 0) {
                    
                    // store temporary value of board[row][col] to shift down
                    int temp = board[col][row].getValue();
                    board[col][row].updateNum(0);
                    board[col][row].removeNumber();
                    
                    /**
                     * shift value of board[row][col] down or if cannot shift down,
                     * remain in the same x, y coordinate position
                     * */  
                    board[col][rowNum].updateNum(temp);
                    board[col][rowNum].drawNumber();
                    rowNum++;
                }
            }
            // repeat this process for each column
            rowNum = 0;   
            
            /** 
             * combine vertically adjacent Tiles that store the same number 
             * (if 3 adjacent tiles with same number, only combine the lower two
             * tiles)
             * */
            for (int row = 0; row < 3; row++) {
                if (board[col][row].getValue() == 
                    board[col][row + 1].getValue() &&  
                    board[col][row].getValue() != 0) { 
                    
                    /**
                     * combine two Tiles at the position of the lower Tile and 
                     * update value by 2 times
                     * */
                    board[col][row].removeNumber();
                    board[col][row].updateNum(
                        board[col][row].getValue() * 2);
                    board[col][row].drawNumber();
                    
                    // remove upper Tile
                    board[col][row + 1].updateNum(0);
                    board[col][row + 1].removeNumber();
                    row++;
                 }
             }
            
            /**
             * check if there are any empty spaces below Tiles storing a number,
             * and shift Tiles down if this is true
             * */
            for (int row = 0; row < 3; row++) {
                if (board[col][row].getValue() == 0 && 
                    board[col][row + 1].getValue() != 0) {
                    
                    // shift Tiles down if space below is empty
                    board[col][row].updateNum(
                        board[col][row + 1].getValue());
                    board[col][row].drawNumber();
                    
                    board[col][row + 1].updateNum(0);
                    board[col][row + 1].removeNumber();
                }
            }            
        }        
    }
    
    /**
     * Inputs: nothing
     * Outputs: shifting all Tiles to the right and combining Tiles storing the 
     * same number (void)
     * 
     * Description: This method first shifts all the Tiles to the right to fill 
     * empty spaces, and then combines horizontally adjacent Tiles storing the same
     * number. It then checks if there are any blank spaces to the right of filled 
     * spaces, and fills those blank spaces in to fully shift all Tiles to the 
     * right, including combined Tiles. 
    */
    public void shiftRight() {               
        // shift all Tiles to the right without combining values yet
        int colNum = 3;
        for (int row = 0; row < 4; row++) {
            for (int col = 3; col >= 0; col--) {
                if (board[col][row].getValue() != 0) {
                    
                    // store temporary value of board[row][col] to shift right
                    int temp = board[col][row].getValue();
                    board[col][row].updateNum(0);
                    board[col][row].removeNumber();
                    
                    /**
                     * shift value of board[row][col] right or if cannot shift 
                     * right, remain in the same x, y coordinate position
                     * */  
                    board[colNum][row].updateNum(temp);
                    board[colNum][row].drawNumber();
                    colNum--;
                }
            }
            // repeat this process for each row
            colNum = 3; 
            
            /** 
             * combine horizontally adjacent Tiles that store the same number 
             * (if 3 adjacent tiles with same number, only combine the rightmost two
             * tiles)
             * */
            for (int col = 2; col >= 0; col--) {
                if (board[col][row].getValue() == 
                    board[col + 1][row].getValue() &&  
                    board[col][row].getValue() != 0) { 
                    
                    /**
                     * combine two Tiles at the position of the rightmost Tile and 
                     * update value by 2 times
                     * */
                    board[col + 1][row].removeNumber();
                    board[col + 1][row].updateNum(
                        board[col][row].getValue() * 2);
                    board[col + 1][row].drawNumber();
                    
                    // remove leftmost Tile
                    board[col][row].updateNum(0);
                    board[col][row].removeNumber();
                    col++;
                 }
             }
            
            /**
             * check if there are any empty spaces to the right of Tiles storing a 
             * number, and shift Tiles to the right if this is true
             * */
            for (int col = 3; col > 0; col--) {
                if (board[col][row].getValue() == 0 && 
                    board[col - 1][row].getValue() != 0) {
                    
                    // shift Tiles to the right if space to the right is empty
                    board[col][row].updateNum(
                        board[col - 1][row].getValue());
                    board[col][row].drawNumber();
                    
                    board[col - 1][row].updateNum(0);
                    board[col - 1][row].removeNumber();
                }
            }
        }    
    }
    
    /**
     * Inputs: nothing
     * Outputs: shifting all Tiles to the left and combining Tiles storing the 
     * same number (void)
     * 
     * Description: This method first shifts all the Tiles to the left to fill 
     * empty spaces, and then combines horizontally adjacent Tiles storing the same
     * number. It then checks if there are any blank spaces to the left of filled 
     * spaces, and fills those blank spaces in to fully shift all Tiles to the 
     * left, including combined Tiles. 
    */
    public void shiftLeft() {               
        // shift all Tiles to the left without combining values yet
        int colNum = 0;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (board[col][row].getValue() != 0) {
                    
                    // store temporary value of board[row][col] to shift left
                    int temp = board[col][row].getValue();
                    board[col][row].updateNum(0);
                    board[col][row].removeNumber();
                    
                    /**
                     * shift value of board[row][col] left or if cannot shift 
                     * left, remain in the same x, y coordinate position
                     * */ 
                    board[colNum][row].updateNum(temp);
                    board[colNum][row].drawNumber();
                    colNum++;
                }
            }
            // repeat this process for each row
            colNum = 0;
            
            /** 
             * combine horizontally adjacent Tiles that store the same number 
             * (if 3 adjacent tiles with same number, only combine the leftmost two
             * tiles)
             * */
            for (int col = 0; col < 3; col++) {
                if (board[col][row].getValue() == 
                    board[col + 1][row].getValue() &&  
                    board[col][row].getValue() != 0) { 
                    
                    /**
                     * combine two Tiles at the position of the leftmost Tile and 
                     * update value by 2 times
                     * */
                    board[col][row].removeNumber();
                    board[col][row].updateNum(
                        board[col][row].getValue() * 2);
                    board[col][row].drawNumber();
                    
                    // remove rightmost Tile
                    board[col + 1][row].updateNum(0);
                    board[col + 1][row].removeNumber();
                    col++;
                 }
             }
            
            /**
             * check if there are any empty spaces to the left of Tiles storing a 
             * number, and shift Tiles to the left if this is true
             * */
            for (int col = 0; col < 3; col++) {
                if (board[col][row].getValue() == 0 && 
                    board[col + 1][row].getValue() != 0) {
                    
                    // shift Tiles to the left if space to the left is empty
                    board[col][row].updateNum(
                        board[col + 1][row].getValue());
                    board[col][row].drawNumber();
                    
                    board[col + 1][row].updateNum(0);
                    board[col + 1][row].removeNumber();
                }
            }           
        }               
    }
    
    /**
     * Inputs: nothing
     * Outputs: adds additional move to total number of moves (void)
     * 
     * Description: This method adds an additional move to the current 
     * total number of moves
    */
    public void addNumOfMoves() {
        numOfMoves++;
    }
    
    /**
     * Inputs: nothing
     * Outputs: integer representing current number of moves
     * 
     * Description: This method returns the current total number of moves the
     * user has made
    */
    public int numMoves() {
        return numOfMoves;
    }
    
    /**
     * Inputs: nothing
     * Outputs: integer of the largest score on the board
     * 
     * Description: This method returns the current largest score on the board
    */
    public int largestScore() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col].getValue() > score) {
                    score = board[row][col].getValue();
                }
            }                  
        }
        return score;
    }
    
    /**
     * Inputs: nothing
     * Outputs: boolean of whether the user has won or not
     * 
     * Description: This method checks if the player has won (i.e. has a score
     * of 2048 or greater)
    */
    public boolean isWon() {
        if (score == 2048) {
            return true;
        }
        return false;
    }
    
    /**
     * Inputs: nothing
     * Outputs: boolean of whether the user has lost or not
     * 
     * Description: This method checks if the player has lost (i.e. the board
     * is filled with no possible moves)
    */
    public boolean isLost() {
        int total = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j].getValue() > 0) {
                    // check if corner Tiles are able to move
                    if (i == 0 && j == 0) {
                        if (board[i][j].getValue() != board[i + 1][j].getValue() &&
                            board[i][j].getValue() != board[i][j + 1].getValue())
                        {
                            total++;
                        }
                    } else if (i == 3 && j == 0) {
                        if (board[i][j].getValue() != board[i - 1][j].getValue() &&
                            board[i][j].getValue() != board[i][j + 1].getValue())
                        {
                            total++;
                        }
                    } else if (i == 0 && j == 3) {
                        if (board[i][j].getValue() != board[i + 1][j].getValue() &&
                            board[i][j].getValue() != board[i][j - 1].getValue())
                        {
                            total++;
                        }
                    } else if (i == 3 && j == 3) {
                        if (board[i][j].getValue() != board[i - 1][j].getValue() &&
                            board[i][j].getValue() != board[i][j - 1].getValue())
                        {
                            total++;
                        }
                        
                    // check if border Tiles are able to move 
                    } else if (i == 0 && (j == 2 || j == 1)) {
                        if (board[i][j].getValue() != board[i + 1][j].getValue() &&
                            board[i][j].getValue() != board[i][j - 1].getValue() &&
                            board[i][j].getValue() != board[i][j + 1].getValue())
                        {
                            total++;
                        }
                    } else if (i == 3 && (j == 2 || j == 1)) {
                        if (board[i][j].getValue() != board[i - 1][j].getValue() &&
                            board[i][j].getValue() != board[i][j - 1].getValue() &&
                            board[i][j].getValue() != board[i][j + 1].getValue())
                        {
                            total++;
                        }
                    } else if (j == 0 && (i == 2 || i == 1)) {
                        if (board[i][j].getValue() != board[i][j + 1].getValue() &&
                            board[i][j].getValue() != board[i - 1][j].getValue() &&
                            board[i][j].getValue() != board[i + 1][j].getValue())
                        {
                            total++;
                        }
                    } else if (j == 3 && (i == 2 || i == 1)) {
                        if (board[i][j].getValue() != board[i][j - 1].getValue() &&
                            board[i][j].getValue() != board[i - 1][j].getValue() &&
                            board[i][j].getValue() != board[i + 1][j].getValue())
                        {
                            total++;
                        }
                    } else {
                        // check if middle Tiles are able to move
                        if (board[i][j].getValue() != board[i - 1][j].getValue() &&
                            board[i][j].getValue() != board[i + 1][j].getValue() &&
                            board[i][j].getValue() != board[i][j - 1].getValue() &&
                            board[i][j].getValue() != board[i][j + 1].getValue())
                        {
                            total++;
                        }
                    }
                }
            }
        }
        if (total == 16) {
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
//         Board x = new Board();
//         x.createBoard();
//         x.board[0][1].updateNum(6);
//         x.board[0][1].drawNumber();
        
//         x.board[0][3].updateNum(6);
//         x.board[0][3].drawNumber();
        
//         x.board[2][0].updateNum(4);
//         x.board[2][0].drawNumber();
        
//         x.board[1][0].updateNum(4);
//         x.board[1][0].drawNumber();
        
//         x.board[0][0].updateNum(4);
//         x.board[0][0].drawNumber();

//         x.shiftRight();
    }
    
}