package red.felnull.ikisugibot.handler;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import red.felnull.ikisugibot.OptionConfig;
import red.felnull.ikisugibot.command.IKSGCommands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MessageHandler {
    public static void onMessageCreate(MessageCreateEvent e) {
        Message message = e.getMessage();
        MessageChannel channel = message.getChannel().block();
        String[] messages = message.getContent().split(" ");

        if (messages.length == 0 || !messages[0].equals(OptionConfig.COMMAND))
            return;

        if (messages.length < 2) {
            channel.createMessage("使用例 -> " + OptionConfig.COMMAND + " " + "list").block();
            return;
        }
        List<String> ml = new ArrayList<String>();
        ml.addAll(Arrays.asList(messages));
        ml.remove(0);
        onComandMessage(e, ml.toArray(new String[1]));
    }

    public static void onComandMessage(MessageCreateEvent e, String[] mozis) {
        Message message = e.getMessage();
        MessageChannel channel = message.getChannel().block();
        if (IKSGCommands.COMMANDS.containsKey(mozis[0])) {
            List<String> ml = new ArrayList<String>();
            ml.addAll(Arrays.asList(mozis));
            ml.remove(0);
            IKSGCommands.COMMANDS.get(mozis[0]).start(e, ml.toArray(new String[1]));
        } else {
            Random r = new Random();
            channel.createMessage((r.nextBoolean() ? "" : "申し訳ないが") + mozis[0] + "は存在しないコマンドです").block();
        }
    }
}
