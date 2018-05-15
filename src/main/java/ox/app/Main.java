package ox.app;
import ox.app.exceptions.WrongArgumentException;
import ox.app.languages.InstructionDriver;
import ox.app.languages.Language;
import ox.app.states.Setup;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try {
            new Setup(new Scanner(System.in)::nextLine, System.out::println, System.out::printf, new InstructionDriver(Language.EN)).initializeAGame();
        } catch (WrongArgumentException e) {
            System.out.println(e.getCause() + e.getMessage());
        }
    }
}
