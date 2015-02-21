package persistence;

import java.util.ArrayList;

import elements.Follower;

public interface FollowerRepository {
	boolean insert(Follower f);
	ArrayList<String> findFollower(String followingId);
	ArrayList<String> findFollowing(String followerId);
}
