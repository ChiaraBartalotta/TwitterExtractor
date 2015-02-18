package REST;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import oauth.signpost.OAuthConsumer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import elements.Hashtag;
import elements.Tweet;
import elements.UserMentions;
import util.HttpResponseManager;
import util.OAuth_Utility;
import persistence.HashtagRepository;
import persistence.TweetDAO;
import persistence.TweetRepository;
import persistence.UserMentionsDAO;
import persistence.UserMentionsRepository;
import persistence.HashtagDAO;

public class GET_UserTweets {
	public final static void main(String[] args) {
		ArrayList<String> ids = new ArrayList<String>();
		ids.add("877730558");
		getUserTweets(ids);
	}

	public static HashMap<String,ArrayList<Tweet>> getUserTweets(ArrayList<String> ids){
		HashMap<String,ArrayList<Tweet>> user2tweets = new HashMap<String,ArrayList<Tweet>>();
		HttpClient httpClient = new DefaultHttpClient();
		OAuth_Utility auth_utility = new OAuth_Utility();
		OAuthConsumer consumer = auth_utility.getAuthenticatedConsumer();
		for(String id: ids) {
			ArrayList<Tweet> tweets = new ArrayList<Tweet>();

			try {
				String result ="";
				HttpGet httpGetRequest = new HttpGet("https://api.twitter.com/1.1/statuses/user_timeline.json?user_id="+id+"&count=4000&trim_user=1");
				consumer.sign(httpGetRequest);
				HttpResponse httpResponse = httpClient.execute(httpGetRequest);
				HttpEntity entity = httpResponse.getEntity();

				if (entity != null) {

					InputStream instream = entity.getContent();
					HttpResponseManager rm = new HttpResponseManager();
					result = rm.convertStreamToString(instream);

					//System.out.println("RESPONSE: " + result);
					JSONArray obj = new JSONArray(result);
					//System.out.println(obj);
					for(int i=0; i<obj.length();i++) {
						JSONObject jobj = obj.getJSONObject(i);
						ArrayList<Hashtag> hashtag = new ArrayList<Hashtag>();
						ArrayList<UserMentions> user_mention = new ArrayList<UserMentions>();

						String tweetId = jobj.getString("id_str");
						String userId = jobj.getJSONObject("user").getString("id_str");
						String created_at = jobj.getString("created_at");
						String text = jobj.getString("text");
						int retweet_count = jobj.getInt("retweet_count");
						int favourite_count = jobj.getInt("favorite_count");
						Tweet tweet = new Tweet(tweetId, userId, created_at, text, retweet_count, favourite_count);
						TweetRepository trep = new TweetDAO();
						trep.insert(tweet);
						JSONArray ht = jobj.getJSONObject("entities").getJSONArray("hashtags");
						for(int n=0;n<ht.length();n++) {
							Hashtag h = new Hashtag(tweetId,ht.getJSONObject(n).getString("text"));
							hashtag.add(h);
							HashtagRepository hrep = new HashtagDAO();
							hrep.insert(h);
						}
						tweet.setHashtag(hashtag);
						JSONArray um = jobj.getJSONObject("entities").getJSONArray("user_mentions");
						for(int u=0; u<um.length();u++) {
							UserMentions u_m = new UserMentions();
							u_m.setTweet_id(tweetId);
							u_m.setUser_id(um.getJSONObject(u).getString("id_str"));
							u_m.setScreen_name(um.getJSONObject(u).getString("screen_name"));
							user_mention.add(u_m);
							UserMentionsRepository umrep = new UserMentionsDAO();
							umrep.insert(u_m);
						}
						tweet.setUser_mentions(user_mention);
						tweets.add(tweet);

						System.out.println("done");
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			} 
			user2tweets.put(id, tweets);
		}
		return user2tweets;
	}

}
