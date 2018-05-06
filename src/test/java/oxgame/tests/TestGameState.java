package oxgame.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import oxgame.app.*;

import java.util.function.Consumer;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

/**
 * this test class is about testing GameState flow.
 */
public class TestGameState {
    private static GameState gameState;

    @BeforeTest
    private static void initializeTests() {
        RoundBuffer playerList = new RoundBuffer();
        playerList.addPlayer(new Player("Adam",Symbol.O));
        playerList.addPlayer(new Player("Roman",Symbol.X));

        Consumer<String> output = System.out::println;
        gameState = new GameInProgress(playerList,output,Board.newBoard(3,3));
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
