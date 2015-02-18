package util;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

public class OAuth_Utility {
	static String AccessToken = "2335311858-MNd9tPeAMvoRr2XCfGuaTl7XQrX1G5LWso0EMam";
	static String AccessSecret = "AvG3QAHHBE5ubDteaLgLitjVWgB2clkWCARmLPa1c7r1s";
	static String ConsumerKey = "RsY7xaULKTeFN75RHMovEGaev";
	static String ConsumerSecret = "YjezQCGqgvzXfLflTxIk50P8ocREj5POOPaH4ZxUj7klioImHq";

	public OAuthConsumer getAuthenticatedConsumer() {
		OAuthConsumer consumer = new CommonsHttpOAuthConsumer(
				ConsumerKey,
				ConsumerSecret);

		consumer.setTokenWithSecret(AccessToken, AccessSecret);
		return consumer;
		
	}

}
