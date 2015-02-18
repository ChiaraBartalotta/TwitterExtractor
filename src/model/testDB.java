package model;

import persistence.HashtagDAO;
import persistence.HashtagRepository;
import persistence.TweetDAO;
import persistence.TweetRepository;
import persistence.UserMentionsDAO;
import persistence.UserMentionsRepository;
import elements.Hashtag;
import elements.Tweet;
import elements.UserMentions;

public class testDB {
	public static void main(String args[])  {
		UserMentions um= new UserMentions("845","999999999999999","teted");
		UserMentionsRepository r = new UserMentionsDAO();
		r.insert(um);
			}
}
