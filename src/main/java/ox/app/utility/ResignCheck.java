package ox.app.utility;

public class ResignCheck {

    private ResignCheck() {
    }

    public static boolean check(String input) {
        return input.toLowerCase().trim().equals("resign");
    }

    public static boolean checkAll(String input) {
        return input.toLowerCase().trim().equals("resign all") ||
                input.toLowerCase().trim().equals("resignall");
    }
}
