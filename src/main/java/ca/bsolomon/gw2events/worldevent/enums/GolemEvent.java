package ca.bsolomon.gw2events.worldevent.enums;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import ca.bsolomon.gw2events.worldevent.util.EventStatus;
import ca.bsolomon.gw2events.worldevent.util.EventStringFormatter;
import ca.bsolomon.gw2events.worldevent.util.EventData;
import ca.bsolomon.gw2events.worldevent.util.PlaySoundStatus;

public enum GolemEvent {

	KELP(		"A7E0F553-C4E1-452F-B39F-7BDBEC8B0BB1", "Kelp Pre"),
	CONTAINER(	"3ED4FEB4-A976-4597-94E8-8BFD9053522F", "Containers Pre"),
	GOLEM(		"9AA133DC-F630-4A0E-BB5D-EE34A2B306C2", "Active");
	
	private String uid;
	private String prettyName;
	
	GolemEvent(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public String uid() {return uid;}
	public String toString() {return prettyName;}

	private static List<EventStatus> eventStatus = new ArrayList<>();
	
	public static List<EventStatus> getStatus() {
		return eventStatus;
	}
	
	public static void formatGolemString(EventData lowLevelEventData) {
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		for (ServerID servId:ServerID.values()) {
			formatGolemString(lowLevelEventData, status, servId);
		}
		
		eventStatus = status;
	}

	public static void formatGolemString(
			EventData lowLevelEventData, List<EventStatus> statusList,
			ServerID servId) {
		String outStatus = "";
		String color = "";
		DateTime time = null;
		int fontWeight = 400;

		String kelpEventId = servId.getUid()+"-"+KELP.uid();
		String containerEventId = servId.getUid()+"-"+CONTAINER.uid();
		String golemEventId = servId.getUid()+"-"+GOLEM.uid();
		
		String kelpStatus = lowLevelEventData.getEventStatus(kelpEventId);
		String containerStatus = lowLevelEventData.getEventStatus(containerEventId);
		String golemStatus = lowLevelEventData.getEventStatus(golemEventId);
		
		boolean playSound = false;
		
		if ((kelpStatus != null) &&
				(kelpStatus.equals("Active"))) {
			time = lowLevelEventData.getEventTime(kelpEventId);
			
			outStatus = KELP.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else if ((containerStatus != null) &&
				(containerStatus.equals("Active"))) {
			time = lowLevelEventData.getEventTime(containerEventId);
			
			playSound = true;
			
			outStatus = CONTAINER.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else if ((golemStatus != null) &&
				(golemStatus.equals("Warmup") || golemStatus.equals("Preparation"))) {
			time = lowLevelEventData.getEventTime(golemEventId);
			
			outStatus = "Arrival imminent";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else if ((golemStatus != null) &&
				(golemStatus.equals("Active"))) {
			time = lowLevelEventData.getEventTime(golemEventId);
			
			outStatus = "Active";
			color = EventStateColor.ACTIVE.color();
			fontWeight = 900;
		}  else {
			time = lowLevelEventData.getEventTime(golemEventId);
			
			outStatus = "Not up";
			color = EventStateColor.FAIL.color();
		}
		
		String soundKey = servId.getUid()+"-golem";
		boolean playSoundList = playSound;
		if (PlaySoundStatus.playSoundStatus.containsKey(soundKey) && PlaySoundStatus.playSoundStatus.get(soundKey)) {
			playSound = false;
		}
		
		PlaySoundStatus.playSoundStatus.put(soundKey, playSoundList);
		
		EventStringFormatter.generateEventString(statusList, servId, outStatus, color, fontWeight, time, "Golem MKII", Waypoint.GOLEM.toString(), playSound);
	}
}
