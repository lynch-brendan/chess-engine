import java.awt.Image;

/**
 * The Tile class either holds a piece, or does not and is an
 * empty tile. It holds its own TileButton, which helps the tile
 * be shown on the UI.
 */
public class Tile {
    
    //Fields
    private boolean isEmpty;
    private boolean isTileWhite;
    private Piece p;
    private TileButton tb;
    private boolean isPressed;
    private boolean isHighlighted;
    
    //Constructor
    public Tile(Piece p, boolean isTileWhite) {
        if (p == null) {
            this.p = null;
            this.isEmpty = true;
        } else {
            this.p = p;
            this.isEmpty = false;
        }
        this.isTileWhite = isTileWhite;
        this.isPressed = false;
        this.tb = new TileButton(this.isTileWhite, this.getImage(), this);
        this.isHighlighted = false;
    }
    
    //Simple Getters and setters
    public boolean isEmpty() {
        return this.isEmpty;
    }
    
    public void removePiece() {
        this.p = null;
        this.isEmpty = true;
    }
    
    public void addPiece(Piece p) {
        this.p = p;
        this.isEmpty = false;
    }
    
    public boolean isWhite() {
        if (p == null) {
            return false;
        } else {
            return p.isWhite();
        }
    }
    
    public boolean isPossibleMove(int x, int y) {
        if (p == null) {
            return false;
        } else if (x == p.getX() && y == p.getY()) {
            return false;
        } else {
            return p.isPossibleMove(x, y);
        }
    }
    
    public boolean getCanCastle() {
        return p.getCanCastle();
    }
    
    public String getType() {
        if (p == null) {
            return "Empty Tile";
        }
        return p.getType();
    }
    
    public boolean canBePassanted() {
        if (p == null) {
            return false;
        }
        return p.canBePassanted();
    }
    
    public boolean isPieceHereWhite() {
        if (p == null) {
            return false;
        } else {
            return this.p.isWhite();
        }
    }
    
    public Image getImage() {
        if (p == null) {
            return null;
        } else {
            return p.getImage();
        }
    }
    
    public void setHighlighted(boolean b) {
        this.isHighlighted = b;
    }
    
    public boolean getHighlighted() {
        return this.isHighlighted;
    }
    
    public Piece getPiece() {
        return this.p;
    }
    
    public boolean isPressed() {
        return this.isPressed;
    }
    
    public void setPressed(boolean pressed) {
        this.isPressed = pressed;
    }
    
    public TileButton getTileButton() {
        return this.tb;
    }
    
}
