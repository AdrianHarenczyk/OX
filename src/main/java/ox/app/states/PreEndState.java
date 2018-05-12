package ox.app.states;

import ox.app.game.ScoreBoard;
import ox.app.game.Board;
import ox.app.game.Player;
import ox.app.utility.RoundBuffer;

import java.util.function.Consumer;

public class PreEndState implements GameState {

    private Board board;
    private final RoundBuffer playerBuffer;
    private final Player winningPlayer;
    private final Consumer<String> output;
    private final ScoreBoard scoreBoard;
    private static final Integer POINTS_FOR_WIN = 3;
    private static final Integer POINTS_FOR_LOST = 0;
    private static final int NUMBER_OF_ROUNDS = 3;
    private int roundCounter;

    PreEndState(RoundBuffer playerBuffer, Consumer<String> output, Board board, ScoreBoard scoreBoard,int roundCounter) {
        this.playerBuffer = playerBuffer;
        this.winningPlayer = playerBuffer.takePlayer();
        this.output = output;
        this.board = board;
        this.scoreBoard = scoreBoard;
        this.roundCounter = roundCounter;
    }

    @Override
    public void showState() {
        pointsDisposer();
        roundCounter++;
        scoreBoard.showScoreBoard();
        if (roundCounter != NUMBER_OF_ROUNDS) {
            output.accept(winningPlayer + " wins the round!\n" +
                    "Press enter to start round " + (roundCounter+1));
        }
    }

    @Override
    public GameState nextState(String input) {
        if (roundCounter == NUMBER_OF_ROUNDS) {
            return new SummaryState(scoreBoard,playerBuffer);
        }
        else {
            int height = board.getHeight();
            int width = board.getWidth();
            board = Board.newBoard(width,height);
            return new RunState(playerBuffer,output,board,scoreBoard,roundCounter);
        }
    }

    private void pointsDisposer() {
        scoreBoard.addPoint(winningPlayer, POINTS_FOR_WIN);
        playerBuffer.swapPlayers();
        scoreBoard.addPoint(playerBuffer.takePlayer(),POINTS_FOR_LOST);
    }
}
