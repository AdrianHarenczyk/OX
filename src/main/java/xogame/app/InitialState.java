package xogame.app;

import java.util.List;
import java.util.function.Consumer;

/**
 * this class is responsible to start the actual game. Prints
 * information about who's turn is it an such on. Method nextState()
 * provides next state of the application, next turn for example.
 */
public class InitialState implements GameState {

    private List<Player> playerList;
    private final Consumer<String> output;

    public InitialState(List<Player> playerList, Consumer<String> output) {
        this.playerList = playerList;
        this.output = output;
    }
    public void showState() {
        output.accept(playerList.get(0).toString());
    }
    public GameState nextState(String input) {
        // todo something with input

        return new GameInProgress(playerList, output);
    }

    @Override
    public Player showPlayer() {
        return playerList.get(1);
    }
}
