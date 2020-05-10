import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class KnightTest {

    @Test
    public void testKnightUpLeft() {
        Board board = new Board();
        Knight k = new Knight(3, 3, true, board);
        board.removePiece(2, 1);
        assertTrue(k.isPossibleMove(2, 1)); 
    }
    
    @Test
    public void testKnightUpRight() {
        Board board = new Board();
        Knight k = new Knight(3, 3, true, board);
        board.removePiece(1, 4);
        assertTrue(k.isPossibleMove(1, 4));
    }
    
    @Test
    public void testKnightRightUp() {
        Board board = new Board();
        Knight k = new Knight(3, 3, true, board);
        board.removePiece(2, 5);
        assertTrue(k.isPossibleMove(2, 5));
    }
    
    @Test
    public void testKnightRightDown() {
        Board board = new Board();
        Knight k = new Knight(3, 3, true, board);
        board.removePiece(5, 4);
        assertTrue(k.isPossibleMove(5, 4));
    }
    
    @Test
    public void testKnightDownRight() {
        Board board = new Board();
        Knight k = new Knight(3, 3, true, board);
        board.removePiece(4, 5);
        assertTrue(k.isPossibleMove(4, 5));
    }
    
    @Test
    public void testKnightDownLeft() {
        Board board = new Board();
        Knight k = new Knight(3, 3, true, board);
        board.removePiece(2, 5);
        assertTrue(k.isPossibleMove(2, 5));
    }
    
    @Test
    public void testKnightLeftDown() {
        Board board = new Board();
        Knight k = new Knight(3, 3, true, board);
        board.removePiece(1, 4);
        assertTrue(k.isPossibleMove(1, 4));
    }
    
    @Test
    public void testKnightLeftUp() {
        Board board = new Board();
        Knight k = new Knight(3, 3, true, board);
        board.removePiece(1, 4);
        assertTrue(k.isPossibleMove(1, 4));
    }
    
    @Test
    public void testKnightCanCapture() {
        Board board = new Board();
        Knight k = new Knight(3, 3, true, board);
        board.setPiece(new Pawn(1, 4, false, board), 1, 4);
        assertTrue(k.isPossibleMove(1, 4));
    }
    
    @Test
    public void testKnightCantCaptureOwnPiece() {
        Board board = new Board();
        Knight k = new Knight(3, 3, true, board);
        board.setPiece(new Pawn(1, 4, true, board), 1, 4);
        assertFalse(k.isPossibleMove(1, 4));
    }
    
    @Test
    public void testKnightOnlyMoveInLs() {
        Board board = new Board();
        Knight k = new Knight(1, 0, true, board);
        board.setPiece(new Pawn(1, 4, false, board), 1, 4);
        assertFalse(k.isPossibleMove(1, 4));
    }

}
