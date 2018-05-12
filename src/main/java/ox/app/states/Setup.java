package ox.app.states;

import ox.app.exceptions.WrongArgumentException;
import ox.app.game.Board;
import ox.app.game.Player;
import ox.app.game.ScoreBoard;
import ox.app.utility.RoundBuffer;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Setup {
    private GameState currentState;
    private final Supplier<String> input;
    private final Consumer<String> output;

    public Setup(Supplier<String> input, Consumer<String> output) {
        this.input = input;
        this.output = output;
    }

    public void initializeAGame() throws WrongArgumentException {
        Player firstPlayer = Player.playerCreator(input,output);
        Player secondPlayer = Player.playerCreator(input,output,firstPlayer);

        final RoundBuffer playerBuffer = new RoundBuffer();
        playerBuffer.addPlayers(firstPlayer,secondPlayer);
        Board board = Board.newBoard(3,3);
        ScoreBoard scoreBoard = new ScoreBoard(playerBuffer,output);
        currentState = new RunState(playerBuffer,output,board,scoreBoard);
        instructions();
        applicationLoop();
    }

    private void applicationLoop() {
        while (true) {
            if (currentState.getClass().equals(EndState.class))
                return;
            this.currentState.showState();
            startTurn();
        }
    }

    private void startTurn() {
        try {
            this.currentState = currentState.nextState(input.get());
        } catch (WrongArgumentException e) {
            output.accept(e.getMessage());
            startTurn();
        }
    }

    private void instructions() {
        output.accept("Game has started. To resign pass resign as a command.\n");
    }

}
