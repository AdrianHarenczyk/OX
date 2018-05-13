package ox.app.states;

import ox.app.exceptions.WrongArgumentException;
import ox.app.game.Board;
import ox.app.game.Coordinates;
import ox.app.game.Player;
import ox.app.game.ScoreBoard;
import ox.app.utility.ResignCheck;
import ox.app.utility.RoundBuffer;
import ox.app.utility.VictoryChecker;
import ox.app.validators.CoordinatesValidator;

import java.util.function.Consumer;

public class RunState implements GameState {

    private final RoundBuffer playerBuffer;
    private final Consumer<String> output;
    private final Board board;
    private Player player;
    private int currentBoardSize;
    private final ScoreBoard scoreBoard;
    private final int roundCounter;
    private final int winningStroke;
    private static final int ENDING_ROUND = 3;

    public RunState(RoundBuffer playerBuffer, Consumer<String> output, Board board, ScoreBoard scoreBoard,int roundCounter, int winningStroke) {
        this.playerBuffer = playerBuffer;
        this.output = output;
        this.board = board;
        currentBoardSize = board.size();
        this.scoreBoard = scoreBoard;
        this.roundCounter = roundCounter;
        this.winningStroke = winningStroke;
    }


    @Override
    public void showState() {
        player = playerBuffer.takePlayer();
        output.accept(player.toString());
        board.showBoard();
    }

    @Override
    public GameState nextState(String input) throws WrongArgumentException {
        PreEndState resignState;
        if ((resignState = checkResign(input))!=null) {
            return resignState;
        }
        int validCoordinates = validateCoordinateAndPlaceSymbol(input);
        if (checkIfDrawOrWin(validCoordinates)) {
            board.showBoard();
            return new PreEndState(playerBuffer,output,board,scoreBoard,roundCounter,currentBoardSize,winningStroke);
        }
        playerBuffer.swapPlayers();
        return this;
    }
    private boolean checkIfDrawOrWin(int validCoordinates) {
        return VictoryChecker.check(Coordinates.apply(validCoordinates),board,winningStroke)
                || currentBoardSize == 0;
    }
    private int validateCoordinateAndPlaceSymbol(String input) throws WrongArgumentException{
        int validCoordinates = CoordinatesValidator.validate(input,board.size(),board);
        board.placeSymbol(Coordinates.apply(validCoordinates),player.showSymbol());
        currentBoardSize--;
        return validCoordinates;
    }
    private PreEndState checkResign(String input) {
        if (ResignCheck.checkAll(input)) {
            playerBuffer.swapPlayers();
            return new PreEndState(playerBuffer,output,board,scoreBoard,ENDING_ROUND,currentBoardSize,winningStroke);
        } else if (ResignCheck.check(input)) {
            playerBuffer.swapPlayers();
            return new PreEndState(playerBuffer,output,board,scoreBoard,roundCounter,currentBoardSize,winningStroke);
        } else return null;
    }
}
