import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RookTest {

    @Test
    public void testRookUninhibitedMovingLeft() {
        Board board = new Board();
        Rook r = new Rook(3, 3, true, board);
        assertTrue(r.isPossibleMove(0,  3));
    }
    
    @Test
    public void testRookUninhibitedMovingDown() {
        Board board = new Board();
        Rook r = new Rook(3, 3, true, board);
        assertTrue(r.isPossibleMove(3,  5));
    }
    
    @Test
    public void testRookUninhibitedMovingRight() {
        Board board = new Board();
        Rook r = new Rook(3, 3, true, board);
        assertTrue(r.isPossibleMove(7,  3));
    }
    
    @Test
    public void testRookUninhibitedMovingUp() {
        Board board = new Board();
        Rook r = new Rook(3, 3, true, board);
        assertTrue(r.isPossibleMove(3,  2));
    }
    
    @Test
    public void testRookCapture() {
        Board board = new Board();
        Rook r = new Rook(3, 3, true, board);
        board.setPiece(new Pawn(3, 2, false, board), 3, 2);
        assertTrue(r.isPossibleMove(3,  2));
    }
    
    @Test
    public void testRookCantCaptureOwnPiece() {
        Board board = new Board();
        Rook r = new Rook(3, 3, true, board);
        board.setPiece(new Pawn(3, 2, true, board), 3, 2);
        assertFalse(r.isPossibleMove(3,  2));
    }
    
    @Test
    public void testRookCantMoveBlocked() {
        Board board = new Board();
        Rook r = new Rook(3, 3, true, board);
        board.setPiece(new Pawn(1, 3, true, board), 1, 3);
        assertFalse(r.isPossibleMove(0,  3));
    }
    
    @Test
    public void testRookWhiteKingsideCastle() {
        Board board = new Board();
        board.removePiece(4, 7);
        board.removePiece(5, 7);
        board.removePiece(6, 7);
        board.removePiece(7, 7);
        Rook r = new Rook(7, 7, true, board);
        assertTrue(r.isPossibleMove(5, 7));
    }
    
    @Test
    public void testRookWhiteQueensideCastle() {
        Board board = new Board();
        board.removePiece(0, 7);
        board.removePiece(1, 7);
        board.removePiece(2, 7);
        board.removePiece(3, 7);
        Rook r = new Rook(0, 7, true, board);
        assertTrue(r.isPossibleMove(3, 7));
    }
    
    @Test
    public void testRookBlackKingsideCastle() {
        Board board = new Board();
        board.removePiece(5, 0);
        board.removePiece(6, 0);
        board.removePiece(7, 0);
        Rook r = new Rook(7, 0, false, board);
        assertTrue(r.isPossibleMove(5,0));
    }
    
    @Test
    public void testRookBlackQueensideCastle() {
        Board board = new Board();
        board.removePiece(0, 0);
        board.removePiece(1, 0);
        board.removePiece(2, 0);
        board.removePiece(3, 0);
        Rook r = new Rook(0, 0, false, board);
        assertTrue(r.isPossibleMove(3, 0));
    }
    
    @Test
    public void testRookWhiteCantCastleBlockedKingside() {
        Board board = new Board();
        board.removePiece(4, 7);
        board.removePiece(6, 7);
        board.removePiece(7, 7);
        Rook r = new Rook(7, 7, true, board);
        assertFalse(r.isPossibleMove(5, 7));
    }
    
    @Test
    public void testRookWhiteCantCastleBlockedQueenside() {
        Board board = new Board();
        board.removePiece(0, 7);
        board.removePiece(1, 7);
        board.removePiece(3, 7);
        Rook r = new Rook(0, 7, true, board);
        assertFalse(r.isPossibleMove(3, 7));
    }
    
    @Test
    public void testRookBlackCantCastleBlockedKingside() {
        Board board = new Board();
        board.removePiece(5, 0);
        board.removePiece(7, 0);
        Rook r = new Rook(7, 0, false, board);
        assertFalse(r.isPossibleMove(5, 7));
    }

    @Test
    public void testRookBlackCantCastleBlockedQueenside() {
        Board board = new Board();
        board.removePiece(0, 0);
        board.removePiece(2, 0);
        board.removePiece(3, 0);
        Rook r = new Rook(0, 0, false, board);
        assertFalse(r.isPossibleMove(3, 0));
    }
}
