package ca.bsolomon.gw2events.worldevent.enums;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import ca.bsolomon.gw2events.worldevent.util.EventData;
import ca.bsolomon.gw2events.worldevent.util.EventStatus;
import ca.bsolomon.gw2events.worldevent.util.EventStringFormatter;

public enum JungleWurmEvent {

	ESCORT("613A7660-8F3A-4897-8FAC-8747C12E42F8", "Escort Gamarien Pre"),
	GROWTH1("456DD563-9FDA-4411-B8C7-4525F0AC4A6F", "Growth 1 Pre"),
	GROWTH2("CF6F0BB2-BD6C-4210-9216-F0A9810AA2BD", "Growth 2 Pre"),
	GRUB("1DCFE4AA-A2BD-44AC-8655-BBD508C505D1", "Grub Pre"),
	AVATARS("61BA7299-6213-4569-948B-864100F35E16", "Avatars Pre"),
	WURM("C5972F64-B894-45B4-BC31-2DEEA6B7C033", "Active");
	
	private String uid;
	private String prettyName;
	
	JungleWurmEvent(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public String uid() {return uid;}
	public String toString() {return prettyName;}
	
	private static List<EventStatus> eventStatus = new ArrayList<>();
	
	public static List<EventStatus> getStatus() {
		return eventStatus;
	}
	
	public static void formatJungleWurmString(EventData lowLevelEventData) {
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		for (ServerID servId:ServerID.values()) {
			formatJungleWurmString(lowLevelEventData, status, servId);
		}
		
		eventStatus = status;
	}

	public static void formatJungleWurmString(EventData lowLevelEventData,
			List<EventStatus> statusList, ServerID servId) {
		String outStatus = "";
		String color = "";
		DateTime time = null;
		int fontWeight = 400;

		String escortEventId = servId.getUid()+"-"+JungleWurmEvent.ESCORT.uid();
		String growth1EventId = servId.getUid()+"-"+JungleWurmEvent.GROWTH1.uid();
		String growth2EventId = servId.getUid()+"-"+JungleWurmEvent.GROWTH2.uid();
		String grubEventId = servId.getUid()+"-"+JungleWurmEvent.GRUB.uid();
		String avatarsEventId = servId.getUid()+"-"+JungleWurmEvent.AVATARS.uid();
		String wurmEventId = servId.getUid()+"-"+JungleWurmEvent.WURM.uid();
		
		String escortStatus = lowLevelEventData.getEventStatus(escortEventId);
		String growth1Status = lowLevelEventData.getEventStatus(growth1EventId);
		String growth2Status = lowLevelEventData.getEventStatus(growth2EventId);
		String grubStatus = lowLevelEventData.getEventStatus(grubEventId);
		String avatarsStatus = lowLevelEventData.getEventStatus(avatarsEventId);
		String wurmStatus = lowLevelEventData.getEventStatus(wurmEventId);
		
		if (escortStatus != null && (escortStatus.equals("Active") || escortStatus.equals("Preparation"))) {
			time = lowLevelEventData.getEventTime(escortEventId);
			
			outStatus = JungleWurmEvent.ESCORT.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else if ((growth1Status != null && growth2Status != null && grubStatus != null) &&
				(growth1Status.equals("Active") || growth2Status.equals("Active") || grubStatus.equals("Active"))) {
			time = lowLevelEventData.getEventTime(grubEventId);
			
			outStatus = "Grub and Growths Pre";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else if ((avatarsStatus != null) &&
				(avatarsStatus.equals("Active"))) {
			time = lowLevelEventData.getEventTime(avatarsEventId);
			
			outStatus = JungleWurmEvent.AVATARS.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else if ((wurmStatus != null) && (wurmStatus.equals("Active") || wurmStatus.equals("Preparation"))) {
			time = lowLevelEventData.getEventTime(wurmEventId);
			
			outStatus = JungleWurmEvent.WURM.toString();
			color = EventStateColor.ACTIVE.color();
			fontWeight = 900;
		} else {
			time = lowLevelEventData.getEventTime(wurmEventId);
			
			outStatus = "Not up";
			color = EventStateColor.FAIL.color();
		}
		
		EventStringFormatter.generateEventString(statusList, servId, outStatus, color, fontWeight, time, "Jungle Wurm", Waypoint.JUNGLEWURM.toString());
	}
}
