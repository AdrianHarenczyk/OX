package oxgame.app;
import oxgame.app.exceptions.WrongArgumentException;
import oxgame.app.states.Setup;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            new Setup(new Scanner(System.in)::nextLine, System.out::println).initializeAGame();
        } catch (WrongArgumentException e) {
            System.out.println(e.getCause() + e.getMessage());
        }
    }
}
