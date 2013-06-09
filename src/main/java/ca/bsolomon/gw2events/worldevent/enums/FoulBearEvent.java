package ca.bsolomon.gw2events.worldevent.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import ca.bsolomon.gw2events.worldevent.util.EventStatus;
import ca.bsolomon.gw2events.worldevent.util.EventStringFormatter;
import ca.bsolomon.gw2events.worldevent.util.EventData;

public enum FoulBearEvent {

	
	SAPPER(		"724343EA-B32C-4AE1-AB7E-E5FC160C26F9", "Sapper Delve"),
	SAPPERDEF(	"8D45B410-B614-4008-8A5C-E8D8230CEB40", "Sapper Delve Defense"),
	BLOODGORGE(	"44ABC8F1-ED5C-4E00-9338-5A8C40B228B4", "Capture Bloodgorge"),
	ASSAULT(	"D9F1CF48-B1CB-49F5-BFAF-4CEC5E68C9CF", "Animal Trainers"),
	DESTROY(	"4B478454-8CD2-4B44-808C-A35918FA86AA", "Destroy Houses"),
	CHIEF(		"B4E6588F-232C-4F68-9D58-8803D67E564D", "Active");
	
	private String uid;
	private String prettyName;
	
	FoulBearEvent(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public String uid() {return uid;}
	public String toString() {return prettyName;}
	
	private static Map<ServerID, Integer> inProgressStatus = new HashMap<>();

	private static List<EventStatus> eventStatus = new ArrayList<>();
	
	public static List<EventStatus> getStatus() {
		return eventStatus;
	}
	
	public static void formatOgreString(EventData lowLevelEventData) {
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		for (ServerID servId:ServerID.values()) {
			formatOgreString(lowLevelEventData, status, servId);
		}
		
		eventStatus = status;
	}

	public static void formatOgreString(EventData lowLevelEventData,
			List<EventStatus> statusList, ServerID servId) {
		String outStatus = "";
		String color = "";
		DateTime time = null;
		int fontWeight = 400;

		String sapperEventId = servId.getUid()+"-"+SAPPER.uid();
		String sapperDefEventId = servId.getUid()+"-"+SAPPERDEF.uid();
		String bloodEventId = servId.getUid()+"-"+BLOODGORGE.uid();
		String assaultEventId = servId.getUid()+"-"+ASSAULT.uid();
		String destroyEventId = servId.getUid()+"-"+DESTROY.uid();
		String chiefEventId = servId.getUid()+"-"+CHIEF.uid();
		
		String sapperStatus = lowLevelEventData.getEventStatus(sapperEventId);
		String sapperDefStatus = lowLevelEventData.getEventStatus(sapperDefEventId);
		String bloodStatus = lowLevelEventData.getEventStatus(bloodEventId);
		String assaultStatus = lowLevelEventData.getEventStatus(assaultEventId);
		String destroyStatus = lowLevelEventData.getEventStatus(destroyEventId);
		String chiefStatus = lowLevelEventData.getEventStatus(chiefEventId);
		
		Integer inProg = 0;
		
		if (inProgressStatus.get(servId) != null)
			inProg = inProgressStatus.get(servId);
		
		if ((chiefStatus != null) &&
				(chiefStatus.equals("Active") || chiefStatus.equals("Preparation") || chiefStatus.equals("Warmup"))) {
			time = lowLevelEventData.getEventTime(chiefEventId);
			
			outStatus = "Active";
			color = EventStateColor.ACTIVE.color();
			fontWeight = 900;
			
			inProgressStatus.put(servId, 0);
		} else if ((destroyStatus != null) &&
				(destroyStatus.equals("Active") || destroyStatus.equals("Preparation") || destroyStatus.equals("Warmup"))) {
			time = lowLevelEventData.getEventTime(destroyEventId);
			
			outStatus = DESTROY.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
			inProgressStatus.put(servId, 3);
		} else if ((assaultStatus != null) &&
				(assaultStatus.equals("Active") || assaultStatus.equals("Preparation"))) {
			time = lowLevelEventData.getEventTime(assaultEventId);
			
			outStatus = ASSAULT.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
			inProgressStatus.put(servId, 2);
		} else if ((bloodStatus != null) &&
				(bloodStatus.equals("Active")  || bloodStatus.equals("Preparation"))) {
			time = lowLevelEventData.getEventTime(bloodEventId);
			
			outStatus = BLOODGORGE.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
			
			inProgressStatus.put(servId, 1);
		} else if ((destroyStatus != null) &&
				(destroyStatus.equals("Success"))  && inProg == 3) {
			time = lowLevelEventData.getEventTime(destroyEventId);
			
			outStatus = "Waiting to kill chief";
			color = EventStateColor.PREPARATION.color();
		} else if ((assaultStatus != null) &&
				(assaultStatus.equals("Success"))  && inProg == 2) {
			time = lowLevelEventData.getEventTime(assaultEventId);
			
			outStatus = "Waiting to destroy houses";
			color = EventStateColor.PREPARATION.color();
		} else if ((bloodStatus != null) &&
				(bloodStatus.equals("Success"))  && inProg == 1) {
			time = lowLevelEventData.getEventTime(bloodEventId);
			
			outStatus = "Waiting to attack trainers";
			color = EventStateColor.PREPARATION.color();
		} else {
			time = lowLevelEventData.getEventTime(chiefEventId);
			
			outStatus = "Not up";
			color = EventStateColor.FAIL.color();
		}
		
		EventStringFormatter.generateEventString(statusList, servId, outStatus, color, fontWeight, time, "Foulbear", Waypoint.FOULBEAR.toString());
	}
}
