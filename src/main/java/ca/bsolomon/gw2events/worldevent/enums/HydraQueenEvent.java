package ca.bsolomon.gw2events.worldevent.enums;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import ca.bsolomon.gw2events.worldevent.util.EventData;
import ca.bsolomon.gw2events.worldevent.util.EventStatus;
import ca.bsolomon.gw2events.worldevent.util.EventStringFormatter;

public enum HydraQueenEvent {

	LIONGUARD(	"D682ABC2-6B73-4C8E-A246-E9C23ED99153", "Lionguard Pre"),
	NORTHCANON(	"B6B7EE2A-AD6E-451B-9FE5-D5B0AD125BB2", "North Canon Pre"),
	SOUTHTOWER(	"189E7ABE-1413-4F47-858E-4612D40BF711", "South Tower Pre"),
	GALLEON(	"0E0801AF-28CF-4FF7-8064-BB2F4A816D23", "Galleon Pre"),
	TAIDHA(		"242BD241-E360-48F1-A8D9-57180E146789", "Active");
	
	private String uid;
	private String prettyName;
	
	HydraQueenEvent(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public String uid() {return uid;}
	public String toString() {return prettyName;}

	private static List<EventStatus> eventStatus = new ArrayList<>();
	
	public static List<EventStatus> getStatus() {
		return eventStatus;
	}	
	
	public static void formatHydraString(EventData lowLevelEventData) {
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		for (ServerID servId:ServerID.values()) {
			formatHydraString(lowLevelEventData, status, servId);
		}
			
		eventStatus = status;
	}

	public static void formatHydraString(
			EventData lowLevelEventData, List<EventStatus> statusList,
			ServerID servId) {
		String outStatus = "";
		String color = "";
		DateTime time = null;
		int fontWeight = 400;

		String lionguardEventId = servId.getUid()+"-"+LIONGUARD.uid();
		String northCanonEventId = servId.getUid()+"-"+NORTHCANON.uid();
		String sothTowerEventId = servId.getUid()+"-"+SOUTHTOWER.uid();
		String galleonEventId = servId.getUid()+"-"+GALLEON.uid();
		String taidhaEventId = servId.getUid()+"-"+TAIDHA.uid();
		
		String lionguardStatus = lowLevelEventData.getEventStatus(lionguardEventId);
		String northCanonStatus = lowLevelEventData.getEventStatus(northCanonEventId);
		String sothTowerStatus = lowLevelEventData.getEventStatus(sothTowerEventId);
		String galleonStatus = lowLevelEventData.getEventStatus(galleonEventId);
		String taidhaStatus = lowLevelEventData.getEventStatus(taidhaEventId);
		
		if ((taidhaStatus != null) &&
				(taidhaStatus.equals("Active"))) {
			time = lowLevelEventData.getEventTime(taidhaEventId);
			
			outStatus = "Active";
			color = EventStateColor.ACTIVE.color();
			fontWeight = 900;
		} else if ((galleonStatus != null) &&
				(galleonStatus.equals("Active") || galleonStatus.equals("Warmup"))) {
			time = lowLevelEventData.getEventTime(galleonEventId);
			
			outStatus = GALLEON.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else if ((sothTowerStatus != null) &&
				(sothTowerStatus.equals("Active") || sothTowerStatus.equals("Warmup"))) {
			time = lowLevelEventData.getEventTime(sothTowerEventId);
			
			outStatus = SOUTHTOWER.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else if ((northCanonStatus != null) &&
				(northCanonStatus.equals("Active") || northCanonStatus.equals("Warmup"))) {
			time = lowLevelEventData.getEventTime(northCanonEventId);
			
			outStatus = NORTHCANON.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else if ((lionguardStatus != null) &&
				(lionguardStatus.equals("Active") || lionguardStatus.equals("Warmup"))) {
			time = lowLevelEventData.getEventTime(lionguardEventId);
			
			outStatus = LIONGUARD.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else {
			time = lowLevelEventData.getEventTime(taidhaEventId);
			
			outStatus = "Not up";
			color = EventStateColor.FAIL.color();
		}
		
		EventStringFormatter.generateEventString(statusList, servId, outStatus, color, fontWeight, time, "Hydra Queen", Waypoint.HYDRA.toString());
	}
}
