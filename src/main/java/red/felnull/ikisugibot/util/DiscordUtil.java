package red.felnull.ikisugibot.util;

import discord4j.common.util.Snowflake;
import discord4j.core.object.entity.channel.MessageChannel;
import red.felnull.ikisugibot.Main;

public class DiscordUtil {
    public static void sendMessage(MessageChannel chanel, String st) {
        sendMessage(chanel.getId().asLong(), st);
    }

    public static void sendMessage(long chanelID, String st) {
        SendMessageThread smt = new SendMessageThread(chanelID, StringHelper.split(1999, st));
        smt.start();
    }

    static class SendMessageThread extends Thread {
        private String[] message;
        private long chanelID;

        public SendMessageThread(long chanelID, String[] st) {
            this.message = st;
            this.chanelID = chanelID;
        }

        @Override
        public void run() {

            for (String st : message) {
                Main.CLIENT.getChannelById(Snowflake.of(chanelID)).createMessage(st).block();
            }
        }
    }
}
