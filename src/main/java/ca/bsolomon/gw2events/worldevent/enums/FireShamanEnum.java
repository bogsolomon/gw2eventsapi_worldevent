package ca.bsolomon.gw2events.worldevent.enums;

import org.joda.time.DateTime;

import ca.bsolomon.gw2events.worldevent.util.EventStringFormatter;
import ca.bsolomon.gw2events.worldevent.util.EventData;

public enum FireShamanEnum {
	
	SHAMAN("295E8D3B-8823-4960-A627-23E07575ED96", "Active");
	
	private String uid;
	private String prettyName;
	
	FireShamanEnum(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public String uid() {return uid;}
	public String toString() {return prettyName;}
	
	public static String formatShamanString(EventData lowLevelEventData) {
		StringBuffer sb = new StringBuffer();
		
		for (ServerID servId:ServerID.values()) {
			formatShamanString(lowLevelEventData, sb, servId);
		}
			
		return sb.toString();
	}

	public static void formatShamanString(
			EventData lowLevelEventData, StringBuffer sb,
			ServerID servId) {
		String outStatus = "";
		String color = "";
		DateTime time = null;
		int fontWeight = 400; 

		String eventId = servId.uid()+"-"+SHAMAN.uid();
		
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
		
		 EventStringFormatter.generateEventString(sb, servId, outStatus, color, fontWeight, time);
	}
}
