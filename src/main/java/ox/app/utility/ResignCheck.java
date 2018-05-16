package ox.app.utility;

public class ResignCheck {

    public static boolean check(String input) {
        return input.toLowerCase().equals("resign");
    }

    private ResignCheck() {
    }

    public static boolean checkAll(String input) {
        return input.toLowerCase().equals("resign all") ||
                input.toLowerCase().equals("resignall");
    }
}
