package ox.app.states;

import ox.app.game.Board;
import ox.app.game.Player;
import ox.app.game.ScoreBoard;
import ox.app.languages.Messenger;
import ox.app.utility.RoundBuffer;

import java.util.function.Consumer;

public class PreEndState implements GameState {

    private static final int POINTS_FOR_WIN = 3;
    private static final int POINTS_FOR_DRAW = 1;
    private static final int POINTS_FOR_LOST = 0;
    private static final int NUMBER_OF_ROUNDS = 3;
    final RoundBuffer playerBuffer;
    final Consumer<String> output;
    final Consumer<String> boardOutput;
    final ScoreBoard scoreBoard;
    final int winningStroke;
    final Messenger messenger;
    private final Player winningPlayer;
    private final int currentBoardSize;
    private final String winnerMessage;
    Board board;
    int roundCounter;

    public PreEndState(RoundBuffer playerBuffer, Consumer<String> output, Board board,
                       ScoreBoard scoreBoard, int roundCounter, int currentBoardSize,
                       int winningStroke, Consumer<String> boardOutput, Messenger messenger) {

        this.playerBuffer = playerBuffer;
        this.winningPlayer = playerBuffer.takePlayer();
        this.winnerMessage = winningPlayer + messenger.winsTheRoundMessage();
        this.output = output;
        this.board = board;
        this.scoreBoard = scoreBoard;
        this.roundCounter = roundCounter;
        this.currentBoardSize = currentBoardSize;
        this.winningStroke = winningStroke;
        this.boardOutput = boardOutput;
        this.messenger = messenger;
    }

    PreEndState(RunState runState) {
        this(runState.playerBuffer, runState.output, runState.board,
                runState.scoreBoard, runState.roundCounter, runState.currentBoardSize,
                runState.winningStroke, runState.boardOutput, runState.messenger);
    }

    @Override
    public void showState() {
        pointsDisposer();
    }

    @Override
    public GameState nextState(String input) {
        if (roundCounter > NUMBER_OF_ROUNDS) {
            return new SummaryState(scoreBoard, output, messenger);
        } else {
            this.board = createNewBoard();
            return new RunState(this);
        }
    }

    private Board createNewBoard() {
        int height = board.getHeight();
        int width = board.getWidth();
        return Board.newBoard(width, height);
    }

    private void pointsDisposer() {
        if (currentBoardSize > 0) {
            winningPoints();
            printRoundMessage(winnerMessage);
        } else {
            drawPoints();
            printRoundMessage(messenger.drawMessage());
        }
        roundCounter++;
    }

    private void winningPoints() {
        scoreBoard.addPoint(winningPlayer, POINTS_FOR_WIN);
        playerBuffer.swapPlayers();
        scoreBoard.addPoint(playerBuffer.takePlayer(), POINTS_FOR_LOST);
        scoreBoard.showScoreBoard(output);
    }

    private void drawPoints() {
        scoreBoard.addPoint(playerBuffer.takePlayer(), POINTS_FOR_DRAW);
        playerBuffer.swapPlayers();
        scoreBoard.addPoint(playerBuffer.takePlayer(), POINTS_FOR_DRAW);
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
            output.accept(messenger.pressEnterMessage() + (roundCounter + 1));
        }
    }

    private void printInfoToSeeResults() {
        if (roundCounter == NUMBER_OF_ROUNDS) {
            output.accept(messenger.endGameMessage());
        }
    }
}
