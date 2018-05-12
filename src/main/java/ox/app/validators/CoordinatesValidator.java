package ox.app.validators;

import ox.app.exceptions.WrongArgumentException;
import ox.app.game.Coordinates;
import ox.app.game.Board;

public class CoordinatesValidator {

    public static int validate(String value, int maxBoardNumber, Board board) throws WrongArgumentException {
        int numericValue;
        if (value.trim().equals("") || !InputValidator.isNumber(value)) {
            throw new WrongArgumentException("Wrong coordinate. Pick the number from the board.\n");
        } else {
            numericValue = Integer.parseInt(value);
        }
        if (numericValue > maxBoardNumber || numericValue < 1) {
            throw new WrongArgumentException("The number which you picked exceeds the board.\nPick a number from the board.\n");
        } else if (
            (board.getSymbol(Coordinates.apply(Integer.parseInt(value))))
            !=
            null) {
            throw new WrongArgumentException("This position is already taken. Use other number.\n");
        } else {
            return numericValue;
        }
    }
    private CoordinatesValidator() {
    }
}
