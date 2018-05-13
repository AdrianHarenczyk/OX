package ox.app.validators;

import ox.app.exceptions.WrongArgumentException;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class NameValidator {
    private static final String EMPTY_NAME_MESSAGE = "Name which can't be empty. Please pass valid name.";

    public static String isNotEmpty(Supplier<String> input, Consumer<String> output) {
        String validName;
        while (true) {
            try {
                if ((validName = input.get().trim()).equals("")) {
                    throw new WrongArgumentException(EMPTY_NAME_MESSAGE);
                } else return validName;
            } catch (WrongArgumentException e) {
                output.accept(e.getMessage());
            }
        }
    }
}
