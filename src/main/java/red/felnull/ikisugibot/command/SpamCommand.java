package red.felnull.ikisugibot.command;

import red.felnull.ikisugibot.OptionConfig;
import red.felnull.ikisugibot.util.DiscordUtil;
import red.felnull.ikisugibot.util.StringHelper;

public class SpamCommand extends Command {
    @Override
    public void start(long chanelID, String[] attackd) {
        if (attackd[0] == null) {
            DiscordUtil.sendMessage(chanelID, "使用例 -> " + OptionConfig.COMMAND + " " + "spam [メッセージ] [回数] [１行ずつ送るか]");
            return;
        }
        String spamed = attackd[0];
        int cont = attackd.length > 1 ? StringHelper.forInt(attackd[1]) : 0;
        cont = cont <= 0 ? 10 : cont;

        boolean onegyou = attackd.length > 2 ? StringHelper.forBoolean(attackd[2]) : false;
        try {
            if (onegyou) {
                for (int i = 0; i < cont; i++) {
                    DiscordUtil.sendMessage(chanelID, spamed);
                }
            } else {
                String text = "";
                for (int i = 0; i < cont; i++) {
                    text += spamed;
                }
                DiscordUtil.sendMessage(chanelID, text);
            }
        } catch (Exception ex) {
            String st = "エラーが発生しました:";
            st += ex.getLocalizedMessage();
            DiscordUtil.sendMessage(chanelID, st);
        }

    }

    @Override
    public String getExplanatory() {
        return "何度も同じことを発言したい人用";
    }
}
