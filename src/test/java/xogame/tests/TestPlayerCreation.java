package xogame.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import xogame.app.Symbol;
import xogame.app.SymbolValidator;

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
