package persistence;

import elements.Tweet;

public interface TweetRepository {
	boolean insert(Tweet tweet);
}
