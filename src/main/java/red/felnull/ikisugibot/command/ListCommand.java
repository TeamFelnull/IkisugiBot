package red.felnull.ikisugibot.command;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import red.felnull.ikisugibot.OptionConfig;

import java.util.Map;

public class ListCommand extends Command {
    @Override
    public void start(MessageCreateEvent e, String[] attackd) {
        Message message = e.getMessage();
        MessageChannel channel = message.getChannel().block();
        String lists = "コマンド一覧\n";
        for (Map.Entry<String, Command> comand : IKSGCommands.COMMANDS.entrySet()) {
            lists += "-" + comand.getKey() + " " + comand.getValue().getExplanatory() + "\n";
        }
        lists += "使用時は" + OptionConfig.COMMAND + " [引数１] [引数2] [引数3]...のようにメッセージを送信してください\n";
        channel.createMessage(lists).block();
    }

    @Override
    public String getExplanatory() {
        return "コマンド一覧を表示します";
    }
}
