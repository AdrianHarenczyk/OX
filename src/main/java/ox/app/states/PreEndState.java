package ox.app.states;

import ox.app.game.Board;
import ox.app.game.Player;
import ox.app.game.ScoreBoard;
import ox.app.io.InputOutput;
import ox.app.languages.Messenger;
import ox.app.utility.PlayerBuffer;

public class PreEndState implements GameState {

    final InputOutput inputOutput;
    final Messenger messenger;

    final PlayerBuffer playerBuffer;
    Board board;

    final ScoreBoard scoreBoard;

    int roundCounter;
    final int winningStroke;
    private final int currentBoardSize;

    private final Player winningPlayer;
    private final String winnerMessage;

    private static final int POINTS_FOR_WIN = 3;
    private static final int POINTS_FOR_DRAW = 1;
    private static final int POINTS_FOR_LOST = 0;
    private static final int NUMBER_OF_ROUNDS = 3;

    public PreEndState(InputOutput inputOutput, Messenger messenger, PlayerBuffer playerBuffer,
                       Board board, ScoreBoard scoreBoard, int roundCounter, int winningStroke, int currentBoardSize) {

        this.inputOutput = inputOutput;
        this.messenger = messenger;
        this.playerBuffer = playerBuffer;
        this.board = board;
        this.scoreBoard = scoreBoard;
        this.roundCounter = roundCounter;
        this.winningStroke = winningStroke;
        this.winningPlayer = playerBuffer.takePlayer();
        this.currentBoardSize = currentBoardSize;
        this.winnerMessage = winningPlayer + messenger.winsTheRoundMessage();
    }

    PreEndState(RunState runState) {
        this(runState.inputOutput, runState.messenger, runState.playerBuffer,
                runState.board, runState.scoreBoard, runState.roundCounter,
                runState.winningStroke, runState.currentBoardSize);
    }

    @Override
    public void showState() {
        pointsDisposer();
    }

    @Override
    public GameState nextState(String input) {
        if (roundCounter > NUMBER_OF_ROUNDS) {
            return new SummaryState(scoreBoard, inputOutput, messenger);
        } else {
            this.board = createNewBoard();
            return new RunState(this);
        }
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
        scoreBoard.showScoreBoard(inputOutput);
    }

    private void drawPoints() {
        scoreBoard.addPoint(playerBuffer.takePlayer(), POINTS_FOR_DRAW);
        playerBuffer.swapPlayers();
        scoreBoard.addPoint(playerBuffer.takePlayer(), POINTS_FOR_DRAW);
        scoreBoard.showScoreBoard(inputOutput);
    }

    private void printRoundMessage(String message) {
        printResultOfRound(message);
        printInfoToStartNewRound();
        printInfoToSeeResults();
    }

    private void printResultOfRound(String message) {
        if (roundCounter <= NUMBER_OF_ROUNDS) {
            inputOutput.message(message);
        }
    }

    private void printInfoToStartNewRound() {
        if (roundCounter != NUMBER_OF_ROUNDS) {
            inputOutput.message(messenger.pressEnterMessage() + (roundCounter + 1));
        }
    }

    private void printInfoToSeeResults() {
        if (roundCounter == NUMBER_OF_ROUNDS) {
            inputOutput.message(messenger.endGameMessage());
        }
    }

    private Board createNewBoard() {
        int height = board.getHeight();
        int width = board.getWidth();
        return Board.newBoard(width, height);
    }
}
