import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JButton;

/**
 * The TileButton class extends JButton, making a custom
 * button that can project the image of any chess piece,
 * and handle user mouse clicks.
 */

@SuppressWarnings("serial")
public class TileButton extends JButton{

    //Fields
    private boolean isWhite;
    private Image image;
    private Tile t;
    
    //Constructor
    public TileButton(boolean isWhite, Image image, Tile t) {
        super();
        this.isWhite = isWhite;
        this.image = t.getImage();
        this.t = t;
        
    }
    
    //Simple methods to help show what tile is pressed.
    public void setPressed(boolean pressed) {
        t.setPressed(pressed);
    }
    
    public boolean getPressed() {
        return t.isPressed();
    }
    
    public void updateImage() {
        this.image = t.getImage();
    }
    
    //Override the paintcomponent in order to customize the appearance.
    @Override
    protected void paintComponent(Graphics g) {
        
        //Call super constructor, get an updated image.
        updateImage();
        super.paintComponent(g);
        
        //Draw the Square
        if (isWhite) {
            if (t.getHighlighted()) { 
                Color c= new Color(253, 255, 169);
                g.setColor(c);
            } else {
                g.setColor(Color.WHITE);
            }
        } else {
             if (t.getHighlighted()) {
                 Color c = new Color(217, 240, 65);
                 g.setColor(c);
             } else {
                 Color c = new Color(29, 105, 14);
                 g.setColor(c);
             }
        }
        
        //Draw our image
        int width = getWidth();
        int height = getHeight();
        int imageWidth = (int) (width * .75);
        int imageHeight = (int) (height *.75);
        Image tmp = null;
        if (this.image != null) {
            tmp = this.image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
        }
        g.fillRect(0, 0, width, height);
        g.drawImage(tmp, (int) Math.round(width*.125), (int) Math.round(height*.125), imageWidth, imageHeight, null);
    }
}
