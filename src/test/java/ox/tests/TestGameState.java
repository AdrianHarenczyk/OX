package ox.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ox.app.exceptions.WrongArgumentException;
import ox.app.game.Board;
import ox.app.game.Player;
import ox.app.game.ScoreBoard;
import ox.app.game.Symbol;
import ox.app.languages.InstructionDriver;
import ox.app.languages.Language;
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
    private static InstructionDriver instructionDriver = new InstructionDriver(Language.EN);

    @BeforeTest
    private static void initializeTests() throws WrongArgumentException {
        RoundBuffer playerList = new RoundBuffer();
        playerList.addPlayer(new Player("Adam", Symbol.O));
        playerList.addPlayer(new Player("Roman", Symbol.X));

        ScoreBoard scoreBoard = new ScoreBoard(playerList, instructionDriver);
        Consumer<String> output = s -> {
        };
        Consumer<String> boardOutput = s -> {
        };
        InstructionDriver instructionDriver = new InstructionDriver(Language.EN);
        gameState = new RunState(playerList, output, Board.newBoard(3, 3),
                scoreBoard, 0, WINNING_STROKE,
                boardOutput, instructionDriver);
    }

    @Test(expectedExceptions = WrongArgumentException.class)
    public static void whenWrongInputNextStateThrowsIAException() throws WrongArgumentException {
        // Given
        // When
        gameState = gameState.nextState("x");
    }


}
