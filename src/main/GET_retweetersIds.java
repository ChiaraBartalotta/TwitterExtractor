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

import persistence.FollowerDAO;
import persistence.FollowerRepository;
import persistence.RetweeterDAO;
import persistence.RetweeterRepository;
import persistence.TweetDAO;
import persistence.TweetRepository;
import util.HttpResponseManager;
import util.OAuth_Utility;
import elements.Follower;
import elements.Retweeter;

public class GET_retweetersIds {
	public final static void main(String[] args) {
		TweetRepository tr = new TweetDAO();
		ArrayList<String> ids = tr.findTweetId();
		for(int count=0;count<ids.size();count+=14) {
			ArrayList<String> subIds = new ArrayList<String>(ids.subList(count, count+=14));
			getRetweetersIds(subIds);
			try {
				TimeUnit.MINUTES.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	public static void getRetweetersIds(ArrayList<String> ids){

		HttpClient httpClient = new DefaultHttpClient();
		OAuth_Utility auth_utility = new OAuth_Utility();
		OAuthConsumer consumer = auth_utility.getAuthenticatedConsumer();
		for(String id: ids) {
			try {
				long cursor = -1;
				String result ="";
				while(cursor!=0) {
					HttpGet httpGetRequest = new HttpGet("https://api.twitter.com/1.1/statuses/retweeters/ids.json?id="+id+"&cursor="+cursor);
					consumer.sign(httpGetRequest);
					HttpResponse httpResponse = httpClient.execute(httpGetRequest);
					HttpEntity entity = httpResponse.getEntity();

					if (entity != null) {

						InputStream instream = entity.getContent();
						HttpResponseManager rm = new HttpResponseManager();
						result = rm.convertStreamToString(instream);
						
						System.out.println("RESPONSE: " + result);

						JSONObject obj = new JSONObject(result);
						JSONArray a = (JSONArray) obj.get("ids");
						
						for(int i=0;i<a.length();i++){
							RetweeterRepository rp = new RetweeterDAO();
							Retweeter f = new Retweeter(Long.parseLong(id),Long.parseLong(a.get(i).toString()));
							rp.insert(f);
						}
						cursor=obj.getLong("next_cursor");

					}
				} 
				//System.out.println(cont);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		System.out.println("done");
		//return following2follower;
	}
}
