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
 
/**
 * This class is the same as the ApacheHttpRestClient2 class, but with
 * fewer try/catch clauses, and fewer comments.
*/
public class TestRestGET {
	 static String AccessToken = "2335311858-MNd9tPeAMvoRr2XCfGuaTl7XQrX1G5LWso0EMam";
	  static String AccessSecret = "AvG3QAHHBE5ubDteaLgLitjVWgB2clkWCARmLPa1c7r1s";
	  static String ConsumerKey = "RsY7xaULKTeFN75RHMovEGaev";
	  static String ConsumerSecret = "YjezQCGqgvzXfLflTxIk50P8ocREj5POOPaH4ZxUj7klioImHq";

  public final static void main(String[] args) {
	  
	  OAuthConsumer consumer = new CommonsHttpOAuthConsumer(
              ConsumerKey,
              ConsumerSecret);

      consumer.setTokenWithSecret(AccessToken, AccessSecret);
    HttpClient httpClient = new DefaultHttpClient();
    try {
      HttpGet httpGetRequest = new HttpGet("https://api.twitter.com/1.1/search/tweets.json?q=Rachida&include_entities=true");
      consumer.sign(httpGetRequest);
      HttpResponse httpResponse = httpClient.execute(httpGetRequest);
 
      System.out.println("----------------------------------------");
      //System.out.println(httpResponse.getStatusLine());
      System.out.println("----------------------------------------");
  	JSONObject jot = new JSONObject(httpResponse.getStatusLine());
	//JSONObject objTweets =  jot.getJSONObject(2).getJSONObject("metadata");
	System.out.println(jot);
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
          }
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          try { inputStream.close(); } catch (Exception ignore) {}
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      httpClient.getConnectionManager().shutdown();
    }
  }
}

