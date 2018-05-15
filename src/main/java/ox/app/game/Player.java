package ox.app.game;

import ox.app.exceptions.WrongArgumentException;
import ox.app.languages.InstructionDriver;
import ox.app.validators.NameValidator;
import ox.app.validators.SymbolValidator;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Player {
    private final String name;
    private final Symbol symbol;
    private static int playerCounter = 1;

    public Player(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public static Player playerCreator(Supplier<String> input, Consumer<String> output, InstructionDriver instructionDriver) {
        String name = getNameFromInput(input,output,instructionDriver);
        Symbol symbol = getSymbolFromInput(input,output,instructionDriver);
        return new Player(name,symbol);
    }
    public static Player playerCreator(Supplier<String> input, Consumer<String> output, Player player, InstructionDriver instructionDriver) {
        String name = getNameFromInput(input,output,instructionDriver);
        Symbol symbol = returnOtherSymbol(player);
        return new Player(name,symbol);
    }

    public Symbol showSymbol() {
        return this.symbol;
    }


    private static String getNameFromInput(Supplier<String> input, Consumer<String> output,InstructionDriver instructionDriver) {
        output.accept("Please provide " + playerCounter + " player name.");
        playerCounter++;
        return NameValidator.isNotEmpty(input,output);
    }

    private static Symbol getSymbolFromInput(Supplier<String> input, Consumer<String> output, InstructionDriver instructionDriver) {
        output.accept(instructionDriver.pleaseChooseSymbolInstruction());
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
        return name + " : " + symbol;
    }

}
