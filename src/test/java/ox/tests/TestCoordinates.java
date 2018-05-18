package ox.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ox.app.game.Coordinate;

public class TestCoordinates {

    @Test
    public static void ifCompareToWithSameValuesInCoordinatesReturnesZero() {
        Coordinate coordinateWithValueTen = Coordinate.apply(10);
        Coordinate coordinateWithSameValue = Coordinate.apply(10);
        int zero = 0;
        Assert.assertEquals(zero, coordinateWithValueTen.compareTo(coordinateWithSameValue));
    }

    @Test
    public static void ifCompareToWithOtherValuesReturnsOneIfGreaterThan() {
        Coordinate valueTen = Coordinate.apply(10);
        Coordinate valueFifteen = Coordinate.apply(15);
        int one = 1;
        Assert.assertEquals(one, valueFifteen.compareTo(valueTen));
    }

    @Test
    public static void ifCompareToWithOtherValuesReturnsMinusOneIfLessThan() {
        Coordinate valueTen = Coordinate.apply(10);
        Coordinate valueFifteen = Coordinate.apply(15);
        int minusOne = -1;
        Assert.assertEquals(minusOne, valueTen.compareTo(valueFifteen));
    }
}
