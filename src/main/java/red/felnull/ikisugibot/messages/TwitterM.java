package red.felnull.ikisugibot.messages;

import red.felnull.ikisugibot.OptionConfig;
import red.felnull.ikisugibot.util.DiscordUtil;
import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterM {
    public static Twitter twitter;

    public static void init() {
        Configuration c = new ConfigurationBuilder().setDebugEnabled(true)
                .setOAuthConsumerKey("66nxPJWzhsLFESjbeKk9Z6PVD")
                .setOAuthConsumerSecret("XMUxV20IlwNrVjD84IlpJaSq2x473LbEintSpjwcywE2XMu943")
                .setOAuthAccessToken("1274208840402124800-dqVKgEssusGCy4piVHdUmZ9vSXrkPB")
                .setOAuthAccessTokenSecret("B4r0EwUqKvrUH368eHHqzlRTDzSn1X77KBHywhbfOx5S2")
                .build();

        TwitterFactory tf = new TwitterFactory(c);
        twitter = tf.getInstance();

        StatusListener listener = new StatusListener() {

            @Override
            public void onException(Exception ex) {

            }

            @Override
            public void onStatus(Status status) {
                String text = status.getUser().getName() + "がつぶやきました\n";
                if (status.isRetweet()) {
                    text = status.getUser().getName() + "がリツイートしました\n";
                }
                text += status.getText();
                DiscordUtil.sendMessage(OptionConfig.TIME_SIGNAL_CHANNELID, text);
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {

            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {

            }

            @Override
            public void onStallWarning(StallWarning warning) {

            }
        };

        TwitterStream twStream = new TwitterStreamFactory(c).getInstance();
        twStream.addListener(listener);

        FilterQuery filter = new FilterQuery();
        filter.follow(1274208840402124800l);
        //    filter.track("野獣の日");
        twStream.filter(filter);
    }
}
