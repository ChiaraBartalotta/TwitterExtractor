package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;

import persistence.CliqueDAO;

public class CliqueMaker {
	
	
	/*public void makeClique(String idNodeGreater, String nodes) {
		CliqueDAO cliqueDAO = new CliqueDAO();
		
		
	}*/
	public void makeCliques(JSONObject map) {
		HashMap<String, HashSet<String>> result; 
		ObjectMapper mapper = new ObjectMapper();
		try {
			result = mapper.readValue(map.toJSONString(), HashMap.class);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
