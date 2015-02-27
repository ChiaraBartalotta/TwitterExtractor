package elements;

import java.util.ArrayList;

public class Tweet {
	
	String id;
	String user_id;
	String created_at;
	ArrayList<Hashtag> hashtag;
	ArrayList<UserMentions> user_mentions;
	String text;
	int retweet_count;
	int favourite_count;
	String retweeted_id;
	
	public Tweet(String id, String user_id, String created_at, String text, int retweet_count,
			int favourite_count, String retweeted_id) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.created_at = created_at;
		this.text = text;
		this.retweet_count = retweet_count;
		this.favourite_count = favourite_count;
		this.retweeted_id = retweeted_id;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public ArrayList<Hashtag> getHashtag() {
		return hashtag;
	}
	public void setHashtag(ArrayList<Hashtag> hashtag) {
		this.hashtag = hashtag;
	}
	public ArrayList<UserMentions> getUser_mentions() {
		return user_mentions;
	}
	public void setUser_mentions(ArrayList<UserMentions> user_mentions) {
		this.user_mentions = user_mentions;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getRetweet_count() {
		return retweet_count;
	}
	public void setRetweet_count(int retweet_count) {
		this.retweet_count = retweet_count;
	}
	public int getFavourite_count() {
		return favourite_count;
	}
	public void setFavourite_count(int favourite_count) {
		this.favourite_count = favourite_count;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Tweet [id=" + id + ", user_id=" + user_id + ", created_at="
				+ created_at + ", hashtag=" + hashtag.toString() + ", user_mentions="
				+ user_mentions.toString() + ", text=" + text + ", retweet_count="
				+ retweet_count + ", favourite_count=" + favourite_count + "\n\n]";
	}

	public String getRetweeted_id() {
		return retweeted_id;
	}

	public void setRetweeted_id(String retweeted_id) {
		this.retweeted_id = retweeted_id;
	}
}
