package oxgame.app.states;

import oxgame.app.game.Board;
import oxgame.app.game.Player;

public class EndState implements GameState {
    @Override
    public void showState() {

    }

    @Override
    public GameState nextState(String input) {
        return null;
    }

    @Override
    public Player showPlayer() {
        return null;
    }

    @Override
    public Board getBoard() {
        return null;
    }
}
