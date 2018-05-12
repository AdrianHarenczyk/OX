package ox.app.utility;

import ox.app.game.Coordinates;
import ox.app.game.Symbol;
import ox.app.game.Board;

public class VictoryChecker {

    private VictoryChecker() {
    }

    public static boolean check(Coordinates coordinates, Board board, int winningStroke) {
        return verifyHorizontal(coordinates,board,winningStroke) ||
                verifyVertical(coordinates,board,winningStroke) ||
                verifySlash(coordinates,board,winningStroke);
    }
    private static int checkForMatches(Coordinates coordinates, Board board, Symbol currentSymbol, int counter, int condition, int moduloValue) {
        int coordinatesValue = coordinates.getValue();
        while (checkCondition(currentSymbol,board,coordinatesValue, condition,moduloValue)) {
            coordinatesValue+= condition;
            counter++;
        }
        return counter;
    }

    private static int checkForMatches(Coordinates coordinates, Board board, Symbol currentSymbol, int counter, int condition) {
        int coordinatesValue = coordinates.getValue();
        while (checkCondition(currentSymbol,board,coordinatesValue, condition)) {
            coordinatesValue+= condition;
            counter++;
        }
        return counter;
    }

    private static boolean checkCondition(Symbol currentSymbol,Board board, int coordinatesValue, int condition, int moduloValue) {
        return (currentSymbol.equals(board.getSymbol(coordinatesValue + condition)))
                &&
                (coordinatesValue % board.getWidth() != moduloValue);
    }
    private static boolean checkCondition(Symbol currentSymbol,Board board, int coordinatesValue, int condition) {
        return (currentSymbol.equals(board.getSymbol(coordinatesValue + condition)));
    }

    private static boolean verifyHorizontal(Coordinates coordinates, Board board, int winningStroke){
        Symbol currentSymbol = board.getSymbol(coordinates);
        int counter = 1;
        counter = checkForMatches(coordinates, board, currentSymbol, counter,1,0);
        counter = checkForMatches(coordinates, board, currentSymbol, counter,-1,1);
        return counter>=winningStroke;
    }

    private static boolean verifyVertical(Coordinates coordinates, Board board, int winningStroke){
        Symbol currentSymbol = board.getSymbol(coordinates);
        int counter = 1;
        counter = checkForMatches(coordinates, board, currentSymbol, counter,-board.getWidth());
        counter = checkForMatches(coordinates, board, currentSymbol, counter,board.getWidth());
        return  counter>=winningStroke;
    }

    private static boolean verifySlash(Coordinates coordinates, Board board, int winningStroke){
        Symbol currentSymbol = board.getSymbol(coordinates);
        int counter = 1;
        counter = checkForMatches(coordinates, board, currentSymbol, counter,-board.getWidth() + 1,0);
        counter = checkForMatches(coordinates, board, currentSymbol, counter,board.getWidth() - 1,1);
        if (counter>=winningStroke) {
            return true;
        }
        counter = 1;
        counter = checkForMatches(coordinates, board, currentSymbol, counter,-board.getWidth() - 1,1);
        counter = checkForMatches(coordinates, board, currentSymbol, counter,board.getWidth() + 1,0);
        return counter>=winningStroke;
    }
}
