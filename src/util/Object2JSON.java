package util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class Object2JSON {
	public String serializeObject(Object obj) {
		String serializeObj = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			serializeObj = mapper.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return serializeObj;
	}
}
