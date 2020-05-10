import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TileListener is a custom ButtonListener, which sets
 * the chess moves into action when it is pressed.
 */
public class TileListener implements ActionListener{
    
    //Fields
    private TileButton tb;
    private boolean pressed;
    
    //Constructor
    public TileListener (TileButton tb) {
        super();
        this.tb = tb;
        this.pressed = false;
    }
    
    //Override action performed to start piece moves.
    @Override
    public void actionPerformed(ActionEvent e) {
        tb.setPressed(!pressed);
    }
}
