package ox.app.game;

import java.util.Objects;

public class Coordinate implements Comparable<Coordinate> {
    private final int value;

    private Coordinate(int value) {
        this.value = value;
    }

    public static Coordinate apply(int value) {
        return new Coordinate(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(Coordinate o) {
        Integer comparableValue = this.value;
        Integer valueToCompareTo = o.value;
        return comparableValue.compareTo(valueToCompareTo);
    }

    public int getValue() {
        return value;
    }
}
