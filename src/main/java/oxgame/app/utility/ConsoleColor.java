package oxgame.app.utility;

public enum ConsoleColor {
    RESET("\u001B[0m"),
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001b[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    GRAY("\u001b[37m");

    private String value;
    ConsoleColor(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
