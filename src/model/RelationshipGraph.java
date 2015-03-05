package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import persistence.FollowerDAO;
import persistence.FollowerRepository;

public class RelationshipGraph {
	
	
	public HashMap<String,HashSet<String>> getGraph() {
		HashMap<String,HashSet<String>> relationship = new HashMap <String,HashSet<String>>();
		FollowerRepository fr = new FollowerDAO();
		ArrayList<String> follower = fr.findAllFollower();
		ArrayList<String> following = fr.findAllFollowing();
		for(String f : follower) {
			if(!relationship.containsKey(f)) {
				relationship.put(f, new HashSet<String>());
				
			}
			for(String fing : fr.findFollower(f)) 
				relationship.get(f).add(fing);
		}
		for(String f : following) {
			if(!relationship.containsKey(f)) {
				relationship.put(f, new HashSet<String>());
			}
			for(String fing : fr.findFollower(f)) 
				relationship.get(f).add(fing);
		}
		return relationship;
	}
	
}
