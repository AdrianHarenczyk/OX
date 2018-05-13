package ox.app.validators;

import ox.app.exceptions.WrongArgumentException;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class BoardValidator {
    private static final String VALIDATE_WIDTH_INFO = "Please pass desired board width\n" +
                                    "(only integer higher than zero and lower than 51).";
    private static final String VALIDATE_HEIGHT_INFO = "Now please pass desired board height.";

    public static int validateHeight(Supplier<String> input, Consumer<String> output) {
        return validateAndAssign(VALIDATE_HEIGHT_INFO,input,output);
    }
    public static int validateWidth(Supplier<String> input, Consumer<String> output) {
        return validateAndAssign(VALIDATE_WIDTH_INFO,input,output);
    }

    private static int validateAndAssign(String message, Supplier<String> input, Consumer<String> output) {
        output.accept(message);
        int number;
        while (true) {
            try {
                number = ifNumberAssign(input.get());
                return number;
            } catch (WrongArgumentException e) {
                output.accept(e.getMessage());
            }
        }
    }
    private static int ifNumberAssign(String possibleNumber) throws WrongArgumentException{
        if (InputIsNumberValidator.isNumber(possibleNumber)) {
            return Integer.parseInt(possibleNumber);
        } else {
            throw new WrongArgumentException("Passed text is not a number. Try again.");
        }
    }
}
