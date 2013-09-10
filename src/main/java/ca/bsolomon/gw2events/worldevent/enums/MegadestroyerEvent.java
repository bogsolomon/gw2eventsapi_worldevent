package ca.bsolomon.gw2events.worldevent.enums;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import ca.bsolomon.gw2events.worldevent.util.EventData;
import ca.bsolomon.gw2events.worldevent.util.EventStatus;
import ca.bsolomon.gw2events.worldevent.util.EventStringFormatter;
import ca.bsolomon.gw2events.worldevent.util.PlaySoundStatus;

public enum MegadestroyerEvent {
	STOP_INQUEST(	"294E08F6-CA36-42B3-8D06-B321BA06EECA", "4. Stop Inquest"),
	DEFEND_LAB(		"61D4579A-C53F-4C26-A31B-92FABE3DA566", "4. Defend Lab"),
	GOLEM(			"3BA29A69-A30B-405D-96AC-CBA5D511C163", "4. Inquest Golem"),
	FISS1(			"9E5D9F1A-FE14-49C6-917F-43AAE227165C", "3. Fissures: "),
	FISS2(			"4210CE81-BDB7-448E-BE33-46E18A5A3477", "3. Fissures: "),
	FISS3(			"584A4D22-33DC-4D77-A5D9-2FA7379401ED", "3. Fissures: "),
	PROTECT(		"36E81760-7D92-458E-AA22-7CDE94112B8F", "2. Protect Assura"),
	KILL(			"3BA29A69-A30B-405D-96AC-CBA5D511C163", "1. KILL!");
	
	private String uid;
	private String prettyName;
	
	MegadestroyerEvent(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public String uid() {return uid;}
	public String toString() {return prettyName;}

	private static List<EventStatus> eventStatus = new ArrayList<>();
	
	public static List<EventStatus> getStatus() {
		return eventStatus;
	}	
	
	public static void formatMegadestroyerString(EventData lowLevelEventData) {
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		for (ServerID servId:ServerID.values()) {
			formatMegadestroyerString(lowLevelEventData, status, servId);
		}
		
		eventStatus = status;
	}

	public static void formatMegadestroyerString(
			EventData lowLevelEventData, List<EventStatus> statusList,
			ServerID servId) {
		String outStatus = "";
		String color = "";
		DateTime time = null;
		int fontWeight = 400; 

		String stopInqId = servId.getUid()+"-"+STOP_INQUEST.uid();
		String defendLabId = servId.getUid()+"-"+DEFEND_LAB.uid();
		String golemId = servId.getUid()+"-"+GOLEM.uid();
		String fiss1Id = servId.getUid()+"-"+FISS1.uid();
		String fiss2Id = servId.getUid()+"-"+FISS2.uid();
		String fiss3Id = servId.getUid()+"-"+FISS3.uid();
		String protectId = servId.getUid()+"-"+PROTECT.uid();
		String killId = servId.getUid()+"-"+KILL.uid();
		
		String stopInqStatus = lowLevelEventData.getEventStatus(stopInqId);
		String defendLabStatus = lowLevelEventData.getEventStatus(defendLabId);
		String golemStatus = lowLevelEventData.getEventStatus(golemId);
		String fiss1Status = lowLevelEventData.getEventStatus(fiss1Id);
		String fiss2Status = lowLevelEventData.getEventStatus(fiss2Id);
		String fiss3Status = lowLevelEventData.getEventStatus(fiss3Id);
		String protectStatus = lowLevelEventData.getEventStatus(protectId);
		String killStatus = lowLevelEventData.getEventStatus(killId);
		
		boolean playSound = false;
		
		int fissStatus = checkDestroyerStatus(fiss1Status, fiss2Status, fiss3Status);
		
		if ((killStatus != null) &&
					(killStatus.equals("Active"))) {
			time = lowLevelEventData.getEventTime(killId);
				
			outStatus = KILL.toString();
			color = EventStateColor.ACTIVE.color();
			fontWeight = 900;
			
			playSound = true;
		} else if ((protectStatus != null) &&
				(protectStatus.equals("Active"))) {
			time = lowLevelEventData.getEventTime(protectId);
			
			outStatus = PROTECT.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else if (fissStatus != 0) {
			time = lowLevelEventData.getEventTime(fiss1Id);
			
			outStatus = FISS3.toString()+fissStatus+"/3";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else if ((golemStatus != null) &&
				(golemStatus.equals("Active"))) {
			time = lowLevelEventData.getEventTime(golemId);
			
			outStatus = GOLEM.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else if ((defendLabStatus != null) &&
				(defendLabStatus.equals("Active"))) {
			time = lowLevelEventData.getEventTime(defendLabId);
			
			outStatus = DEFEND_LAB.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else if ((stopInqStatus != null) &&
				(stopInqStatus.equals("Active"))) {
			time = lowLevelEventData.getEventTime(stopInqId);
			
			outStatus = STOP_INQUEST.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else {
			time = lowLevelEventData.getEventTime(killId);
			
			outStatus = "Not up";
			color = EventStateColor.FAIL.color();
		}
		
		String soundKey = servId.getUid()+"-megad";
		boolean playSoundList = playSound;
		if (PlaySoundStatus.playSoundStatus.containsKey(soundKey) && PlaySoundStatus.playSoundStatus.get(soundKey)) {
			playSound = false;
		}
		
		PlaySoundStatus.playSoundStatus.put(soundKey, playSoundList);
		
		EventStringFormatter.generateEventString(statusList, servId, outStatus, color, fontWeight, time, "Mega Destroyer", Waypoint.MEGADESTROYER.toString(), playSound);
	}

	private static int checkDestroyerStatus(String fiss1Status,
			String fiss2Status, String fiss3Status) {
		int count = 0;
		
		if ((fiss1Status != null) &&
				(fiss1Status.equals("Active") || fiss1Status.equals("Preparation"))) {
			count++;
		} 
		
		if ((fiss2Status != null) &&
				(fiss2Status.equals("Active") || fiss2Status.equals("Preparation"))) {
			count++;
		}  
		
		if ((fiss3Status != null) &&
				(fiss3Status.equals("Active") || fiss3Status.equals("Preparation"))) {
			count++;
		} 
		
		return count;
	}
}
