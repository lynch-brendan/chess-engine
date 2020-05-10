import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The Board class handles the bulk of the logic as to how pieces can move.
 * It holds all of the pieces in a 2D array, and also handles
 * the drawing of the board.
 */

public class Board implements Runnable {

    // Fields
    private Tile[][] board;
    private boolean[][] boardColors;
    private int pressedTileX;
    private int pressedTileY;
    private JPanel panel;
    private CardLayout cl;
    private JPanel screens;

    // Constructor
    public Board() {
        this.board = new Tile[8][8];
        this.boardColors = getColorsOfSquares();
        this.pressedTileX = -1;
        this.pressedTileY = -1;

        // Set up pieces on our board.
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {

                // Add the black pieces
                if (i == 0) {
                    if (j == 0) {
                        board[i][j] = new Tile(new Rook(j, i, false, this), boardColors[i][j]);
                    } else if (j == 1) {
                        board[i][j] = new Tile(new Knight(j, i, false, this), boardColors[i][j]);
                    } else if (j == 2) {
                        board[i][j] = new Tile(new Bishop(j, i, false, this), boardColors[i][j]);
                    } else if (j == 3) {
                        board[i][j] = new Tile(new Queen(j, i, false, this), boardColors[i][j]);
                    } else if (j == 4) {
                        board[i][j] = new Tile(new King(j, i, false, this), boardColors[i][j]);
                    } else if (j == 5) {
                        board[i][j] = new Tile(new Bishop(j, i, false, this), boardColors[i][j]);
                    } else if (j == 6) {
                        board[i][j] = new Tile(new Knight(j, i, false, this), boardColors[i][j]);
                    } else if (j == 7) {
                        board[i][j] = new Tile(new Rook(j, i, false, this), boardColors[i][j]);
                    }
                } else if (i == 1) {
                    board[i][j] = new Tile(new Pawn(j, i, false, this), boardColors[i][j]);

                    // Add the white pieces
                } else if (i == 6) {
                    board[i][j] = new Tile(new Pawn(j, i, true, this), boardColors[i][j]);
                } else if (i == 7) {
                    if (j == 0) {
                        board[i][j] = new Tile(new Rook(j, i, true, this), boardColors[i][j]);
                    } else if (j == 1) {
                        board[i][j] = new Tile(new Knight(j, i, true, this), boardColors[i][j]);
                    } else if (j == 2) {
                        board[i][j] = new Tile(new Bishop(j, i, true, this), boardColors[i][j]);
                    } else if (j == 3) {
                        board[i][j] = new Tile(new Queen(j, i, true, this), boardColors[i][j]);
                    } else if (j == 4) {
                        board[i][j] = new Tile(new King(j, i, true, this), boardColors[i][j]);
                    } else if (j == 5) {
                        board[i][j] = new Tile(new Bishop(j, i, true, this), boardColors[i][j]);
                    } else if (j == 6) {
                        board[i][j] = new Tile(new Knight(j, i, true, this), boardColors[i][j]);
                    } else if (j == 7) {
                        board[i][j] = new Tile(new Rook(j, i, true, this), boardColors[i][j]);
                    }
                    // The rest of the board is empty pieces.
                } else {
                    board[i][j] = new Tile(null, boardColors[i][j]);
                }
            }
        }
    }

    // This function returns a 2D array of the colors the tiles
    // are supposed to be in our board. It is a helper function for
    // the board constructor.
    public static boolean[][] getColorsOfSquares() {

        boolean current = true;
        boolean[][] boardColors = new boolean[8][8];
        for (int i = 0; i < boardColors.length; ++i) {
            for (int j = 0; j < boardColors[i].length; ++j) {
                boardColors[i][j] = current;
                current = !current;
                // Start next row the opposite color
                if (j == 7) {
                    current = !current;
                }
            }
        }

        return boardColors;
    }
    
    // This function returns whether or not a king/rook in the given
    // square can castle
    public boolean canCastle(int x, int y) {
        // Check if there is even a piece there
        if (board[y][x].isEmpty()) {
            return false;
        } else if (board[y][x].getCanCastle()) {
            return true;
        } else {
            return false;
        }
    }

    //This function returns whether or not a tile in our board
    //has been pressed by the user.
    public boolean isTilePressed() {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                if (board[i][j].isPressed()) {
                    // Get its x and y
                    this.pressedTileX = j;
                    this.pressedTileY = i;

                    // Unpress it
                    board[i][j].setPressed(false);
                    return true;
                }
            }
        }
        // If no tiles are pressed return false
        return false;
    }

    // Handle the movement of pieces
    public void move(int x1, int y1, int x2, int y2) {
                
        // Get the tile we are trying to move, and move to
        Tile t = board[y1][x1];
        Tile t2 = board[y2][x2];

        // Check if that tile can move
        boolean canMove = t.isPossibleMove(x2, y2);         
        int xDistance = x1 - x2;

        if (canMove) {
            
            //Handle castling and promotion
            if (t.getType().equals("King")) {
                if (t.getPiece().isWhite()) {
                    if (xDistance == -2) {
                        
                        //Move the rook
                        Tile whiteRook = board[7][7];
                        Piece whiteRookPiece = whiteRook.getPiece();
                        whiteRookPiece.move(5, 7);
                        
                        //Move the king
                        Piece whiteKingPiece = t.getPiece();
                        whiteKingPiece.move(x2, y2);
                        
                        //Update the board
                        Tile fillWithRook = board[7][5];
                        fillWithRook.addPiece(whiteRookPiece); //Add the rook
                        whiteRook.removePiece();
                        t2.addPiece(whiteKingPiece);
                        t.removePiece();
                        
                    } else if (xDistance == 2) {
 
                        //Move the rook
                        Tile whiteRook = board[7][0];
                        Piece whiteRookPiece = whiteRook.getPiece();
                        whiteRookPiece.move(3, 7);
                        
                        //Move the king
                        Piece whiteKingPiece = t.getPiece();
                        whiteKingPiece.move(x2, y2);
                        
                        //Update the board
                        Tile fillWithRook = board[7][3];
                        fillWithRook.addPiece(whiteRookPiece); //Add the rook
                        whiteRook.removePiece();
                        t2.addPiece(whiteKingPiece);
                        t.removePiece(); 
                        
                    } else {
                        
                        //Update the piece
                        Piece p = t.getPiece();
                        p.move(x2, y2);
                        
                        //Handle the actual moving
                        if (t2.isEmpty()) {
                            t2.addPiece(p);
                        } else {
                            t2.removePiece();
                            t2.addPiece(p);
                        }
                        
                        //Empty the square it moved from
                        t.removePiece();
                    }
                //This branch handles black king functionality.    
                } else {
                    if (xDistance == -2) { 
                        
                        //Move the rook
                        Tile blackRook = board[0][7];
                        Piece blackRookPiece = blackRook.getPiece();
                        blackRookPiece.move(5, 0);
                        
                        //Move the king
                        Piece blackKingPiece = t.getPiece();
                        blackKingPiece.move(x2, y2);
                        
                        //Update the board
                        Tile fillWithRook = board[0][5];
                        fillWithRook.addPiece(blackRookPiece); //Add the rook
                        blackRook.removePiece();
                        t2.addPiece(blackKingPiece);
                        t.removePiece();
                        
                    } else if (xDistance == 2) {
 
                        //Move the rook
                        Tile blackRook = board[0][0];
                        Piece blackRookPiece = blackRook.getPiece();
                        blackRookPiece.move(3, 0);
                        
                        //Move the king
                        Piece blackKingPiece = t.getPiece();
                        blackKingPiece.move(x2, y2);
                        
                        //Update the board
                        Tile fillWithRook = board[0][3];
                        fillWithRook.addPiece(blackRookPiece); //Add the rook
                        blackRook.removePiece();
                        t2.addPiece(blackKingPiece);
                        t.removePiece();    
                        
                    } else {
                        
                        //Update the piece
                        Piece p = t.getPiece();
                        p.move(x2, y2);
                        
                        //Handle the actual moving
                        if (t2.isEmpty()) {
                            t2.addPiece(p);
                        } else {
                            t2.removePiece();
                            t2.addPiece(p);
                        }
                        
                        //Empty the square it moved from
                        t.removePiece();
                    }
                }
            } else if (t.getType().equals("Pawn")) {
                
                if (t.getPiece().isWhite()) {
                    
                    //Handle en-passant
                    if (Math.abs(xDistance) == 1 && !isPieceHere(x2, y2)) {
                        
                        //Update the pawn's position
                        Piece whitePawn = t.getPiece();
                        whitePawn.move(x2, y2);
                        
                        //Get the tiles where this is happening
                        Tile ourPawn = board[y1][x1];
                        Tile opponentPawn = board[y1][x2]; //This is the opponents pawn's tile
                        Tile newTile = board[y2][x2];
                        
                        //Update the board
                        ourPawn.removePiece();
                        opponentPawn.removePiece();
                        newTile.addPiece(whitePawn);
                        
                    //Handle pawn promotion
                    } else if (y2 == 0) {
                        Queen q = new Queen(x1, 0, true, this);
                        Tile promotionSquare = board[y2][x2];
                        Tile currentSquare = board[y1][x1];
                        currentSquare.removePiece();
                        promotionSquare.removePiece();
                        promotionSquare.addPiece(q);
                        
                    } else {
                        //Update the piece
                        Piece p = t.getPiece();
                        p.move(x2, y2);
                        
                        //Handle the actual moving
                        if (t2.isEmpty()) {
                            t2.addPiece(p);
                        } else {
                            t2.removePiece();
                            t2.addPiece(p);
                        }
                        
                        //Empty the square it moved from
                        t.removePiece(); 
                    }
                    
                //Handle black pawn functionality    
                } else {
                    
                    //Handle en-passant
                    if (Math.abs(xDistance) == 1 && !isPieceHere(x2, y2)) {
                        
                        //Update the pawn's position
                        Piece blackPawn = t.getPiece();
                        blackPawn.move(x2, y2);
                        
                        //Get the tiles where this is happening
                        Tile ourPawn = board[y1][x1];
                        Tile opponentPawn = board[y1][x2]; //This is the opponents pawn's tile
                        Tile newTile = board[y2][x2];
                        
                        //Update the board
                        ourPawn.removePiece();
                        opponentPawn.removePiece();
                        newTile.addPiece(blackPawn);
                        
                    //Handle pawn promotion
                    } else if (y2 == 7) {
                        Queen q = new Queen(x1, 0, false, this);
                        Tile promotionSquare = board[y2][x2];
                        Tile currentSquare = board[y1][x1];
                        currentSquare.removePiece();
                        promotionSquare.removePiece();
                        promotionSquare.addPiece(q);  
                        
                    } else {
                        //Update the piece
                        Piece p = t.getPiece();
                        p.move(x2, y2);
                        
                        //Handle the actual moving
                        if (t2.isEmpty()) {
                            t2.addPiece(p);
                        } else {
                            t2.removePiece();
                            t2.addPiece(p);
                        }
                        
                        //Empty the square it moved from
                        t.removePiece(); 
                    }
                }
                    
            } else {
            
                //Update the piece
                Piece p = t.getPiece();
                p.move(x2, y2);
                
                //Handle the actual moving
                if (t2.isEmpty()) {
                    t2.addPiece(p);
                } else {
                    t2.removePiece();
                    t2.addPiece(p);
                }
                
                //Empty the square it moved from
                t.removePiece();
            }
            this.panel.repaint();
        }
    }

    @Override
    public void run() {
        
        // Initialize our board
        JFrame frame = new JFrame("Chess");
        frame.setPreferredSize(new Dimension(600, 600));
        
        cl = new CardLayout();
        screens = new JPanel(cl);
        
        //Create our board
        this.panel = new JPanel();
        panel.setLayout(new GridLayout(8, 8));
        initializeBoard();
        
        //Create the loading screen
        IntroductionScreen is = new IntroductionScreen(this);
        
        //Add our panel to our screens
        screens.add(is, "Introduction");
        screens.add(this.panel, "Main Game");
        cl.show(screens, "Introduction");
        frame.getContentPane().add(screens);
        
        //Make sure the frame handles resizing, closing well
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
    
    // This is a helper function that initializes our grid
    // of custom JButtons.
    public void initializeBoard() {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                TileButton tb = board[i][j].getTileButton();
                this.panel.add(tb);
                tb.addActionListener(new TileListener(tb));
            }
        }
    }
    
    
    // Simple Getters and setters
    public boolean isPieceHere(int x, int y) {
        return !board[y][x].isEmpty();
    }

    public boolean isPieceHereWhite(int x, int y) {
        return board[y][x].isPieceHereWhite();
    }

    public boolean canBePassanted(int x, int y) {
        return board[y][x].getType().equals("Pawn") && board[y][x].canBePassanted();
    }
    
    public void setPiece(Piece p, int x, int y) {
        board[y][x] = new Tile(p, boardColors[y][x]);
    }

    public void removePiece(int x, int y) {
        board[y][x] = new Tile(null, boardColors[y][x]);
    }

    public void setTileHighlighted(int x, int y, boolean b) {
        board[y][x].setHighlighted(b);
        panel.repaint();
    }

    public int getPressedX() {
        return this.pressedTileX;
    }

    public int getPressedY() {
        return this.pressedTileY;
    }
    
    public boolean isPossibleMove(int x1, int y1, int x2, int y2) {
        return board[y1][x1].isPossibleMove(x2, y2);
    }
    
    public void swapScreens() {
        this.cl.show(this.screens, "Main Game");
    }
    
}
