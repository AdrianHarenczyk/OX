package ox.app.utility;

import ox.app.game.Board;
import ox.app.game.Coordinate;
import ox.app.game.Symbol;

import java.util.Map;

public class BoardDrawer {
    private static final String OS_NAME;
    static {
        String[] array = System.getProperty("os.name").split(" ");
        OS_NAME = array[0].toLowerCase();
    }

    public static void showBoard(Board board) {
        Map<Coordinate,Symbol> coordinatesSymbolMap = board.getMap();
        int width = board.getWidth();
        int size = width * board.getHeight();

        int columnCounter = 0;
        for (int i = 1; i <= size; i++) {
            if (columnCounter == width) {
                System.out.println();
                columnCounter = 0;
            }
            Symbol receivedSymbol = coordinatesSymbolMap.get(Coordinate.apply(i));
            changePrintMode(receivedSymbol,i,size);
            columnCounter++;
        }
        System.out.println();
    }
    private static void changePrintMode(Symbol receivedSymbol, int iterator, int size) {
        if (OS_NAME.equalsIgnoreCase("windows")) {
            windowsPrintMode(receivedSymbol,iterator,size);
        } else {
            linuxPrintMode(receivedSymbol,iterator,size);
        }
    }
    private static void linuxPrintMode(Symbol receivedSymbol, int iterator, int size) {
        String symbolColor;
        if (receivedSymbol == null) {
            System.out.printf(ConsoleColor.RED+"["+ ConsoleColor.GRAY +"%1$-"+String.valueOf(size).length()+"s"+ ConsoleColor.RED+"]"+ ConsoleColor.RESET,iterator);
        } else if (receivedSymbol.equals(Symbol.X)) {
            symbolColor = ConsoleColor.BLUE.toString();
            System.out.printf(linuxMessageForSymbol(symbolColor,size),receivedSymbol);
        } else {
            symbolColor = ConsoleColor.PURPLE.toString();
            System.out.printf(linuxMessageForSymbol(symbolColor,size),receivedSymbol);
        }
    }
    private static String linuxMessageForSymbol(String symbolColor, int size) {
        return ConsoleColor.RED+"["+symbolColor+"%1$-"+String.valueOf(size).length()+"s"+ ConsoleColor.RED+"]"+ ConsoleColor.RESET;
    }
    private static void windowsPrintMode(Symbol receivedSymbol, int iterator, int size) {
        if (receivedSymbol == null) {
            System.out.printf("[%1$-"+String.valueOf(size).length()+"s]",iterator);
        } else {
            System.out.printf("[%1$-" + String.valueOf(size).length() + "s]", receivedSymbol);
        }
    }
}
