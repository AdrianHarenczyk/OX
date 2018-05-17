package ox.app.states;

import ox.app.exceptions.WrongArgumentException;
import ox.app.game.Board;
import ox.app.game.Coordinate;
import ox.app.game.Player;
import ox.app.game.ScoreBoard;
import ox.app.languages.Messenger;
import ox.app.utility.BoardDrawer;
import ox.app.utility.ResignCheck;
import ox.app.utility.RoundBuffer;
import ox.app.utility.VictoryChecker;
import ox.app.validators.CoordinateValidator;

import java.util.function.Consumer;

public class RunState implements GameState {

    private static final int ENDING_ROUND = 3;
    final RoundBuffer playerBuffer;
    final Consumer<String> output;
    final Consumer<String> boardOutput;
    final Board board;
    final ScoreBoard scoreBoard;
    final int winningStroke;
    final Messenger messenger;
    int currentBoardSize;
    int roundCounter;
    private Player player;

    public RunState(RoundBuffer playerBuffer, Consumer<String> output, Board board,
                    ScoreBoard scoreBoard, int roundCounter, int winningStroke,
                    Consumer<String> boardOutput, Messenger messenger) {
        this.playerBuffer = playerBuffer;
        this.output = output;
        this.board = board;
        this.currentBoardSize = board.size();
        this.scoreBoard = scoreBoard;
        this.roundCounter = roundCounter;
        this.winningStroke = winningStroke;
        this.boardOutput = boardOutput;
        this.messenger = messenger;
    }

    RunState(PreEndState preEndState) {
        this(preEndState.playerBuffer, preEndState.output, preEndState.board,
                preEndState.scoreBoard, preEndState.roundCounter, preEndState.winningStroke,
                preEndState.boardOutput, preEndState.messenger);
    }

    @Override
    public void showState() {
        player = playerBuffer.takePlayer();
        output.accept(player.toString());
        BoardDrawer.showBoard(board, boardOutput);
    }

    @Override
    public GameState nextState(String input) throws WrongArgumentException {
        PreEndState resignState;
        if ((resignState = checkResign(input)) != null) {
            return resignState;
        }
        int validCoordinates = validateCoordinateAndPlaceSymbol(input);
        if (checkIfWinAndDraw(validCoordinates)) {
            BoardDrawer.showBoard(board, boardOutput);
            currentBoardSize++;
            return new PreEndState(this);
        } else if (checkIfWinOrDraw(validCoordinates)) {
            BoardDrawer.showBoard(board, boardOutput);
            return new PreEndState(this);
        }
        playerBuffer.swapPlayers();
        return this;
    }

    private boolean checkIfWinOrDraw(int validCoordinates) {
        return VictoryChecker.check(Coordinate.apply(validCoordinates), board, winningStroke)
                || currentBoardSize == 0;
    }

    private boolean checkIfWinAndDraw(int validCoordinates) {
        return VictoryChecker.check(Coordinate.apply(validCoordinates), board, winningStroke)
                && currentBoardSize == 0;
    }

    private int validateCoordinateAndPlaceSymbol(String input) throws WrongArgumentException {
        int validCoordinates = CoordinateValidator.validate(input, board.size(), board, messenger);
        board.placeSymbol(Coordinate.apply(validCoordinates), player.showSymbol());
        currentBoardSize--;
        return validCoordinates;
    }

    private PreEndState checkResign(String input) {
        if (ResignCheck.checkAll(input)) {
            playerBuffer.swapPlayers();
            roundCounter = ENDING_ROUND;
            return new PreEndState(this);
        } else if (ResignCheck.check(input)) {
            playerBuffer.swapPlayers();
            return new PreEndState(this);
        } else return null;
    }
}
