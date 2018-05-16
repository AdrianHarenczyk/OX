package ox.app;

import ox.app.run.ApplicationRunner;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        ApplicationRunner.run(args, new Scanner(System.in)::nextLine, System.out::println, System.out::printf);
    }
}
