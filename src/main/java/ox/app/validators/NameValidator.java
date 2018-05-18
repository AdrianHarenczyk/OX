package ox.app.validators;

import ox.app.exceptions.WrongArgumentException;
import ox.app.io.InputOutput;
import ox.app.languages.Messenger;

public class NameValidator {

    public static String isNotEmpty(InputOutput inputOutput, Messenger messenger) {
        String validName;
        while (true) {
            try {
                if ((validName = inputOutput.input().trim()).equals("")) {
                    throw new WrongArgumentException(messenger.nameCannotBeEmptyError());
                } else return validName;
            } catch (WrongArgumentException e) {
                inputOutput.message(e.getMessage());
            }
        }
    }
}
