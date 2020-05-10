import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The Knight class implements the Piece interface,
 * and handles the functionality of a knight piece.
 */
public class Knight implements Piece{

    //Fields
    private int x;
    private int y;    
    private boolean isWhite;
    private Board board;
    
    //Constructor
    public Knight(int x, int y, boolean isWhite, Board board) {
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
        this.board = board;
    }
    
    //This function moves the piece, keeping the coordinates
    //of the piece in line with where it is on the board.
    public void move(int x, int y) {
      this.x = x;
      this.y = y;
    }
    
    //This function returns whether or not a potential move
    //is allowed.
    public boolean isPossibleMove(int x, int y) {
      
        //Get our displacement
        int xDistance = this.x - x;
        int yDistance = this.y - y;
        
        //Handle captures
        if (board.isPieceHere(x, y)) {
            if (board.isPieceHereWhite(x, y) == this.isWhite) {
                return false;
            
            //Make sure it's a legal move    
            } else {
                if (Math.abs(xDistance) == 1 && Math.abs(yDistance) == 2) {
                    return true;
                } else if (Math.abs(xDistance) == 2 && Math.abs(yDistance) == 1) {
                    return true;
                } else {
                    return false;
                }
            }
            
        //If there is no piece there, just see if it is a legal move    
        } else {
            if (Math.abs(xDistance) == 1 && Math.abs(yDistance) == 2) {
                return true;
            } else if (Math.abs(xDistance) == 2 && Math.abs(yDistance) == 1) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    //This function returns the Image object that represents
    //the knight on the board.
    public Image getImage() {
        if (this.isWhite) {
            Image image;
            try {
                image = ImageIO.read(new File("/Users/Lyncbre/Documents/Penn/Semester Two/CIS 120/Chess/src/images/whiteKnight.png"));                
                return image;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Image image;
            try {
                image = ImageIO.read(new File("/Users/Lyncbre/Documents/Penn/Semester Two/CIS 120/Chess/src/images/blackKnight.png"));
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
    
    public boolean canBePassanted() {
        return false;
    }
    
    public int getY() {
        return this.y;
    }
    
    public boolean getCanCastle() {
        return false;
    }
    
    public boolean isWhite() {
        return this.isWhite;
    }
    
    public String getType() {
        return "Knight";
    }
}
