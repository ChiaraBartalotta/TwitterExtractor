package main;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

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
import persistence.FollowerDAO;
import persistence.FollowerRepository;
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
		ids.add("368947547");
		ids.add("14068446");
		ids.add("23240695");
		ids.add("40090727");
		ids.add("63881465");
		ids.add("66792123");
		ids.add("76736871");
		ids.add("156576211");
		ids.add("191721901");
		ids.add("192854350");
		ids.add("221332544");
		ids.add("236857407");
		ids.add("277460126");
		ids.add("283070184");
		ids.add("336975968");
		ids.add("408384407");
		ids.add("429847277");
		ids.add("529338144");
		getUserTweets(ids);
//		for(String id: ids) {
//			FollowerRepository f = new FollowerDAO();
//			ArrayList<String> followers =f.findFollower(id);
//			
//			for(int count=0;count<followers.size();count+=200) {
//				ArrayList<String> subF = new ArrayList<String>(followers.subList(count, count+=200));
//				getUserTweets(subF);
//				try {
//					TimeUnit.MINUTES.sleep(15);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
	}

	public static void getUserTweets(ArrayList<String> ids){
		//HashMap<String,ArrayList<Tweet>> user2tweets = new HashMap<String,ArrayList<Tweet>>();
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
