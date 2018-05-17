package ox.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ox.app.game.Board;
import ox.app.game.Coordinate;
import ox.app.game.Symbol;
import ox.app.utility.VictoryChecker;

public class TestWinningCondition {
    private static Board board;

    @BeforeMethod
    private static void init() {
        board = Board.newBoard(5, 5);
    }

    private static void placeMultipleSymbols(int... coordinates) {
        for (int coordinate : coordinates) {
            board.placeSymbol(coordinate, Symbol.X);
        }
    }

    @Test(dataProvider = "slash")
    public static void whenSlashWithThreeMatchingSymbolsVCReturnsTrue(Integer first, Integer second, Integer third) {
        // Given
        // When
        placeMultipleSymbols(first, second);
        board.placeSymbol(third, Symbol.X);
        boolean result = VictoryChecker.check(Coordinate.apply(third), board, 3);

        // Then
        Assert.assertTrue(result);
    }

    @Test(dataProvider = "slashWrong")
    public static void whenSlashWithoutWinningStrokeVCReturnsFalse(Integer first, Integer second, Integer third) {
        // Given
        // When
        placeMultipleSymbols(first, second);
        board.placeSymbol(third, Symbol.X);
        boolean result = VictoryChecker.check(Coordinate.apply(third), board, 3);
        // Then
        Assert.assertFalse(result);
    }

    @Test(dataProvider = "horizontal")
    public static void whenHorizontalWithThreeMatchingSymbolsVCReturnsTrue(Integer first, Integer second, Integer third) {
        // Given
        // When
        placeMultipleSymbols(first, second);
        board.placeSymbol(third, Symbol.X);
        boolean result = VictoryChecker.check(Coordinate.apply(third), board, 3);

        // Then
        Assert.assertTrue(result);
    }

    @Test(dataProvider = "horizontalWrong")
    public static void whenHorizontalWithoutWinningStrokeVCReturnsFalse(Integer first, Integer second, Integer third) {
        // Given
        // When
        placeMultipleSymbols(first, second);
        board.placeSymbol(third, Symbol.X);
        boolean result = VictoryChecker.check(Coordinate.apply(third), board, 3);
        // Then
        Assert.assertFalse(result);
    }

    @Test(dataProvider = "otherPatterns")
    public static void withPatternsWhichDoNotMatchWinningStreakVCReturnsFalse(Integer first, Integer second, Integer third, Integer fourth, Integer fifth) {
        // Given
        // When
        placeMultipleSymbols(first, second, third, fourth);
        board.placeSymbol(fifth, Symbol.X);
        boolean result = VictoryChecker.check(Coordinate.apply(fifth), board, 3);
        // Then
        Assert.assertFalse(result);
    }

    @DataProvider(name = "slash")
    Object[][] slash() {
        return new Object[][]{
                {1, 7, 13},
                {2, 8, 14},
                {3, 9, 15},
                {11, 17, 23},
                {12, 18, 24},
                {13, 19, 25},
                {11, 7, 3},
                {12, 8, 4},
                {13, 9, 5},
                {13, 5, 9},
                {5, 9, 13}
        };
    }

    @DataProvider(name = "slashWrong")
    Object[][] slashWrong() {
        return new Object[][]{
                {14, 20, 26},
                {24, 20, 16},
                {19, 15, 11},
                {9, 15, 21},
                {15, 21, 9},
                {11, 19, 15},
                {17, 11, 5},
                {11, 5, 17},
                {17, 5, 11},
                {14, 10, 6},
                {6, 14, 10}
        };
    }

    @DataProvider(name = "horizontal")
    Object[][] horizontal() {
        return new Object[][]{
                {1, 2, 3},
                {8, 9, 10},
                {13, 14, 15},
                {12, 13, 11},
                {18, 16, 17},
                {23, 22, 21},
                {3, 2, 1},
                {3, 1, 2}
        };
    }

    @DataProvider(name = "horizontalWrong")
    Object[][] horizontalWrong() {
        return new Object[][]{
                {7, 6, 5},
                {5, 6, 7},
                {16, 17, 15},
                {19, 20, 21},
        };
    }

    @DataProvider(name = "otherPatterns")
    Object[][] otherPatterns() {
        return new Object[][]{
                {1, 7, 3, 9, 5},
                {6, 2, 8, 4, 10},
                {1, 7, 11, 17, 21},
                {4, 10, 14, 20, 24},
                {2, 6, 12, 16, 22},
                {22, 16, 12, 6, 2},
                {21, 3, 7, 13, 9},
                {25, 7, 8, 12, 13},
                {19, 20, 11, 12, 10},
                {16, 17, 1, 7, 25},
                {2, 3, 11, 14, 15}
        };
    }


}
