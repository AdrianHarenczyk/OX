package ox.app.utility;

import ox.app.game.Board;
import ox.app.game.Coordinate;
import ox.app.game.Symbol;

import java.util.Map;
import java.util.function.Consumer;

public class BoardDrawer {
    private static final String OS_NAME;
    private static final String WINDOWS = "windows";
    static {
        String[] array = System.getProperty("os.name").split(" ");
        OS_NAME = array[0].toLowerCase();
    }

    public static void showBoard(Board board, Consumer<String> output) {
        Map<Coordinate,Symbol> coordinatesSymbolMap = board.getMap();
        int width = board.getWidth();
        int size = width * board.getHeight();

        int columnCounter = 0;
        for (int i = 1; i <= size; i++) {
            if (columnCounter == width) {
                printBlankLine(output);
                columnCounter = 0;
            }
            Symbol receivedSymbol = coordinatesSymbolMap.get(Coordinate.apply(i));
            changePrintMode(receivedSymbol,i,size,output);
            columnCounter++;
        }
        printBlankLine(output);
    }

    private static void changePrintMode(Symbol receivedSymbol, int iterator, int size, Consumer<String> output) {
        if (OS_NAME.equalsIgnoreCase(WINDOWS)) {
            windowsPrintMode(receivedSymbol,iterator,size,output);
        } else {
            linuxPrintMode(receivedSymbol,iterator,size,output);
        }
    }

    private static void linuxPrintMode(Symbol receivedSymbol, int iterator, int size, Consumer<String> output) {
        String symbolColor;
        if (receivedSymbol == null) {
            output.accept(linuxMessageForNumber(size,iterator));
        } else if (receivedSymbol.equals(Symbol.X)) {
            symbolColor = ConsoleColor.BLUE.toString();
            output.accept(linuxMessageForSymbol(symbolColor,size,receivedSymbol));
        } else {
            symbolColor = ConsoleColor.PURPLE.toString();
            output.accept(linuxMessageForSymbol(symbolColor,size, receivedSymbol));
        }
    }
    private static String linuxMessageForSymbol(String symbolColor, int size, Symbol receivedSymbol) {
        return String.format(ConsoleColor.RED+"["+symbolColor+"%1$-"+String.valueOf(size).length()+"s"+ ConsoleColor.RED+"]"+ ConsoleColor.RESET,receivedSymbol);
    }
    private static String linuxMessageForNumber(int size, int iterator) {
        return String.format(ConsoleColor.RED+"["+ ConsoleColor.GRAY +"%1$-"+String.valueOf(size).length()+"s"+ ConsoleColor.RED+"]"+ ConsoleColor.RESET, iterator);
    }

    private static void windowsPrintMode(Symbol receivedSymbol, int iterator, int size, Consumer<String> output) {
        if (receivedSymbol == null) {
            output.accept(windowsMessageForNumber(size,iterator));
        } else {
            output.accept(windowsMessageForSymbol(size, receivedSymbol));
        }
    }
    private static String windowsMessageForNumber(int size, int iterator) {
        return String.format("[%1$-"+String.valueOf(size).length()+"s]",iterator);
    }
    private static String windowsMessageForSymbol(int size, Symbol receivedSymbol) {
        return String.format("[%1$-" + String.valueOf(size).length() + "s]", receivedSymbol);
    }

    private static void printBlankLine(Consumer<String> output) {
        output.accept("\n");
    }
}
