package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;

import persistence.CliqueDAO;
import util.JSON2Object;

public class CliqueMaker {
	
	
	/*public void makeClique(String idNodeGreater, String nodes) {
		CliqueDAO cliqueDAO = new CliqueDAO();
		
		
	}*/
	public void makeCliques(JSONObject map) {
		HashMap<String, HashSet<String>> result; 
		JSON2Object json2Object = new JSON2Object();
		result = (HashMap<String, HashSet<String>>) json2Object.deserializeObject(map.toJSONString(), HashMap.class);
	}
}
