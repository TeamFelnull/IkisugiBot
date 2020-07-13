package red.felnull.ikisugibot.util;

import java.io.*;
import java.nio.file.Path;
import java.util.Map;

public class FileLoader {
    public static void createFolder(Path path) {
        path.toFile().mkdirs();
    }

    public static void txtWriter(Map<String, String> map, Path path, boolean isCreate) {
        if (isCreate)
            createFolder(path.getParent());
        try {
            FileWriter fw = new FileWriter(path.toString(), false);
            PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
            map.entrySet().forEach(maps -> {
                pw.println(maps.getKey() + ":" + maps.getValue());
            });

            pw.close();
        } catch (IOException e) {

        }
    }

    public static void txtReader(Map<String, String> map, Path path) {
        map.clear();
        try {
            FileReader re = new FileReader(path.toString());
            BufferedReader bre = new BufferedReader(re);
            String st;
            while ((st = bre.readLine()) != null) {
                try {
                    String[] fruit = st.split(":", 0);
                    map.put(fruit[0], fruit[1]);
                } catch (Exception e) {

                }
            }

        } catch (IOException e) {

        }

    }
}
