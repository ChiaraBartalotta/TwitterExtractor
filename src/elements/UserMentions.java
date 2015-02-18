package elements;

public class UserMentions {
	public UserMentions(){
		
	}
	public UserMentions(String tweet_id, String user_id, String screen_name) {
		super();
		this.tweet_id = tweet_id;
		this.user_id = user_id;
		this.screen_name = screen_name;
	}
	String tweet_id;
	String user_id;
	String screen_name;
	public String getTweet_id() {
		return tweet_id;
	}
	public void setTweet_id(String tweet_id) {
		this.tweet_id = tweet_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getScreen_name() {
		return screen_name;
	}
	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}
	@Override
	public String toString() {
		return "UserMentions [tweet_id=" + tweet_id + ", user_id=" + user_id
				+ ", screen_name=" + screen_name + "]";
	}
	

}
