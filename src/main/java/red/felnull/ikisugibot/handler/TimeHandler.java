package red.felnull.ikisugibot.handler;

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

        if (hour == 7 && minute == 30) {
            DiscordUtil.sendMessage(OptionConfig.TIME_SIGNAL_CHANNELID, "定期連絡");
            IKSGCommands.send(OptionConfig.TIME_SIGNAL_CHANNELID, "covid-19", "japan");
        }

    }
}
