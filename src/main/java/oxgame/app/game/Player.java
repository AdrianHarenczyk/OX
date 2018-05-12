package oxgame.app.game;

import oxgame.app.exceptions.WrongArgumentException;
import oxgame.app.utility.ConsoleColor;
import oxgame.app.validators.SymbolValidator;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Player {
    private String name;
    private Symbol symbol;
    private static int playerCounter = 1;

    public Player(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public static Player playerCreator(Supplier<String> input, Consumer<String> output) {
        String name = getNameFromInput(input,output);
        Symbol symbol = getSymbolFromInput(input,output);
        return new Player(name,symbol);
    }
    public static Player playerCreator(Supplier<String> input, Consumer<String> output, Player player) {
        String name = getNameFromInput(input,output);
        Symbol symbol = returnOtherSymbol(player);
        return new Player(name,symbol);
    }

    public Symbol showSymbol() {
        return this.symbol;
    }


    private static String getNameFromInput(Supplier<String> input, Consumer<String> output) {
        output.accept("Please provide " + playerCounter + " player name.");
        playerCounter++;
        return input.get();
    }

    private static Symbol getSymbolFromInput(Supplier<String> input, Consumer<String> output) {
        output.accept("Now please choose symbol: X or O.");
        while (true) {
                try {
                    return SymbolValidator.validateSymbol(input);
                } catch (WrongArgumentException e) {
                    output.accept(e.getMessage());
                }
        }
    }

    private static Symbol returnOtherSymbol(Player firstPlayer) {
        return firstPlayer.symbol.otherSymbol();
    }


    @Override
    public String toString() {
        return name + " with symbol " + symbol;
    }

}
