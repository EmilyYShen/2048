/**
 * Name: Emily (Yiting) Shen
 * Pennkey: shenyit
 * Execution: java Tile
 *
 * This class defines the features in each Tile of the 2048 game. The fields
 * include the coordinates and number stored in the Tile. There are four methods
 * in this class. The drawNumber() method draws the number stored in the Tile 
 * using PennDraw. The getValue() method gets the number stored in the Tile. The
 * updateNum(int value) method changes the number stored in the Tile and the 
 * removeNumber() method removes the number displayed using PennDraw.
**/
public class Tile {   
    // fields: coordinates (x, y) and number stored in the Tile
    private double x;
    private double y;
    private int number; 
    
    /**
    * Constructor: creates Tile with specified coordinates and number stored
    */
    public Tile(int x, int y, int number) {
        this.x = x;
        this.y = y;
        this.number = number;
    }
        
    /**
     * Inputs: nothing
     * Outputs: number drawn on 2048 board in specified position (void)
     * 
     * Description: This method draws the number stored in the Tile in the position
     * specified by the (x, y) coordinates
    */
    public void drawNumber() {
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(x, y, Integer.toString(number));
    }
    
    /**
     * Inputs: nothing
     * Outputs: integer of the number stored in the Tile
     * 
     * Description: This method returns the integer of the number stored 
     * in the Tile
    */
    public int getValue() {
        return number;
    }
    
    /**
     * Inputs: integer value to replace current number stored in the Tile
     * Outputs: integer of new number stored in the Tile
     * 
     * Description: This method updates the number stored in the Tile with the 
     * value specified and returns the new number stored in the Tile
    */
    public int updateNum(int value) {
        number = value;
        return number;
    }
    
    /**
     * Inputs: nothing
     * Outputs: removes number drawn on 2048 board (void)
     * 
     * Description: This method removes the drawing of the number stored in the
     * Tile on the 2048 board
    */
    public void removeNumber() {
        PennDraw.setPenColor(PennDraw.WHITE);
        PennDraw.filledSquare(x, y, 0.4);
    }
    
    public static void main(String[] args) {

    }
        
}