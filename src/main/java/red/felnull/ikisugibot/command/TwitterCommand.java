package red.felnull.ikisugibot.command;

import red.felnull.ikisugibot.OptionConfig;
import red.felnull.ikisugibot.messages.TwitterM;
import red.felnull.ikisugibot.util.DiscordUtil;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;

public class TwitterCommand extends Command {
    @Override
    public void start(long chanelID, String[] attackd) {
        if (attackd[0] == null) {
            DiscordUtil.sendMessage(chanelID, "使用例 -> " + OptionConfig.COMMAND + " " + "twitter [検索]");
            return;
        }

        String search = attackd[0];
        Twitter twitter = TwitterM.twitter;
        Query query = new Query();
        query.setQuery(search);
        try {
            QueryResult result = twitter.search(query);
            String text = "検索結果";
            text += "ヒット数 : " + result.getTweets().size() + "\n";
            for (Status tweet : result.getTweets()) {
                text += tweet.getText();
                //    text += tweet.getUser();
                //     text += tweet.getCreatedAt();
            }
            DiscordUtil.sendMessage(chanelID, text);
        } catch (Exception ex) {
            String st = "エラーが発生しました:";
            st += ex.getLocalizedMessage();
            DiscordUtil.sendMessage(chanelID, st);
        }


    }

    @Override
    public String getExplanatory() {
        return "Twitterテスト";
    }
}
