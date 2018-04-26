package xogame.tests;
import org.testng.annotations.Test;
import xogame.app.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

/**
 * this test class is about testing xogame.app.GameState flow.
 */
public class TestGameState {

    @Test
    public static void initialStateChangesAfterUserInput() {
        // Given
        GameState gameState = new InitialState(new Player("user", Symbol.X));
        // When
        gameState = gameState.nextState("Text");
        // Then
        assertEquals(gameState.getClass(),GameInProgress.class);
    }


}
