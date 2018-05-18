package ox.app.validators;

import ox.app.exceptions.WrongArgumentException;
import ox.app.game.Symbol;
import ox.app.io.InputOutput;
import ox.app.languages.Messenger;

public class SymbolValidator {
    private static final String X_SYMBOL = "X";
    private static final String O_SYMBOL = "O";
    private static final String ZERO_DIGIT = "0";

    private SymbolValidator() {
    }

    public static Symbol validateSymbol(InputOutput inputOutput, Messenger messenger) throws WrongArgumentException {
        String potentialSymbol = inputOutput.input().toUpperCase().trim();
        switch (potentialSymbol) {
            case X_SYMBOL:
            case O_SYMBOL:
                return Symbol.valueOf(potentialSymbol);
            case ZERO_DIGIT:
                return Symbol.O;
            default:
                throw new WrongArgumentException(messenger.symbolNotSupportedError());
        }
    }
}
