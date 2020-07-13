package red.felnull.ikisugibot.command;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CurrentTimeCommand extends Command {
    @Override
    public void start(MessageCreateEvent e, String[] attackd) {
        Message message = e.getMessage();
        MessageChannel channel = message.getChannel().block();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日E曜日h時m分s秒");
        String lists = "現在は" + sdf.format(calendar.getTime()) + "です\n";
        channel.createMessage(lists).block();
    }

    @Override
    public String getExplanatory() {
        return "現在の日付と時刻を表示します";
    }
}
