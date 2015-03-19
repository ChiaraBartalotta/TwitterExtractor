package main;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;

import oauth.signpost.OAuthConsumer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import persistence.HashtagDAO;
import persistence.HashtagRepository;
import persistence.TrendsDAO;
import persistence.TrendsRepository;
import persistence.TweetDAO;
import persistence.TweetRepository;
import persistence.UserMentionsDAO;
import persistence.UserMentionsRepository;
import util.HttpResponseManager;
import util.OAuth_Utility;
import elements.Hashtag;
import elements.Tweet;
import elements.UserMentions;

public class GET_TrendsTweets {
		public final static void main(String[] args) {
			TrendsRepository tr = new TrendsDAO();
			Timestamp t = new Timestamp(2015,03,11,10,14,58,69);// "2015-03-11 10:14:58.69")
			ArrayList<String> urls = tr.getUrls_byTimestamp(t);
			getTrendsTweets(urls);
		}
	public static void getTrendsTweets(ArrayList<String> urls){
		HttpClient httpClient = new DefaultHttpClient();
		OAuth_Utility auth_utility = new OAuth_Utility();
		OAuthConsumer consumer = auth_utility.getAuthenticatedConsumer();
		for(String url: urls) {
			ArrayList<Tweet> tweets = new ArrayList<Tweet>();
			
			try {
				
				String result ="";
				HttpGet httpGetRequest = new HttpGet(url);
				consumer.sign(httpGetRequest);
				HttpResponse httpResponse = httpClient.execute(httpGetRequest);
				HttpEntity entity = httpResponse.getEntity();

				if (entity != null) {

					InputStream instream = entity.getContent();
					HttpResponseManager rm = new HttpResponseManager();
					result = rm.convertStreamToString(instream);

					//System.out.println("RESPONSE: " + result);
					JSONArray obj = new JSONArray(result);
					System.out.println(obj);
					for(int i=0; i<obj.length();i++) {
						JSONObject jobj = obj.getJSONObject(i);
						ArrayList<Hashtag> hashtag = new ArrayList<Hashtag>();
						ArrayList<UserMentions> user_mention = new ArrayList<UserMentions>();

						String tweetId = jobj.getString("id_str");
						String userId = jobj.getJSONObject("user").getString("id_str");
						String created_at = jobj.getString("created_at");
						String text = jobj.getString("text");
						String retweeted_id = "0";
						if(jobj.has("retweeted_status")) {
							retweeted_id = jobj.getJSONObject("retweeted_status").getString("id_str");
						}
						int retweet_count = jobj.getInt("retweet_count");
						int favourite_count = jobj.getInt("favorite_count");
						Tweet tweet = new Tweet(tweetId, userId, created_at, text, retweet_count, favourite_count, retweeted_id);
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
			
		}
		//return user2tweets;
	}

}

