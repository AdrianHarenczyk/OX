package oxgame.app;

public class EndState implements GameState {
    public EndState(GameState gameState) {

    }

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
}
