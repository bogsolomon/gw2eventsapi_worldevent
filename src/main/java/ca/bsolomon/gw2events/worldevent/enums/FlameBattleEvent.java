package ca.bsolomon.gw2events.worldevent.enums;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import ca.bsolomon.gw2events.worldevent.util.EventData;
import ca.bsolomon.gw2events.worldevent.util.EventStatus;
import ca.bsolomon.gw2events.worldevent.util.EventStringFormatter;
import ca.bsolomon.gw2events.worldevent.util.PlaySoundStatus;

public enum FlameBattleEvent {

	WARBAND1(	"0C226D56-5FD2-4C02-B5A8-867F02D53CF5", "Warband 1"),
	WARBAND2(	"7675AD4C-BA0B-45D9-8F1A-4BE593B41BA8", "Warband 2"),
	WARBAND3(	"75136F02-4B80-4F71-8B3C-D5D0FA85BDCD", "Warband 3"),
	REACH_TEMPLE(	"92326A14-9C61-4DA2-8114-C3F6F43787C8", "Reach Temple"),
	CAPTURE_TEMPLE(	"013027EE-50D9-4CA0-8C10-1FC6BDFAE75D", "Capture Temple"),
	DEFEND_TEMPLE(	"3D862300-22FA-44D4-B9D2-097EB4E82C4A", "Defend Temple");
	
	private String uid;
	private String prettyName;
	
	FlameBattleEvent(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public String uid() {return uid;}
	public String toString() {return prettyName;}

	private static List<EventStatus> eventStatus = new ArrayList<>();
	
	public static List<EventStatus> getStatus() {
		return eventStatus;
	}	
	
	public static void formatFlameBattleString(EventData lowLevelEventData) {
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		for (ServerID servId:ServerID.values()) {
			formatFlameBattleString(lowLevelEventData, status, servId);
		}
		
		eventStatus = status;
	}

	public static void formatFlameBattleString(
			EventData lowLevelEventData, List<EventStatus> statusList,
			ServerID servId) {
		String outStatus = "";
		String color = "";
		DateTime time = null;
		int fontWeight = 400; 

		String warband1Id = servId.getUid()+"-"+WARBAND1.uid();
		String warband2Id = servId.getUid()+"-"+WARBAND2.uid();
		String warband3Id = servId.getUid()+"-"+WARBAND3.uid();
		String reachId = servId.getUid()+"-"+REACH_TEMPLE.uid();
		String captureId = servId.getUid()+"-"+CAPTURE_TEMPLE.uid();
		String defendId = servId.getUid()+"-"+DEFEND_TEMPLE.uid();
		
		String warband1Status = lowLevelEventData.getEventStatus(warband1Id);
		String warband2Status = lowLevelEventData.getEventStatus(warband2Id);
		String warband3Status = lowLevelEventData.getEventStatus(warband3Id);
		String reachStatus = lowLevelEventData.getEventStatus(reachId);
		String captureStatus = lowLevelEventData.getEventStatus(captureId);
		String defendStatus = lowLevelEventData.getEventStatus(defendId);
		
		boolean playSound = false;
		
		int warbandStatus = checkWarbandStatus(warband1Status, warband2Status, warband3Status);
		
		if ((defendStatus != null) &&
					(defendStatus.equals("Active") || defendStatus.equals("Preparation"))) {
			time = lowLevelEventData.getEventTime(defendId);
				
			outStatus = "Defend Temple";
			color = EventStateColor.ACTIVE.color();
			fontWeight = 900;
			
			playSound = true;
		} else if ((captureStatus != null) &&
				(captureStatus.equals("Active")  || captureStatus.equals("Preparation"))) {
			time = lowLevelEventData.getEventTime(captureId);
			
			outStatus = "Capture Temple";
			color = EventStateColor.ACTIVE.color();
			fontWeight = 900;
			
			playSound = true;
		} else if ((reachStatus != null) &&
				(reachStatus.equals("Active") || reachStatus.equals("Preparation"))) {
			time = lowLevelEventData.getEventTime(reachId);
			
			outStatus = "Reach Temple";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else if (warbandStatus != 0) {
			time = lowLevelEventData.getEventTime(warband1Id);
			
			outStatus = "Reunite "+warbandStatus+"/3";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else {
			time = lowLevelEventData.getEventTime(defendId);
			
			outStatus = "Not up";
			color = EventStateColor.FAIL.color();
		}
		
		String soundKey = servId.getUid()+"-flamebattle";
		boolean playSoundList = playSound;
		if (PlaySoundStatus.playSoundStatus.containsKey(soundKey) && PlaySoundStatus.playSoundStatus.get(soundKey)) {
			playSound = false;
		}
		
		PlaySoundStatus.playSoundStatus.put(soundKey, playSoundList);
		
		EventStringFormatter.generateEventString(statusList, servId, outStatus, color, fontWeight, time, "Flame Battle", Waypoint.FLAME_BATTLE.toString(), playSound);
	}

	private static int checkWarbandStatus(String warband1Status,
			String warband2Status, String warband3Status) {
		int count = 0;
		
		if ((warband1Status != null) &&
				(warband1Status.equals("Active") || warband1Status.equals("Preparation"))) {
			count++;
		} 
		
		if ((warband2Status != null) &&
				(warband2Status.equals("Active") || warband2Status.equals("Preparation"))) {
			count++;
		}  
		
		if ((warband3Status != null) &&
				(warband3Status.equals("Active") || warband3Status.equals("Preparation"))) {
			count++;
		} 
		
		return count;
	}
}
