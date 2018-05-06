package oxgame.app;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

public class VictoryChecker {

    public static Symbol check(Board currentBoard) {
        SortedSet<Coordinates> set;
        int width = currentBoard.getWidth();
        for (Method method:VictoryChecker.class.getDeclaredMethods()) {
            if (method.getName().startsWith("check")) {
                set = currentBoard.getCoordinates(Symbol.X);
                method.invoke(null,)
            }
        }
        if (set!=null) {

        }
        return null;
    }

    private static Symbol winnerIsX(boolean checkingMethod) {
        if (checkingMethod) {
            return Symbol.X;
        } else {
            return null;
        }
    }
    private static Symbol winnerIsO(boolean checkingMethod) {
        if (checkingMethod) {
            return Symbol.O;
        } else {
            return null;
        }
    }

    private static boolean checkHorizontal(SortedSet<Coordinates> set, int winCombination) {
        List<Integer> list = new ArrayList<>();
        set.forEach(coordinate -> list.add(coordinate.getValue()));
        int counter = 1;
        for (int i = 0; i < list.size() -1; i++) {
            if ((list.get(i) + 1) == list.get(i + 1)) {
                counter++;
            }
            if (counter >= winCombination) {
                return true;
            }
        }
        return false;
    }

}
