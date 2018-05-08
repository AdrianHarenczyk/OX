package oxgame.app;

class VictoryChecker {

    static boolean check(Coordinates coordinates, Board board, int winningStroke) {
        return verifyHorizontal(coordinates,board,winningStroke,1) ||
                verifyHorizontalBack(coordinates,board,winningStroke,-1) ||
                verifyVertical(coordinates,board,winningStroke,board.getWidth()) ||
                verifyVertical(coordinates,board,winningStroke,-board.getWidth()) ||
                verifySlash(coordinates,board,winningStroke,board.getWidth()+1) ||
                verifySlash(coordinates,board,winningStroke,board.getWidth()-1) ||
                verifySlash(coordinates,board,winningStroke,-board.getWidth()+1) ||
                verifySlash(coordinates,board,winningStroke,-board.getWidth()-1);
    }
    private static boolean verifyHorizontal(Coordinates coordinates, Board board, int winningStroke, int condition){
        Symbol currentSymbol = board.getSymbol(coordinates);
        int counter = 1;
        while (
                (currentSymbol.equals(board.getSymbol(Coordinates.apply(coordinates.getValue() + condition))))
                &&
                (coordinates.getValue()%board.getWidth() != 0)
                )
        {
            counter++;
            coordinates = Coordinates.apply(coordinates.getValue() + condition);
            if (counter == winningStroke) {
                return true;
            }
        }
        return false;
    }
    private static boolean verifyHorizontalBack(Coordinates coordinates, Board board, int winningStroke, int condition){
        Symbol currentSymbol = board.getSymbol(coordinates);
        int counter = 1;
        while (
                (currentSymbol.equals(board.getSymbol(Coordinates.apply(coordinates.getValue() + condition))))
                        &&
                        (coordinates.getValue()%board.getWidth() != 1)
                )
        {
            counter++;
            coordinates = Coordinates.apply(coordinates.getValue() + condition);
            if (counter == winningStroke) {
                return true;
            }
        }
        return false;
    }
    private static boolean verifyVertical(Coordinates coordinates, Board board, int winningStroke, int condition){
        Symbol currentSymbol = board.getSymbol(coordinates);
        int counter = 1;
        while ((currentSymbol.equals(board.getSymbol(Coordinates.apply(coordinates.getValue() + condition))))) {
            counter++;
            coordinates = Coordinates.apply(coordinates.getValue() + condition);
            if (counter == winningStroke) {
                return true;
            }
        }
        return false;
    }
    private static boolean verifySlash(Coordinates coordinates, Board board, int winningStroke, int condition) {
        Symbol currentSymbol = board.getSymbol(coordinates);
        int counter = 1;
        while (
                (currentSymbol.equals(board.getSymbol(Coordinates.apply(coordinates.getValue() + condition))))
                        &&
                        (coordinates.getValue()%board.getWidth() != 0)
                )
        {
            counter++;
            coordinates = Coordinates.apply(coordinates.getValue() + condition);
            if (counter == winningStroke) {
                return true;
            }
        }
        return false;
    }
    private static boolean verifySlashBack(Coordinates coordinates, Board board, int winningStroke, int condition) {
        Symbol currentSymbol = board.getSymbol(coordinates);
        int counter = 1;
        while (
                (currentSymbol.equals(board.getSymbol(Coordinates.apply(coordinates.getValue() + condition))))
                        &&
                        (coordinates.getValue()%board.getWidth() != 1)
                )
        {
            counter++;
            coordinates = Coordinates.apply(coordinates.getValue() + condition);
            if (counter == winningStroke) {
                return true;
            }
        }
        return false;
    }




}
