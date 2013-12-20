package ca.bsolomon.gw2events.worldevent.dynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "EventWindows")
public class EventWindow {

	private String eventUID;
	private String maxWindow;
	private String minWindow;

	public EventWindow(String eventUID, String maxWindow,
			String minWindow) {
		this.eventUID = eventUID;
		this.maxWindow = maxWindow;
		this.minWindow = minWindow;
	}

	//Empty constructor for dynamodb retrieval
	public EventWindow() {
		
	}

	@DynamoDBAttribute(attributeName="maxWindow")
	public String getMaxWindow() {
		return maxWindow;
	}

	public void setMaxWindow(String maxWindow) {
		this.maxWindow = maxWindow;
	}

	@DynamoDBAttribute(attributeName="minWindow")
	public String getMinWindow() {
		return minWindow;
	}

	public void setMinWindow(String minWindow) {
		this.minWindow = minWindow;
	}
	
	@DynamoDBHashKey(attributeName="eventUID")  
	public String getEventUID() {
		return eventUID;
	}

	public void setEventUID(String eventUID) {
		this.eventUID = eventUID;
	}
}
