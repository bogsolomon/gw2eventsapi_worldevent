package ca.bsolomon.gw2events.worldevent.dynamodb;

import java.util.ArrayList;
import java.util.List;

import ca.bsolomon.gw2events.worldevent.dynamodb.model.EventWindow;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;

public class EventWindowDao {

	public static List<EventWindow> getAllEventWindows(DynamoDBMapper mapper) {
		List<EventWindow> events = new ArrayList<>();

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

		PaginatedScanList<EventWindow> result = mapper.scan(EventWindow.class,
				scanExpression);

		for (EventWindow info : result) {
			events.add(info);
		}

		return events;
	}
	
	public static void updateEventWindow(DynamoDBMapper mapper, EventWindow map) {
		DynamoDBMapperConfig config = new DynamoDBMapperConfig(DynamoDBMapperConfig.SaveBehavior.UPDATE);
		mapper.save(map, config);
	}
}
