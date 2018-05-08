package oxgame.app;

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
