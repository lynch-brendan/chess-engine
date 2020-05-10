import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The King class implements the Piece Interface, 
 * and contains the functionality of the king chess piece.
 */

public class King implements Piece {

    
    //Fields
    private int x;
    private int y;
    private boolean canCastle;
    private boolean isWhite;
    private Board board;
    
    
    //Constructor
    public King(int x, int y, boolean isWhite, Board board) {
        this.x = x;
        this.y = y;
        this.canCastle = true;
        this.isWhite = isWhite;
        this.board = board;
    }
    
    //This function moves the king, keeping it in line with its
    //position on the board.
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
        this.canCastle = false;

    }
    
    //This function returns whether or not a potential move
    //is allowed.
    public boolean isPossibleMove(int x, int y) {
        
        //Get xDistance and yDistance
        int xDistance = this.x - x;
        int yDistance = this.y - y;
        
        //First handle case when there are no pieces where it is trying to go
        if (!board.isPieceHere(x, y)) {
                        
            //Kings can't move more than one space in both directions
            if (xDistance > 1 && yDistance > 1) {
                return false;
                
            } else if (yDistance == 0) {
                if (isWhite) {
                    
                    //King-side castling
                    if (xDistance == -2 && (this.y == 7) &&
                            this.canCastle) {
                                                
                        //Make sure there isn't a piece in the way
                        if (!board.isPieceHere(5, 7)) {
                            
                            //You can only castle if the rook can too
                            if (board.canCastle(7, 7)) {
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                        
                    //Queen-side castling    
                    } else if (xDistance == 2 && this.y == 7 && this.canCastle) {
                        
                        //Make sure there aren't pieces in the way
                        if (!board.isPieceHere(3, 7) && !board.isPieceHere(2, 7)
                                && !board.isPieceHere(1, 7)) {
                            
                            //Can only castle if the rook can as well
                            if (board.canCastle(0, 7)) {
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    //Otherwise we can move one square left/right    
                    } else if (Math.abs(xDistance) == 1) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    
                    //King-side castling
                    if (xDistance == -2 && (this.y == 0) &&
                            this.canCastle) {
                        
                        //Make sure there isn't a piece in the way
                        if (!board.isPieceHere(5, 0)) {
                            
                            //You can only castle if the rook can too
                            if (board.canCastle(7, 0)) {
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                        
                    //Queen-side castling    
                    } else if (xDistance == 2 && this.y == 0 && this.canCastle) {
                        
                        //Make sure there aren't pieces in the way
                        if (!board.isPieceHere(3, 0) && !board.isPieceHere(2, 0)
                                && !board.isPieceHere(1, 0)) {
                            
                            //Can only castle if the rook can as well
                            if (board.canCastle(0, 0)) {
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    //Otherwise we can move one square left/right    
                    } else if (Math.abs(xDistance) == 1) {
                        return true;
                    } else {
                        return false;
                    }
                }  
            //Otherwise, as long as the king is moving one square that's okay    
            } else if (Math.abs(yDistance) == 1){
                
                if (xDistance < 2) {
                    return true;
                } else {
                    return false;
                }
            }
            
        //This is the case when there is a piece where the king is trying to go    
        } else {
            
            //Can't capture your own color piece
            if (board.isPieceHereWhite(x, y) == this.isWhite) {
                return false;
            } else {
                
                //Kings can only move one square
                if (xDistance > 1 || yDistance > 1) {
                    return false;
                 
                //Otherwise it is a legal capture    
                } else {
                    return true;
                }
            }
        }
        
        //All other random moves are illegal
        return false;
    }

    //This function returns the Image object that represents the King
    //on the board.
    public Image getImage() {
        if (this.isWhite) {
            Image image;
            try {
                image = ImageIO.read(new File("/Users/Lyncbre/Documents/Penn/Semester Two/CIS 120/Chess/src/images/whiteKing.png"));
                return image;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Image image;
            try {
                image = ImageIO.read(new File("/Users/Lyncbre/Documents/Penn/Semester Two/CIS 120/Chess/src/images/blackKing.png"));
                return image;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    
    //Simple getters
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    
    public boolean getCanCastle() {
        return this.canCastle;
    }
    
    public boolean isWhite() {
        return this.isWhite;
    }
    
    public String getType() {
        return "King";
    }
    
    public boolean canBePassanted() {
        return false;
    }

}
