package oxgame.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ox.app.game.Symbol;
import ox.app.validators.SymbolValidator;
import ox.app.exceptions.WrongArgumentException;

public class TestPlayerCreation {

    @Test(expectedExceptions = WrongArgumentException.class)
    public static void whenPassingOtherCharThanCorrespondingToSymbolMethodThrowsIAException() throws WrongArgumentException {
        SymbolValidator.validateSymbol(() -> "h");
    }
    @Test
    public static void symbolValidatorReturnsXWhenPassedSuchString() throws WrongArgumentException {
        // Given
        // When
        Symbol symbol = SymbolValidator.validateSymbol(()-> "x");
        // Then
        Assert.assertEquals(symbol,Symbol.X);
    }
    @Test
    public static void symbolValidatorReturnsOEvenWhenPassedZero() throws WrongArgumentException {
        // Given
        // When
        Symbol symbol = SymbolValidator.validateSymbol(()-> "0");
        // Then
        Assert.assertEquals(symbol,Symbol.O);
    }


}
