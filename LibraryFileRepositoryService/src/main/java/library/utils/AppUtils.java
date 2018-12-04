package library.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AppUtils {
	public static JsonNode parseJson(String jsonData) throws Exception {
		JsonFactory jsonFactory = new JsonFactory();
		ObjectMapper objectMapper = new ObjectMapper(jsonFactory);
		JsonNode jsonNode = objectMapper.readTree(jsonData);

		return jsonNode;
	}
}
