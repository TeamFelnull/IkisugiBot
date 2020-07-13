package red.felnull.ikisugibot.command;

import red.felnull.ikisugibot.OptionConfig;
import red.felnull.ikisugibot.util.DiscordUtil;

import java.util.Map;

public class ListCommand extends Command {
    @Override
    public void start(long chanelID, String[] attackd) {
        String lists = "コマンド一覧\n";
        for (Map.Entry<String, Command> comand : IKSGCommands.COMMANDS.entrySet()) {
            lists += "-" + comand.getKey() + " " + comand.getValue().getExplanatory() + "\n";
        }
        lists += "使用時は" + OptionConfig.COMMAND + " [引数１] [引数2] [引数3]...のようにメッセージを送信してください\n";
        DiscordUtil.sendMessage(chanelID, lists);
    }

    @Override
    public String getExplanatory() {
        return "コマンド一覧を表示します";
    }
}
