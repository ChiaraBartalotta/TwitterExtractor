package main;



import java.io.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import util.HttpResponseManager;
import util.OAuth_Utility;

/**
 * This class is the same as the ApacheHttpRestClient2 class, but with
 * fewer try/catch clauses, and fewer comments.
 */
public class GET_SampleUsers {
	public final static void main(String[] args) {

		HttpClient httpClient = new DefaultHttpClient();
		OAuth_Utility auth_utility = new OAuth_Utility();
		OAuthConsumer consumer = auth_utility.getAuthenticatedConsumer();
		try {
			HttpGet httpGetRequest = new HttpGet("https://api.twitter.com/1.1/users/search.json?q=chiara");
			consumer.sign(httpGetRequest);
			HttpResponse httpResponse = httpClient.execute(httpGetRequest);

			System.out.println("----------------------------------------");
			System.out.println(httpResponse.getStatusLine());
			System.out.println("----------------------------------------");

			HttpEntity entity = httpResponse.getEntity();
			String result ="";
			if (entity != null) {
				InputStream instream = entity.getContent();
				HttpResponseManager rm = new HttpResponseManager();
				result = rm.convertStreamToString(instream);
				//System.out.println("RESPONSE: " + result);
				JSONArray obj = new JSONArray(result);
				//System.out.println(obj);
				for(int i=0; i<obj.length();i++) {
					JSONObject jobj = obj.getJSONObject(i);
					String userId = jobj.getString("id_str");
					String name = jobj.getString("screen_name");
					String location = jobj.getString("location");
					String created_at = jobj.getString("created_at");
					System.out.println("INSERT INTO users VALUES ("+userId+",'"+name+"','"+location+"','"+created_at+"');\n");
				}
				instream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}
}