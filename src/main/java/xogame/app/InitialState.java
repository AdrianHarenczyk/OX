package xogame.app;

/**
 * this class is responsible to start the actual game. Prints
 * information about who's turn is it an such on. Method nextState()
 * provides next state of the application, next turn for example.
 */
public class InitialState implements GameState {
    private Player player;
    public InitialState(Player player) {
        this.player = player;
    }
    public void showState() {
        System.out.println(player);
    }
    public GameState nextState(String input) {
        return new GameInProgress(player);
    }
}
