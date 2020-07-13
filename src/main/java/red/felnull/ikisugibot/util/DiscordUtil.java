package red.felnull.ikisugibot.util;

import discord4j.common.util.Snowflake;
import red.felnull.ikisugibot.Main;

public class DiscordUtil {
    public static void sendMessage(long chanelID, String st) {
        SendMessageThread smt = new SendMessageThread(chanelID, st);
        smt.start();
    }
    static class SendMessageThread extends Thread {
        private String message;
        private long chanelID;
        public SendMessageThread(long chanelID, String st) {
            this.message = st;
            this.chanelID = chanelID;
        }
        @Override
        public void run() {
            Main.CLIENT.getChannelById(Snowflake.of(chanelID)).createMessage(message).block();
        }
    }
}
