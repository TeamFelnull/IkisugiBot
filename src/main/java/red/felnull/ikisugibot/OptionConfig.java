package red.felnull.ikisugibot;

import red.felnull.ikisugibot.util.FileLoader;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class OptionConfig {
    public static Path OPTION_CONFIG_PATH;

    public static String COMMAND;
    public static String TOKEN;

    public static void init() {
        OPTION_CONFIG_PATH = Paths.get("config.txt");
        HashMap<String, String> defmap = new HashMap<String, String>();
        defmap.put("command", "!1919");
        defmap.put("token", "TheTestToken");

        HashMap<String, String> opmap = new HashMap<String, String>();
        FileLoader.txtReader(opmap, OPTION_CONFIG_PATH);
        for (Map.Entry<String, String> en : defmap.entrySet()) {
            if (!opmap.containsKey(en.getKey())) {
                opmap.put(en.getKey(), en.getValue());
            }
        }
        FileLoader.txtWriter(opmap, OPTION_CONFIG_PATH, false);

        COMMAND = opmap.get("command");
        TOKEN = opmap.get("token");

    }

}
