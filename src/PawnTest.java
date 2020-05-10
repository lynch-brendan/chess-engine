import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Test
    public void testIsPossibleMoveStartOneSquare() {
        Board board = new Board();
        Pawn p = new Pawn(0, 7, true, board);
        assertTrue(p.isPossibleMove(0, 6));
    }

    @Test
    public void testIsPossibleMoveStartTwoSquares() {
        Board board = new Board();
        Pawn p = new Pawn(0, 7, true, board);
        assertTrue(p.isPossibleMove(0, 5));        
    }
    
    @Test
    public void testIsPossibleMoveOneSquareNotPossible() {
        Board board = new Board();
        Pawn p = new Pawn(0, 7, true, board);
        Pawn pBlocking = new Pawn(0, 6, false, board);
        board.setPiece(pBlocking, 0, 6);
        assertFalse(p.isPossibleMove(0, 6));  
    }
    
    @Test
    public void testIsPossibleMoveTwoSquaresNotPossibleStart() {
        Board board = new Board();
        Pawn p = new Pawn(0, 7, true, board);
        Pawn pBlocking = new Pawn(0, 6, false, board);
        board.setPiece(pBlocking, 0, 6);
        assertFalse(p.isPossibleMove(0, 5));
    }
    
    @Test
    public void testIsPossibleMoveTwoSquaresNotPossibleNotFirstMove() {
        Board board = new Board();
        Pawn p = new Pawn(0, 7, true, board);
        p.move(0, 6);
        assertFalse(p.isPossibleMove(0, 4));
    }
    
    @Test
    public void testIsPossibleMoveEnPassant() {
        Board board = new Board();
        Pawn p = new Pawn(0, 7, true, board);
        Pawn p2 = new Pawn(1, 1, false, board);
        p.move(0, 5);
        p.move(0, 4);
        p.move(0, 3);
        p2.move(1, 3);
        assertTrue(p.isPossibleMove(1, 2));
    }
    
    @Test
    public void testIsPossibleMoveEnPassantNotPossible() {
        Board board = new Board();
        Pawn p = new Pawn(0, 7, true, board);
        Pawn p2 = new Pawn(1, 1, false, board);
        p.move(0, 5);
        p.move(0, 4);
        p.move(0, 3);
        p2.move(1, 2);
        p2.move(1, 3);
        assertFalse(p.isPossibleMove(1, 2));
    }
    
    @Test
    public void testIsPossibleMoveCapture() {
        Board board = new Board();
        Pawn p = new Pawn(0, 7, true, board);
        Pawn p2 = new Pawn(1, 1, false, board);
        p.move(0, 5);
        p.move(0, 4);
        p.move(0, 3);
        p2.move(1, 2);
        assertTrue(p.isPossibleMove(1, 2));
    }
    
    @Test
    public void testIsPossibleMoveCaptureNotPossible() {
        Board board = new Board();
        Pawn p = new Pawn(0, 7, true, board);
        p.move(0, 5);
        p.move(0, 4);
        p.move(0, 3);
        assertFalse(p.isPossibleMove(1, 2));
    }
    
    @Test
    public void testIsPossibleMoveCantCaptureOwnColorPiece() {
        Board board = new Board();
        Pawn p = new Pawn(0, 7, true, board);
        assertFalse(p.isPossibleMove(1, 6));
    }
}
