package ox.app.states;

import ox.app.exceptions.WrongArgumentException;
import ox.app.game.Board;
import ox.app.game.Coordinates;
import ox.app.game.Player;
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

    public RunState(RoundBuffer playerBuffer, Consumer<String> output, Board board) {
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
}
