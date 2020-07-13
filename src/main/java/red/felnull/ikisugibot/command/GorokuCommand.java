package red.felnull.ikisugibot.command;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import red.felnull.ikisugibot.OptionConfig;
import red.felnull.ikisugibot.inm.GorokuEnum;

public class GorokuCommand extends Command {
    @Override
    public void start(MessageCreateEvent e, String[] attackd) {
        Message message = e.getMessage();
        MessageChannel channel = message.getChannel().block();

        if (attackd[0] == null) {
            channel.createMessage("使用例 -> " + OptionConfig.COMMAND + " " + "goroku [語録名/list]").block();
            return;
        } else if (attackd[0].equals("list")) {
            String listText = "語録一覧";
            for (GorokuEnum goroku : GorokuEnum.values()) {
                listText += "\n-" + goroku.toString().toLowerCase();
            }
            channel.createMessage(listText).block();
            return;
        }

        for (GorokuEnum goroku : GorokuEnum.values()) {
            if (goroku.toString().toLowerCase().equals(attackd[0])) {
                channel.createMessage(goroku.goroku).block();
                return;
            }
        }
        channel.createMessage("その" + attackd[0] + "は存在しません").block();

    }

    @Override
    public String getExplanatory() {
        return "淫夢語録関係";
    }
}
