package ox.app.io;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class InputOutput {
    private Supplier<String> input;
    private Consumer<String> output;
    private Consumer<String> boardOutput;

    public InputOutput(Supplier<String> input, Consumer<String> output, Consumer<String> boardOutput) {
        this.input = input;
        this.output = output;
        this.boardOutput = boardOutput;
    }

    public String input() {
        return input.get();
    }

    public void messageOut(String message) {
        output.accept(message);
    }

    public void boardOut(String instruction) {
        boardOutput.accept(instruction);
    }
}
