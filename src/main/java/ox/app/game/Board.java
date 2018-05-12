package ox.app.game;

import ox.app.utility.ConsoleColor;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private Map<Coordinates,Symbol> coordinatesSymbolMap;
    private int width;
    private int size;
    private int height;
    private static final String OS_NAME;
    static {
        String[] array = System.getProperty("os.name").split(" ");
        OS_NAME = array[0].toLowerCase();
    }

    public static Board newBoard(int width, int height) {
        Board board = new Board(width,height);
        for (int i = 1; i <= board.size; i++) {
            board.coordinatesSymbolMap.put(Coordinates.apply(i), null);
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
    public void placeSymbol(int coordinates, Symbol symbol) {
        placeSymbol(Coordinates.apply(coordinates),symbol);
    }

    public void showBoard() {
        int columnCounter = 0;
        for (int i = 1; i <= size; i++) {
            if (columnCounter == width) {
                System.out.println();
                columnCounter = 0;
            }
            Symbol receivedSymbol = coordinatesSymbolMap.get(Coordinates.apply(i));
            changePrintMode(receivedSymbol,i);
            columnCounter++;
        }
        System.out.println();
    }

    private void changePrintMode(Symbol receivedSymbol, int iterator) {
        if (OS_NAME.equalsIgnoreCase("windows")) {
            windowsPrintMode(receivedSymbol,iterator);
        } else {
            linuxPrintMode(receivedSymbol,iterator);
        }
    }
    private void linuxPrintMode(Symbol receivedSymbol, int iterator) {
        String consoleColor;
        if (receivedSymbol == null) {
            System.out.printf(ConsoleColor.RED+"["+ ConsoleColor.GRAY +"%1$-"+String.valueOf(size).length()+"s"+ ConsoleColor.RED+"]"+ ConsoleColor.RESET,iterator);
            consoleColor = ConsoleColor.GRAY.toString();
        } else if (receivedSymbol.equals(Symbol.X)) {
            consoleColor = ConsoleColor.BLUE.toString();
        } else {
            consoleColor = ConsoleColor.PURPLE.toString();
        }
        System.out.printf(ConsoleColor.RED+"["+consoleColor+"%1$-"+String.valueOf(size).length()+"s"+ ConsoleColor.RED+"]"+ ConsoleColor.RESET,receivedSymbol);
    }
    private void windowsPrintMode(Symbol receivedSymbol, int iterator) {
        if (receivedSymbol == null) {
            System.out.printf("[%1$-"+String.valueOf(size).length()+"s]",iterator);
        } else {
            System.out.printf("[%1$-" + String.valueOf(size).length() + "s]", receivedSymbol);
        }
    }

    public int size() {
        return size;
    }
    public Symbol getSymbol(Coordinates coordinates) {
        return coordinatesSymbolMap.get(coordinates);
    }
    public Symbol getSymbol(int number) {
        return getSymbol(Coordinates.apply(number));
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
