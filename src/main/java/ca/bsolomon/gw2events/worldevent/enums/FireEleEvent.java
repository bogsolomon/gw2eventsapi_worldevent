package ca.bsolomon.gw2events.worldevent.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import ca.bsolomon.gw2events.worldevent.util.EventData;
import ca.bsolomon.gw2events.worldevent.util.EventStatus;
import ca.bsolomon.gw2events.worldevent.util.EventStringFormatter;

public enum FireEleEvent {

	CHAOTIC("6B897FF9-4BA8-4EBD-9CEC-7DCFDA5361D8", "Chaotic Material Pre"),
	ESCORT("5E4E9CD9-DD7C-49DB-8392-C99E1EF4E7DF", "Escort Golem Pre"),
	DEFEND("2C833C11-5CD5-4D96-A4CE-A74C04C9A278", "Defend Golem Pre"),
	STOLEN("FCB42C06-547F-4DA2-904B-0098E60C47BC", "Destroy Golem Pre"),
	FIREELE("33F76E9E-0BB6-46D0-A3A9-BE4CDFC4A3A4", "Active");
	
	private String uid;
	private String prettyName;
	
	FireEleEvent(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public String uid() {return uid;}
	public String toString() {return prettyName;}
	
	private static Map<ServerID, Integer> inProgressStatus = new HashMap<>();
	
	private static List<EventStatus> feStatus = new ArrayList<>();
	
	public static List<EventStatus> getStatus() {
		return feStatus;
	}
	
	public static void formatFireEleString(EventData lowLevelEventData) {
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		for (ServerID servId:ServerID.values()) {
			formatFireEleString(lowLevelEventData, status, servId);
		}
		
		feStatus = status;
	}

	public static void formatFireEleString(EventData lowLevelEventData,
			List<EventStatus> statusList, ServerID servId) {
		String outStatus = "";
		String color = "";
		DateTime time = null;
		int fontWeight = 400; 

		String chaoticEventId = servId.getUid()+"-"+FireEleEvent.CHAOTIC.uid();
		String defendEventId = servId.getUid()+"-"+FireEleEvent.DEFEND.uid();
		String escortEventId = servId.getUid()+"-"+FireEleEvent.ESCORT.uid();
		String stolenEventId = servId.getUid()+"-"+FireEleEvent.STOLEN.uid();
		String fireEleEventId = servId.getUid()+"-"+FireEleEvent.FIREELE.uid();
		
		String chaoticStatus = lowLevelEventData.getEventStatus(chaoticEventId);
		String defendStatus = lowLevelEventData.getEventStatus(defendEventId);
		String escortStatus = lowLevelEventData.getEventStatus(escortEventId);
		String stolenStatus = lowLevelEventData.getEventStatus(stolenEventId);
		String fireEleStatus = lowLevelEventData.getEventStatus(fireEleEventId);
		
		Integer inProg = 0;
		
		if (inProgressStatus.get(servId) != null)
			inProg = inProgressStatus.get(servId);
		
		if (chaoticStatus!=null && (chaoticStatus.equals("Active") || chaoticStatus.equals("Preparation"))) {
			time = lowLevelEventData.getEventTime(chaoticEventId);
			
			outStatus = FireEleEvent.CHAOTIC.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else if (escortStatus!=null && (escortStatus.equals("Active") || escortStatus.equals("Preparation"))) {
			time = lowLevelEventData.getEventTime(escortEventId);
			
			outStatus = FireEleEvent.ESCORT.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
			
			inProgressStatus.put(servId, 1);
		} else if (defendStatus!=null && defendStatus.equals("Active")) {
			time = lowLevelEventData.getEventTime(defendEventId);
			
			outStatus = FireEleEvent.DEFEND.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
			
			inProgressStatus.put(servId, 2);
		} else if (stolenStatus!=null && (stolenStatus.equals("Active") || stolenStatus.equals("Preparation"))) {
			time = lowLevelEventData.getEventTime(stolenEventId);
			
			outStatus = FireEleEvent.STOLEN.toString();
			color = EventStateColor.FAIL.color();
			
			inProgressStatus.put(servId, 0);
		} else if (fireEleStatus!=null && (fireEleStatus.equals("Active") || fireEleStatus.equals("Preparation"))) {
			time = lowLevelEventData.getEventTime(fireEleEventId);
			
			outStatus = FireEleEvent.FIREELE.toString();
			color = EventStateColor.ACTIVE.color();
			fontWeight = 900;
			
			inProgressStatus.put(servId, 0);
		} else if (defendStatus!=null && defendStatus.equals("Success") && inProg == 2) {
			time = lowLevelEventData.getEventTime(defendEventId);
			
			outStatus = "Spawning";
			color = EventStateColor.ACTIVE.color();
			fontWeight = 900;
		} else if (escortStatus!=null && escortStatus.equals("Success") && inProg == 1) {
			time = lowLevelEventData.getEventTime(escortEventId);
			
			outStatus = "Escort Starting";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 900;
		} else {
			time = lowLevelEventData.getEventTime(fireEleEventId);
			
			outStatus = "Not up";
			color = EventStateColor.FAIL.color();
		}
		
		EventStringFormatter.generateEventString(statusList, servId, outStatus, color, fontWeight, time, "Fire Elemental", Waypoint.FIREELE.toString());
	}
}
