package xogame.tests;

import org.testng.annotations.Test;
import xogame.app.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static org.testng.Assert.assertEquals;

/**
 * this test class is about testing xogame.app.GameState flow.
 */
public class TestGameState {

    @Test
    public static void initialStateChangesAfterUserInput() {
        // Given
        List<Player> playerList = Arrays.asList(new Player("Adam", Symbol.X), new Player("Eve", Symbol.O));
        Consumer<String> output = System.out::println;
        GameState gameState = new InitialState(playerList,output);
        // When
        gameState = gameState.nextState("Text");
        // Then
        assertEquals(gameState.getClass(),GameInProgress.class);
    }


}
