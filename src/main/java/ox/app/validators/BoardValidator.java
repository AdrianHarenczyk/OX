package ox.app.validators;

import ox.app.exceptions.WrongArgumentException;
import ox.app.io.InputOutput;
import ox.app.languages.Messenger;

public class BoardValidator {
    private static final int MIN_BOARD_SIZE = 3;
    private static final int MAX_BOARD_SIZE = 100;

    public static int validateHeight(InputOutput inputOutput, Messenger messenger) {
        return validateAndAssign(messenger.validateHeightMessage(), inputOutput, messenger);
    }

    public static int validateWidth(InputOutput inputOutput, Messenger messenger) {
        return validateAndAssign(messenger.validateWidthMessage(), inputOutput, messenger);
    }

    private static int validateAndAssign(String message, InputOutput inputOutput, Messenger messenger) {
        inputOutput.message(message);
        int number;
        while (true) {
            try {
                number = ifNumberAssign(inputOutput.input(), messenger);
                return number;
            } catch (WrongArgumentException e) {
                inputOutput.message(e.getMessage());
            }
        }
    }

    private static int ifNumberAssign(String possibleNumber, Messenger messenger) throws WrongArgumentException {
        possibleNumber = possibleNumber.trim();
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
