package util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JSON2Object {
	
	public <T> Object deserializeObject(String jsonObj, Class<T> type) {
		Object result = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			result = mapper.readValue(jsonObj, type);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
