package main;

import java.io.InputStream;
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
import elements.Trends;
import elements.Tweet;
import elements.UserMentions;

public class GET_PopularHashtag {
	public static void main (String[] args) {
		ArrayList<String> id = new ArrayList<String>();
		id.add("23424853"); //italia
		//id.add("23424977"); //usa
		//id.add("23424819"); //france
		//id.add("23424950"); 
		getPopularHashtagByWOEID(id);
	}
	public static void getPopularHashtagByWOEID(ArrayList<String> woeid){
		//HashMap<String,ArrayList<Tweet>> user2tweets = new HashMap<String,ArrayList<Tweet>>();
		HttpClient httpClient = new DefaultHttpClient();
		OAuth_Utility auth_utility = new OAuth_Utility();
		OAuthConsumer consumer = auth_utility.getAuthenticatedConsumer();
		for(String id: woeid) {
			ArrayList<Trends> trends = new ArrayList<Trends>();

			try {

				String result ="";
				HttpGet httpGetRequest = new HttpGet("https://api.twitter.com/1.1/trends/place.json?id="+id);
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

						JSONArray popular_trends = jobj.getJSONArray("trends");
						for(int p=0; p<popular_trends.length();p++) {
							Trends t = new Trends();
							t.setName(popular_trends.getJSONObject(p).getString("name"));
							t.setUrl(popular_trends.getJSONObject(p).getString("url"));
							t.setQuery(popular_trends.getJSONObject(p).getString("query"));
							t.setSearched_at(jobj.getString("as_of"));
							t.setWoeid(jobj.getJSONArray("locations").getJSONObject(0).getDouble("woeid"));
							t.setWoeid_name(jobj.getJSONArray("locations").getJSONObject(0).getString("name"));
							TrendsRepository tr = new TrendsDAO();
							tr.insert(t);

							/*INSERIMENTO TWEET RELATIVI AL TREND*/
							ArrayList<Tweet> tweets = new ArrayList<Tweet>();

							try {

								String res ="";
								HttpGet httpGetRequestTweets = new HttpGet("https://api.twitter.com/1.1/search/tweets.json?q="+popular_trends.getJSONObject(p).getString("query")+"&include_entities=true&count=100");
								consumer.sign(httpGetRequestTweets);
								HttpResponse httpResponseTweets = httpClient.execute(httpGetRequestTweets);
								HttpEntity entityTweets = httpResponseTweets.getEntity();

								if (entity != null) {

									InputStream instreamTweets = entityTweets.getContent();
									HttpResponseManager rmTweets = new HttpResponseManager();
									res = rmTweets.convertStreamToString(instreamTweets);

									//System.out.println("RESPONSE: " + res);
									JSONObject jot = new JSONObject(res);
									JSONArray objTweets = (JSONArray) jot.getJSONArray("statuses");
									System.out.println(objTweets);
									for(int n=0; n<objTweets.length();n++) {
										JSONObject jobjTweets = objTweets.getJSONObject(n);
										ArrayList<Hashtag> hashtag = new ArrayList<Hashtag>();
										ArrayList<UserMentions> user_mention = new ArrayList<UserMentions>();

										String tweetId = jobjTweets.getString("id_str");
										String userId = jobjTweets.getJSONObject("user").getString("id_str");
										String created_at = jobjTweets.getString("created_at");
										String text = jobjTweets.getString("text");
										String retweeted_id = "0";
										if(jobjTweets.has("retweeted_status")) {
											retweeted_id = jobjTweets.getJSONObject("retweeted_status").getString("id_str");
										}
										int retweet_count = jobjTweets.getInt("retweet_count");
										int favourite_count = jobjTweets.getInt("favorite_count");
										Tweet tweet = new Tweet(tweetId, userId, created_at, text, retweet_count, favourite_count, retweeted_id);
										TweetRepository trep = new TweetDAO();
										trep.insert(tweet);
										JSONArray ht = jobjTweets.getJSONObject("entities").getJSONArray("hashtags");
										for(int c=0;c<ht.length();c++) {
											Hashtag h = new Hashtag(tweetId,ht.getJSONObject(c).getString("text"),popular_trends.getJSONObject(p).getString("query"));
											hashtag.add(h);
											HashtagRepository hrep = new HashtagDAO();
											hrep.insert(h);
										}
										tweet.setHashtag(hashtag);
										JSONArray um = jobjTweets.getJSONObject("entities").getJSONArray("user_mentions");
										for(int u=0; u<um.length();u++) {
											UserMentions u_m = new UserMentions();
											u_m.setTweet_id(tweetId);
											u_m.setUser_id(um.getJSONObject(u).getString("id_str"));
											u_m.setScreen_name(um.getJSONObject(u).getString("screen_name"));
											u_m.setTrend(popular_trends.getJSONObject(p).getString("query"));
											user_mention.add(u_m);
											UserMentionsRepository umrep = new UserMentionsDAO();
											umrep.insert(u_m);
										}
										tweet.setUser_mentions(user_mention);
										tweets.add(tweet);

										System.out.println("done");
									}

								}
							}catch (Exception e) {
								e.printStackTrace();
							} 


						}

					}
				}
			}catch (Exception e) {
					e.printStackTrace();
				} 

			}
			//return user2tweets;
		}
	}

