package oxgame.app;

import java.util.Objects;

public class Coordinates {
    private int value;
    private Coordinates(int value) {
        this.value = value;
    }

    static Coordinates apply(String value) throws IllegalArgumentException{
        int possibleNumber = CoordinatesValidator.validate(value);
        return new Coordinates(possibleNumber);
    }
    static Coordinates apply(int value) {
        return new Coordinates(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
