package oxgame.app;

import java.util.function.Consumer;

public class GameInProgress implements GameState {

    private final RoundBuffer playerBuffer;
    private final Consumer<String> output;
    private Board board;
    private Player player;

    public GameInProgress(RoundBuffer playerBuffer, Consumer<String> output, Board board) {
        this.playerBuffer = playerBuffer;
        this.output = output;
        this.board = board;
    }


    @Override
    public void showState() {
        player = playerBuffer.takePlayer();
        output.accept(player.toString());
        board.showBoard();
    }

    @Override
    public GameState nextState(String input) throws IllegalArgumentException{
        if (ResignCheck.check(input)) {
            return new EndState(player);
        }
        int validCoordinates = CoordinatesValidator.validate(input,board.size());
        board.placeSymbol(Coordinates.apply(validCoordinates),player.showSymbol());
        if (VictoryChecker.check(Coordinates.apply(validCoordinates),board,3)){
            return new EndState(player);
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
