package oxgame.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import oxgame.app.exceptions.WrongArgumentException;
import oxgame.app.game.Player;
import oxgame.app.utility.RoundBuffer;
import oxgame.app.game.Symbol;

public class TestRoundBuffer {
    private static RoundBuffer buffer;

    @BeforeTest
    private static void initialize() throws WrongArgumentException {
        buffer = new RoundBuffer();
        buffer.addPlayer(new Player("Adam",Symbol.X));
        buffer.addPlayer(new Player("Eve",Symbol.O));
    }

    @Test
    public static void checkIfSwapChangesPlayers() {
        // When
        Player adam = buffer.takePlayer();
        buffer.swapPlayers();
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
