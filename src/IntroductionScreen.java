import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * This class creates a loading screen (custom JPanel) for
 * the chess game.
 */
@SuppressWarnings("serial")
public class IntroductionScreen extends JPanel{
    
    public IntroductionScreen(Board board) {
        
        //Set up layout
        super();
        this.setLayout(new GridLayout(4, 1));
        Color c = new Color(60, 158, 127);
        this.setBackground(c);
        
        //Add components
        JLabel label = new JLabel("Play Chess!");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 36));
        this.add(label);
        
        JLabel instructionLabel = new JLabel("<html>" + "Chess is a classic two player game of "
                + "strategy and planning. The main purpose of the game is to "
                + "checkmate the opposing players king, a state where the "
                + "king is in check, and there are no possible moves to get them "
                + "out of check. There are many more rules dictating the possible "
                + "ways pieces can move in chess, and you can find them at the US "
                + "Chess Federation's official website: "
                + "http://www.uschess.org/content/view/7324. " + "<html>");
        this.add(instructionLabel);
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        instructionLabel.setVerticalAlignment(SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Serif", Font.BOLD, 18));
        
        JLabel specialFeatures = new JLabel("<html>" + "This version of chess "
                + "has all of chess piece's moving functionality "
                + "like castling and en-passant. However, it is meant to replicate "
                + "playing on a real chess board, so the players themselves "
                + "ensure that they don't move into check, and determine when "
                + "one of them is checkmated." + "<html>");
        this.add(specialFeatures);
        specialFeatures.setFont(new Font("Serif", Font.BOLD, 18));
        
        JButton button = new JButton("Start Playing!");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                board.swapScreens();
                
            }
            
        });
        this.add(button);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    
}