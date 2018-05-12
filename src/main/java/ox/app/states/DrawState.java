package ox.app.states;

import ox.app.game.Board;
import ox.app.game.ScoreBoard;
import ox.app.utility.RoundBuffer;

import java.util.function.Consumer;

public class DrawState implements GameState {
    private final RoundBuffer playerBuffer;
    private final Consumer<String> output;
    private final Board board;
    private final ScoreBoard scoreBoard;
    private int roundCounter;
    private static final int POINTS_FOR_DRAW = 1;
    private static final int NUMBER_OF_ROUNDS = 3;

    DrawState(RoundBuffer playerBuffer, Consumer<String> output, Board board, ScoreBoard scoreBoard, int roundCounter) {
        this.playerBuffer = playerBuffer;
        this.output = output;
        this.board = board;
        this.scoreBoard = scoreBoard;
        this.roundCounter = roundCounter;
    }

    @Override
    public void showState() {
        pointsDisposer();
        roundCounter++;
        if (roundCounter != NUMBER_OF_ROUNDS) {
            output.accept("It is a draw!\n" +
                    "Press enter to start round " + (roundCounter + 1));
        }
    }

    @Override
    public GameState nextState(String input) {
        if (roundCounter == NUMBER_OF_ROUNDS) {
            return new SummaryState(scoreBoard,playerBuffer);
        } else {
            int height = board.getHeight();
            int width = board.getWidth();
            Board board = Board.newBoard(width,height);
            return new RunState(playerBuffer,output,board,scoreBoard,roundCounter);
        }
    }
    private void pointsDisposer() {
        scoreBoard.addPoint(playerBuffer.takePlayer(), POINTS_FOR_DRAW);
        playerBuffer.swapPlayers();
        scoreBoard.addPoint(playerBuffer.takePlayer(),POINTS_FOR_DRAW);
    }

}
