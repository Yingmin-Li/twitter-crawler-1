/* We use this class to get the working NOT annotated tweets
 * using the files that have ONLY the user ID and the tweet ID
 */
package twittercrawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class NotAnnotatedCrawler {
    
    final String CONSUMER_KEY = "5fPDqW5R9GXo9bWH3C5w";
    final String CONSUMER_KEY_SECRET = "yXljitRmLP3L6TIhOOVw73x48CzGqXvxDuLWXiZEOs4";
    final String ACCESS_TOKEN = "974982476-zxZ81ypNUxqo00m1tN26Py8phw153WlVrkm4wvcR";
    final String ACCESS_TOKEN_SECRET = "WNXNzgPzuDjaqojPYnPBhf01BClNnxDB8nmn8l33U";
    Status status;
    
    public void run() throws FileNotFoundException, IOException { 
        Twitter twitter = new TwitterFactory().getInstance();;
        twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_KEY_SECRET);
        AccessToken accessToken = new AccessToken(ACCESS_TOKEN, ACCESS_TOKEN_SECRET);
        twitter.setOAuthAccessToken(accessToken);
        
        FileInputStream fInstream = new FileInputStream("selectionTweets.txt");
        DataInputStream in = new DataInputStream(fInstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        
        FileWriter fOutstream = new FileWriter("notAnnotatedOut.txt");
        BufferedWriter out = new BufferedWriter(fOutstream);
        
        String strLine;
        int i = 0;
        while ((strLine = br.readLine()) != null) {
            i++;
            int index = strLine.indexOf("\t");
            String userId = strLine.substring(0, index);
            String tweetId = strLine.substring(index + "\t".length());
            try {
                status = twitter.showStatus(Long.parseLong(tweetId));
                String result = userId + "\t" + tweetId +"\t"+ status.getText() +  "\n";
                out.write(result);
            } catch (TwitterException ex) {
                System.out.println("Twitter error at " + i);
                ex.printStackTrace();
            }
            
            // due to rate limit of twitter, we stop at 180 messages and continue
            // after 15 minutes
            if (i == 180)
                break;
        }
        out.close();
        in.close();
    }
}