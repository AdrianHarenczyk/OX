package ox.app.validators;

import ox.app.exceptions.WrongArgumentException;
import ox.app.languages.InstructionDriver;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class BoardValidator {
    private static final int MIN_BOARD_SIZE = 3;
    private static final int MAX_BOARD_SIZE = 100;

    public static int validateHeight(Supplier<String> input, Consumer<String> output, InstructionDriver instructionDriver) {
        return validateAndAssign(instructionDriver.validateHeightMessage(), input, output, instructionDriver);
    }

    public static int validateWidth(Supplier<String> input, Consumer<String> output, InstructionDriver instructionDriver) {
        return validateAndAssign(instructionDriver.validateWidthMessage(), input, output, instructionDriver);
    }

    private static int validateAndAssign(String message, Supplier<String> input, Consumer<String> output, InstructionDriver instructionDriver) {
        output.accept(message);
        int number;
        while (true) {
            try {
                number = ifNumberAssign(input.get(), instructionDriver);
                return number;
            } catch (WrongArgumentException e) {
                output.accept(e.getMessage());
            }
        }
    }

    private static int ifNumberAssign(String possibleNumber, InstructionDriver instructionDriver) throws WrongArgumentException {
        int actualNumber;
        if (InputIsNumberValidator.isNumber(possibleNumber) &&
                (actualNumber = Integer.parseInt(possibleNumber)) <= MAX_BOARD_SIZE &&
                actualNumber >= MIN_BOARD_SIZE) {
            return Integer.parseInt(possibleNumber);
        } else {
            throw new WrongArgumentException(instructionDriver.stringIsNotIntegerOrExceedsError());
        }
    }
}
