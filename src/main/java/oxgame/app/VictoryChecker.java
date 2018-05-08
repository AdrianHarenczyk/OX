package oxgame.app;

public class VictoryChecker {

    public static boolean check(Coordinates coordinates, Board board, int winningStroke) {
        return verifyHorizontal(coordinates,board,winningStroke) ||
                verifyVertical(coordinates,board,winningStroke) ||
                verifySlash(coordinates,board,winningStroke);
    }
    /**
     * Verify if there is a win on HORIZONTAL.
     * */
    private static boolean verifyHorizontal(Coordinates coordinates, Board board, int winningStroke){
        Symbol currentSymbol = board.getSymbol(coordinates);
        int counter = 1;
        counter = horizontalRight(coordinates, board, currentSymbol, counter);
        counter = horizontalLeft(coordinates, board, currentSymbol, counter);
        if (counter>=winningStroke) {
            return true;
        }
        return false;
    }

    private static int horizontalRight(Coordinates coordinates, Board board, Symbol currentSymbol, int counter) {
        int condition = 1;
        int coordinatesValue = coordinates.getValue();
        while ( (currentSymbol.equals(board.getSymbol(coordinatesValue + condition) ))
                &&
                (coordinatesValue % board.getWidth() != 0) ) {
            coordinatesValue+=condition;
            counter++;

        }
        return counter;
    }
    private static int horizontalLeft(Coordinates coordinates, Board board, Symbol currentSymbol, int counter) {
        int condition = -1;
        int coordinatesValue = coordinates.getValue();
        while ( (currentSymbol.equals(board.getSymbol(coordinatesValue + condition) ))
                &&
                (coordinatesValue % board.getWidth() != 1) ) {
            coordinatesValue+=condition;
            counter++;
        }
        return counter;
    }
    /**
     * Verify if there is a win on VERTICAL.
     * */
    private static boolean verifyVertical(Coordinates coordinates, Board board, int winningStroke){
        Symbol currentSymbol = board.getSymbol(coordinates);
        int counter = 1;
        counter = verticalUp(coordinates, board, currentSymbol, counter);
        counter = verticalDown(coordinates, board, currentSymbol, counter);
        if (counter>=winningStroke) {
            return true;
        }
        return false;
    }

    private static int verticalUp(Coordinates coordinates, Board board, Symbol currentSymbol, int counter) {
        int condition = -board.getWidth();
        int coordinatesValue = coordinates.getValue();
        while ( (currentSymbol.equals(board.getSymbol(coordinatesValue + condition) ))) {
            coordinatesValue+=condition;
            counter++;
        }
        return counter;
    }
    private static int verticalDown(Coordinates coordinates, Board board, Symbol currentSymbol, int counter) {
        int condition = board.getWidth();
        int coordinatesValue = coordinates.getValue();
        while ( (currentSymbol.equals(board.getSymbol(coordinatesValue + condition) )) ) {
            coordinatesValue+=condition;
            counter++;
        }
        return counter;
    }
    /**
     * Verify if there is a win on DIAGONAL.
     * */
    private static boolean verifySlash(Coordinates coordinates, Board board, int winningStroke){
        Symbol currentSymbol = board.getSymbol(coordinates);
        int counter = 1;
        counter = slashUpRight(coordinates, board, currentSymbol, counter);
        counter = slashDownLeft(coordinates, board, currentSymbol, counter);
        if (counter>=winningStroke) {
            return true;
        }
        counter = 1;
        counter = slashUpLeft(coordinates, board, currentSymbol, counter);
        counter = slashDownRight(coordinates, board, currentSymbol, counter);
        if (counter>=winningStroke) {
            return true;
        }
        return false;
    }

    private static int slashUpRight(Coordinates coordinates, Board board, Symbol currentSymbol, int counter) {
        int condition = -board.getWidth() + 1;
        int coordinatesValue = coordinates.getValue();
        while ( (currentSymbol.equals(board.getSymbol(coordinatesValue + condition) ))
                &&
                (coordinatesValue % board.getWidth() != 0) ) {
            coordinatesValue+=condition;
            counter++;

        }
        return counter;
    }
    private static int slashDownLeft(Coordinates coordinates, Board board, Symbol currentSymbol, int counter) {
        int condition = board.getWidth() - 1;
        int coordinatesValue = coordinates.getValue();
        while ( (currentSymbol.equals(board.getSymbol(coordinatesValue + condition) ))
                &&
                (coordinatesValue % board.getWidth() != 1) ) {
            coordinatesValue+=condition;
            counter++;
        }
        return counter;
    }
    private static int slashUpLeft(Coordinates coordinates, Board board, Symbol currentSymbol, int counter) {
        int condition = -board.getWidth() - 1;
        int coordinatesValue = coordinates.getValue();
        while ( (currentSymbol.equals(board.getSymbol(coordinatesValue + condition) ))
                &&
                (coordinatesValue % board.getWidth() != 1) ) {
            coordinatesValue+=condition;
            counter++;

        }
        return counter;
    }
    private static int slashDownRight(Coordinates coordinates, Board board, Symbol currentSymbol, int counter) {
        int condition = board.getWidth() + 1;
        int coordinatesValue = coordinates.getValue();
        while ( (currentSymbol.equals(board.getSymbol(coordinatesValue + condition) ))
                &&
                (coordinatesValue % board.getWidth() != 0) ) {
            coordinatesValue+=condition;
            counter++;
        }
        return counter;
    }




}
