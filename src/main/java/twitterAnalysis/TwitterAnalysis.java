package twitterAnalysis;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.twitter.TwitterSource;

public class TwitterAnalysis {
	
	public static void main(String args[]) {
		
		ParameterTool params = ParameterTool.fromArgs(args);
		
		if(params.has("propFile") && params.has("outputFile")) {
			try {
			ParameterTool twitterParams = ParameterTool.fromPropertiesFile(params.get("propFile"));
			
			if(twitterParams.has("CONSUMER_KEY") && twitterParams.has("CONSUMER_SECRET") && twitterParams.has("TOKEN") && twitterParams.has("TOKEN_SECRET") && twitterParams.has("KEYWORDS")) {
				
				final List<String> keywords = Arrays.asList(twitterParams.get("KEYWORD"));
				final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
				
				Properties twitterCred  = new Properties();
				twitterCred.setProperty(TwitterSource.CONSUMER_KEY, twitterParams.get("CONSUMER_KEY"));
				twitterCred.setProperty(TwitterSource.CONSUMER_SECRET, twitterParams.get("CONSUMER_SECRET"));
				twitterCred.setProperty(TwitterSource.TOKEN, twitterParams.get("TOKEN"));
				twitterCred.setProperty(TwitterSource.TOKEN_SECRET, twitterParams.get("TOKEN_SECRET"));
				
				
				DataStream<String> rawData = env.addSource( new TwitterSource(twitterCred));
				
				
				
				
			}
			else {
				
				System.out.println("Incorrect twitter credentials provided.");
			}
					
				
			}
			catch(Exception e) {
				System.out.println("Error in reading properties file:\n");
				e.printStackTrace();
			}
			
		}
		else {
			
			System.out.println("Incorrect number or name of parameters.\nRequired parameters are:\n1. propfile\n2. outputFile ");
		}
		
		
		
	}

}
