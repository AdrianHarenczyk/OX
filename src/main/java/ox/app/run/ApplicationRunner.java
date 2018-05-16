package ox.app.run;

import ox.app.exceptions.WrongArgumentException;
import ox.app.languages.InstructionDriver;
import ox.app.languages.Language;
import ox.app.states.Setup;

import java.util.Scanner;

public class ApplicationRunner {

    public static void run(String[] args) {
        String languageCommand = "default";
        if (args.length > 0 && args[0] != null) {
            languageCommand = args[0].toLowerCase();
        }
        Language language;
        try {
            switch (languageCommand) {
                case "pl":
                    language = Language.PL;
                    break;
                case "en":
                    language = Language.EN;
                    break;
                default:
                    language = Language.EN;
            }
            new Setup(new Scanner(System.in)::nextLine, System.out::println, System.out::printf, new InstructionDriver(language)).initializeAGame();
        } catch (WrongArgumentException e) {
            System.out.println(e.getCause() + e.getMessage());
        }
    }
}
