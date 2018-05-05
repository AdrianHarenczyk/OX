package oxgame.app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        new Setup(new Scanner(System.in)::nextLine, System.out::println).initializeAGame();
        Board board = Board.newBoard(7,9);
        board.placeSymbol(new Coordinates(1),Symbol.X);
        board.placeSymbol(new Coordinates(6),Symbol.O);
        board.showBoard();

    }
}
