package xogame.tests;
import org.testng.annotations.Test;
import xogame.app.CurrentState;
import xogame.app.GameState;
import xogame.app.InitialState;
import xogame.app.Player;

import static org.testng.Assert.assertNotEquals;

/**
 * this test class is about testing xogame.app.GameState flow.
 */
public class TestGameState {

    @Test
    public static void initialStateChangesAfterUserInput() {
        // Given
        GameState gameState = new InitialState(new Player("user",Player.Symbol.X));
        // When
        gameState.nextState("Text");
        // Then
        assertNotEquals(gameState.getClass(),CurrentState.class);
    }
}
