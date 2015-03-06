package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.json.simple.JSONObject;

import persistence.FollowerDAO;
import persistence.FollowerRepository;
import util.FileManager;
import util.FileManagerInterface;

public class RelationshipGraph {


	public JSONObject getGraph() {
		HashMap<String,HashSet<String>> relationship = new HashMap <String,HashSet<String>>();
		FollowerRepository fr = new FollowerDAO();
		ArrayList<String> follower = fr.findAllFollower();

		for(String f : follower) {
			if(!relationship.containsKey(f)) {
				relationship.put(f, new HashSet<String>());
			}
			for(String fing : fr.findFollower(f)) {
				relationship.get(f).add(fing);
				if(!relationship.containsKey(fing)) {
					relationship.put(fing, new HashSet<String>());
				}
				relationship.get(fing).add(f);
			}
		}
		JSONObject jsonRelationship = new JSONObject(relationship);
		FileManagerInterface ff = new FileManager();
		ff.createFile("./src/file/graph.txt", jsonRelationship.toJSONString());
		return jsonRelationship;
	}

}
