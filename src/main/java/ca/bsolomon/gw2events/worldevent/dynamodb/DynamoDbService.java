package ca.bsolomon.gw2events.worldevent.dynamodb;

import java.util.List;

import ca.bsolomon.gw2events.worldevent.dynamodb.model.EventWindow;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class DynamoDbService {

	private AmazonDynamoDBClient dynamoDB = null;
	private DynamoDBMapper mapper = null;
		
	public void initDynamoDbClient() throws Exception {
		dynamoDB = new AmazonDynamoDBClient(new ClasspathPropertiesFileCredentialsProvider());
        Region region = Region.getRegion(Regions.US_EAST_1);
        dynamoDB.setRegion(region);
        
        mapper = new DynamoDBMapper(dynamoDB);
    }
	
	public List<EventWindow> getAllEventWindows() {
		return EventWindowDao.getAllEventWindows(mapper);
	}
	
	public void updateEventWindow(EventWindow window) {
		EventWindowDao.updateEventWindow(mapper, window);
	}
}
