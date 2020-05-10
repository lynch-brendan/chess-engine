import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The Queen class implements the Piece interface, and 
 * handles the functionality of a queen chess piece.
 */
public class Queen implements Piece{

    //Fields
    private int x;
    private int y;
    private boolean isWhite;
    private Board board;
    
    //Constructor
    public Queen(int x, int y, boolean isWhite, Board board) {
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
        this.board = board;
    }
    
    //This function moves the queen in accordance with its position on
    //the board.
    public void move(int x, int y) {
      this.x = x;
      this.y = y;
    }
    
    //This function returns whether or not a potential move is allowed.
    public boolean isPossibleMove(int x, int y) {
        
        //Get our displacements
        int xDistance = this.x - x; 
        int yDistance = this.y - y;
                
        //Can just use bishop test for this case
        if (Math.abs(xDistance) == Math.abs(yDistance)) {
            
            return (new Bishop(this.x, this.y, this.isWhite, 
                    this.board)).isPossibleMove(x, y);
        //This can use the rook test.    
        } else if ((xDistance == 0 && yDistance != 0) || 
                (xDistance != 0 && yDistance == 0)) {
                    
            return (new Rook(this.x, this.y, this.isWhite, 
                    this.board)).isPossibleMove(x, y);
        //All other moves are illegal   
        } else {
            return false;
        }
    }
    
    //This function returns the image object that represents the Queen on the board.
    public Image getImage() {
        if (this.isWhite) {
            Image image;
            try {
                image = ImageIO.read(new File("/Users/Lyncbre/Documents/Penn/Semester Two/CIS 120/Chess/src/images/whiteQueen.png"));
                return image;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Image image;
            try {
                image = ImageIO.read(new File("/Users/Lyncbre/Documents/Penn/Semester Two/CIS 120/Chess/src/images/blackQueen.png"));
                return image;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    //Simple Getters
    public int getX() {
        return this.x;
    }
    
    public boolean getCanCastle() {
        return false;
    }
    
    public boolean canBePassanted() {
        return false;
    }
    
    public int getY() {
        return this.y;
    }
    
    public boolean isWhite() {
        return this.isWhite;
    }
    
    public String getType() {
        return "Queen";
    }
}
