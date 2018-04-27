package xogame.app;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Setup {
    private Board board;
    private GameState currentState;
    private Supplier<String> input;
    private Consumer<String> output;

    public Setup(Supplier<String> input, Consumer<String> output) {
        this.input = input;
        this.output = output;
    }

    public void initializeAGame() {
        Player firstPlayer = null;
        try {
            firstPlayer = Player.playerCreator(input,output);
        } catch (IllegalArgumentException e) {
            output.accept(e.getMessage()+'\n');
            initializeAGame();
        }
        Player secondPlayer = Player.playerCreator(input,output,firstPlayer);

        RoundBuffer playerBuffer = new RoundBuffer();
        playerBuffer.addPlayer(firstPlayer);
        playerBuffer.addPlayer(secondPlayer);

        currentState = new InitialState(playerBuffer,output);
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
