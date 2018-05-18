package ox.app.validators;

class InputIsNumberValidator {

    private InputIsNumberValidator() {
    }

    static boolean isNumber(String possibleNumber) {
        try {
            Integer.parseInt(possibleNumber);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
