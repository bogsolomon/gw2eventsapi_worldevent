package ca.bsolomon.gw2events.worldevent.enums;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import ca.bsolomon.gw2events.worldevent.util.EventData;
import ca.bsolomon.gw2events.worldevent.util.EventStatus;
import ca.bsolomon.gw2events.worldevent.util.EventStringFormatter;
import ca.bsolomon.gw2events.worldevent.util.PlaySoundStatus;

public enum ShadowBehemothEvent {

	PORTAL_MON(  "E539A5E3-A33B-4D5F-AEED-197D2716F79B", "Monastery"),
	PORTAL_WOOD( "CFBC4A8C-2917-478A-9063-1A8B43CC8C38", "Wood"),
	PORTAL_HILL( "AFCF031A-F71D-4CEA-85E1-957179414B25", "Hill"),
	PORTAL_SWAMP("36330140-7A61-4708-99EB-010B10420E39", "Final"),
	SB(			 "31CEBA08-E44D-472F-81B0-7143D73797F5", "Active");
	
	private String uid;
	private String prettyName;
	
	ShadowBehemothEvent(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public String uid() {return uid;}
	public String toString() {return prettyName;}
	
	private static boolean swampPortalsDestroyed = false;
	
	private static List<EventStatus> eventStatus = new ArrayList<>();
	
	public static List<EventStatus> getStatus() {
		return eventStatus;
	}
	
	public static void formatShadowBehemothString(EventData lowLevelEventData) {
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		for (ServerID servId:ServerID.values()) {
			formatShadowBehemothString(lowLevelEventData, status, servId);
		}
		
		eventStatus = status;
	}

	public static void formatShadowBehemothString(EventData lowLevelEventData,
			List<EventStatus> statusList, ServerID servId) {
		String outStatus = "";
		String color = "";
		DateTime time = null;
		int fontWeight = 400;

		String hillEventId = servId.getUid()+"-"+PORTAL_HILL.uid();
		String monEventId = servId.getUid()+"-"+PORTAL_MON.uid();
		String woodEventId = servId.getUid()+"-"+PORTAL_WOOD.uid();
		String swampEventId = servId.getUid()+"-"+PORTAL_SWAMP.uid();
		String sbEventId = servId.getUid()+"-"+SB.uid();
		
		String hillStatus = lowLevelEventData.getEventStatus(hillEventId);
		String monStatus = lowLevelEventData.getEventStatus(monEventId);
		String woodStatus = lowLevelEventData.getEventStatus(woodEventId);
		String swampStatus = lowLevelEventData.getEventStatus(swampEventId);
		String sbStatus = lowLevelEventData.getEventStatus(sbEventId);
		
		boolean playSound = false;
		
		if ((hillStatus != null && monStatus != null && woodStatus != null) &&
				(hillStatus.equals("Active") || monStatus.equals("Active") || woodStatus.equals("Active")
						|| hillStatus.equals("Warmup") || monStatus.equals("Warmup") || woodStatus.equals("Warmup"))) {
			time = lowLevelEventData.getEventTime(hillEventId);
			
			outStatus = "Outside Portals Pre";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
			
			playSound = true;
		} else if ((swampStatus != null) &&
				(swampStatus.equals("Active"))) {
			time = lowLevelEventData.getEventTime(swampEventId);
			
			outStatus = "Inside Portals Pre";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
			
			swampPortalsDestroyed = true;
		}  else if ((sbStatus != null) &&
				(sbStatus.equals("Active") || (sbStatus.equals("Preparation") && swampPortalsDestroyed))) {
			time = lowLevelEventData.getEventTime(sbEventId);
			
			outStatus = "Active";
			color = EventStateColor.ACTIVE.color();
			fontWeight = 900;
		} else {
			time = lowLevelEventData.getEventTime(sbEventId);
			
			outStatus = "Not up";
			color = EventStateColor.FAIL.color();
			
			swampPortalsDestroyed = false;
		}
		
		String soundKey = servId.getUid()+"-sb";
		boolean playSoundList = playSound;
		if (PlaySoundStatus.playSoundStatus.containsKey(soundKey) && PlaySoundStatus.playSoundStatus.get(soundKey)) {
			playSound = false;
		}
		
		PlaySoundStatus.playSoundStatus.put(soundKey, playSoundList);
		
		EventStringFormatter.generateEventString(statusList, servId, outStatus, color, fontWeight, time, "SB", Waypoint.SB.toString(), playSound);
	}
}
