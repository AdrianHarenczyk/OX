package oxgame.app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Setup(new Scanner(System.in)::nextLine, System.out::println).initializeAGame();
/*
        Board board = Board.newBoard(3,3);
        board.placeSymbol(Coordinates.apply("1"),Symbol.X);
        board.placeSymbol(Coordinates.apply("6"),Symbol.O);
        board.showBoard();
*/

    }
}
