package ox.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ox.app.exceptions.WrongArgumentException;
import ox.app.game.Player;
import ox.app.game.Symbol;
import ox.app.utility.PlayerBuffer;

public class TestPlayerBuffer {
    private static PlayerBuffer buffer;

    @BeforeTest
    private static void initialize() throws WrongArgumentException {
        buffer = new PlayerBuffer();
        buffer.addPlayer(new Player("Adam", Symbol.X));
        buffer.addPlayer(new Player("Eve", Symbol.O));
    }

    @Test
    public static void checkIfSwapChangesPlayers() {
        // When
        Player adam = buffer.takePlayer();
        buffer.swapPlayers();
        Player supposedToBeNotAdam = buffer.takePlayer();
        // Then
        Assert.assertNotEquals(adam, supposedToBeNotAdam);
    }

    @Test
    public static void checkIfMultipleTimesTakeChangesPlayersProperly() {
        // When
        Player adam = buffer.takePlayer();
        Player eva = buffer.takePlayer();
        Player shouldBeAdamAgain = buffer.takePlayer();
        // Then
        Assert.assertEquals(adam, shouldBeAdamAgain);

    }

}
