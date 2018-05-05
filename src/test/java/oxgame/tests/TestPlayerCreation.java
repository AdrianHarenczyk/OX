package oxgame.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import oxgame.app.Player;
import oxgame.app.Symbol;
import oxgame.app.SymbolValidator;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class TestPlayerCreation {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public static void whenPassingOtherCharThanCorrespondingToSymbolMethodThrowsIAException() throws IllegalArgumentException {
        SymbolValidator.validateSymbol(() -> "h");
    }
    @Test
    public static void symbolValidatorReturnsProperSymbol() {
        // Given
        // When
        Symbol symbol = SymbolValidator.validateSymbol(()-> "x");
        // Then
        Assert.assertEquals(symbol,Symbol.X);
    }


}
