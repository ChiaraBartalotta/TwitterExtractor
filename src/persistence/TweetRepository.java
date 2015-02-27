package persistence;

import java.util.ArrayList;

import elements.Tweet;

public interface TweetRepository {
	boolean insert(Tweet tweet);
	ArrayList<String> findRetweetId();
	ArrayList<String> findTweetId();
}
