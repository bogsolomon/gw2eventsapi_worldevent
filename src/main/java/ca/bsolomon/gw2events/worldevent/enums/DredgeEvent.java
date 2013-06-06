package ca.bsolomon.gw2events.worldevent.enums;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import ca.bsolomon.gw2events.worldevent.util.EventData;
import ca.bsolomon.gw2events.worldevent.util.EventStatus;
import ca.bsolomon.gw2events.worldevent.util.EventStringFormatter;

public enum DredgeEvent {

	MOLEKCAPTURE(		"141654A6-D42E-415E-A3C4-918A1E664AF3", "Capture Molek Pre"),
	MOLEKDEFENSE(	"64B94537-00D5-4CB6-8558-44987A9C5F76", "Protect Molek Pre"),
	DREDGE(		"95CA969B-0CC6-4604-B166-DBCCE125864F", "Active");
	
	private String uid;
	private String prettyName;
	
	DredgeEvent(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public String uid() {return uid;}
	public String toString() {return prettyName;}

	private static List<EventStatus> eventStatus = new ArrayList<>();
	
	public static List<EventStatus> getStatus() {
		return eventStatus;
	}	
	
	public static void formatDredgeString(EventData lowLevelEventData) {
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		for (ServerID servId:ServerID.values()) {
			formatDredgeString(lowLevelEventData, status, servId);
		}
		
		eventStatus = status;
	}

	public static void formatDredgeString(
			EventData lowLevelEventData, List<EventStatus> statusList,
			ServerID servId) {
		String outStatus = "";
		String color = "";
		DateTime time = null;
		int fontWeight = 400; 

		String molekCEventId = servId.uid()+"-"+MOLEKCAPTURE.uid();
		String molekDEventId = servId.uid()+"-"+MOLEKDEFENSE.uid();
		String dredgeEventId = servId.uid()+"-"+DREDGE.uid();
		
		String molekCStatus = lowLevelEventData.getEventStatus(molekCEventId);
		String molekDStatus = lowLevelEventData.getEventStatus(molekDEventId);
		String dredgeStatus = lowLevelEventData.getEventStatus(dredgeEventId);
		
		 if ((dredgeStatus != null) &&
					(dredgeStatus.equals("Active"))) {
			time = lowLevelEventData.getEventTime(dredgeEventId);
				
			outStatus = "Active";
			color = EventStateColor.ACTIVE.color();
			fontWeight = 900;
		} else if ((molekCStatus != null) &&
				(molekCStatus.equals("Active"))) {
			time = lowLevelEventData.getEventTime(molekCEventId);
			
			outStatus = MOLEKCAPTURE.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else if ((molekDStatus != null) &&
				(molekDStatus.equals("Active") || molekDStatus.equals("Preparation"))) {
			time = lowLevelEventData.getEventTime(molekDEventId);
			
			outStatus = MOLEKDEFENSE.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else {
			time = lowLevelEventData.getEventTime(dredgeEventId);
			
			outStatus = "Not up";
			color = EventStateColor.FAIL.color();
		}
		
		EventStringFormatter.generateEventString(statusList, servId, outStatus, color, fontWeight, time);
	}
}
