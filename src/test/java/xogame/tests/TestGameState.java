package xogame.tests;
import org.testng.annotations.Test;
import xogame.app.*;
import static org.testng.Assert.assertEquals;

/**
 * this test class is about testing xogame.app.GameState flow.
 */
class TestGameState {

    @Test
    public void initialStateChangesAfterUserInput() {
        // Given
        GameState gameState = new InitialState(new Player("user",Player.Symbol.X));
        // When
        gameState.nextState("Text");
        // Then
        assertEquals(gameState.getClass(),InitialState.class);
    }
}
