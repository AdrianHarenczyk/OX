package oxgame.app;

public class DrawState implements GameState {
    @Override
    public void showState() {
        System.out.println("This is a draw!");
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
