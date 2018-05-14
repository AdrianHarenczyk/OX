package ox.app.utility;

public enum ConsoleColor {
    RESET("\u001B[0m"),
    RED("\u001B[31m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    GRAY("\u001b[37m");

    private final String value;
    ConsoleColor(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
