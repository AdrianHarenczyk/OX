package oxgame.app;
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
        Player firstPlayer = Player.playerCreator(input,output);
        Player secondPlayer = Player.playerCreator(input,output,firstPlayer);

        RoundBuffer playerBuffer = new RoundBuffer();
        playerBuffer.addPlayer(firstPlayer);
        playerBuffer.addPlayer(secondPlayer);
        board = Board.newBoard(10,10);
        currentState = new GameInProgress(playerBuffer,output,board);
        instructions();
        applicationLoop();
    }

    private void applicationLoop() {
        while (true) {
            this.currentState.showState();
            startTurn();
        }
    }

    private void startTurn() {
        try {
            this.currentState = currentState.nextState(input.get());
        } catch (IllegalArgumentException e) {
            output.accept(e.getMessage());
            startTurn();
        }
    }

    private void instructions() {
        output.accept("Game has started. To resign pass resign as a command.\n");
    }

}
