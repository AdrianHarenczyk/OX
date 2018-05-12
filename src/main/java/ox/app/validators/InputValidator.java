package ox.app.validators;

class InputValidator {

    static boolean isNumber(String possibleNumber) {
        try {
            Integer.parseInt(possibleNumber);
        }
        catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
    private InputValidator() {
    }
}
