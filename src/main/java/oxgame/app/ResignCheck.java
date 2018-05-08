package oxgame.app;

public class ResignCheck {

    public static boolean check(String input) {
        switch (input.toLowerCase()) {
            case "resign":
                return true;
            default:
                return false;
        }
    }
}
