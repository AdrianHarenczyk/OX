package oxgame.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ox.app.exceptions.WrongArgumentException;
import ox.app.game.Board;
import ox.app.game.Player;
import ox.app.game.ScoreBoard;
import ox.app.game.Symbol;
import ox.app.states.GameState;
import ox.app.states.RunState;
import ox.app.utility.RoundBuffer;

import java.util.function.Consumer;

/**
 * this test class is about testing GameState flow.
 */
public class TestGameState {
    private static GameState gameState;
    private static final int WINNING_STROKE = 3;

    @BeforeTest
    private static void initializeTests() throws WrongArgumentException{
        RoundBuffer playerList = new RoundBuffer();
        playerList.addPlayer(new Player("Adam",Symbol.O));
        playerList.addPlayer(new Player("Roman",Symbol.X));

        ScoreBoard scoreBoard = new ScoreBoard(playerList,System.out::println);
        Consumer<String> output = System.out::println;
        gameState = new RunState(playerList,output,Board.newBoard(3,3),scoreBoard,0,WINNING_STROKE);
        gameState.showState();
    }
    @Test(expectedExceptions = WrongArgumentException.class)
    public static void whenWrongInputNextStateThrowsIAException() throws WrongArgumentException {
        // Given
        // When
        gameState = gameState.nextState("x");
    }




}
