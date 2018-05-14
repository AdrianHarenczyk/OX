package ox.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ox.app.game.Board;
import ox.app.game.Coordinate;

public class TestBoard {
    private static Board board;
    private static int size;

    @BeforeTest
    private static void initialize() {
        board = Board.newBoard(3,3);
        size = board.size();
    }

    @Test
    public static void whenPassed3x3BoardSizeIs9() {
        // Given
        // When
        int nine = 9;
        // Then
        Assert.assertEquals(nine,size);
    }
    @Test
    public static void methodReturnsNullWhenGetSymbolWithNoSymbolInside() {
        // Given
        Coordinate coordinate = Coordinate.apply(1);
        // When
        // Then
        Assert.assertNull(board.getSymbol(coordinate));
    }
}
