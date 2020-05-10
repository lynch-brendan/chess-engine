import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The Rook class implements the Piece interface, and 
 * handles the functionality of a rook chess piece.
 */
public class Rook implements Piece {
    
    //Fields
    private int x;
    private int y;
    private boolean canCastle;
    private boolean isWhite;
    private Board board;
    
    //Constructor
    public Rook(int x, int y, boolean isWhite, Board board) {
        this.x = x;
        this.y = y;
        this.canCastle = true;
        this.isWhite = isWhite;
        this.board = board;
    }
    
    //This function moves the rook in accordance
    //with its position on the board.
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
        this.canCastle = false;

    }

    //This function returns whether or not a move is allowed.
    public boolean isPossibleMove(int x, int y) {
        
        //Get our displacement
        int xDistance = this.x - x;
        int yDistance = this.y - y;
        int currentX = this.x;
        int currentY = this.y;
        
        //Can only move one dimension at a time
        if (xDistance != 0 && yDistance != 0) {
            return false;
             
        //Either xDistance is 0 or yDistance is 0    
        } else if (xDistance == 0) {
            
            //Make sure there aren't any pieces in the way
            for (int i = 0; i < yDistance - 1; ++i) {
                if (yDistance > 0) {
                    currentY--;
                } else {
                    currentY++;
                }
                if (board.isPieceHere(currentX, currentY)) {
                    return false;
                }
            }
            
        //This case yDistance == 0    
        } else {
            
            //Make sure there aren't any pieces in the way
            for (int i = 0; i < Math.abs(xDistance) - 1; ++i) {
                
                if (xDistance > 0) {
                    currentX--;
                } else {
                    currentX++;
                }
                if (board.isPieceHere(currentX, currentY)) {
                    return false;
                }
            }
            
        }
                
        //Now handle captures
        if (board.isPieceHere(x, y)) {
            
            if (board.isPieceHereWhite(x, y) == this.isWhite) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
        
    }
    
    //This function returns the image object representing the rook on the board.
    public Image getImage() {
        if (this.isWhite) {
            Image image;
            try {
                image = ImageIO.read(new File("/Users/Lyncbre/Documents/Penn/Semester Two/CIS 120/Chess/src/images/whiteRook.png"));
                return image;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Image image;
            try {
                image = ImageIO.read(new File("/Users/Lyncbre/Documents/Penn/Semester Two/CIS 120/Chess/src/images/blackRook.png"));
                return image;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    //Simple getters 
    public boolean canBePassanted() {
        return false;
    }
    
    public boolean getCanCastle() {
        return this.canCastle;
    }

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
        return "Rook";
    }

}
