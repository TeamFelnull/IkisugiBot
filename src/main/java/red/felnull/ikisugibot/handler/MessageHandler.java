package red.felnull.ikisugibot.handler;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import red.felnull.ikisugibot.OptionConfig;
import red.felnull.ikisugibot.command.IKSGCommands;
import red.felnull.ikisugibot.messages.Ai;
import red.felnull.ikisugibot.util.DiscordUtil;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MessageHandler {
    public static void onMessageCreate(MessageCreateEvent e) {
        Message message = e.getMessage();
        MessageChannel channel = message.getChannel().block();

        long chanelID = channel.getId().asLong();
        String[] messages = message.getContent().split(" ");

        message.getAuthor().get().getMention();

        if (channel.getId().asLong() == OptionConfig.AI_CHANNELID) {
            try {
                String re = Ai.getResbone(message.getContent());
                if (re != null)
                    channel.createMessage(re).block();
            } catch (Exception ex) {
                String st = "エラーが発生しました:";
                st += ex.getLocalizedMessage();
                channel.createMessage(st).block();
            }
        }


        if (messages.length == 0 || !messages[0].equals(OptionConfig.COMMAND))
            return;

        if (messages.length < 2) {
            channel.createMessage("使用例 -> " + OptionConfig.COMMAND + " " + "list").block();
            return;
        }
        List<String> ml = new ArrayList<String>();
        ml.addAll(Arrays.asList(messages));
        ml.remove(0);
        onComandMessage(channel.getId().asLong(), ml.toArray(new String[1]));

    }

    public static void onComandMessage(long chanelID, String[] mozis) {
        if (IKSGCommands.COMMANDS.containsKey(mozis[0])) {
            List<String> ml = new ArrayList<String>();
            ml.addAll(Arrays.asList(mozis));
            ml.remove(0);
            try {
                IKSGCommands.COMMANDS.get(mozis[0]).start(chanelID, ml.toArray(new String[1]));
            } catch (Exception ex) {
                String st = "エラーが発生しました:";
                st += ex.getLocalizedMessage();
                DiscordUtil.sendMessage(chanelID, st);
            }
        } else {
            Random r = new Random();
            DiscordUtil.sendMessage(chanelID, (r.nextBoolean() ? "" : "申し訳ないが") + mozis[0] + "は存在しないコマンドです");
        }
    }
}
