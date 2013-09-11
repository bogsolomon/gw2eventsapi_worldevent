package ca.bsolomon.gw2events.worldevent.temples.enums;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import ca.bsolomon.gw2events.worldevent.enums.EventStateColor;
import ca.bsolomon.gw2events.worldevent.enums.ServerID;
import ca.bsolomon.gw2events.worldevent.enums.Waypoint;
import ca.bsolomon.gw2events.worldevent.util.EventData;
import ca.bsolomon.gw2events.worldevent.util.EventStatus;
import ca.bsolomon.gw2events.worldevent.util.EventStringFormatter;
import ca.bsolomon.gw2events.worldevent.util.PlaySoundStatus;

public enum DwaynaEvent {

	ALTAR_ESCORT("F531683F-FC09-467F-9661-6741E8382E24", "4. Escort Scholar"),
	PRIESTESS("7EF31D63-DB2A-4FEB-A6C6-478F382BFBCB", "3. Kill Priestess"),
	MALCHOR("526732A0-E7F2-4E7E-84C9-7CDED1962000", "2. Drive Malchor"),
	STATUE("6A6FD312-E75C-4ABF-8EA1-7AE31E469ABA", "1. Destroy Statue");
	//PROTECT1("0723E056-E665-439F-99B7-20385442AD4E", "Protect"),
	//PROTECT2("B78631EA-1584-452A-859F-CE935321B52D", "Protect");
	
	private String uid;
	private String prettyName;
	
	DwaynaEvent(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public String uid() {return uid;}
	public String toString() {return prettyName;}
	
	private static List<EventStatus> status = new ArrayList<>();
	
	public static List<EventStatus> getStatus() {
		return status;
	}
	
	public static void formatDwaynaString(EventData lowLevelEventData) {
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		for (ServerID servId:ServerID.values()) {
			formatDwaynaString(lowLevelEventData, status, servId);
		}
		
		DwaynaEvent.status = status;
	}
	
	public static void formatDwaynaString(EventData templeEventData,
			List<EventStatus> statusList, ServerID servId) {
		String outStatus = "";
		String color = "";
		DateTime time = null;
		int fontWeight = 400; 

		String altarEscortEventId = servId.getUid()+"-"+ALTAR_ESCORT.uid();
		String malchorEventId = servId.getUid()+"-"+MALCHOR.uid();
		String priestessEventId = servId.getUid()+"-"+PRIESTESS.uid();
		String statueEventId = servId.getUid()+"-"+STATUE.uid();
		
		String altarEscortStatus = templeEventData.getEventStatus(altarEscortEventId);
		String malchorStatus = templeEventData.getEventStatus(malchorEventId);
		String priestessStatus = templeEventData.getEventStatus(priestessEventId);
		String statueStatus = templeEventData.getEventStatus(statueEventId);
		
		boolean playSound = false;
		
		if (altarEscortStatus!=null && (altarEscortStatus.equals("Warmup"))) {
			time = templeEventData.getEventTime(altarEscortEventId);
			
			outStatus = "Not up";
			color = EventStateColor.FAIL.color();
			fontWeight = 900;
		} else if (altarEscortStatus!=null && (altarEscortStatus.equals("Preparation") || altarEscortStatus.equals("Active"))) {
			time = templeEventData.getEventTime(altarEscortEventId);
			
			outStatus = ALTAR_ESCORT.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 900;
		} else if (priestessStatus!=null && (priestessStatus.equals("Preparation") || priestessStatus.equals("Active"))) {
			time = templeEventData.getEventTime(priestessEventId);
			
			playSound = true;
			
			outStatus = PRIESTESS.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 900;
		} else if (malchorStatus!=null && (malchorStatus.equals("Preparation") || malchorStatus.equals("Active"))) {
			time = templeEventData.getEventTime(malchorEventId);
			
			outStatus = MALCHOR.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 900;
		} else if (statueStatus!=null && (statueStatus.equals("Preparation") || statueStatus.equals("Active"))) {
			time = templeEventData.getEventTime(statueEventId);
			
			outStatus = STATUE.toString();
			color = EventStateColor.ACTIVE.color();
			fontWeight = 900;
		} else if (statueStatus!=null && (statueStatus.equals("Success"))) {
			time = templeEventData.getEventTime(statueEventId);
			
			outStatus = "0. Under Pact Control";
			color = EventStateColor.ACTIVE.color();
			fontWeight = 900;
		} else {
			time = templeEventData.getEventTime(statueEventId);
			
			outStatus = "Not up";
			color = EventStateColor.FAIL.color();
			fontWeight = 900;
		}
		
		String soundKey = servId.getUid()+"-dwayna";
		boolean playSoundList = playSound;
		if (PlaySoundStatus.playSoundStatus.containsKey(soundKey) && PlaySoundStatus.playSoundStatus.get(soundKey)) {
			playSound = false;
		}
		
		PlaySoundStatus.playSoundStatus.put(soundKey, playSoundList);
		
		EventStringFormatter.generateEventString(statusList, servId, outStatus, color, fontWeight, time, "Dwayna", Waypoint.DWAYNA.toString(), playSound);
	}
}

