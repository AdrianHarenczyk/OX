package oxgame.app;

public class CoordinatesValidator {

    public static int validate(String value, int maxBoardNumber, Board board) throws IllegalArgumentException{
        int numericValue;
        if (value.trim().equals("") || !isNumber(value)) {
            throw new IllegalArgumentException("Wrong coordinate. Pick the number from the board.\n");
        } else {
            numericValue = Integer.parseInt(value);
        }
        if (numericValue > maxBoardNumber || numericValue < 1) {
            throw new IllegalArgumentException("The number which you picked exceeds the board.\nPick a number from the board.\n");
        } else if (
            (board.getSymbol(Coordinates.apply(Integer.parseInt(value))))
            !=
            null) {
            throw new IllegalArgumentException("This position is already taken. Use other number.\n");
        } else {
            return numericValue;
        }
    }

    private static boolean isNumber(String possibleNumber) {
        try {
            int i = Integer.parseInt(possibleNumber);
        }
        catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
}
