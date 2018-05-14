package ox.app.validators;

import ox.app.exceptions.WrongArgumentException;
import ox.app.game.Board;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class WinningStrokeValidator {
    private static final String USER_INSTRUCTIONS = "Please pass the number of characters\nwhich are required to win. (can't be less than 2\nnor more than one of board dimensions).";
    private static final String EXCEEDS_WIDTH = "This number exceeds width of the board,\nor is less than 2.\nPlease pass correct number.";
    private static final String EXCEEDS_HEIGHT = "This number exceeds height of the board,\nor is less than 2.\nPlease pass correct number.";
    private static final String IS_NOT_A_NUMBER = "Passed text is not an integer.\nTry again.";
    private static final int MINIMUM_STROKE_VALUE = 2;

    public static int validate(Board board, Supplier<String> input, Consumer<String> output) {
        output.accept(USER_INSTRUCTIONS);
        int validInteger;
        while (true) {
            try {
                validInteger = ifNumberAssign(input.get());
                if (checkWithBoth(board,validInteger)) {
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
    private static int ifNumberAssign(String possibleNumber) throws WrongArgumentException {
        if (InputIsNumberValidator.isNumber(possibleNumber)) {
            return Integer.parseInt(possibleNumber);
        } else {
            throw new WrongArgumentException(IS_NOT_A_NUMBER);
        }
    }
    private static boolean checkWithBoth(Board board, int checkedStroke) throws WrongArgumentException{
        return checkOneDimension(board.getWidth(),checkedStroke,EXCEEDS_WIDTH) &&
                checkOneDimension(board.getHeight(),checkedStroke,EXCEEDS_HEIGHT);
    }

}
