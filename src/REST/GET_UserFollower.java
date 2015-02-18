package REST;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import oauth.signpost.OAuthConsumer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import util.HttpResponseManager;
import util.OAuth_Utility;

public class GET_UserFollower {
	public final static void main(String[] args) {
		ArrayList<String> ids = new ArrayList<String>();
		//ids.add("877730558"); fatto
		//ids.add("368947547"); fatto
		//ids.add("14068446");
		//ids.add("23240695");
		//ids.add("40090727");
		//ids.add("63881465");
		//ids.add("66792123");
		//ids.add("76736871");
		//ids.add("156576211");
		//ids.add("191721901");
		//ids.add("192854350");
		//ids.add("221332544");
		//ids.add("236857407");
		//ids.add("277460126");
		//ids.add("283070184");
		//ids.add("336975968");
		//ids.add("408384407");
		//ids.add("429847277");
		//ids.add("529338144");
		HashMap<String,ArrayList<String>> follower = getFollower(ids);

		try {
			map2DB(follower);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static HashMap<String,ArrayList<String>> getFollower(ArrayList<String> ids){

		HashMap<String,ArrayList<String>> following2follower = new HashMap<String,ArrayList<String>>();
		HttpClient httpClient = new DefaultHttpClient();
		OAuth_Utility auth_utility = new OAuth_Utility();
		OAuthConsumer consumer = auth_utility.getAuthenticatedConsumer();
		for(String id: ids) {
			ArrayList<String> follower = new ArrayList<String>();

			try {
				long cursor = -1;
				String result ="";
				int cont=0;
				while(cursor!=0) {
					HttpGet httpGetRequest = new HttpGet("https://api.twitter.com/1.1/followers/ids.json?user_id="+id+"&cursor="+cursor);
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
							follower.add(a.get(i).toString());
							cont++;
							//System.out.println(a.get(i).toString());
						}
						cursor=obj.getLong("next_cursor");

					}
				} 
				//System.out.println(cont);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			following2follower.put(id, follower);
		}
		System.out.println(following2follower.toString());
		return following2follower;
	}
	public static void map2DB(HashMap<String,ArrayList<String>> mp) throws IOException {
		Iterator it = mp.entrySet().iterator();
		int cont=0;
		File file = new File("./FOLLOWER.txt");
		 
		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry)it.next();
			Iterator itValue = ((ArrayList<String>) pairs.getValue()).iterator();
			while(itValue.hasNext()){
				cont++;
				bw.write("INSERT INTO follower VALUES ("+pairs.getKey() + "," + itValue.next()+");");
				//System.out.println("INSERT INTO follower VALUES ("+pairs.getKey() + "," + itValue.next()+");");
			}

			it.remove(); // avoids a ConcurrentModificationException
		}
		bw.close();
		System.out.println("done");
	}
}

