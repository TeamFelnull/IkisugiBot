package red.felnull.ikisugibot.handler;

import red.felnull.ikisugibot.OptionConfig;
import red.felnull.ikisugibot.util.DiscordUtil;

import java.util.Calendar;

public class TimeHandler {
    private static int lastminis;

    public static void onTime() {
        Calendar cak = Calendar.getInstance();


        if (lastminis != cak.get(Calendar.MINUTE)) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
            if ((cak.get(Calendar.HOUR) == 18 || cak.get(Calendar.HOUR) == 6) && cak.get(Calendar.MINUTE) == 59) {
                DiscordUtil.sendMessage(OptionConfig.AREA_19_CHANNELID, "┗( ･_･)┛<6時59分!6時59分!\n" + "  《    》");
            }
            if ((cak.get(Calendar.HOUR) == 19 || cak.get(Calendar.HOUR) == 7) && cak.get(Calendar.MINUTE) == 19) {
                DiscordUtil.sendMessage(OptionConfig.AREA_19_CHANNELID, "19.19!");
            }
            lastminis = cak.get(Calendar.MINUTE);
        }
    }
}
