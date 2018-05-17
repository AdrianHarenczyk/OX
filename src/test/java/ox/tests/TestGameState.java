package ox.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ox.app.exceptions.WrongArgumentException;
import ox.app.game.Board;
import ox.app.game.Player;
import ox.app.game.ScoreBoard;
import ox.app.game.Symbol;
import ox.app.languages.Language;
import ox.app.languages.Messenger;
import ox.app.states.GameState;
import ox.app.states.RunState;
import ox.app.utility.RoundBuffer;

import java.util.function.Consumer;

/**
 * this test class is about testing GameState flow.
 */
public class TestGameState {
    private static final int WINNING_STROKE = 3;
    private static GameState gameState;
    private static Messenger messenger = new Messenger(Language.EN);

    @BeforeTest
    private static void initializeTests() throws WrongArgumentException {
        RoundBuffer playerList = new RoundBuffer();
        playerList.addPlayer(new Player("Adam", Symbol.O));
        playerList.addPlayer(new Player("Roman", Symbol.X));

        ScoreBoard scoreBoard = new ScoreBoard(playerList, TestGameState.messenger);
        Consumer<String> output = s -> {
        };
        Consumer<String> boardOutput = s -> {
        };
        Messenger messenger = new Messenger(Language.EN);
        gameState = new RunState(playerList, output, Board.newBoard(3, 3),
                scoreBoard, 0, WINNING_STROKE,
                boardOutput, messenger);
    }

    @Test(expectedExceptions = WrongArgumentException.class)
    public static void whenWrongInputNextStateThrowsIAException() throws WrongArgumentException {
        // Given
        // When
        gameState = gameState.nextState("x");
    }


}
