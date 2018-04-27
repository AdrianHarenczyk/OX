package xogame.app;

import java.util.function.Consumer;

/**
 * this class is responsible to start the actual game. Prints
 * information about who's turn is it an such on. Method nextState()
 * provides next state of the application, next turn for example.
 */
public class InitialState implements GameState {

    private final Consumer<String> output;
    private RoundBuffer playerBuffer;

    public InitialState(RoundBuffer playerBuffer, Consumer<String> output) {
        this.output = output;
        this.playerBuffer = playerBuffer;
    }
    public void showState() {
        output.accept(playerBuffer.takePlayer().toString());
    }
    public GameState nextState(String input) {
        // todo something with input

        return new GameInProgress(playerBuffer, output);
    }

    @Override
    public Player showPlayer() {
        return playerBuffer.takePlayer();
    }
}
