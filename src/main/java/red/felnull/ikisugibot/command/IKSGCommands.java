package red.felnull.ikisugibot.command;

import java.util.HashMap;
import java.util.Map;

public class IKSGCommands {
    public static Map<String, Command> COMMANDS = new HashMap<String, Command>();

    public static void init() {
        reg("current", new CurrentTimeCommand());
        reg("covid-19", new CoronaCommand());
        reg("list", new ListCommand());
        reg("spam", new SpamCommand());
        reg("goroku", new GorokuCommand());
        reg("scan", new ScanCommand());
    }

    public static void reg(String st, Command cm) {
        COMMANDS.put(st, cm);
    }
}
