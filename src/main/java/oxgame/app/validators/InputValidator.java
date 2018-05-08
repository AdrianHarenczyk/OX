package oxgame.app.validators;

public class InputValidator {

    public static boolean isNumber(String possibleNumber) {
        try {
            int i = Integer.parseInt(possibleNumber);
        }
        catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
    private InputValidator() {
    }
}
