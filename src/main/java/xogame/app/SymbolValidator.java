package xogame.app;

import java.util.function.Supplier;

public class SymbolValidator {
    public static Symbol validateSymbol(Supplier<String> input) throws IllegalArgumentException {
        String potentialSymbol = input.get().toUpperCase();
        if (potentialSymbol.equals("X") || potentialSymbol.equals("O"))
            return Symbol.valueOf(potentialSymbol);
        else
            throw new IllegalArgumentException("This symbol is not supported.");

    }
}
