package ox.app.states;

import ox.app.game.ScoreBoard;
import ox.app.io.InputOutput;
import ox.app.languages.Messenger;

import java.util.function.Consumer;

public class SummaryState implements GameState {
    private final InputOutput inputOutput;
    private final Messenger messenger;
    private final ScoreBoard scoreBoard;

    SummaryState(ScoreBoard scoreBoard, InputOutput inputOutput, Messenger messenger) {
        this.inputOutput = inputOutput;
        this.messenger = messenger;
        this.scoreBoard = scoreBoard;
    }

    @Override
    public void showState() {
        scoreBoard.showTheWinner(inputOutput);
        printEndingCommand();
    }

    @Override
    public GameState nextState(String input) {
        return new EndState();
    }

    private void printEndingCommand() {
        inputOutput.message(messenger.exitAppMessage());
    }
}
