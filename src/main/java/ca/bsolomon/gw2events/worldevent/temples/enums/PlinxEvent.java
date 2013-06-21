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

public enum PlinxEvent {

	HAZMAT("7A5F71F1-87C1-476B-95EC-F5BD95852A4A", "Hazmat Suite"),
	SIGNAL_ESCORT("827EFF31-60EF-4B92-8441-0F1BEA923E5D", "Signal Escort"),
	CLEAR_LZ("C918FDCB-FB3D-424F-A47C-82C0D04A0670", "Clear LZ"),
	SUPPLIES("4DC90E66-60BC-4C95-9986-1133AD000E4E", "Supplies"),
	CHAMP_ABOM("2BC86A2C-3EB9-4A33-BE34-83E525E57A1A", "Champion Abomination");
	
	private String uid;
	private String prettyName;
	
	PlinxEvent(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public String uid() {return uid;}
	public String toString() {return prettyName;}
	
	private static List<EventStatus> status = new ArrayList<>();
	
	public static List<EventStatus> getStatus() {
		return status;
	}
	
	public static void formatPlinxString(EventData lowLevelEventData) {
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		for (ServerID servId:ServerID.values()) {
			formatPlinxString(lowLevelEventData, status, servId);
		}
		
		PlinxEvent.status = status;
	}
	
	public static void formatPlinxString(EventData templeEventData,
			List<EventStatus> statusList, ServerID servId) {
		String outStatus = "";
		String color = "";
		DateTime time = null;
		int fontWeight = 400; 

		String hazmatRepairEventId = servId.getUid()+"-"+HAZMAT.uid();
		String singalEscortEventId = servId.getUid()+"-"+SIGNAL_ESCORT.uid();
		String clearLZEventId = servId.getUid()+"-"+CLEAR_LZ.uid();
		String suppliesEscortEventId = servId.getUid()+"-"+SUPPLIES.uid();
		String champAbomEventId = servId.getUid()+"-"+CHAMP_ABOM.uid();
		
		String hazmatRepairEscortStatus = templeEventData.getEventStatus(hazmatRepairEventId);
		String singalEscortStatus = templeEventData.getEventStatus(singalEscortEventId);
		String clearLZStatus = templeEventData.getEventStatus(clearLZEventId);
		String suppliesEscortStatus = templeEventData.getEventStatus(suppliesEscortEventId);
		String champAbomStatus = templeEventData.getEventStatus(champAbomEventId);
		
		boolean playSound = false;
		
		if (champAbomStatus!=null && (champAbomStatus.equals("Active"))) {
			time = templeEventData.getEventTime(champAbomEventId);
			
			outStatus = "Kill Abomination";
			color = EventStateColor.ACTIVE.color();
			fontWeight = 900;
		} else if (suppliesEscortStatus!=null && (suppliesEscortStatus.equals("Active"))) {
			time = templeEventData.getEventTime(suppliesEscortEventId);
			
			outStatus = "Escort Supplies";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 900;
		} else if (clearLZStatus!=null && (clearLZStatus.equals("Active"))) {
			time = templeEventData.getEventTime(clearLZEventId);
			
			outStatus = "Clear LZ";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 900;
		} else if (singalEscortStatus!=null && (singalEscortStatus.equals("Active"))) {
			time = templeEventData.getEventTime(singalEscortEventId);
			
			outStatus = "Escort Plinx to LZ";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 900;
		} else if (hazmatRepairEscortStatus!=null && (hazmatRepairEscortStatus.equals("Active") ||
				hazmatRepairEscortStatus.equals("Preparation") || hazmatRepairEscortStatus.equals("Warmup"))) {
			time = templeEventData.getEventTime(hazmatRepairEventId);
			
			outStatus = "Repair Hazmat";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 900;
		} else {
			time = templeEventData.getEventTime(champAbomEventId);
			
			outStatus = "Not up";
			color = EventStateColor.FAIL.color();
			fontWeight = 900;
		}
		
		String soundKey = servId.getUid()+"-plinx";
		boolean playSoundList = playSound;
		if (PlaySoundStatus.playSoundStatus.containsKey(soundKey) && PlaySoundStatus.playSoundStatus.get(soundKey)) {
			playSound = false;
		}
		
		PlaySoundStatus.playSoundStatus.put(soundKey, playSoundList);
		
		EventStringFormatter.generateEventString(statusList, servId, outStatus, color, fontWeight, time, "Plinx", Waypoint.PLINX.toString(), playSound);
	}
}

