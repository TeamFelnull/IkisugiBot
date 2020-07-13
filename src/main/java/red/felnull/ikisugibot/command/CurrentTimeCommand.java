package red.felnull.ikisugibot.command;

import red.felnull.ikisugibot.util.DiscordUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CurrentTimeCommand extends Command {
    @Override
    public void start(long chanelID, String[] attackd) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日E曜日h時m分s秒");
        String lists = "現在は" + sdf.format(calendar.getTime()) + "です\n";
        DiscordUtil.sendMessage(chanelID, lists);
    }

    @Override
    public String getExplanatory() {
        return "現在の日付と時刻を表示します";
    }
}
