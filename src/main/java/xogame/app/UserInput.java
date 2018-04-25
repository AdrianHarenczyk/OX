package xogame.app;

import java.util.function.Supplier;

/**
 * Wraps user input String supplier to use as user input.
 */
public class UserInput {
    private Supplier<String> data;
    UserInput(Supplier<String> data) {
        this.data = data;
    }
    public String getData() {
        return data.get();
    }
}
