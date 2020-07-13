package red.felnull.ikisugibot.command;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import red.felnull.ikisugibot.OptionConfig;
import red.felnull.ikisugibot.messages.TwitterM;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;

public class TwitterCommand extends Command {
    @Override
    public void start(MessageCreateEvent e, String[] attackd) {
        Message message = e.getMessage();
        MessageChannel channel = message.getChannel().block();
        if (attackd[0] == null) {
            channel.createMessage("使用例 -> " + OptionConfig.COMMAND + " " + "twitter [検索]").block();
            return;
        }

        String search = attackd[0];
        Twitter twitter = TwitterM.twitter;
        Query query = new Query();
        query.setQuery(search);
        try {
            QueryResult result = twitter.search(query);
            String text = "検索結果";
            text += "ヒット数 : " + result.getTweets().size();
            for (Status tweet : result.getTweets()) {
                text += tweet.getText();
                text += tweet.getUser();
                text += tweet.getCreatedAt();
            }
            channel.createMessage(text);
        } catch (Exception ex) {
            String st = "エラーが発生しました:";
            st += ex.getLocalizedMessage();
            channel.createMessage(st).block();
        }


    }

    @Override
    public String getExplanatory() {
        return "Twitterテスト";
    }
}
