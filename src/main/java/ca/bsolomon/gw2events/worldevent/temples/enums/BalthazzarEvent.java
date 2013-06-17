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

public enum BalthazzarEvent {

	ALTAR_ESCORT("D0ECDACE-41F8-46BD-BB17-8762EF29868C", "Escort Altar"),
	SEIZE_ALTAR	("7B7D6D27-67A0-44EF-85EA-7460FFA621A1", "Seize Altar"),
	TEMPLE_DEF	("589B1C41-DD96-4AEE-8A3A-4CC607805B05", "Defense"),
	PRIEST		("2555EFCB-2927-4589-AB61-1957D9CC70C8", "Priest");
	
	
	private String uid;
	private String prettyName;
	
	BalthazzarEvent(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public String uid() {return uid;}
	public String toString() {return prettyName;}
	
	private static List<EventStatus> status = new ArrayList<>();
	
	public static List<EventStatus> getStatus() {
		return status;
	}
	
	public static void formatBalthazarString(EventData lowLevelEventData) {
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		for (ServerID servId:ServerID.values()) {
			formatBalthazarString(lowLevelEventData, status, servId);
		}
		
		BalthazzarEvent.status = status;
	}
	
	public static void formatBalthazarString(EventData templeEventData,
			List<EventStatus> statusList, ServerID servId) {
		String outStatus = "";
		String color = "";
		DateTime time = null;
		int fontWeight = 400; 

		String altarEscortEventId = servId.getUid()+"-"+ALTAR_ESCORT.uid();
		String seizeAltarEventId = servId.getUid()+"-"+SEIZE_ALTAR.uid();
		String templeDefEventId = servId.getUid()+"-"+TEMPLE_DEF.uid();
		String priestEventId = servId.getUid()+"-"+PRIEST.uid();
		
		String altarEscortStatus = templeEventData.getEventStatus(altarEscortEventId);
		String seizeAltarStatus = templeEventData.getEventStatus(seizeAltarEventId);
		String templeDefStatus = templeEventData.getEventStatus(templeDefEventId);
		String priestStatus = templeEventData.getEventStatus(priestEventId);
		
		boolean playSound = false;
		
		if (templeDefStatus!=null && (templeDefStatus.equals("Active"))) {
			time = templeEventData.getEventTime(altarEscortEventId);
			
			outStatus = "Defend Altar";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 900;
		} else if (templeDefStatus!=null && (templeDefStatus.equals("Fail"))) {
			time = templeEventData.getEventTime(altarEscortEventId);
			
			outStatus = "Not up";
			color = EventStateColor.FAIL.color();
			fontWeight = 900;
		} else if (altarEscortStatus!=null && (altarEscortStatus.equals("Preparation") || altarEscortStatus.equals("Active"))) {
			time = templeEventData.getEventTime(altarEscortEventId);
			
			outStatus = "Escort to Altar";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 900;
			
			playSound = true;
		} else if (seizeAltarStatus!=null && seizeAltarStatus.equals("Active")) {
			time = templeEventData.getEventTime(seizeAltarEventId);
			
			outStatus = "Seize Altar";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 900;
		} else if (priestStatus!=null && (priestStatus.equals("Active") || priestStatus.equals("Warmup"))) {
			time = templeEventData.getEventTime(priestEventId);
			
			outStatus = "Kill Priest";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 900;
		} else if (priestStatus!=null && (priestStatus.equals("Success"))) {
			time = templeEventData.getEventTime(priestEventId);
			
			outStatus = "Under Pact Control";
			color = EventStateColor.ACTIVE.color();
			fontWeight = 900;
		} else {
			time = templeEventData.getEventTime(priestEventId);
			
			outStatus = "Not up";
			color = EventStateColor.FAIL.color();
			fontWeight = 900;
		}
		
		String soundKey = servId.getUid()+"-balth";
		boolean playSoundList = playSound;
		if (PlaySoundStatus.playSoundStatus.containsKey(soundKey) && PlaySoundStatus.playSoundStatus.get(soundKey)) {
			playSound = false;
		}
		
		PlaySoundStatus.playSoundStatus.put(soundKey, playSoundList);
		
		EventStringFormatter.generateEventString(statusList, servId, outStatus, color, fontWeight, time, "Balthazar", Waypoint.BALTH.toString(), playSound);
	}
}

