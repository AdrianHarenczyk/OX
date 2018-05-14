package ox.app.game;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private Map<Coordinate,Symbol> coordinatesSymbolMap;
    private int width;
    private int size;
    private int height;


    public static Board newBoard(int width, int height) {
        Board board = new Board(width,height);
        for (int i = 1; i <= board.size; i++) {
            board.coordinatesSymbolMap.put(Coordinate.apply(i), null);
        }
        return board;
    }
    private Board(int width, int height) {
        this.coordinatesSymbolMap = new HashMap<>();
        this.width = width;
        this.height = height;
        this.size = width * height;

    }

    public void placeSymbol(Coordinate coordinate, Symbol symbol) {
        coordinatesSymbolMap.put(coordinate,symbol);
    }
    public void placeSymbol(int coordinates, Symbol symbol) {
        placeSymbol(Coordinate.apply(coordinates),symbol);
    }

    public int size() {
        return size;
    }
    public Symbol getSymbol(Coordinate coordinate) {
        return coordinatesSymbolMap.get(coordinate);
    }
    public Symbol getSymbol(int number) {
        return getSymbol(Coordinate.apply(number));
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public Map<Coordinate, Symbol> getMap() {
        return Collections.unmodifiableMap(coordinatesSymbolMap);
    }
}
