package ox.app.utility;

import ox.app.game.Coordinate;
import ox.app.game.Symbol;
import ox.app.game.Board;

public class VictoryChecker {

    private VictoryChecker() {
    }

    public static boolean check(Coordinate coordinate, Board board, int winningStroke) {
        return verifyHorizontal(coordinate, board, winningStroke) ||
                verifyVertical(coordinate, board, winningStroke) ||
                verifySlash(coordinate, board, winningStroke);
    }

    private static int checkForMatches(Coordinate coordinate, Board board, Symbol currentSymbol, int counter, int condition, int moduloValue) {
        int coordinatesValue = coordinate.getValue();
        while (checkCondition(currentSymbol, board, coordinatesValue, condition, moduloValue)) {
            coordinatesValue += condition;
            counter++;
        }
        return counter;
    }

    private static int checkForMatches(Coordinate coordinate, Board board, Symbol currentSymbol, int counter, int condition) {
        int coordinatesValue = coordinate.getValue();
        while (checkCondition(currentSymbol, board, coordinatesValue, condition)) {
            coordinatesValue += condition;
            counter++;
        }
        return counter;
    }

    private static boolean checkCondition(Symbol currentSymbol, Board board, int coordinatesValue, int condition, int moduloValue) {
        return (currentSymbol.equals(board.getSymbol(coordinatesValue + condition)))
                &&
                (coordinatesValue % board.getWidth() != moduloValue);
    }

    private static boolean checkCondition(Symbol currentSymbol, Board board, int coordinatesValue, int condition) {
        return (currentSymbol.equals(board.getSymbol(coordinatesValue + condition)));
    }

    private static boolean verifyHorizontal(Coordinate coordinate, Board board, int winningStroke) {
        Symbol currentSymbol = board.getSymbol(coordinate);
        int counter = 1;
        counter = checkForMatches(coordinate, board, currentSymbol, counter, 1, 0);
        counter = checkForMatches(coordinate, board, currentSymbol, counter, -1, 1);
        return counter >= winningStroke;
    }

    private static boolean verifyVertical(Coordinate coordinate, Board board, int winningStroke) {
        Symbol currentSymbol = board.getSymbol(coordinate);
        int counter = 1;
        counter = checkForMatches(coordinate, board, currentSymbol, counter, -board.getWidth());
        counter = checkForMatches(coordinate, board, currentSymbol, counter, board.getWidth());
        return counter >= winningStroke;
    }

    private static boolean verifySlash(Coordinate coordinate, Board board, int winningStroke) {
        Symbol currentSymbol = board.getSymbol(coordinate);
        int counter = 1;
        counter = checkForMatches(coordinate, board, currentSymbol, counter, -board.getWidth() + 1, 0);
        counter = checkForMatches(coordinate, board, currentSymbol, counter, board.getWidth() - 1, 1);
        if (counter >= winningStroke) {
            return true;
        }
        counter = 1;
        counter = checkForMatches(coordinate, board, currentSymbol, counter, -board.getWidth() - 1, 1);
        counter = checkForMatches(coordinate, board, currentSymbol, counter, board.getWidth() + 1, 0);
        return counter >= winningStroke;
    }
}
