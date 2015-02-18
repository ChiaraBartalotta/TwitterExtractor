package elements;

public class Hashtag {
	public Hashtag(String tweet_id, String text) {
		super();
		this.tweet_id = tweet_id;
		this.text = text;
	}
	String tweet_id;
	String text;
	public String getTweet_id() {
		return tweet_id;
	}
	public void setTweet_id(String tweet_id) {
		this.tweet_id = tweet_id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return "Hashtag [tweet_id=" + tweet_id + ", text=" + text + "]";
	}

}
