package ox.app.validators;

import ox.app.exceptions.WrongArgumentException;
import ox.app.game.Coordinate;
import ox.app.game.Board;
import ox.app.languages.InstructionDriver;

public class CoordinateValidator {

    public static int validate(String value, int maxBoardNumber, Board board, InstructionDriver instructionDriver) throws WrongArgumentException {
        int numericValue;
        if (value.trim().equals("") || !InputIsNumberValidator.isNumber(value)) {
            throw new WrongArgumentException(instructionDriver.wrongCoordinateError());
        } else {
            numericValue = Integer.parseInt(value);
        }
        if (numericValue > maxBoardNumber || numericValue < 1) {
            throw new WrongArgumentException(instructionDriver.numberExceedsTheBoardError());
        } else if (
                (board.getSymbol(Coordinate.apply(Integer.parseInt(value))))
                        !=
                        null) {
            throw new WrongArgumentException(instructionDriver.positionAlreadyTakenError());
        } else {
            return numericValue;
        }
    }

    private CoordinateValidator() {
    }
}
