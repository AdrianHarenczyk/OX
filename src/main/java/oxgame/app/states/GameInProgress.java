package oxgame.app.states;

import oxgame.app.exceptions.WrongArgumentException;
import oxgame.app.game.Board;
import oxgame.app.game.Coordinates;
import oxgame.app.game.Player;
import oxgame.app.utility.ResignCheck;
import oxgame.app.utility.RoundBuffer;
import oxgame.app.utility.VictoryChecker;
import oxgame.app.validators.CoordinatesValidator;

import java.util.function.Consumer;

public class GameInProgress implements GameState {

    private final RoundBuffer playerBuffer;
    private final Consumer<String> output;
    private Board board;
    private Player player;
    private int currentBoardSize;

    public GameInProgress(RoundBuffer playerBuffer, Consumer<String> output, Board board) {
        this.playerBuffer = playerBuffer;
        this.output = output;
        this.board = board;
        currentBoardSize = board.size();
    }


    @Override
    public void showState() {
        player = playerBuffer.takePlayer();
        output.accept(player.toString());
        board.showBoard();
    }

    @Override
    public GameState nextState(String input) throws WrongArgumentException {
        if (ResignCheck.check(input)) {
            playerBuffer.swapPlayers();
            return new PreEndState(playerBuffer.takePlayer(),playerBuffer,output,board);
        }
        int validCoordinates = CoordinatesValidator.validate(input,board.size(),board);
        board.placeSymbol(Coordinates.apply(validCoordinates),player.showSymbol());
        currentBoardSize--;
        if (VictoryChecker.check(Coordinates.apply(validCoordinates),board,3)){
            board.showBoard();
            return new PreEndState(player,playerBuffer,output,board);
        }
        if (currentBoardSize == 0) {
            board.showBoard();
            return new DrawState();
        }
        playerBuffer.swapPlayers();
        return this;
    }

    @Override
    public Player showPlayer() {
        return playerBuffer.takePlayer();
    }
    @Override
    public Board getBoard() {
        return board;
    }

}
