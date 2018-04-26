package xogame.app;

import java.util.List;
import java.util.function.Consumer;

public class GameInProgress implements GameState {

    private final List<Player> playerList;
    private final Consumer<String> output;

    public GameInProgress(List<Player> player, Consumer<String> output) {
        this.playerList = player;
        this.output = output;
    }


    @Override
    public void showState() {
        output.accept(playerList.get(0).toString());
    }

    @Override
    public GameState nextState(String input) {

        return this;
    }

    @Override
    public Player showPlayer() {
        return playerList.get(0);
    }
}
