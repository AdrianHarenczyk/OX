package ox.app.states;

import ox.app.exceptions.WrongArgumentException;
import ox.app.game.Board;
import ox.app.game.Coordinate;
import ox.app.game.Player;
import ox.app.game.ScoreBoard;
import ox.app.io.InputOutput;
import ox.app.languages.Messenger;
import ox.app.utility.BoardDrawer;
import ox.app.utility.PlayerBuffer;
import ox.app.utility.ResignCheck;
import ox.app.utility.VictoryChecker;
import ox.app.validators.CoordinateValidator;

public class RunState implements GameState {

    private static final int ENDING_ROUND = 3;
    final PlayerBuffer playerBuffer;
    final InputOutput inputOutput;
    final Board board;
    final ScoreBoard scoreBoard;
    final int winningStroke;
    final Messenger messenger;
    int currentBoardSize;
    int roundCounter;
    private Player player;

    public RunState(InputOutput inputOutput, Messenger messenger, PlayerBuffer playerBuffer, Board board,
                    ScoreBoard scoreBoard, int roundCounter, int winningStroke) {
        this.inputOutput = inputOutput;
        this.messenger = messenger;
        this.playerBuffer = playerBuffer;
        this.board = board;
        this.scoreBoard = scoreBoard;
        this.roundCounter = roundCounter;
        this.winningStroke = winningStroke;
        this.currentBoardSize = board.size();
    }

    RunState(PreEndState preEndState) {
        this(preEndState.inputOutput, preEndState.messenger, preEndState.playerBuffer, preEndState.board,
                preEndState.scoreBoard, preEndState.roundCounter, preEndState.winningStroke);
    }

    @Override
    public void showState() {
        player = playerBuffer.takePlayer();
        inputOutput.message(player.toString());
        BoardDrawer.showBoard(board, inputOutput);
    }

    @Override
    public GameState nextState(String input) throws WrongArgumentException {
        PreEndState resignState;
        if ((resignState = checkResign(input)) != null) {
            return resignState;
        }
        int validCoordinates = validateCoordinateAndPlaceSymbol(input);
        if (checkIfWinAndDraw(validCoordinates)) {
            BoardDrawer.showBoard(board, inputOutput);
            currentBoardSize++;
            return new PreEndState(this);
        } else if (checkIfWinOrDraw(validCoordinates)) {
            BoardDrawer.showBoard(board, inputOutput);
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
