package ox.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ox.app.game.Player;
import ox.app.game.Symbol;
import ox.app.io.InputOutput;
import ox.app.languages.Language;
import ox.app.languages.Messenger;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class TestPlayer {
    private static Supplier<String> input;
    private static Consumer<String> output;
    private static Consumer<String> boardOutput;
    private static InputOutput inputOutput;
    private static Messenger messenger = new Messenger(Language.EN);

    @BeforeMethod
    public static void initialize() {
        output = s -> {
        };
        boardOutput = s -> {};
    }

    @Test(dataProvider = "playerNames")
    public static void ifGetSymbolFromInputReturnsXWhenXStringIsPassed(String playerNames, Symbol symbol) {
        // Given
        System.setIn(new ByteArrayInputStream(playerNames.getBytes()));
        input = new Scanner(System.in)::nextLine;
        inputOutput = new InputOutput(input,output,boardOutput);

        Player player = Player.playerCreator(inputOutput, messenger);
        // When
        Symbol playerSymbol = player.showSymbol();
        // Then
        Assert.assertEquals(symbol, playerSymbol);
    }

    @Test(dataProvider = "playerNamesSwitchSymbol")
    public static void ifWhenCreatingPlayerBasedUponAnotherPlayerReturnsPlayerWithOppositeSymbol(String firstPlayerInput, String secondPlayerInput, Symbol symbol) {
        // Given
        System.setIn(new ByteArrayInputStream(firstPlayerInput.getBytes()));
        input = new Scanner(System.in)::nextLine;
        inputOutput = new InputOutput(input,output,boardOutput);
        Player firstPlayer = Player.playerCreator(inputOutput, messenger);

        System.setIn(new ByteArrayInputStream(secondPlayerInput.getBytes()));
        input = new Scanner(System.in)::nextLine;
        inputOutput = new InputOutput(input,output,boardOutput);
        Player secondPlayer = Player.playerCreator(inputOutput, firstPlayer, messenger);

        // When
        Symbol secondPlayerSymbol = secondPlayer.showSymbol();
        // Then
        Assert.assertEquals(symbol, secondPlayerSymbol);
    }

    @DataProvider(name = "playerNames")
    Object[][] playerNames() {
        return new Object[][]{
                {"Roman\nX\n", Symbol.X},
                {"Adam\nO\n", Symbol.O}
        };
    }

    @DataProvider(name = "playerNamesSwitchSymbol")
    Object[][] playerNamesSwitchSymbol() {
        return new Object[][]{
                {"Roman\nX\n", "Adam\n", Symbol.O},
                {"Adam\nO\n", "Roman\n", Symbol.X}
        };
    }
}
