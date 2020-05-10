import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;

/**
 * The Game class handles much of the logic of the chess game.
 * It makes sure that white and black change turns, and handles
 * user input.
 * When you run its main function, the User Interface appears,
 * and the game is playable.
 */

public class Game { 

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        
        //Initialize the variables we are going to need
        Board board = new Board();
        SwingUtilities.invokeLater(board);
        boolean waitMode = true;
        boolean secondWaitMode = false;
        boolean whitesTurn = true;
        int firstClickX = -1;
        int firstClickY = -1;
        int secondClickX = -1;
        int secondClickY = -1;
        
        
        //Infinite While loop
        while (true) {
            
            //Only let white pieces move when it is white's turn
            if (whitesTurn) {
                if (waitMode) {
                    
                    //Check if any piece has been pressed
                    boolean pressed = board.isTilePressed();
                    
                    //If so, get its x and y, get out of wait mode
                    if (pressed) { 
                        firstClickX = board.getPressedX();
                        firstClickY = board.getPressedY();
                        
                        //Make sure it is a white piece
                        if (board.isPieceHereWhite(firstClickX, firstClickY)) {
                            board.setTileHighlighted(firstClickX, firstClickY, true);
                            waitMode = false;
                            secondWaitMode = true;
                            pressed = false;
                        
                        //Otherwise it can't be moved.    
                        } else {
                            pressed = false;
                            
                        }
                    }
                
                //This branch waits for the second click
                //of the mouse, signifying where the piece
                //is trying to go.
                } else if (secondWaitMode) {
                    
                    //Again check if they have clicked another tile
                    boolean pressedAgain = board.isTilePressed();
                                    
                    //Handle it if they have
                    if (pressedAgain) {                    
                        
                        //Make sure it isn't the same tile as before
                        secondClickX = board.getPressedX();
                        secondClickY = board.getPressedY();
                        if (secondClickX == firstClickX && secondClickY == firstClickY) {
                            pressedAgain = false;
                            
                        //Check if they are trying to switch pieces
                        } else if (board.isPieceHere(secondClickX, secondClickY) && board.isPieceHereWhite(secondClickX, secondClickY)) {
                            board.setTileHighlighted(firstClickX, firstClickY, false);
                            firstClickX = secondClickX;
                            firstClickY = secondClickY;
                            board.setTileHighlighted(firstClickX, firstClickY, true);
                            
                        } else if (!board.isPossibleMove(firstClickX, firstClickY, secondClickX, secondClickY)) {
                            pressedAgain = false;
                            
                        } else {
                            
                            //Get new coordinates
                            board.move(firstClickX, firstClickY, secondClickX, secondClickY);
                            secondWaitMode = false;
                            waitMode = true;
                            pressedAgain = false;
                            board.setTileHighlighted(firstClickX, firstClickY, false);
                            whitesTurn = false;
                        }
                    } 
                }
                
            //Then it is black's turn    
            } else {
                if (waitMode) {
                    
                    //Check if any piece has been pressed
                    boolean pressed = board.isTilePressed();
                    
                    //If so, get its x and y, get out of wait mode
                    if (pressed) { 
                        firstClickX = board.getPressedX();
                        firstClickY = board.getPressedY();
                        
                        //Make sure it is a white piece
                        if (!board.isPieceHereWhite(firstClickX, firstClickY) &&
                                board.isPieceHere(firstClickX, firstClickY)) {
                            board.setTileHighlighted(firstClickX, firstClickY, true);
                            waitMode = false;
                            secondWaitMode = true;
                            pressed = false;
                            
                        } else {
                            pressed = false;
                        }
                    }
                    
                } else if (secondWaitMode) {
                    
                    //Again check if they have clicked another tile
                    boolean pressedAgain = board.isTilePressed();
                                    
                    //Handle it if they have
                    if (pressedAgain) {                    
                        
                        //Make sure it isn't the same tile as before
                        secondClickX = board.getPressedX();
                        secondClickY = board.getPressedY();
                        if (secondClickX == firstClickX && secondClickY == firstClickY) {
                            pressedAgain = false;
                            
                        //Check if they are trying to switch pieces
                        } else if (board.isPieceHere(secondClickX, secondClickY) && !board.isPieceHereWhite(secondClickX, secondClickY)) {
                            board.setTileHighlighted(firstClickX, firstClickY, false);
                            firstClickX = secondClickX;
                            firstClickY = secondClickY;    
                            board.setTileHighlighted(firstClickX, firstClickY, true);
                            
                        } else if (!board.isPossibleMove(firstClickX, firstClickY, secondClickX, secondClickY)) {
                            pressedAgain = false;    
                            
                        } else {
                            //Get new coordinates
                            board.move(firstClickX, firstClickY, secondClickX, secondClickY);
                            secondWaitMode = false;
                            waitMode = true;
                            pressedAgain = false;
                            board.setTileHighlighted(firstClickX, firstClickY, false);
                            whitesTurn = true;
                        }
                    } 
                }                
            }
        }

    }
}
