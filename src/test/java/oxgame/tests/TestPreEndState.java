package oxgame.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ox.app.exceptions.WrongArgumentException;
import ox.app.game.Board;
import ox.app.game.Player;
import ox.app.game.ScoreBoard;
import ox.app.game.Symbol;
import ox.app.states.GameState;
import ox.app.states.PreEndState;
import ox.app.states.RunState;
import ox.app.states.SummaryState;
import ox.app.utility.RoundBuffer;

import java.util.function.Consumer;


public class TestPreEndState {
    private static Board board;
    private static RoundBuffer playerBuffer;
    private static final Consumer<String> output = System.out::println;
    private static ScoreBoard scoreBoard;
    private static int currentBoardSize;

    @BeforeMethod
    private static void init() {
        board = Board.newBoard(5,5);
        playerBuffer = new RoundBuffer();
        Player playerOne = new Player("Adam",Symbol.X);
        Player playerTwo = new Player("Roman",Symbol.O);
        try {
            playerBuffer.addPlayers(playerOne,playerTwo);
        } catch (WrongArgumentException e) {
            output.accept(e.getMessage());
        }
        scoreBoard = new ScoreBoard(playerBuffer,output);
        currentBoardSize = board.size();
    }
    @Test
    public static void currentStateReturnsSummaryStateWhenRoundCounterIsGreaterThanThree() {
        // Given
        int roundCounterGreaterThanMaxRounds = 5;
        PreEndState preEndState = new PreEndState(playerBuffer, output, board, scoreBoard, roundCounterGreaterThanMaxRounds, currentBoardSize);
        // When
        GameState returnedState = preEndState.nextState("Something");
        // Then
        Assert.assertEquals(SummaryState.class,returnedState.getClass());
    }
    @Test
    public static void currentStateReturnsRunStateWhenRoundCounterIsLessThanThree() {
        // Given
        int roundCounterLessThanMaxRounds = 2;
        PreEndState preEndState = new PreEndState(playerBuffer, output, board, scoreBoard, roundCounterLessThanMaxRounds, currentBoardSize);
        // When
        GameState returnedState = preEndState.nextState("Something");
        // Then
        Assert.assertEquals(RunState.class,returnedState.getClass());
    }

}
