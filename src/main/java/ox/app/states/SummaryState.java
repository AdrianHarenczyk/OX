package ox.app.states;

import ox.app.game.ScoreBoard;
import ox.app.languages.InstructionDriver;

import java.util.function.Consumer;

class SummaryState implements GameState {
    private final ScoreBoard scoreBoard;
    private final Consumer<String> output;
    private final InstructionDriver instructionDriver;

    SummaryState(ScoreBoard scoreBoard, Consumer<String> output, InstructionDriver instructionDriver) {
        this.scoreBoard = scoreBoard;
        this.output = output;
        this.instructionDriver = instructionDriver;
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
        output.accept(instructionDriver.exitAppMessage());
    }
}
