package ox.app.states;

public class DrawState implements GameState {
    @Override
    public void showState() {
        System.out.println("This is a draw!");
    }

    @Override
    public GameState nextState(String input) {
        return new EndState();
    }

}
