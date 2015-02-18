package model;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import elements.User;

public class GetUsers {
	static String AccessToken = "2335311858-MNd9tPeAMvoRr2XCfGuaTl7XQrX1G5LWso0EMam";
	static String AccessSecret = "AvG3QAHHBE5ubDteaLgLitjVWgB2clkWCARmLPa1c7r1s";
	static String ConsumerKey = "RsY7xaULKTeFN75RHMovEGaev";
	static String ConsumerSecret = "YjezQCGqgvzXfLflTxIk50P8ocREj5POOPaH4ZxUj7klioImHq";

	public ArrayList<String> getFollower(String id) {

		OAuthConsumer consumer = new CommonsHttpOAuthConsumer(ConsumerKey, ConsumerSecret);
		consumer.setTokenWithSecret(AccessToken, AccessSecret);
		HttpClient httpClient = new DefaultHttpClient();
		ArrayList<String> follower = new ArrayList<String>();


		try {
			
			HttpGet httpGetRequest = new HttpGet("https://api.twitter.com/1.1/follower/ids.json?user_id="+id);
			consumer.sign(httpGetRequest);
			HttpResponse httpResponse = httpClient.execute(httpGetRequest);

			System.out.println("----------------------------------------");
			System.out.println(httpResponse.getStatusLine());
			System.out.println("----------------------------------------");

			HttpEntity entity = httpResponse.getEntity();

			byte[] buffer = new byte[1024];
			if (entity != null) {
				InputStream inputStream = entity.getContent();
				try {
					int bytesRead = 0;
					BufferedInputStream bis = new BufferedInputStream(inputStream);
					while ((bytesRead = bis.read(buffer)) != -1) {
						String chunk = new String(buffer, 0, bytesRead);
						System.out.println(chunk);
						JSONObject obj = new JSONObject(chunk);
						JSONArray arr = obj.getJSONArray("ids");
						

						for (int i = 0; i < arr.length(); i++)
						{
							follower.add(arr.getString(i));
							System.out.println(arr.getString(i)+"   ");

						}

					}
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					try { inputStream.close(); } catch (Exception ignore) {}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return follower;
	}
	
	
	
	
	
	public ArrayList<String> getFollowing(String id) {

		OAuthConsumer consumer = new CommonsHttpOAuthConsumer(ConsumerKey, ConsumerSecret);
		consumer.setTokenWithSecret(AccessToken, AccessSecret);
		HttpClient httpClient = new DefaultHttpClient();
		ArrayList<String> following = new ArrayList<String>();


		try {
			
			HttpGet httpGetRequest = new HttpGet("https://api.twitter.com/1.1/friends/ids.json?user_id="+id);
			consumer.sign(httpGetRequest);
			HttpResponse httpResponse = httpClient.execute(httpGetRequest);

			System.out.println("----------------------------------------");
			System.out.println(httpResponse.getStatusLine());
			System.out.println("----------------------------------------");

			HttpEntity entity = httpResponse.getEntity();

			byte[] buffer = new byte[1024];
			if (entity != null) {
				InputStream inputStream = entity.getContent();
				try {
					int bytesRead = 0;
					BufferedInputStream bis = new BufferedInputStream(inputStream);
					while ((bytesRead = bis.read(buffer)) != -1) {
						String chunk = new String(buffer, 0, bytesRead);
						System.out.println(chunk);
						JSONObject obj = new JSONObject(chunk);
						JSONArray arr = obj.getJSONArray("ids");
						

						for (int i = 0; i < arr.length(); i++)
						{
							following.add(arr.getString(i));
							System.out.println(arr.getString(i)+"   ");

						}

					}
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					try { inputStream.close(); } catch (Exception ignore) {}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return following;
	}
	
	
	
}

