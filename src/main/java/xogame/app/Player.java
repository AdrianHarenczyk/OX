package xogame.app;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Player {
    private String name;
    private Symbol symbol;

    public Player(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public static Player playerCreator(Supplier<String> input, Consumer<String> output) {
        output.accept("Please provide player name.");
        String playerName = input.get();
        output.accept("Now please choose symbol for " + playerName);
        Symbol chosenSymbol = Symbol.valueOf(input.get());
        return new Player(playerName, chosenSymbol);
    }

    @Override
    public String toString() {
        return name + " with symbol " + symbol;
    }
}
