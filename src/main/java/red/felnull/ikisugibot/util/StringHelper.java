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

    public static String[] split(int size, String st) {

        int sps = st.length() / size;
        String[] sts = new String[sps + 1];
        for (int i = 0; i < sps; i++) {
            sts[i] = st.substring(size * i, size * i + size);
        }
        sts[sps] = st.substring(size * sps);
        return sts;
    }
}
