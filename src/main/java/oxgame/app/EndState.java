package oxgame.app;

public class EndState implements GameState {
    public EndState(Player player) {
        System.out.println(player + " wins the game!");
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

    @Override
    public Board getBoard() {
        return null;
    }
}
