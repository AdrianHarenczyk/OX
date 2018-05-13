package ox.app.states;

import ox.app.game.ScoreBoard;
import ox.app.game.Board;
import ox.app.game.Player;
import ox.app.utility.RoundBuffer;

import java.util.function.Consumer;

public class PreEndState implements GameState {

    private final Board board;
    private final RoundBuffer playerBuffer;
    private final Player winningPlayer;
    private final Consumer<String> output;
    private final ScoreBoard scoreBoard;
    private final int currentBoardSize;
    private final int winningStroke;

    private static final int POINTS_FOR_WIN = 3;
    private static final int POINTS_FOR_DRAW = 1;
    private static final int POINTS_FOR_LOST = 0;
    private static final int NUMBER_OF_ROUNDS = 3;

    private final String winnerMessage;
    private static final String DRAW_MESSAGE = "It's a draw!";

    private int roundCounter;

    public PreEndState(RoundBuffer playerBuffer, Consumer<String> output, Board board, ScoreBoard scoreBoard, int roundCounter, int currentBoardSize,int winningStroke) {
        this.playerBuffer = playerBuffer;
        this.winningPlayer = playerBuffer.takePlayer();
        winnerMessage = winningPlayer + " wins the round!";
        this.output = output;
        this.board = board;
        this.scoreBoard = scoreBoard;
        this.roundCounter = roundCounter;
        this.currentBoardSize = currentBoardSize;
        this.winningStroke = winningStroke;
    }

    @Override
    public void showState() {
        pointsDisposer();
    }

    @Override
    public GameState nextState(String input) {
        if (roundCounter > NUMBER_OF_ROUNDS) {
            return new SummaryState(scoreBoard,output);
        }
        else {
            return new RunState(playerBuffer,output,createNewBoard(),scoreBoard,roundCounter,winningStroke);
        }
    }

    private Board createNewBoard() {
        int height = board.getHeight();
        int width = board.getWidth();
        return Board.newBoard(width,height);
    }

    private void pointsDisposer() {
        if (currentBoardSize>0) {
            winningPoints();
            printRoundMessage(winnerMessage);
        } else {
            drawPoints();
            printRoundMessage(DRAW_MESSAGE);
        }
        roundCounter++;
    }

    private void winningPoints() {
        scoreBoard.addPoint(winningPlayer, POINTS_FOR_WIN);
        playerBuffer.swapPlayers();
        scoreBoard.addPoint(playerBuffer.takePlayer(),POINTS_FOR_LOST);
        scoreBoard.showScoreBoard(output);
    }
    private void drawPoints() {
        scoreBoard.addPoint(playerBuffer.takePlayer(), POINTS_FOR_DRAW);
        playerBuffer.swapPlayers();
        scoreBoard.addPoint(playerBuffer.takePlayer(),POINTS_FOR_DRAW);
        scoreBoard.showScoreBoard(output);
    }

    private void printRoundMessage(String message) {
        printResultOfRound(message);
        printInfoToStartNewRound();
        printInfoToSeeResults();
    }
    private void printResultOfRound(String message) {
        if (roundCounter <= NUMBER_OF_ROUNDS) {
            output.accept(message);
        }
    }
    private void printInfoToStartNewRound() {
        if (roundCounter != NUMBER_OF_ROUNDS) {
            output.accept("Press enter to start round " + (roundCounter+1));
        }
    }
    private void printInfoToSeeResults() {
        if (roundCounter == NUMBER_OF_ROUNDS) {
            output.accept("The game has ended. Press enter to see the results.");
        }
    }
}
