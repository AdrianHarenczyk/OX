package ox.app.validators;

class InputIsNumberValidator {

    static boolean isNumber(String possibleNumber) {
        try {
            Integer.parseInt(possibleNumber);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private InputIsNumberValidator() {
    }
}
