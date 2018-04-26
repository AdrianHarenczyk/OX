package xogame.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import xogame.app.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

/**
 * this test class is about testing xogame.app.GameState flow.
 */
public class TestGameState {
    private static GameState gameState;

    @BeforeTest
    private static void initializeTests() {
        List<Player> playerList = Arrays.asList(new Player("Adam", Symbol.X), new Player("Eve", Symbol.O));
        Consumer<String> output = System.out::println;
        gameState = new InitialState(playerList,output);
    }
    @Test
    public static void initialStateChangesAfterUserInput() {
        // Given
        // When
        gameState = gameState.nextState("Text");
        // Then
        assertEquals(gameState.getClass(),GameInProgress.class);
    }
    @Test
    public static void nextGameStateContainsPlayerWithOtherSymbol() {
        // Given
        // When
        gameState = gameState.nextState("Something");
        Player playerBefore = gameState.showPlayer();
        gameState = gameState.nextState("Other thing");
        Player playerAfter = gameState.showPlayer();
        // Then
        assertNotEquals(playerBefore,playerAfter);
    }


}
