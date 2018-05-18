package ox.app.validators;

import ox.app.exceptions.WrongArgumentException;
import ox.app.game.Board;
import ox.app.game.Coordinate;
import ox.app.languages.Messenger;

public class CoordinateValidator {

    private CoordinateValidator() {
    }

    public static int validate(String value, int maxBoardNumber, Board board, Messenger messenger) throws WrongArgumentException {
        int numericValue;
        if (value.trim().equals("") || !InputIsNumberValidator.isNumber(value)) {
            throw new WrongArgumentException(messenger.wrongCoordinateError());
        } else {
            numericValue = Integer.parseInt(value);
        }
        if (numericValue > maxBoardNumber || numericValue < 1) {
            throw new WrongArgumentException(messenger.numberExceedsTheBoardError());
        } else if (
                (board.getSymbol(Coordinate.apply(Integer.parseInt(value))))
                        !=
                        null) {
            throw new WrongArgumentException(messenger.positionAlreadyTakenError());
        } else {
            return numericValue;
        }
    }
}
