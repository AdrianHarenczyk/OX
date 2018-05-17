package ox.app.validators;

import ox.app.exceptions.WrongArgumentException;
import ox.app.languages.Messenger;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class BoardValidator {
    private static final int MIN_BOARD_SIZE = 3;
    private static final int MAX_BOARD_SIZE = 100;

    public static int validateHeight(Supplier<String> input, Consumer<String> output, Messenger messenger) {
        return validateAndAssign(messenger.validateHeightMessage(), input, output, messenger);
    }

    public static int validateWidth(Supplier<String> input, Consumer<String> output, Messenger messenger) {
        return validateAndAssign(messenger.validateWidthMessage(), input, output, messenger);
    }

    private static int validateAndAssign(String message, Supplier<String> input, Consumer<String> output, Messenger messenger) {
        output.accept(message);
        int number;
        while (true) {
            try {
                number = ifNumberAssign(input.get(), messenger);
                return number;
            } catch (WrongArgumentException e) {
                output.accept(e.getMessage());
            }
        }
    }

    private static int ifNumberAssign(String possibleNumber, Messenger messenger) throws WrongArgumentException {
        int actualNumber;
        if (InputIsNumberValidator.isNumber(possibleNumber) &&
                (actualNumber = Integer.parseInt(possibleNumber)) <= MAX_BOARD_SIZE &&
                actualNumber >= MIN_BOARD_SIZE) {
            return Integer.parseInt(possibleNumber);
        } else {
            throw new WrongArgumentException(messenger.stringIsNotIntegerOrExceedsError());
        }
    }
}
