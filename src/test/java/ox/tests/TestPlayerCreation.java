package ox.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ox.app.game.Symbol;
import ox.app.languages.InstructionDriver;
import ox.app.languages.Language;
import ox.app.validators.SymbolValidator;
import ox.app.exceptions.WrongArgumentException;

public class TestPlayerCreation {
    private static InstructionDriver instructionDriver = new InstructionDriver(Language.EN);

    @Test(expectedExceptions = WrongArgumentException.class)
    public static void whenPassingOtherCharThanCorrespondingToSymbolMethodThrowsIAException() throws WrongArgumentException {
        SymbolValidator.validateSymbol(() -> "h", instructionDriver);
    }

    @Test
    public static void symbolValidatorReturnsXWhenPassedSuchString() throws WrongArgumentException {
        // Given
        // When
        Symbol symbol = SymbolValidator.validateSymbol(() -> "x", instructionDriver);
        // Then
        Assert.assertEquals(symbol, Symbol.X);
    }

    @Test
    public static void symbolValidatorReturnsOEvenWhenPassedZero() throws WrongArgumentException {
        // Given
        // When
        Symbol symbol = SymbolValidator.validateSymbol(() -> "0", instructionDriver);
        // Then
        Assert.assertEquals(symbol, Symbol.O);
    }


}
