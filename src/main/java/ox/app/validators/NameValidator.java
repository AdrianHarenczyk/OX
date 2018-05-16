package ox.app.validators;

import ox.app.exceptions.WrongArgumentException;
import ox.app.languages.InstructionDriver;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class NameValidator {

    public static String isNotEmpty(Supplier<String> input, Consumer<String> output, InstructionDriver instructionDriver) {
        String validName;
        while (true) {
            try {
                if ((validName = input.get().trim()).equals("")) {
                    throw new WrongArgumentException(instructionDriver.nameCannotBeEmptyError());
                } else return validName;
            } catch (WrongArgumentException e) {
                output.accept(e.getMessage());
            }
        }
    }
}
