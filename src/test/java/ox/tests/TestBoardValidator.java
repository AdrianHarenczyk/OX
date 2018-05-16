package ox.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ox.app.game.Board;
import ox.app.languages.InstructionDriver;
import ox.app.languages.Language;
import ox.app.validators.BoardValidator;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class TestBoardValidator {
    private static Board board;
    private static int width;
    private static int height;
    private static final Consumer<String> output = s -> {
    };
    private static final InstructionDriver instructionDriver = new InstructionDriver(Language.EN);

    @BeforeMethod
    private static void init() {
        board = null;
        width = 0;
        height = 0;
    }

    @DataProvider(name = "validNumbers")
    Object[][] validNumbers() {
        return new Object[][]{
                {"3", "3"},
                {"3", "5"},
                {"55", "22"},
                {"33", "11"},
                {"5", "7"},
                {"77", "6"},
                {"75", "52"},
                {"9", "3"},
                {"3", "11"},
        };
    }

    @Test(dataProvider = "validNumbers")
    public static void whenUserProvidesValidDataForBoard_WidthWhichUserProvided_IsBoardActualWidth(String widthString, String heightString) {
        // Given
        Supplier<String> input = () -> widthString;
        width = BoardValidator.validateWidth(input, output, instructionDriver);
        input = () -> heightString;
        height = BoardValidator.validateHeight(input, output, instructionDriver);
        // When
        board = Board.newBoard(width, height);
        // Then
        Assert.assertEquals(width, board.getWidth());
    }
}
