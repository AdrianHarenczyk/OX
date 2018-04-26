package xogame.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import xogame.app.Player;
import xogame.app.RoundBuffer;
import xogame.app.Symbol;

public class TestRoundBuffer {
    private static RoundBuffer buffer;

    @BeforeTest
    private static void initialize() {
        buffer = new RoundBuffer();
        buffer.addPlayer(new Player("Adam",Symbol.X));
        buffer.addPlayer(new Player("Eve",Symbol.O));
    }

    @Test
    public static void checkIfTakeChangesPlayers() {
        // When
        Player adam = buffer.takePlayer();
        Player supposedToBeNotAdam = buffer.takePlayer();
        // Then
        Assert.assertNotEquals(adam,supposedToBeNotAdam);
    }
    @Test
    public static void checkIfMultipleTimesTakeChangesPlayersProperly() {
        // When
        Player adam = buffer.takePlayer();
        Player eva = buffer.takePlayer();
        Player shouldBeAdamAgain = buffer.takePlayer();
        // Then
        Assert.assertEquals(adam,shouldBeAdamAgain);

    }
}
