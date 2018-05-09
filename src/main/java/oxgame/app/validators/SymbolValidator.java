package oxgame.app.validators;

import oxgame.app.game.Symbol;
import oxgame.app.exceptions.WrongArgumentException;

import java.util.function.Supplier;

public class SymbolValidator {
    private static final String X_SYMBOL = "X";
    private static final String O_SYMBOL = "O";
    private static final String ZERO_DIGIT = "0";

    private SymbolValidator(){}

    public static Symbol validateSymbol(Supplier<String> input) throws WrongArgumentException {
        String potentialSymbol = input.get().toUpperCase();
        if (potentialSymbol.equals(X_SYMBOL) || potentialSymbol.equals(O_SYMBOL)) {
            return Symbol.valueOf(potentialSymbol);
        }
        else if (potentialSymbol.equals(ZERO_DIGIT)) {
            return Symbol.O;
        } else {
            throw new WrongArgumentException("This symbol is not supported. Use X or O instead.");
        }
    }
}
