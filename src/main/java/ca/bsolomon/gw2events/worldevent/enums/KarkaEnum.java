package ca.bsolomon.gw2events.worldevent.enums;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import ca.bsolomon.gw2events.worldevent.util.EventData;
import ca.bsolomon.gw2events.worldevent.util.EventStatus;
import ca.bsolomon.gw2events.worldevent.util.EventStringFormatter;

public enum KarkaEnum {

	QUEEN("E1CC6E63-EFFE-4986-A321-95C89EA58C07", "Active");

	private String uid;
	private String prettyName;
	
	KarkaEnum(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public String uid() {return uid;}
	public String toString() {return prettyName;}
	
	public static List<EventStatus> formatKarkaString(EventData lowLevelEventData) {
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		for (ServerID servId:ServerID.values()) {
			formatKarkaString(lowLevelEventData, status, servId);
		}
			
		return status;
	}
	
	public static void formatKarkaString(
			EventData lowLevelEventData, List<EventStatus> statusList,
			ServerID servId) {
		String outStatus = "";
		String color = "";
		DateTime time = null;
		int fontWeight = 400; 

		String eventId = servId.uid()+"-"+QUEEN.uid();
		
		String status = lowLevelEventData.getEventStatus(eventId);
		
		 if ((status != null) &&
					(status.equals("Active"))) {
			 time = lowLevelEventData.getEventTime(eventId);
			
			 outStatus = "Active";
			 color = EventStateColor.ACTIVE.color();
			 fontWeight = 900;
		 }  else {
			 time = lowLevelEventData.getEventTime(eventId);
			
			 outStatus = "Not up";
			 color = EventStateColor.FAIL.color();
		 }
		
		 EventStringFormatter.generateEventString(statusList, servId, outStatus, color, fontWeight, time);
	}
}
