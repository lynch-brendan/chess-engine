import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The Pawn class implements the Piece interface, and 
 * handles the functionality of a pawn chess piece.
 */
public class Pawn implements Piece {
    
    //Fields
    private int x;
    private int y;
    private boolean firstMove;
    private boolean enPassantPossible;
    private boolean isWhite;
    private Board board;
    private boolean canBePassanted;
    
    //Constructor
    public Pawn(int x, int y, boolean isWhite, Board board) {
        
        //Initialize our fields
        this.x = x;
        this.y = y;
        this.firstMove = true;
        this.enPassantPossible = false; 
        this.isWhite = isWhite;
        this.board = board;
        this.canBePassanted = false;
    }
    
    //This function moves the pawn, keeping it in-line
    //with it's position on the board, and keeping track of
    //whether or not en-passant is possible.
    public void move(int x, int y) {

        //Check for en-passant, being en-passanted and promotion
        if (isWhite) {
            if (y == 3) {
                this.enPassantPossible = true;
            } else if (this.firstMove && y == 5) {
                this.canBePassanted = true;
            }
        } else {
            if (y == 4) {
                this.enPassantPossible = true;
            } else if (this.firstMove && y == 3) {
                this.canBePassanted = true;
            }
        }
        
        //Move the piece
        this.x = x;
        this.y = y;
        this.firstMove = false;
    }
    
    //This function returns whether or not a potential 
    //move is possible.
    public boolean isPossibleMove(int x, int y) {
        
        //Get x and y distance wanted to be traveled
        int xDistance = this.x - x;
        int yDistance = this.y - y; 
                
        //Handle the orientation of the board
        if (isWhite) {
                        
            //Check if the piece is capturing
            if (Math.abs(xDistance) == 1 && yDistance == 1) {
                
                //If they are capturing there has to be a black piece there
                if (board.isPieceHere(x, y) && !board.isPieceHereWhite(x, y)) {
                    return true;
                    
                //Handle en passant case 
                } else if (board.canBePassanted(x, this.y) && this.enPassantPossible && 
                        !board.isPieceHereWhite(x, this.y)){
                    return true;
                    
                //Otherwise this is not a legal capture.
                } else {
                    return false;
                }
                
            //They are allowed to move forward    
            } else if (xDistance == 0) {
                                
                if (yDistance == 1) {
                    if (!board.isPieceHere(x, y)) {
                        return true;
                    } else {
                        return false;
                    }
                //They can only go forward two if they haven't moved yet    
                } else if (yDistance == 2) {
                    
                    if (this.firstMove) {
                        
                        //Make sure there aren't pieces in the way.
                        if (board.isPieceHere(this.x, this.y - 1) || board.isPieceHere(this.x, this.y - 2)) {
                            return false;
                        } else {
                            return true;
                        }
                    } else {
                        return false;
                    }
                }
            //There are no other legal pawn moves.    
            } else {
                return false;
            }
            
        //Handle logic for black pawn case    
        } else {
            
            //Check if the piece is capturing
            if (Math.abs(xDistance) == 1 && yDistance == -1) {
                
                //If they are capturing there has to be a white piece there
                if (board.isPieceHere(x, y) && board.isPieceHereWhite(x, y)) {
                    return true;
                    
                //Handle en passant case 
                } else if (board.canBePassanted(x, this.y) && this.enPassantPossible && 
                        board.isPieceHereWhite(x, this.y)){
                    return true;
                    
                //Otherwise this is not a legal capture.
                } else {
                    return false;
                }
                
            //They are allowed to move forward    
            } else if (xDistance == 0) {
                if (yDistance == -1) {
                    if (!board.isPieceHere(x, y)) {
                        return true;
                    } else {
                        return false;
                    }
                //They can only go forward two if they haven't moved yet    
                } else if (yDistance == -2) {
                    if (this.firstMove) {
                        
                        //Make sure there aren't pieces in the way.
                        if (board.isPieceHere(this.x, this.y + 1) || board.isPieceHere(this.x, this.y + 2)) {
                            return false;
                        } else {
                            return true;
                        }
                    } else {
                        return false;
                    }
                }   
            }
        }
        
        //There are no other legal pawn moves.
        return false;
    }
    
    //This function returns the Image object that represents
    //the pawn on the board.
    public Image getImage() {
        if (this.isWhite) {
            BufferedImage image = null;
            try {
                image = ImageIO.read(new File("/Users/Lyncbre/Documents/Penn/Semester Two/CIS 120/Chess/src/images/whitePawn.png"));
                return image;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            BufferedImage image = null;
            try {
                image = ImageIO.read(new File("/Users/Lyncbre/Documents/Penn/Semester Two/CIS 120/Chess/src/images/BlackPawn.png"));
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
    
    public boolean getFirstMove() {
        return this.firstMove;
    }
    
    public boolean getEnPassantPossible() {
        return this.enPassantPossible;
    }
    
    public boolean isWhite() {
        return this.isWhite;
    }
    
    public String getType() {
        return "Pawn";
    }
    
    public boolean canBePassanted() {
        return this.canBePassanted;
    }
    
    public boolean getCanCastle() {
        return false;
    }
}
