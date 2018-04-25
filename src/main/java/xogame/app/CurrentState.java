package xogame.app;

public class CurrentState implements GameState {

    private Player player;
    public CurrentState(Player player) {
        this.player = player;
    }

    @Override
    public void showState() {
        System.out.println(player);
    }

    @Override
    public GameState nextState(String input) {
        return this;
    }
}
