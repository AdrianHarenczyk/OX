package ox.app.validators;

import ox.app.exceptions.WrongArgumentException;
import ox.app.game.Board;
import ox.app.languages.InstructionDriver;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class WinningStrokeValidator {
    private static final int MINIMUM_STROKE_VALUE = 2;

    public static int validate(Board board, Supplier<String> input, Consumer<String> output, InstructionDriver instructionDriver) {
        output.accept(instructionDriver.passNumberOfStrokesToWinMessage());
        int validInteger;
        while (true) {
            try {
                validInteger = ifNumberAssign(input.get(),instructionDriver);
                if (checkWithBoth(board,validInteger,instructionDriver)) {
                    return validInteger;
                }
            } catch (WrongArgumentException e) {
                output.accept(e.getMessage());
            }
        }
    }
    private static boolean checkOneDimension(int dimension, int checkedStroke, String message) throws WrongArgumentException{
        boolean isCheckStrokeValueCorrect = dimension >= checkedStroke && checkedStroke >= MINIMUM_STROKE_VALUE;
        if (!isCheckStrokeValueCorrect) {
            throw new WrongArgumentException(message);
        } else {
            return true;
        }
    }
    private static int ifNumberAssign(String possibleNumber, InstructionDriver instructionDriver) throws WrongArgumentException {
        if (InputIsNumberValidator.isNumber(possibleNumber)) {
            return Integer.parseInt(possibleNumber);
        } else {
            throw new WrongArgumentException(instructionDriver.stringIsNotIntegerError());
        }
    }
    private static boolean checkWithBoth(Board board, int checkedStroke, InstructionDriver instructionDriver) throws WrongArgumentException{
        return checkOneDimension(board.getWidth(),checkedStroke,instructionDriver.exceedsBoardWidthError()) &&
                checkOneDimension(board.getHeight(),checkedStroke,instructionDriver.exceedsBoardHeightError());
    }

}
