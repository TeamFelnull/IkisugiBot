package red.felnull.ikisugibot.command;

import red.felnull.ikisugibot.util.DiscordUtil;

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
        reg("twitter", new TwitterCommand());
    }

    public static void send(long chanelID, String comand, String... insus) {
        try {


            COMMANDS.get(comand).start(chanelID, insus);
        } catch (Exception ex) {
            String st = "エラーが発生しました:";
            st += ex.getLocalizedMessage();
            DiscordUtil.sendMessage(chanelID, st);
        }
    }

    public static void reg(String st, Command cm) {
        COMMANDS.put(st, cm);
    }
}
