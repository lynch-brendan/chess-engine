import java.awt.Image;

/**
 * The Piece interface holds the functions that
 * all pieces must implement in a chess game.
 */
public interface Piece {

    //This function moves a piece to the location specified by x and y
    public void move(int x, int y);
    
    //This function returns a JLabel with the drawn piece
    public Image getImage();
    
    //This function checks whether an attempted move is allowed
    public boolean isPossibleMove(int x, int y);
        
    //Simple getters
    public int getX();
    
    public int getY();
    
    public boolean isWhite();
    
    public String getType();    
    
    public boolean getCanCastle();
    
    public boolean canBePassanted();
}
