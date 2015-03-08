package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.*;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;

import persistence.CliqueDAO;
import util.JSON2Object;

public class CliqueMaker {
	
	
	/*
	public void makeCliques(JSONObject map) {
		HashMap<String, HashSet<String>> result; 
		JSON2Object json2Object = new JSON2Object();
		result = (HashMap<String, HashSet<String>>) json2Object.deserializeObject(map.toJSONString(), HashMap.class);
		List<String> taboo = findNodeGreater(result, 4);
	}
	
	public List<String> findNodeGreater(HashMap<String, HashSet<String>> map, int m) {
		List<String> taboo = new ArrayList<String>();
		for (String el : map.keySet()) {
			if (map.get(el).size()>=m)
				taboo.add(el);
		}
		return taboo;
	}*/
	public Collection<Set<Node>> makeCliques(UndirectGraph gg) {
		//BronKerboschCliqueFinder nn = new BronKerboschCliqueFinder();
		HashMap<String, HashSet<String>> result; 
		JSON2Object json2Object = new JSON2Object();
		//result = (HashMap<String, HashSet<String>>) json2Object.deserializeObject(map.toJSONString(), HashMap.class);
		BronKerboschCliqueFinder<Node, UndirectEdge> fgh = new BronKerboschCliqueFinder<Node, UndirectEdge>(gg);
		Collection<Set<Node>> nodes = fgh.getAllMaximalCliques();
		Collection<Set<Node>> nodes2 =  fgh.getBiggestMaximalCliques();
		return nodes2;
		
	}
}
