package oxgame.app;

import java.lang.reflect.Method;
import java.util.*;

public class VictoryChecker {

    public static Symbol check(Board currentBoard) {
        SortedSet<Coordinates> setX;
        SortedSet<Coordinates> setO;

        if ((setX = currentBoard.getCoordinates(Symbol.X)) == null) {
            setX = new TreeSet<>();
        }
        if ((setO = currentBoard.getCoordinates(Symbol.O)) == null) {
            setO = new TreeSet<>();
        }

        int width = currentBoard.getWidth();

        boolean xWinner = (
                checkHorizontal(setX,3,width) ||
                checkVertical(setX,3,width) ||
                checkFrontSlash(setX,3,width) ||
                checkBackSlash(setX,3,width)
        );

        boolean oWinner = (
                checkHorizontal(setO,3,width) ||
                checkVertical(setO,3,width) ||
                checkFrontSlash(setO,3,width) ||
                checkBackSlash(setO,3,width)
        );

        if (xWinner) {
            return Symbol.X;
        } else if (oWinner) {
            return Symbol.O;
        } else {
            return null;
        }
    }

    private static boolean checkHorizontal(SortedSet<Coordinates> set, int winCombination, int width) {
        List<Integer> list = new ArrayList<>();
        set.forEach(coordinate -> list.add(coordinate.getValue()));
        int counter = 1;
        for (int i = 0; i < list.size() -1; i++) {
            if ((list.get(i) + 1) == list.get(i + 1)
                    &&
                !(list.get(i)%width==0)) {
                counter++;
            } else {
                counter =1;
            }
            if (counter >= winCombination) {
                return true;
            }
        }
        return false;
    }
    private static boolean checkVertical(SortedSet<Coordinates> set, int winCombination, int width) {
        List<Integer> list = new ArrayList<>();
        set.forEach(coordinate -> list.add(coordinate.getValue()));
        int counter = 1;
        for (int i = 0; i < list.size() -1; i++) {
            if ((list.get(i) + width) == list.get(i + 1)) {
                counter++;
            }
            if (counter >= winCombination) {
                return true;
            }
        }
        return false;
    }
    private static boolean checkFrontSlash(SortedSet<Coordinates> set, int winCombination, int width) {
        List<Integer> list = new ArrayList<>();
        set.forEach(coordinate -> list.add(coordinate.getValue()));
        int counter = 1;
        for (int i = 0; i < list.size() -1; i++) {
            if (
                    ( list.contains(list.get(i) + width - 1) )
                    &&
                    !(list.get(i)%width==0)
            ){
                counter++;
            } else {
                counter =1;
            }
            if (counter >= winCombination) {
                return true;
            }
        }
        return false;
    }
    private static boolean checkBackSlash(SortedSet<Coordinates> set, int winCombination, int width) {
        List<Integer> list = new ArrayList<>();
        set.forEach(coordinate -> list.add(coordinate.getValue()));
        int counter = 1;
        for (int i = 0; i < list.size() -1; i++) {
            if (
                    ( list.contains(list.get(i) + width + 1) )
                    &&
                    !(list.get(i)%width==0)
            ){
                counter++;
            } else {
                counter =1;
            }
            if (counter >= winCombination) {
                return true;
            }
        }
        return false;
    }
}
