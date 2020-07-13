package red.felnull.ikisugibot.command;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import red.felnull.ikisugibot.OptionConfig;
import red.felnull.ikisugibot.util.DiscordUtil;
import red.felnull.ikisugibot.util.ImageHelper;
import red.felnull.ikisugibot.util.StringHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.HttpURLConnection;
import java.net.URL;

public class ScanCommand extends Command {
    @Override
    public void start(long chanelID, String[] attackd) {
        String lang = "jpn";
        boolean sirokuro = true;
        if (attackd[0] == null) {
            DiscordUtil.sendMessage(chanelID, "使用例 -> " + OptionConfig.COMMAND + " " + "scan [URL] [jpn/eng] [白黒化するか]");
            return;
        } else if (attackd.length > 1) {
            if (!attackd[1].equals("jpn") && !attackd[1].equals("eng")) {
                DiscordUtil.sendMessage(chanelID, "使用例 -> " + OptionConfig.COMMAND + " " + "scan [URL] [jpn/eng] [白黒化するか]");
                return;
            }
            lang = attackd[1];
        } else if (attackd.length > 2) {
            sirokuro = StringHelper.forBoolean(attackd[2]);
        }
        try {
            String urls = attackd[0];
            URL url = new URL(urls);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:37.0) Gecko/20100101 Firefox/37.0");
            BufferedImage img = sirokuro ? ImageHelper.covWhiteAndBlack(ImageIO.read(connection.getInputStream())) : ImageIO.read(connection.getInputStream());
            ITesseract tesseract = new Tesseract();
            tesseract.setDatapath(OptionConfig.DATA_PATH.toString());
            tesseract.setLanguage(lang);
            String str = tesseract.doOCR(img);
            String text = "読み込み結果です\n";
            text += str;
            DiscordUtil.sendMessage(chanelID, text);
        } catch (Exception ex) {
            String st = "エラーが発生しました:";
            st += ex.getLocalizedMessage();
            DiscordUtil.sendMessage(chanelID, st);
        }

    }

    @Override
    public String getExplanatory() {
        return "画像を読み取って文字を抽出します";
    }
}
