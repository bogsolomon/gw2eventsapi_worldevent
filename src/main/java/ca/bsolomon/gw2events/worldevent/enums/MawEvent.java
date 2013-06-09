package ca.bsolomon.gw2events.worldevent.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import ca.bsolomon.gw2events.worldevent.util.EventData;
import ca.bsolomon.gw2events.worldevent.util.EventStatus;
import ca.bsolomon.gw2events.worldevent.util.EventStringFormatter;

public enum MawEvent {

	PROTECT("6F516B2C-BD87-41A9-9197-A209538BB9DF", "Grawl Restless"),
	ESCORT("D5F31E0B-E0E3-42E3-87EC-337B3037F437", "Escort Scholar"),
	TOTEM("6565EFD4-6E37-4C26-A3EA-F47B368C866D", "Destroy Totem"),
	PORTALS("374FC8CB-7AB7-4381-AC71-14BFB30D3019", "Portals"),
	GUARDS("90B241F5-9E59-46E8-B608-2507F8810E00", "Guards"),
	SHAMANS("DB83ABB7-E5FE-4ACB-8916-9876B87D300D", "Shamans"),
	CHIEF("F7D9D427-5E54-4F12-977A-9809B23FBA99", "Active");
	
	
	private String uid;
	private String prettyName;
	
	MawEvent(String uid, String prettyName) {
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
	
	public static void formatMawString(EventData lowLevelEventData) {
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		for (ServerID servId:ServerID.values()) {
			formatMawString(lowLevelEventData, status, servId);
		}
		
		eventStatus = status;
	}

	public static void formatMawString(EventData lowLevelEventData,
			List<EventStatus> statusList, ServerID servId) {
		String outStatus = "";
		String color = "";
		DateTime time = null;
		int fontWeight = 400;

		String protectEventId = servId.getUid()+"-"+MawEvent.PROTECT.uid();
		String escortEventId = servId.getUid()+"-"+MawEvent.ESCORT.uid();
		String totemEventId = servId.getUid()+"-"+MawEvent.TOTEM.uid();
		
		String portalEventId = servId.getUid()+"-"+MawEvent.PORTALS.uid();
		String guardsEventId = servId.getUid()+"-"+MawEvent.GUARDS.uid();
		String shamanEventId = servId.getUid()+"-"+MawEvent.SHAMANS.uid();
		
		String chiefEventId = servId.getUid()+"-"+MawEvent.CHIEF.uid();

		String protectStatus = lowLevelEventData.getEventStatus(protectEventId);
		String escortStatus = lowLevelEventData.getEventStatus(escortEventId);
		String totemStatus = lowLevelEventData.getEventStatus(totemEventId);
		String portalStatus = lowLevelEventData.getEventStatus(portalEventId);
		String guardsStatus = lowLevelEventData.getEventStatus(guardsEventId);
		String shamaStatus = lowLevelEventData.getEventStatus(shamanEventId);
		String chiefStatus = lowLevelEventData.getEventStatus(chiefEventId);
		
		Integer inProg = 0;
		
		if (inProgressStatus.get(servId) != null)
			inProg = inProgressStatus.get(servId);
		
		if (protectStatus != null && protectStatus.equals("Active")) {
			time = lowLevelEventData.getEventTime(protectEventId);
			
			outStatus = MawEvent.PROTECT.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
			
			inProgressStatus.put(servId, 1);
		} else if (escortStatus != null && (escortStatus.equals("Active") || escortStatus.equals("Preparation"))) {
			time = lowLevelEventData.getEventTime(escortEventId);
			
			outStatus = MawEvent.ESCORT.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
			inProgressStatus.put(servId, 2);
		} else if (totemStatus != null && (totemStatus.equals("Active") || totemStatus.equals("Preparation"))) {
			time = lowLevelEventData.getEventTime(totemEventId);
			
			outStatus = MawEvent.TOTEM.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
			inProgressStatus.put(servId, 3);
		} else if ((portalStatus !=null && guardsStatus!=null && shamaStatus!=null) && 
				(portalStatus.equals("Active") || guardsStatus.equals("Active") || shamaStatus.equals("Active"))) {
			time = lowLevelEventData.getEventTime(portalEventId);
			
			outStatus = "Portals/Guards";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
			inProgressStatus.put(servId, 4);
		} else if (chiefStatus != null && (chiefStatus.equals("Active") || chiefStatus.equals("Preparation"))) {
			time = lowLevelEventData.getEventTime(chiefEventId);
			
			outStatus = MawEvent.CHIEF.toString();
			color = EventStateColor.ACTIVE.color();
			fontWeight = 900;
			
			inProgressStatus.put(servId, 0);
		} else if ((protectStatus != null) &&
				(protectStatus.equals("Success")) && inProg == 1) {
			time = lowLevelEventData.getEventTime(protectEventId);
			
			outStatus = "Waiting for escort";
			color = EventStateColor.PREPARATION.color();
		} else if ((escortStatus != null) &&
				(escortStatus.equals("Success")) && inProg == 2) {
			time = lowLevelEventData.getEventTime(escortEventId);
			
			outStatus = "Waiting to destroy totem";
			color = EventStateColor.PREPARATION.color();
		} else if ((totemStatus != null) &&
				(totemStatus.equals("Success")) && inProg == 3) {
			time = lowLevelEventData.getEventTime(totemEventId);
			
			outStatus = "Portals spawning";
			color = EventStateColor.PREPARATION.color();
		}  else if ((totemStatus != null) &&
				(totemStatus.equals("Success")) && inProg == 4) {
			time = lowLevelEventData.getEventTime(portalEventId);
			
			outStatus = "Shaman Chief ready";
			color = EventStateColor.PREPARATION.color();
		} else {
			time = lowLevelEventData.getEventTime(chiefEventId);
			
			outStatus = "Not up";
			color = EventStateColor.FAIL.color();
		}
		
		EventStringFormatter.generateEventString(statusList, servId, outStatus, color, fontWeight, time, "Maw", Waypoint.MAW.toString());
	}
}
