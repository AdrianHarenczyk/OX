package ox.app.utility;

import ox.app.exceptions.WrongArgumentException;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class SetupChooser {
    private static int iterator = 3;
    private static final String WRONG_COMMAND = "You passed unknown command.\nTry again (number of tries after default settings: ";
    private static final String CUSTOM_SETTINGS_MESSAGE = "CUSTOM SETTINGS\n";
    private static final String DEFAULT_SETTINGS_MESSAGE = "DEFAULT SETTINGS\n";


    public static boolean check(Supplier<String> input, Consumer<String> output) {
        for (;iterator > 0;iterator--) {
            try {
                switch (input.get().toLowerCase()) {
                    case "custom":
                        output.accept(CUSTOM_SETTINGS_MESSAGE);
                        return true;
                    case "default":
                        output.accept(DEFAULT_SETTINGS_MESSAGE);
                        return false;
                    default:
                        if (iterator > 1) {
                            throw new WrongArgumentException(WRONG_COMMAND + (iterator - 1));
                        }
                }
            } catch (WrongArgumentException e) {
                output.accept(e.getMessage());
            }
        }
        output.accept(DEFAULT_SETTINGS_MESSAGE);
        return false;
    }
}
