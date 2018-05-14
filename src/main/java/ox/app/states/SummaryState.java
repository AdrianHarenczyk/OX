package ox.app.states;

import ox.app.game.ScoreBoard;

import java.util.function.Consumer;

public class SummaryState implements GameState {
    private final ScoreBoard scoreBoard;
    private static final String ENDING_MESSAGE = "\nTo exit application, press enter.";
    private final Consumer<String> output;

    SummaryState(ScoreBoard scoreBoard, Consumer<String> output) {
        this.scoreBoard = scoreBoard;
        this.output = output;
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
        output.accept(ENDING_MESSAGE);
    }
}
