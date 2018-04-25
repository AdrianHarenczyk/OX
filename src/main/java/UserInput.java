import java.util.function.Supplier;

/**
 * Wraps user input String supplier to use as user input.
 */
class UserInput {
    private Supplier<String> data;
    public UserInput(Supplier<String> data) {
        this.data = data;
    }
    public String getData() {
        return data.get();
    }
}
