package red.felnull.ikisugibot.command;

import red.felnull.ikisugibot.OptionConfig;
import red.felnull.ikisugibot.inm.GorokuEnum;
import red.felnull.ikisugibot.util.DiscordUtil;

public class GorokuCommand extends Command {
    @Override
    public void start(long chanelID, String[] attackd) {

        if (attackd[0] == null) {
            DiscordUtil.sendMessage(chanelID, "使用例 -> " + OptionConfig.COMMAND + " " + "goroku [語録名/list]");
            return;
        } else if (attackd[0].equals("list")) {
            String listText = "語録一覧";
            for (GorokuEnum goroku : GorokuEnum.values()) {
                listText += "\n-" + goroku.toString().toLowerCase();
            }
            DiscordUtil.sendMessage(chanelID, listText);
            return;
        }

        for (GorokuEnum goroku : GorokuEnum.values()) {
            if (goroku.toString().toLowerCase().equals(attackd[0])) {
                DiscordUtil.sendMessage(chanelID, goroku.goroku);
                return;
            }
        }
        DiscordUtil.sendMessage(chanelID, "その" + attackd[0] + "は存在しません");

    }

    @Override
    public String getExplanatory() {
        return "淫夢語録関係";
    }
}
