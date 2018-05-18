package ox.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ox.app.exceptions.WrongArgumentException;
import ox.app.io.InputOutput;
import ox.app.languages.Language;
import ox.app.languages.Messenger;
import ox.app.states.Setup;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class TestSimulation {
    @BeforeMethod
    private static void init() {
        String[] args = {"PL"};

    }

    @DataProvider(name = "data")
    Object[][] data() {
        return new Object[][]{
                {"default\n1\n2\n3\n" +
                        "4\n5\n6\n7\n" +
                        "\n1\n2\n3\n4\n" +
                        "5\n6\n7\n\n1\n" +
                        "2\n3\n4\n5\n6\n" +
                        "7\n\n\n"},
        };
    }

    @Test(dataProvider = "data")
    public static void testName(String playerInput) throws WrongArgumentException {
        List<String> listOfMessages = new ArrayList<>();

        System.setIn(new ByteArrayInputStream(playerInput.getBytes()));
        Supplier<String> input = new Scanner(System.in)::nextLine;
        Consumer<String> output = listOfMessages::add;
        Consumer<String> boardOutput = s -> {
        };

        Messenger messenger = new Messenger(Language.EN);

        InputOutput inputOutput = new InputOutput(input, output, boardOutput);
        new Setup(inputOutput, messenger).initializeAGame();

        String lastMessageInList = listOfMessages.get(listOfMessages.size() - 1);

        Assert.assertEquals(messenger.exitAppMessage(),lastMessageInList);
    }
}
