package persistence;

import java.util.ArrayList;

import elements.Follower;

public interface FollowerRepository {
	boolean insert(Follower f);
	ArrayList<String> findByFollowing(String followingId);
}
