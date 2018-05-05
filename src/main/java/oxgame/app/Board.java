package oxgame.app;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private Map<Coordinates,Symbol> coordinatesSymbolMap;
    private int width;
    private int height;
    private int size;

    public static Board newBoard(int width, int height) {
        Board board = new Board(width,height);
        for (int i = 1; i <= board.size; i++) {
            board.coordinatesSymbolMap.put(new Coordinates(i),null);
        }
        return board;
    }
    private Board(int width, int height) {
        this.coordinatesSymbolMap = new HashMap<>();
        this.width = width;
        this.height = height;
        this.size = width * height;
    }

    public void placeSymbol(Coordinates coordinates, Symbol symbol) {
        coordinatesSymbolMap.put(coordinates,symbol);
    }

    public void showBoard() {
        int columnCounter = 0;
        for (int i = 1; i <= size; i++) {
            if (columnCounter == width) {
                System.out.println();
                columnCounter = 0;
            }
            Symbol receivedSymbol = coordinatesSymbolMap.get(new Coordinates(i));
            if (receivedSymbol == null) {
                System.out.printf("[%1$-"+String.valueOf(size).length()+"s]",i);
            } else {
                System.out.printf("[%1$-"+String.valueOf(size).length()+"s]",receivedSymbol);
            }
            columnCounter++;
        }

    }
}
