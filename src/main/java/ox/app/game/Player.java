package ox.app.game;

import ox.app.exceptions.WrongArgumentException;
import ox.app.io.InputOutput;
import ox.app.languages.Messenger;
import ox.app.validators.NameValidator;
import ox.app.validators.SymbolValidator;

public class Player {
    private static int playerCounter = 1;
    private final String name;
    private final Symbol symbol;

    public Player(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public static Player playerCreator(InputOutput inputOutput, Messenger messenger) {
        String name = getNameFromInput(inputOutput, messenger);
        Symbol symbol = getSymbolFromInput(inputOutput, messenger);
        return new Player(name, symbol);
    }

    public static Player playerCreator(InputOutput inputOutput, Player player, Messenger messenger) {
        String name = getNameFromInput(inputOutput, messenger);
        Symbol symbol = returnOtherSymbol(player);
        return new Player(name, symbol);
    }

    private static String getNameFromInput(InputOutput inputOutput, Messenger messenger) {
        inputOutput.message(messenger.pleaseProvideInstruction() + playerCounter + messenger.playerNameMessage());
        playerCounter++;
        return NameValidator.isNotEmpty(inputOutput, messenger);
    }

    private static Symbol getSymbolFromInput(InputOutput inputOutput, Messenger messenger) {
        inputOutput.message(messenger.pleaseChooseSymbolInstruction());
        while (true) {
            try {
                return SymbolValidator.validateSymbol(inputOutput, messenger);
            } catch (WrongArgumentException e) {
                inputOutput.message(e.getMessage());
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
