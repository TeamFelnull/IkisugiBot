package red.felnull.ikisugibot.command;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import red.felnull.ikisugibot.OptionConfig;
import red.felnull.ikisugibot.util.StringHelper;

public class SpamCommand extends Command {
    @Override
    public void start(MessageCreateEvent e, String[] attackd) {
        Message message = e.getMessage();
        MessageChannel channel = message.getChannel().block();
        if (attackd[0] == null) {
            channel.createMessage("使用例 -> " + OptionConfig.COMMAND + " " + "spam [メッセージ] [回数] [１行ずつ送るか]").block();
            return;
        }
        String spamed = attackd[0];
        int cont = attackd.length > 1 ? StringHelper.forInt(attackd[1]) : 0;
        cont = cont <= 0 ? 10 : cont;

        boolean onegyou = attackd.length > 2 ? StringHelper.forBoolean(attackd[2]) : false;
        try {
            if (onegyou) {
                for (int i = 0; i < cont; i++) {
                    channel.createMessage(spamed).block();
                }
            } else {
                String text = "";
                for (int i = 0; i < cont; i++) {
                    text += spamed;
                }
                channel.createMessage(text).block();
            }
        } catch (Exception ex) {
            String st = "エラーが発生しました:";
            st += ex.getLocalizedMessage();
            channel.createMessage(st).block();
        }

    }

    @Override
    public String getExplanatory() {
        return "何度も同じことを発言したい人用";
    }
}
