package ca.bsolomon.gw2events.worldevent.temples.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import ca.bsolomon.gw2events.worldevent.enums.EventStateColor;
import ca.bsolomon.gw2events.worldevent.enums.ServerID;
import ca.bsolomon.gw2events.worldevent.enums.Waypoint;
import ca.bsolomon.gw2events.worldevent.util.EventData;
import ca.bsolomon.gw2events.worldevent.util.EventStatus;
import ca.bsolomon.gw2events.worldevent.util.EventStringFormatter;

public enum MelandruEvent {

	BEACON1("3D333172-24CE-47BA-8F1A-1AD47E7B69E4", "Beacon 1 Escort"),
	BEACON2("E7563D8D-838D-4AF4-80CD-1D3A25B6F6AB", "Beacon 2 Escort"),
	BEACON2_DEF("F0CE1E71-4B96-48C6-809D-E1941AF40B1D", "Beacon 2 Defense"),
	ESCORT("351F7480-2B1C-4846-B03B-ED1B8556F3D7", "Escort"),
	PRIEST("7E24F244-52AF-49D8-A1D7-8A1EE18265E0", "Priest"),
	CLEANSING("A5B5C2AF-22B1-4619-884D-F231A0EE0877", "Cleansing");
	
	private String uid;
	private String prettyName;
	
	MelandruEvent(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public String uid() {return uid;}
	public String toString() {return prettyName;}
	
	private static Map<ServerID, Integer> inProgressStatus = new HashMap<>();
	
	private static List<EventStatus> status = new ArrayList<>();
	
	public static List<EventStatus> getStatus() {
		return status;
	}
	
	public static void formatMelandruString(EventData lowLevelEventData) {
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		for (ServerID servId:ServerID.values()) {
			formatMelandruString(lowLevelEventData, status, servId);
		}
		
		MelandruEvent.status = status;
	}
	
	public static void formatMelandruString(EventData templeEventData,
			List<EventStatus> statusList, ServerID servId) {
		String outStatus = "";
		String color = "";
		DateTime time = null;
		int fontWeight = 400; 

		String beacon1EventId = servId.getUid()+"-"+BEACON1.uid();
		String beacon2EventId = servId.getUid()+"-"+BEACON2.uid();
		String beacon2DefEventId = servId.getUid()+"-"+BEACON2_DEF.uid();
		String cleansingEventId = servId.getUid()+"-"+CLEANSING.uid();
		String escortEventId = servId.getUid()+"-"+ESCORT.uid();
		String priestEventId = servId.getUid()+"-"+PRIEST.uid();
		
		String beacon1Status = templeEventData.getEventStatus(beacon1EventId);
		String beacon2Status = templeEventData.getEventStatus(beacon2EventId);
		String beacon2DefStatus = templeEventData.getEventStatus(beacon2DefEventId);
		String cleansingStatus = templeEventData.getEventStatus(cleansingEventId);
		String escortStatus = templeEventData.getEventStatus(escortEventId);
		String priestStatus = templeEventData.getEventStatus(priestEventId);
		
		Integer inProg = 0;
		
		if (inProgressStatus.get(servId) != null)
			inProg = inProgressStatus.get(servId);
		
		if (beacon1Status!=null && (beacon1Status.equals("Warmup"))) {
			time = templeEventData.getEventTime(beacon1EventId);
			
			outStatus = "Not up";
			color = EventStateColor.FAIL.color();
			fontWeight = 900;
		} else if (cleansingStatus!=null && (cleansingStatus.equals("Success"))) {
			time = templeEventData.getEventTime(priestEventId);
			
			outStatus = "Under Pact Control";
			color = EventStateColor.ACTIVE.color();
			fontWeight = 900;
			inProgressStatus.put(servId, 0);
		} else if (cleansingStatus!=null && (cleansingStatus.equals("Active"))) {
			time = templeEventData.getEventTime(priestEventId);
			
			outStatus = "Cleansing Temple";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 900;
			inProgressStatus.put(servId, 0);
		} else if (priestStatus!=null && (priestStatus.equals("Active"))) {
			time = templeEventData.getEventTime(priestEventId);
			
			outStatus = "Kill Priest";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 900;
			
			inProgressStatus.put(servId, 0);
		} else if (inProg == 4 && escortStatus.equals("Success")) {
			time = templeEventData.getEventTime(beacon2DefEventId);
			
			outStatus = "Escort Succeded";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 900;
		} else if (escortStatus!=null && (escortStatus.equals("Active"))) {
			time = templeEventData.getEventTime(escortEventId);
			
			outStatus = "Escort to Temple";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 900;
			
			inProgressStatus.put(servId, 4);
		} else if (inProg == 3 && beacon2DefStatus.equals("Success")) {
			time = templeEventData.getEventTime(beacon2DefEventId);
			
			outStatus = "Beacon 2 Defended";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 900;
		} else if (beacon2DefStatus!=null && (beacon2DefStatus.equals("Active"))) {
			time = templeEventData.getEventTime(beacon2DefEventId);
			
			outStatus = "Beacon 2 Defense";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 900;
			
			inProgressStatus.put(servId, 3);
		} else if (inProg == 2 && beacon2Status.equals("Success")) {
			time = templeEventData.getEventTime(beacon2EventId);
			
			outStatus = "Beacon 2 Success";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 900;
		} else if (beacon2Status!=null && (beacon1Status.equals("Active"))) {
			time = templeEventData.getEventTime(beacon2EventId);
			
			outStatus = "Beacon 2 Escort";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 900;
			
			inProgressStatus.put(servId, 2);
		} else if (inProg == 1 && beacon1Status.equals("Success")) {
			time = templeEventData.getEventTime(beacon1EventId);
			
			outStatus = "Waiting Beacon 2";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 900;
		} else if (beacon1Status!=null && (beacon1Status.equals("Preparation") || beacon1Status.equals("Active"))) {
			time = templeEventData.getEventTime(beacon1EventId);
			
			outStatus = "Beacon 1 Escort";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 900;
			
			inProgressStatus.put(servId, 1);
		} else {
			time = templeEventData.getEventTime(priestEventId);
			
			outStatus = "Not up";
			color = EventStateColor.FAIL.color();
			fontWeight = 900;
		}
		
		EventStringFormatter.generateEventString(statusList, servId, outStatus, color, fontWeight, time, "Melandru", Waypoint.MELANDRU.toString());
	}
}

