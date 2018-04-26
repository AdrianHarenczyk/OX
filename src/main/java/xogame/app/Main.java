package xogame.app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Setup(new Scanner(System.in)::nextLine, System.out::println).initializeAGame();
    }
}
