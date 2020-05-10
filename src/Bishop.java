import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This class implements the piece interface, implementing
 * the functionality of a bishop chess piece.
 */

public class Bishop implements Piece{

    //Fields
    private int x;
    private int y;
    private boolean isWhite;
    private Board board;
    
    //Constructor
    public Bishop(int x, int y, boolean isWhite, Board board) {
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
        this.board = board;
    }
    
    //Move the piece, keeping it in-line with its position on the board.
    public void move(int x, int y) {
      this.x = x;
      this.y = y;
    }
    
    //This function returns whether or not a potential move is
    //allowed.
    public boolean isPossibleMove(int x, int y) {
        
        //Get the distance it is going
        int currentX = this.x;
        int currentY = this.y;
        int xDistance = this.x - x;
        int yDistance = this.y - y;
        int numMoves = xDistance;
        
        //Make sure they are trying to go diagonally
        if (Math.abs(xDistance) != Math.abs(yDistance)) {
            return false;
        }
        
        //Make sure there aren't any pieces in the way BEFORE POTENTIAL SQUARE
        for (int i = 0; i < numMoves - 1; ++i) {
            
            //Find out the direction it is going
            if (xDistance > 0) {
                
                //In this case they are going up and left
                if (yDistance > 0) {
                    currentX--;
                    currentY--;
                    if (board.isPieceHere(currentX, currentY)) {
                        return false;
                    }
                //This case is down and left    
                } else {
                    currentX--;
                    currentY++;
                    if (board.isPieceHere(currentX, currentY)) {
                        return false;
                    }
                }
            } else {
                //This case is right and up
                if (yDistance > 0) {
                    currentX++;
                    currentY--;
                    if (board.isPieceHere(currentX, currentY)) {
                        return false;
                    }
                //This case is right and down    
                } else {
                    currentX++;
                    currentY++;
                    if (board.isPieceHere(currentX, currentY)) {
                        return false;
                    }
                }
                
            }
        }
        
        //We know there is no piece in the way, now handle captures
        if (board.isPieceHere(x, y)) {
            
            //If they are the same color it is not legal
            if (board.isPieceHereWhite(x, y) == this.isWhite) {
                return false;
            } else {
                return true;
            }
        }
        
        //Otherwise it is a legal move!
        return true;
    }
    
    //This function returns the Image object that represents the bishop
    //on the board.
    public Image getImage() {
        if (this.isWhite) {
            Image image;
            try {
                image = ImageIO.read(new File("/Users/Lyncbre/Documents/Penn/Semester Two/CIS 120/Chess/src/images/whiteBishop.png"));
                return image;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Image image;
            try {
                image = ImageIO.read(new File("/Users/Lyncbre/Documents/Penn/Semester Two/CIS 120/Chess/src/images/blackBishop.png"));
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
    
    public int getY() {
        return this.y;
    }
    
    public boolean isWhite() {
        return this.isWhite;
    }
    
    public String getType() {
        return "Bishop";
    }
    
    public boolean getCanCastle() {
        return false;
    }
    
    public boolean canBePassanted() {
        return false;
    }
    
    
}
