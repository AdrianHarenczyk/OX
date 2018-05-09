package oxgame.app.game;

import java.util.Objects;

public class Coordinates implements Comparable<Coordinates>{
    private int value;
    private Coordinates(int value) {
        this.value = value;
    }

    public static Coordinates apply(int value) {
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

    @Override
    public int compareTo(Coordinates o) {
        Integer comparableValue = this.value;
        Integer valueToCompareTo = o.value;
        return comparableValue.compareTo(valueToCompareTo);
    }

    public int getValue() {
        return value;
    }
    @Override
    public String toString() {
        return "Coordinates: " + value;
    }
}
