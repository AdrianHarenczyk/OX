package ox.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ox.app.game.Board;
import ox.app.languages.InstructionDriver;
import ox.app.languages.Language;
import ox.app.validators.WinningStrokeValidator;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class TestWinningStrokeValidator {
    private static final InstructionDriver instructionDriver = new InstructionDriver(Language.EN);
    private static final Board board = Board.newBoard(50, 50);

    @BeforeMethod
    private static void init() {

    }

    @DataProvider(name = "validStrokeValues")
    Object[][] validStrokeValues() {
        return new Object[][]{
                {"6"},
                {"9"},
                {"3"},
                {"49"},
                {"7"},
                {"32"},
                {"24"},
                {"5"},
                {"30"},
                {"22"},
                {"24"},
                {"17"}
        };
    }

    @Test(dataProvider = "validStrokeValues")
    public static void whenLessThanBoardDimensionsAndMoreThanTwoWinningStrokeIsValid(String winningStrokeSupplier) {
        // Given
        Supplier<String> input = () -> winningStrokeSupplier;
        Consumer<String> output = s -> {
        };
        // When
        int returnedValidWinningStroke = WinningStrokeValidator.validate(board, input, output, instructionDriver);
        int providedWinningStroke = Integer.parseInt(input.get());
        // Then
        Assert.assertEquals(providedWinningStroke, returnedValidWinningStroke);
    }
}
