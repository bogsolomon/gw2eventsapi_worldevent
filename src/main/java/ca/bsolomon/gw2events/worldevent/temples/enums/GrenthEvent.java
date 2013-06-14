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

public enum GrenthEvent {

	ESCORT_GAZE("C2AB5C4C-5FAA-449B-985C-93F8E2D579C8", "Death Gaze Escort"),
	CANON_DEF1("B41C90F8-AF33-400E-9AD3-3DB0AFCEDC6C", "Canon Def"),
	ESCORT_JONES1("4B612C93-3700-43B8-B3C1-CBC64FEC0566", "Escort Jones DG"),
	SECURE_DG("1D1BE3D6-2F0D-4D1C-8233-812AAF261CFF", "Secure Death Gaze"),
	ESCORT_JONES2("C8139970-BE46-419B-B026-485A14002D44", "Escort Jones Cath"),
	PORTAL_SHADE("E16113B1-CE68-45BB-9C24-91523A663BCB", "Fight shades portals"),
	COVER_JONES("99254BA6-F5AE-4B07-91F1-61A9E7C51A51", "Jones Cleanse");
	//TEMPLE_DEF("57A8E394-092D-4877-90A5-C238E882C320", "Defense"), 
	//CANON_DEF2("27E2F73C-E26B-4046-AC06-72C442D9B2B7", "Canon Defense");
	
	
	private String uid;
	private String prettyName;
	
	GrenthEvent(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public String uid() {return uid;}
	public String toString() {return prettyName;}
	
	private static List<EventStatus> status = new ArrayList<>();
	
	public static List<EventStatus> getStatus() {
		return status;
	}
	
	public static void formatGrenthString(EventData lowLevelEventData) {
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		for (ServerID servId:ServerID.values()) {
			formatGrenthString(lowLevelEventData, status, servId);
		}
		
		GrenthEvent.status = status;
	}
	
	public static void formatGrenthString(EventData templeEventData,
			List<EventStatus> statusList, ServerID servId) {
		String outStatus = "";
		String color = "";
		DateTime time = null;
		int fontWeight = 400; 

		String canonDef1EventId = servId.getUid()+"-"+CANON_DEF1.uid();
		//String canonDef2EventId = servId.getUid()+"-"+CANON_DEF2.uid();
		String coverJonesEventId = servId.getUid()+"-"+COVER_JONES.uid();
		String escortDGEventId = servId.getUid()+"-"+ESCORT_GAZE.uid();
		String escortJones1EventId = servId.getUid()+"-"+ESCORT_JONES1.uid();
		String escortJones2EventId = servId.getUid()+"-"+ESCORT_JONES2.uid();
		String priestEventId = servId.getUid()+"-"+PORTAL_SHADE.uid();
		String secureDGEventId = servId.getUid()+"-"+SECURE_DG.uid();
		//String templeDefEventId = servId.getUid()+"-"+TEMPLE_DEF.uid();
		
		String canonDef1Status = templeEventData.getEventStatus(canonDef1EventId);
		//String canonDef2Status = templeEventData.getEventStatus(canonDef2EventId);
		String coverJonesStatus = templeEventData.getEventStatus(coverJonesEventId);
		String escortDGStatus = templeEventData.getEventStatus(escortDGEventId);
		String escortJones1Status = templeEventData.getEventStatus(escortJones1EventId);
		String escortJones2Status = templeEventData.getEventStatus(escortJones2EventId);
		String priestStatus = templeEventData.getEventStatus(priestEventId);
		String secureDGStatus = templeEventData.getEventStatus(secureDGEventId);
		//String templeDefStatus = templeEventData.getEventStatus(templeDefEventId);
		
		if (escortDGStatus!=null && (escortDGStatus.equals("Active"))) {
			time = templeEventData.getEventTime(escortDGEventId);
			
			outStatus = "Escort Pact to DG";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 900;
		} else if (canonDef1Status!=null && (canonDef1Status.equals("Active"))) {
			time = templeEventData.getEventTime(canonDef1EventId);
			
			outStatus = "Canon Build";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 900;
		} else if (escortJones1Status!=null && (escortJones1Status.equals("Active") || escortJones1Status.equals("Warmup") ||escortJones1Status.equals("Preparation"))) {
			time = templeEventData.getEventTime(escortJones1EventId);
			
			outStatus = "Escort Jones to DG";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 900;
		} else if (secureDGStatus!=null && (secureDGStatus.equals("Active") || secureDGStatus.equals("Warmup") || secureDGStatus.equals("Preparation"))) {
			time = templeEventData.getEventTime(secureDGEventId);
			
			outStatus = "Secure DG";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 900;
		} else if (escortJones2Status!=null && (escortJones2Status.equals("Active") || escortJones2Status.equals("Warmup") || escortJones2Status.equals("Preparation"))) {
			time = templeEventData.getEventTime(escortJones2EventId);
			
			outStatus = "Escort Jones Cathedral";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 900;
		} else if (priestStatus!=null && (priestStatus.equals("Active") || priestStatus.equals("Preparation"))) {
			time = templeEventData.getEventTime(priestEventId);
			
			outStatus = "Kill Priest";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 900;
		} else if (coverJonesStatus!=null && (coverJonesStatus.equals("Active") || coverJonesStatus.equals("Preparation") || coverJonesStatus.equals("Warmup"))) {
			time = templeEventData.getEventTime(coverJonesEventId);
			
			outStatus = "Protect Jones Ritual";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 900;
		} else if (coverJonesStatus!=null && (coverJonesStatus.equals("Success"))) {
			time = templeEventData.getEventTime(coverJonesEventId);
			
			outStatus = "Under Pact Control";
			color = EventStateColor.ACTIVE.color();
			fontWeight = 900;
		} else {
			time = templeEventData.getEventTime(priestEventId);
			
			outStatus = "Not up";
			color = EventStateColor.FAIL.color();
			fontWeight = 900;
		}
		
		EventStringFormatter.generateEventString(statusList, servId, outStatus, color, fontWeight, time, "Grenth", Waypoint.GRENTH.toString());
	}
}

