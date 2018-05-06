package oxgame.app;

public class CoordinatesValidator {

    public static int validate(String value) throws IllegalArgumentException{
        if (value.trim().equals("") || !isNumber(value)) {
            throw new IllegalArgumentException("Wrong coordinate. Pick the number from the board.");
        } else {
            return Integer.parseInt(value);
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
