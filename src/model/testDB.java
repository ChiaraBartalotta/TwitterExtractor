package model;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import persistence.FollowerDAO;
import persistence.FollowerRepository;
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
		TweetRepository fr = new TweetDAO();
		ArrayList<String> f = fr.findRetweetId();
		for(int i=0;i<f.size();i++) {
			System.out.println("id="+f.get(i)+";");
			
		}
		System.out.println(f.size());
		
		}
}
