package oxgame.app;

import java.util.function.Consumer;

public class PreEndState implements GameState {

    private Board board;
    private RoundBuffer playerBuffer;
    private Player winningPlayer;
    private Consumer<String> output;
    private static ScoreBoard scoreBoard = new ScoreBoard();
    private static final Integer POINTS_FOR_WIN = 3;
    private static final Integer POINTS_FOR_LOST = 0;
    private static final int NUMBER_OF_ROUNDS = 3;
    private static int roundCounter = 0;

    public PreEndState(Player winningPlayer, RoundBuffer playerBuffer, Consumer<String> output,Board board) {
        this.playerBuffer = playerBuffer;
        this.winningPlayer = winningPlayer;
        this.output = output;
        this.board = board;
    }

    @Override
    public void showState() {
        scoreBoard.addPoint(winningPlayer, POINTS_FOR_WIN);
        playerBuffer.swapPlayers();
        scoreBoard.addPoint(playerBuffer.takePlayer(),POINTS_FOR_LOST);
        roundCounter++;
        if (roundCounter != NUMBER_OF_ROUNDS) {
            scoreBoard.showScoreBoard();
            output.accept(winningPlayer + " wins the round!\n" +
                    "Press enter to start round " + (roundCounter+1));
        }
    }

    @Override
    public GameState nextState(String input) {
        if (roundCounter == NUMBER_OF_ROUNDS) {
            return new EndState();
        }
        else {
            int height = board.getHeight();
            int width = board.getWidth();

            board = Board.newBoard(width,height);

            return new GameInProgress(playerBuffer,output,board);
        }
    }

    @Override
    public Player showPlayer() {
        return null;
    }

    @Override
    public Board getBoard() {
        return null;
    }
}
