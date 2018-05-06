package oxgame.app;

public class CoordinatesValidator {

    public static int validate(String value, int maxBoardNumber) throws IllegalArgumentException{
        int numericValue = 0;
        if (value.trim().equals("") || !isNumber(value)) {
            throw new IllegalArgumentException("Wrong coordinate. Pick the number from the board.");
        } else {
            numericValue = Integer.parseInt(value);
        }
        if (numericValue > maxBoardNumber || numericValue < 1) {
            throw new IllegalArgumentException("The number which you piked exceeds the board.");
        } else {
            return numericValue;
        }
    }

    private static boolean isNumber(String possibleNumber) {
        try {
            double d = Double.parseDouble(possibleNumber);
        }
        catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
}
