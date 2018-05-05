package oxgame.app;

import java.util.HashMap;
import java.util.Map;
import static oxgame.app.ConsoleColor.*;

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
            changePrintMode(receivedSymbol,i);
            columnCounter++;
        }
    }

    private void changePrintMode(Symbol receivedSymbol, int iterator) {
        String consoleColor;
        if (receivedSymbol == null) {
            System.out.printf(RED+"["+RESET+"%1$-"+String.valueOf(size).length()+"s"+RED+"]"+RESET,iterator);
            return;
        } else if (receivedSymbol.equals(Symbol.X)) {
            consoleColor = BLUE.toString();
        } else {
            consoleColor = PURPLE.toString();
        }
        System.out.printf(RED+"["+consoleColor+"%1$-"+String.valueOf(size).length()+"s"+RED+"]"+RESET,receivedSymbol);
    }
}
