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
        gameState.showState();
    }
    @Test
    public static void nextGameStateContainsPlayerWithOtherSymbol() {
        // Given
        // When
        gameState = gameState.nextState("1");
        Player playerBefore = gameState.showPlayer();
        gameState = gameState.nextState("2");
        Player playerAfter = gameState.showPlayer();
        // Then
        assertNotEquals(playerBefore,playerAfter);
    }


}
