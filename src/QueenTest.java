import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class QueenTest { 

    @Test 
    public void testQueenFreelyMoveDiagonally() {
        Board board = new Board();
        Queen q = new Queen(3, 3, true, board);
        assertTrue(q.isPossibleMove(1, 5));
    }
    
    @Test
    public void testQueenBlockedByPiece() {
        Board board = new Board();
        Queen q = new Queen(3, 3, true, board);
        Pawn p = new Pawn(2, 5, true, board);
        p.move(2, 4);
        assertFalse(q.isPossibleMove(1, 5));
    }
    
    @Test
    public void testQueenCapturePiece() {
        Board board = new Board();
        Queen q = new Queen(3, 3, true, board);
        Pawn p = new Pawn(2, 3, false, board);
        p.move(2, 4);
        assertTrue(q.isPossibleMove(2, 4));
    }
    
    @Test
    public void testQueenCantCaptureSameColor() {
        Board board = new Board();
        Queen q = new Queen(3, 3, true, board);
        Pawn p = new Pawn(2, 5, true, board);
        p.move(2, 4);
        assertFalse(q.isPossibleMove(2, 4));
    }
    
    @Test
    public void testQueenUninhibitedMovingLeft() {
        Board board = new Board();
        Queen q = new Queen(3, 3, true, board);
        assertTrue(q.isPossibleMove(0,  3));
    }
    
    @Test
    public void testQueenUninhibitedMovingDown() {
        Board board = new Board();
        Queen q = new Queen(3, 3, true, board);
        assertTrue(q.isPossibleMove(3,  5));
    }
    
    @Test
    public void testQueenUninhibitedMovingRight() {
        Board board = new Board();
        Queen q = new Queen(3, 3, true, board);
        assertTrue(q.isPossibleMove(7,  3));
    }
    
    @Test
    public void testQueenUninhibitedMovingUp() {
        Board board = new Board();
        Queen q = new Queen(3, 3, true, board);
        assertTrue(q.isPossibleMove(3,  2));
    }
    
    @Test
    public void testQueenCapture() {
        Board board = new Board();
        Queen q = new Queen(3, 3, true, board);
        board.setPiece(new Pawn(3, 2, false, board), 3, 2);
        assertTrue(q.isPossibleMove(3,  2));
    } 
    
    @Test
    public void testQueenCantCaptureOwnPiece() {
        Board board = new Board();
        Queen q = new Queen(3, 3, true, board);
        board.setPiece(new Pawn(3, 2, true, board), 3, 2);
        assertFalse(q.isPossibleMove(3,  2));
    }
    
    @Test
    public void testQueenCantMoveBlocked() {
        Board board = new Board();
        Queen q = new Queen(3, 3, true, board);
        board.setPiece(new Pawn(1, 3, true, board), 1, 3);
        assertFalse(q.isPossibleMove(0,  3));
    }

}
