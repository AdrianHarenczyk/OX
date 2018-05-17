package ox.app.game;

import ox.app.exceptions.WrongArgumentException;
import ox.app.languages.Messenger;
import ox.app.validators.NameValidator;
import ox.app.validators.SymbolValidator;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Player {
    private static int playerCounter = 1;
    private final String name;
    private final Symbol symbol;

    public Player(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public static Player playerCreator(Supplier<String> input, Consumer<String> output, Messenger messenger) {
        String name = getNameFromInput(input, output, messenger);
        Symbol symbol = getSymbolFromInput(input, output, messenger);
        return new Player(name, symbol);
    }

    public static Player playerCreator(Supplier<String> input, Consumer<String> output, Player player, Messenger messenger) {
        String name = getNameFromInput(input, output, messenger);
        Symbol symbol = returnOtherSymbol(player);
        return new Player(name, symbol);
    }

    private static String getNameFromInput(Supplier<String> input, Consumer<String> output, Messenger messenger) {
        output.accept(messenger.pleaseProvideInstruction() + playerCounter + messenger.playerNameMessage());
        playerCounter++;
        return NameValidator.isNotEmpty(input, output, messenger);
    }

    private static Symbol getSymbolFromInput(Supplier<String> input, Consumer<String> output, Messenger messenger) {
        output.accept(messenger.pleaseChooseSymbolInstruction());
        while (true) {
            try {
                return SymbolValidator.validateSymbol(input, messenger);
            } catch (WrongArgumentException e) {
                output.accept(e.getMessage());
            }
        }
    }

    private static Symbol returnOtherSymbol(Player firstPlayer) {
        return firstPlayer.symbol.otherSymbol();
    }

    public Symbol showSymbol() {
        return this.symbol;
    }

    @Override
    public String toString() {
        return name + " : " + symbol;
    }

}
