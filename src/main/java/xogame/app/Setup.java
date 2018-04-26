package xogame.app;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Setup {
    private List<Player> playerList;
    private Board board;
    private GameState currentState;
    private Supplier<String> input;
    private Consumer<String> output;

    public Setup(Supplier<String> input, Consumer<String> output) {
        this.input = input;
        this.output = output;
        playerList = new ArrayList<>();
    }

    public void initializeAGame() {
        playerList.add(Player.playerCreator(input,output));
        playerList.add(Player.playerCreator(input,output));

        currentState = new InitialState(playerList,output);
        applicationLoop();
    }

    private void applicationLoop() {
        while (true) {
            startTurn();
        }
    }

    private void startTurn() {
        this.currentState.showState();
        this.currentState = currentState.nextState(input.get());
    }

}
