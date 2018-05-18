package ox.app.utility;

import ox.app.exceptions.WrongArgumentException;
import ox.app.io.InputOutput;
import ox.app.languages.Messenger;

public class SetupChooser {
    private static int retryIterator = 3;

    public static boolean check(InputOutput inputOutput, Messenger messenger) {
        for (; retryIterator > 0; retryIterator--) {
            try {
                switch (inputOutput.input().toLowerCase()) {
                    case "custom":
                        inputOutput.message(messenger.customSettingsMessage());
                        return true;
                    case "default":
                        inputOutput.message(messenger.defaultSettingsMessage());
                        return false;
                    default:
                        if (retryIterator > 1) {
                            throw new WrongArgumentException(messenger.wrongCommandMessage() + (retryIterator - 1));
                        }
                }
            } catch (WrongArgumentException e) {
                inputOutput.message(e.getMessage());
            }
        }
        inputOutput.message(messenger.defaultSettingsMessage());
        return false;
    }
}
