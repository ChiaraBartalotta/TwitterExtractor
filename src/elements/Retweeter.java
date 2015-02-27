package elements;

public class Retweeter {
	Long tweet_id;
	Long user_id; 
	
	public Retweeter(Long id, Long user_id) {
		super();
		this.tweet_id = id;
		this.user_id = user_id;
	}
	public Long getTweet_id() {
		return tweet_id;
	}
	public void setTweet_id(Long tweet_id) {
		this.tweet_id = tweet_id;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

}
