package ox.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ox.app.game.Player;
import ox.app.game.Symbol;
import ox.app.languages.Language;
import ox.app.languages.Messenger;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class TestPlayer {
    private static Supplier<String> input;
    private static Consumer<String> output;
    private static Messenger messenger = new Messenger(Language.EN);

    @BeforeMethod
    public static void initialize() {
        output = s -> {
        };
    }

    @Test(dataProvider = "playerNames")
    public static void ifGetSymbolFromInputReturnsXWhenXStringIsPassed(String playerNames, Symbol symbol) {
        // Given
        System.setIn(new ByteArrayInputStream(playerNames.getBytes()));
        input = new Scanner(System.in)::nextLine;
        Player player = Player.playerCreator(input, output, messenger);
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
        Player firstPlayer = Player.playerCreator(input, output, messenger);
        System.setIn(new ByteArrayInputStream(secondPlayerInput.getBytes()));
        input = new Scanner(System.in)::nextLine;
        Player secondPlayer = Player.playerCreator(input, output, firstPlayer, messenger);
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
