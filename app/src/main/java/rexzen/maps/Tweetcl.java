package rexzen.maps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;

import java.util.Calendar;

import io.fabric.sdk.android.Fabric;

/**
 * Created by harishananth on 11/12/16.
 */

public class Tweetcl extends AppCompatActivity{

    Calendar c = Calendar.getInstance();
    int milliseconds=c.get(Calendar.MILLISECOND);
    int second = c.get(Calendar.SECOND);
    int minute = c.get(Calendar.MINUTE);
    //12 hour format
    int hour = c.get(Calendar.HOUR);
    //24 hour format
    int hourofday = c.get(Calendar.HOUR_OF_DAY);

    String s="Alert to 108 from TcpRide 'Vehicle registration number is! "+IncomingSms.regno+" "+"lat is "+IncomingSms.lat+" lon is "+IncomingSms.lon+" "+IncomingSms.details+hourofday+"/"+minute+"/"+second;

    private static final String TWITTER_KEY = "jHgcf1PeOc9URC8cU2EKRWEKE";
    private static final String TWITTER_SECRET = "8VqgAYBRvoFog4u29PxQoW22JBrttJBhr5n9E2Ytst71VzNnBQ";
    TwitterSession twitterSession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
       // Fabric.with(this, new Twitter(authConfig));
        twitterSession=Twitter.getSessionManager().getActiveSession();
        if(twitterSession==null)
        {
            Toast.makeText(Tweetcl.this,"cant sign in",Toast.LENGTH_LONG).show();
        }
        else
        {
            postTweet(s);
        }


    }
    private void postTweet(String text){
        StatusesService statusesService = Twitter.getApiClient().getStatusesService();
        statusesService.update(text, null, false, null, null, null, false, false, null, new Callback<Tweet>() {
            @Override
            public void success(Result<Tweet> result) {
                Toast.makeText(Tweetcl.this, "108 Will Arrive to the spot shortly", Toast.LENGTH_SHORT).show();
                Intent newint=new Intent(Tweetcl.this,optionnav.class);
               startActivity(newint);
            }

            @Override
            public void failure(TwitterException e) {
                Toast.makeText(Tweetcl.this, "Error establishing Communication", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
                Intent newint=new Intent(Tweetcl.this,optionnav.class);
                startActivity(newint);
               // Intent newint=new Intent(Tweetcl.this,optionpage.class);
                //startActivity(newint);
            }
        });
    }

}
