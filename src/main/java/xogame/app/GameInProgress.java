package xogame.app;

import java.util.function.Consumer;

public class GameInProgress implements GameState {

    private final RoundBuffer playerBuffer;
    private final Consumer<String> output;

    public GameInProgress(RoundBuffer playerBuffer, Consumer<String> output) {
        this.playerBuffer = playerBuffer;
        this.output = output;
    }


    @Override
    public void showState() {
        output.accept(playerBuffer.takePlayer().toString());
    }

    @Override
    public GameState nextState(String input) {

        return this;
    }

    @Override
    public Player showPlayer() {
        return playerBuffer.takePlayer();
    }
}
