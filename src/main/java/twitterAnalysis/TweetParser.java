package twitterAnalysis;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;

public class TweetParser implements MapFunction<String, JsonNode>{

	@Override
	public JsonNode map(String value) throws Exception {
		ObjectMapper node = new ObjectMapper();
		JsonNode fnode = node.readValue(value, JsonNode.class);
		return fnode;
	}
	
}
