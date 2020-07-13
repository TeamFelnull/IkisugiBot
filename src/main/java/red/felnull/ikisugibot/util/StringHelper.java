package red.felnull.ikisugibot.util;

public class StringHelper {
    public static int forInt(String st) {
        try {
            return Integer.parseInt(st);
        } catch (Exception ex) {
            return 0;
        }
    }

    public static boolean forBoolean(String st) {
        try {
            return Boolean.parseBoolean(st);
        } catch (Exception ex) {
            return false;
        }
    }
}
