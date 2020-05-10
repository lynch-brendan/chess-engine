import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BishopTest {
    
    @Test
    public void testBishopFreelyMoveDiagonally() {
        Board board = new Board();
        Bishop b = new Bishop(3, 3, true, board);
        assertTrue(b.isPossibleMove(1, 5));
    }
    
    @Test
    public void testBishopBlockedByPiece() {
        Board board = new Board();
        Bishop b = new Bishop(3, 3, true, board);
        Pawn p = new Pawn(2, 5, true, board);
        p.move(2, 4);
        assertFalse(b.isPossibleMove(1, 5));
    }
    
    @Test
    public void testBishopCapturePiece() {
        Board board = new Board();
        Bishop b = new Bishop(3, 3, true, board);
        Pawn p = new Pawn(2, 3, false, board);
        p.move(2, 4);
        assertTrue(b.isPossibleMove(2, 4));
    }
    
    @Test
    public void testBishopCantCaptureSameColor() {
        Board board = new Board();
        Bishop b = new Bishop(3, 3, true, board);
        Pawn p = new Pawn(2, 5, true, board);
        p.move(2, 4);
        assertFalse(b.isPossibleMove(2, 4));
    }
}
