package ox.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ox.app.exceptions.WrongArgumentException;
import ox.app.game.Symbol;
import ox.app.languages.Language;
import ox.app.languages.Messenger;
import ox.app.validators.SymbolValidator;

public class TestPlayerCreation {
    private static Messenger messenger = new Messenger(Language.EN);

    @Test(expectedExceptions = WrongArgumentException.class)
    public static void whenPassingOtherCharThanCorrespondingToSymbolMethodThrowsIAException() throws WrongArgumentException {
        SymbolValidator.validateSymbol(() -> "h", messenger);
    }

    @Test
    public static void symbolValidatorReturnsXWhenPassedSuchString() throws WrongArgumentException {
        // Given
        // When
        Symbol symbol = SymbolValidator.validateSymbol(() -> "x", messenger);
        // Then
        Assert.assertEquals(symbol, Symbol.X);
    }

    @Test
    public static void symbolValidatorReturnsOEvenWhenPassedZero() throws WrongArgumentException {
        // Given
        // When
        Symbol symbol = SymbolValidator.validateSymbol(() -> "0", messenger);
        // Then
        Assert.assertEquals(symbol, Symbol.O);
    }


}
