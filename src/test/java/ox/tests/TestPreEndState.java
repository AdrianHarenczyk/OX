package ox.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ox.app.exceptions.WrongArgumentException;
import ox.app.game.Board;
import ox.app.game.Player;
import ox.app.game.ScoreBoard;
import ox.app.game.Symbol;
import ox.app.io.InputOutput;
import ox.app.languages.Language;
import ox.app.languages.Messenger;
import ox.app.states.GameState;
import ox.app.states.PreEndState;
import ox.app.states.RunState;
import ox.app.states.SummaryState;
import ox.app.utility.PlayerBuffer;

import java.util.function.Consumer;
import java.util.function.Supplier;


public class TestPreEndState {
    private static final Consumer<String> output = s -> {
    };
    private static final Consumer<String> boardOutput = s ->{};
    private static final Supplier<String> input = () -> "";
    private static InputOutput inputOutput;
    private static final int WINNING_STROKE = 3;
    private static final Messenger MESSENGER = new Messenger(Language.EN);
    private static Board board;
    private static PlayerBuffer playerBuffer;
    private static ScoreBoard scoreBoard;
    private static int currentBoardSize;

    @BeforeMethod
    private static void init() {
        board = Board.newBoard(5, 5);
        playerBuffer = new PlayerBuffer();
        Player playerOne = new Player("Adam", Symbol.X);
        Player playerTwo = new Player("Roman", Symbol.O);
        inputOutput = new InputOutput(input,output,boardOutput);

        try {
            playerBuffer.addPlayers(playerOne, playerTwo);
        } catch (WrongArgumentException e) {
            output.accept(e.getMessage());
        }
        scoreBoard = new ScoreBoard(playerBuffer, MESSENGER);
        currentBoardSize = board.size();

    }

    @Test
    public static void currentStateReturnsSummaryStateWhenRoundCounterIsGreaterThanThree() {
        // Given
        int roundCounterGreaterThanMaxRounds = 5;
        PreEndState preEndState = new PreEndState( inputOutput,  MESSENGER,  playerBuffer,
                 board,  scoreBoard, roundCounterGreaterThanMaxRounds, WINNING_STROKE, currentBoardSize);
        // When
        GameState returnedState = preEndState.nextState("Something");
        // Then
        Assert.assertEquals(SummaryState.class, returnedState.getClass());
    }

    @Test
    public static void currentStateReturnsRunStateWhenRoundCounterIsLessThanThree() {
        // Given
        int roundCounterLessThanMaxRounds = 2;
        PreEndState preEndState = new PreEndState( inputOutput,  MESSENGER,  playerBuffer,
                board,  scoreBoard, roundCounterLessThanMaxRounds, WINNING_STROKE, currentBoardSize);
        // When
        GameState returnedState = preEndState.nextState("Something");
        // Then
        Assert.assertEquals(RunState.class, returnedState.getClass());
    }

}
