package red.felnull.ikisugibot.handler;

import discord4j.common.util.Snowflake;
import red.felnull.ikisugibot.Main;
import red.felnull.ikisugibot.OptionConfig;
import red.felnull.ikisugibot.command.IKSGCommands;
import red.felnull.ikisugibot.util.DiscordUtil;

import java.util.Calendar;

public class TimeHandler {

    public static void onTime(int year, int month, int date, int hour, int minute) {
        Calendar cak = Calendar.getInstance();
        if (hour == 6 && minute == 59) {
            DiscordUtil.sendMessage(OptionConfig.TIME_SIGNAL_CHANNELID, "┗( ･_･)┛<6時59分!6時59分!\n" + "  《    》");
        }
        if (hour == 7 && minute == 19) {
            DiscordUtil.sendMessage(OptionConfig.TIME_SIGNAL_CHANNELID, "┗(≧Д≦)┛<19時19分!19時19分!\n" + "  《    》");
        }

        if (hour == 0 && minute == 0) {
            DiscordUtil.sendMessage(OptionConfig.TIME_SIGNAL_CHANNELID, "<@453441552275210252>警告あなたのコンピュータはこれ以上電源がついているとウイルスに感染します。直ちに寝なさい");
        }


        //   Main.CLIENT.getChannelById(Snowflake.of(Main.CLIENT.getUserById(Snowflake.of(453441552275210252l)).getPrivateChannel().block().id())).createMessage("早く寝ろ").block();
        Main.CLIENT.getChannelById(Snowflake.of(Main.CLIENT.getUserById(Snowflake.of(359134298143719424l)).getPrivateChannel().block().id())).createMessage("早く寝ろ").block();
        //   Main.CLIENT.getChannelById(Snowflake.of(Main.CLIENT.getUserById(Snowflake.of(328520268274204673l)).getPrivateChannel().block().id())).createMessage("早く寝ろ").block();


        if (hour == 7 && minute == 30) {
            DiscordUtil.sendMessage(OptionConfig.TIME_SIGNAL_CHANNELID, "定期連絡");
            IKSGCommands.send(OptionConfig.TIME_SIGNAL_CHANNELID, "covid-19", "japan");
        }

    }
}
