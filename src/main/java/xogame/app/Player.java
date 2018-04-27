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

    public static Player playerCreator(Supplier<String> input, Consumer<String> output) throws IllegalArgumentException{
        String name = getNameFromInput(input,output);
        Symbol symbol = getSymbolFromInput(input,output);
        return new Player(name,symbol);
    }
    public static Player playerCreator(Supplier<String> input, Consumer<String> output, Player player) {
        String name = getNameFromInput(input,output);
        Symbol symbol = returnOtherSymbol(player);
        return new Player(name,symbol);
    }


    private static String getNameFromInput(Supplier<String> input, Consumer<String> output) {
        output.accept("Please provide player name.");
        return input.get();
    }

    private static Symbol getSymbolFromInput(Supplier<String> input, Consumer<String> output) throws IllegalArgumentException{
        output.accept("Now please choose symbol.");
        return SymbolValidator.validateSymbol(input);
    }

    private static Symbol returnOtherSymbol(Player firstPlayer) {
        return firstPlayer.symbol.otherSymbol();
    }


    @Override
    public String toString() {
        return name + " with symbol " + symbol;
    }
}
