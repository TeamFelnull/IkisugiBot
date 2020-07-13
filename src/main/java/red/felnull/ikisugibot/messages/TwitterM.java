package red.felnull.ikisugibot.messages;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterM {
    public static Twitter twitter;

    public static void init() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("66nxPJWzhsLFESjbeKk9Z6PVD")
                .setOAuthConsumerSecret("XMUxV20IlwNrVjD84IlpJaSq2x473LbEintSpjwcywE2XMu943")
                .setOAuthAccessToken("1274208840402124800-dqVKgEssusGCy4piVHdUmZ9vSXrkPB")
                .setOAuthAccessTokenSecret("B4r0EwUqKvrUH368eHHqzlRTDzSn1X77KBHywhbfOx5S2");
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }
}
