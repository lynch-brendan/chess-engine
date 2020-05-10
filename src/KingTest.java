import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class KingTest {

    @Test
    public void testKingMoveUnblockedUpLeft() {
        Board board = new Board();
        King k = new King(3, 4, true, board);
        assertTrue(k.isPossibleMove(2, 3));
    }
    
    @Test
    public void testKingMoveUnblockedLeft() {
        Board board = new Board();
        King k = new King(3, 4, true, board);
        assertTrue(k.isPossibleMove(2, 4));
    }
    
    @Test
    public void testKingMoveUnblockedDownLeft() {
        Board board = new Board();
        King k = new King(3, 4, true, board);
        assertTrue(k.isPossibleMove(2, 5));
    }
    
    @Test
    public void testKingMoveUnblockedDown() {
        Board board = new Board();
        King k = new King(3, 4, true, board);
        assertTrue(k.isPossibleMove(3, 5));
    }
    
    @Test
    public void testKingMoveUnblockedDownRight() {
        Board board = new Board();
        King k = new King(3, 4, true, board);
        assertTrue(k.isPossibleMove(4, 5));
    }
    
    @Test
    public void testKingMoveUnblockedRight() {
        Board board = new Board();
        King k = new King(3, 4, true, board);
        assertTrue(k.isPossibleMove(4, 4));
    }
    
    @Test
    public void testKingMoveUnblockedUpRight() {
        Board board = new Board();
        King k = new King(3, 4, true, board);
        assertTrue(k.isPossibleMove(4, 3));
    }
    
    @Test
    public void testKingMoveUnblockedUp() {
        Board board = new Board();
        King k = new King(3, 4, true, board);
        assertTrue(k.isPossibleMove(3, 3));
    }
    
    @Test
    public void testKingCaptureUpLeft() {
        Board board = new Board();
        King k = new King(3, 4, true, board);
        board.setPiece(new Pawn(2, 3, false, board), 2, 3);
        assertTrue(k.isPossibleMove(2, 3));
    }
    
    @Test
    public void testKingCaptureLeft() {
        Board board = new Board();
        King k = new King(3, 4, true, board);
        board.setPiece(new Pawn(2, 4, false, board), 2, 4);
        assertTrue(k.isPossibleMove(2, 4));
    }
    
    @Test
    public void testKingCaptureDownLeft() {
        Board board = new Board();
        King k = new King(3, 4, true, board);
        board.setPiece(new Pawn(2, 5, false, board), 2, 5);
        assertTrue(k.isPossibleMove(2, 5));
    }
    
    @Test
    public void testKingCaptureDown() {
        Board board = new Board();
        King k = new King(3, 4, true, board);
        board.setPiece(new Pawn(3, 5, false, board), 3, 5);
        assertTrue(k.isPossibleMove(3, 5));
    }
    
    @Test
    public void testKingCaptureDownRight() {
        Board board = new Board();
        King k = new King(3, 4, true, board);
        board.setPiece(new Pawn(4, 5, false, board), 4, 5);
        assertTrue(k.isPossibleMove(4, 5));
    }
    
    @Test
    public void testKingCaptureRight() {
        Board board = new Board();
        King k = new King(3, 4, true, board);
        board.setPiece(new Pawn(4, 4, false, board), 4, 4);
        assertTrue(k.isPossibleMove(4, 4));
    }
    
    @Test
    public void testKingCaptureUpRight() {
        Board board = new Board();
        King k = new King(3, 4, true, board);
        board.setPiece(new Pawn(4, 3, false, board), 4, 3);
        assertTrue(k.isPossibleMove(4, 3));
    }
    
    @Test
    public void testKingCaptureUp() {
        Board board = new Board();
        King k = new King(3, 4, true, board);
        board.setPiece(new Pawn(3, 3, false, board), 3, 3);
        assertTrue(k.isPossibleMove(3, 3));
    }
    
    @Test
    public void testKingCantCaptureOwnPiece() {
        Board board = new Board();
        King k = new King(3, 4, true, board);
        board.setPiece(new Pawn(2, 4, true, board), 2, 4);
        assertFalse(k.isPossibleMove(2, 4));
    }
    
    @Test
    public void testWhiteKingQueensideCastle() {
        Board board = new Board();
        board.removePiece(1, 7);
        board.removePiece(2, 7);
        board.removePiece(3, 7);
        board.removePiece(4, 7);
        King k = new King(4, 7, true, board);
        assertTrue(k.isPossibleMove(2, 7));
    }
    
    @Test
    public void testWhiteKingSideCastle() {
        Board board = new Board();
        board.removePiece(4, 7);
        board.removePiece(5, 7);
        board.removePiece(6, 7);
        board.removePiece(4, 7);
        King k = new King(4, 7, true, board);
        assertTrue(k.isPossibleMove(6, 7));
    }
    
    @Test
    public void testBlackKingQueensideCastle() {
        Board board = new Board();
        board.removePiece(1, 0);
        board.removePiece(2, 0);
        board.removePiece(3, 0);
        board.removePiece(4, 0);
        King k = new King(4, 0, false, board);
        assertTrue(k.isPossibleMove(2, 0));
    }
    
    @Test
    public void testBlackKingSideCastle() {
        Board board = new Board();
        board.removePiece(4, 0);
        board.removePiece(5, 0);
        board.removePiece(6, 0);
        board.removePiece(4, 0);
        King k = new King(4, 0, false, board);
        assertTrue(k.isPossibleMove(6, 0));
    }
    
    @Test
    public void testWhiteKingQueensideCantCastle() {
        Board board = new Board();
        board.removePiece(1, 7);
        board.removePiece(3, 7);
        board.removePiece(4, 7);
        King k = new King(4, 7, true, board);
        assertFalse(k.isPossibleMove(2, 7));
    }
    
    @Test
    public void testWhiteKingSideCantCastle() {
        Board board = new Board();
        board.removePiece(4, 7);
        board.removePiece(6, 7);
        board.removePiece(4, 7);
        King k = new King(4, 7, true, board);
        assertFalse(k.isPossibleMove(6, 7));
    }
    
    @Test
    public void testBlackKingQueensideCantCastle() {
        Board board = new Board();
        board.removePiece(1, 0);
        board.removePiece(2, 0);
        board.removePiece(4, 0);
        King k = new King(4, 0, false, board);
        assertFalse(k.isPossibleMove(2, 0));
    }
    
    @Test
    public void testBlackKingSideCantCastle() {
        Board board = new Board();
        board.removePiece(4, 0);
        board.removePiece(6, 0);
        board.removePiece(4, 0);
        King k = new King(4, 0, false, board);
        assertFalse(k.isPossibleMove(6, 0));
    }
    
    @Test
    public void testWhiteKingQueensideCantCastleKingMoved() {
        Board board = new Board();
        board.removePiece(1, 7);
        board.removePiece(2, 7);
        board.removePiece(3, 7);
        board.removePiece(4, 7);
        King k = new King(4, 7, true, board);
        k.move(1, 7);
        k.move(2, 7);
        assertFalse(k.isPossibleMove(2, 7));
    }
    
    @Test
    public void testWhiteKingSideCantCastleKingMoved() {
        Board board = new Board();
        board.removePiece(4, 7);
        board.removePiece(5, 7);
        board.removePiece(6, 7);
        board.removePiece(4, 7);
        King k = new King(4, 7, true, board);
        k.move(3, 7);
        k.move(3, 7);
        assertFalse(k.isPossibleMove(6, 7));
    }
    
    @Test
    public void testBlackKingQueensideCantCastleKingMoved() {
        Board board = new Board();
        board.removePiece(1, 0);
        board.removePiece(2, 0);
        board.removePiece(3, 0);
        board.removePiece(4, 0);
        King k = new King(4, 0, false, board);
        k.move(3, 0);
        k.move(4, 0);
        assertFalse(k.isPossibleMove(2, 0));
    }
    
    @Test
    public void testBlackKingSideCantCastleKingMoved() {
        Board board = new Board();
        board.removePiece(4, 0);
        board.removePiece(5, 0);
        board.removePiece(6, 0);
        King k = new King(4, 0, false, board);
        k.move(5, 0);
        k.move(4, 0);
        assertFalse(k.isPossibleMove(6, 0));
    }
    

}
