package oxgame.app;

public class CoordinatesValidator {

    public static int validate(String value, int maxBoardNumber) throws IllegalArgumentException{
        int numericValue;
        if (value.trim().equals("") || !isNumber(value)) {
            throw new IllegalArgumentException("Wrong coordinate. Pick the number from the board.\n");
        } else {
            numericValue = Integer.parseInt(value);
        }
        if (numericValue > maxBoardNumber || numericValue < 1) {
            throw new IllegalArgumentException("The number which you picked exceeds the board.\nPick a number from the board.\n");
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
