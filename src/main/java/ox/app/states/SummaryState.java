package ox.app.states;

import ox.app.game.ScoreBoard;
import ox.app.languages.Messenger;

import java.util.function.Consumer;

public class SummaryState implements GameState {
    private final ScoreBoard scoreBoard;
    private final Consumer<String> output;
    private final Messenger messenger;

    SummaryState(ScoreBoard scoreBoard, Consumer<String> output, Messenger messenger) {
        this.scoreBoard = scoreBoard;
        this.output = output;
        this.messenger = messenger;
    }

    @Override
    public void showState() {
        scoreBoard.showTheWinner(output);
        printEndingCommand();
    }

    @Override
    public GameState nextState(String input) {
        return new EndState();
    }

    private void printEndingCommand() {
        output.accept(messenger.exitAppMessage());
    }
}
