package main;

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

import persistence.FollowerDAO;
import persistence.FollowerRepository;
import util.HttpResponseManager;
import util.OAuth_Utility;
import elements.Follower;

public class GET_userFollowing {
	
	public final static void main(String[] args) {
		ArrayList<String> ids = new ArrayList<String>();
//		ids.add("877730558"); //fatto
//		ids.add("368947547"); //fatto
//		ids.add("14068446");
//		ids.add("23240695");
//		ids.add("40090727");
//		ids.add("63881465");
//		ids.add("66792123");
//		ids.add("76736871");
//		ids.add("156576211");
//		ids.add("191721901");
//		ids.add("192854350");
//		ids.add("221332544");
//		ids.add("236857407");
//		ids.add("277460126");
//		ids.add("283070184");
//		ids.add("336975968");
//		ids.add("408384407");
//		ids.add("429847277");
		ids.add("529338144");
		
		//ids.add("200925880");
		getFollowing(ids);
	}
	
	public static void getFollowing(ArrayList<String> ids){
		HttpClient httpClient = new DefaultHttpClient();
		OAuth_Utility auth_utility = new OAuth_Utility();
		OAuthConsumer consumer = auth_utility.getAuthenticatedConsumer();
		for(String id: ids) {
			ArrayList<String> follower = new ArrayList<String>();

			try {
				long cursor = -1;
				String result ="";
				while(cursor!=0) {
					HttpGet httpGetRequest = new HttpGet("https://api.twitter.com/1.1/friends/ids.json?user_id="+id+"&cursor="+cursor);
					consumer.sign(httpGetRequest);
					HttpResponse httpResponse = httpClient.execute(httpGetRequest);
					HttpEntity entity = httpResponse.getEntity();

					if (entity != null) {

						InputStream instream = entity.getContent();
						HttpResponseManager rm = new HttpResponseManager();
						result = rm.convertStreamToString(instream);
						
						//System.out.println("RESPONSE: " + result);

						JSONObject obj = new JSONObject(result);
						JSONArray a = (JSONArray) obj.get("ids");
						
						for(int i=0;i<a.length();i++){
							//follower.add(a.get(i).toString());
							FollowerRepository fr = new FollowerDAO();
							Follower f = new Follower(a.get(i).toString(),id);
							fr.insert(f);
						}
						cursor=obj.getLong("next_cursor");

					}
				} 
				//System.out.println(cont);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			//following2follower.put(id, follower);
		}
		System.out.println("done");
		//return following2follower;
	}
}
