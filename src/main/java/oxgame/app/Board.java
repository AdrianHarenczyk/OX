package oxgame.app;
import java.util.*;

import static oxgame.app.ConsoleColor.*;

public class Board {
    private Map<Symbol,SortedSet<Coordinates>> coordinatesSymbolMap;
    private int width;
    private int height;
    private int size;
    private static SortedSet<Coordinates> filledSet;

    public static Board newBoard(int width, int height) {
        Board board = new Board(width,height);
        filledSet = createFilledSet(board.size);;
        return board;
    }
    private Board(int width, int height) {
        this.coordinatesSymbolMap = new HashMap<>();
        this.width = width;
        this.height = height;
        this.size = width * height;
    }

    public void placeSymbol(Symbol symbol, Coordinates coordinates) {
        SortedSet<Coordinates> listOfCoordinates = new TreeSet<>();
        SortedSet<Coordinates> applicableList = coordinatesSymbolMap.getOrDefault(symbol,listOfCoordinates);
        applicableList.add(coordinates);
        coordinatesSymbolMap.put(symbol,applicableList);
    }

    public void showBoard() {
        SortedSet<Coordinates> coordinatesSet;
        int columnCounter = 0;
        for (int i = 1; i <= size; i++) {
            if (columnCounter == width) {
                System.out.println();
                columnCounter = 0;
            }
            if ((coordinatesSet = getCoordinates(Symbol.X)) != null && coordinatesSet.contains(Coordinates.apply(i)) ) {
                changePrintMode(Symbol.X,i);
            } else if ((coordinatesSet = getCoordinates(Symbol.O)) != null && coordinatesSet.contains(Coordinates.apply(i)) ) {
                changePrintMode(Symbol.O,i);
            } else {
                changePrintMode(null,i);
            }
            columnCounter++;
        }
        System.out.println();
    }

    private void changePrintMode(Symbol receivedSymbol, int iterator) {
        String consoleColor;
        if (receivedSymbol == null) {
            System.out.printf(RED+"["+ GRAY +"%1$-"+String.valueOf(size).length()+"s"+RED+"]"+RESET,iterator);
            return;
        } else if (receivedSymbol.equals(Symbol.X)) {
            consoleColor = BLUE.toString();
        } else {
            consoleColor = PURPLE.toString();
        }
        System.out.printf(RED+"["+consoleColor+"%1$-"+String.valueOf(size).length()+"s"+RED+"]"+RESET,receivedSymbol);
    }
    public int size() {
        return size;
    }
    public SortedSet<Coordinates> getCoordinates(Symbol symbol) {
        return coordinatesSymbolMap.get(symbol);
    }

    private static SortedSet<Coordinates> createFilledSet(int boardSize) {
        SortedSet<Coordinates> set = new TreeSet<>();
        for (int i = 1; i <= boardSize; i++) {
            set.add(Coordinates.apply(i));
        }
        return set;
    }
}