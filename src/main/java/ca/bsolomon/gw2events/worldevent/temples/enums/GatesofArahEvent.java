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

public enum GatesofArahEvent {

	ESCORT_WARMASTER("D7246CA2-DD85-42B3-A8D3-D2A1FE464ECF", "6. Escort Warmaster"),
	SECURE_ANCH	(	 "80F7CC11-3116-42B5-A7C3-965EE5A69E51", "5. Secure Anchorage"),
	PREPARE1	(	 "9DA0E1E8-1A44-4A3C-9FCC-257350978CE9", "4. Prepare Assault"),
	PREPARE2	(    "6B5C8659-F3AF-4DFC-A6F5-CD6620E3BE11", "4. Prepare Assault"),
	PREPARE3	(    "7EA1BE90-C3CB-4598-A2DD-D56764785F7D", "4. Prepare Assault"),
	SEIZE_STEPS	(    "E87A021D-4E7C-4A50-BEDB-6F5A54C90A9A", "3. Seize steps"),
	CAPTURE	(    	 "B1B94EFD-4F67-4716-97C2-880CD16F1297", "2. Capture Hall"),
	WIZARD	(    	 "6B5C8659-F3AF-4DFC-A6F5-CD6620E3BE11", "1. KILL wizard");
	
	
	private String uid;
	private String prettyName;
	
	GatesofArahEvent(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public String uid() {return uid;}
	public String toString() {return prettyName;}
	
	private static List<EventStatus> status = new ArrayList<>();
	
	public static List<EventStatus> getStatus() {
		return status;
	}
	
	public static void formatArahString(EventData lowLevelEventData) {
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		for (ServerID servId:ServerID.values()) {
			formatArahString(lowLevelEventData, status, servId);
		}
		
		GatesofArahEvent.status = status;
	}
	
	public static void formatArahString(EventData templeEventData,
			List<EventStatus> statusList, ServerID servId) {
		String outStatus = "";
		String color = "";
		DateTime time = null;
		int fontWeight = 400; 

		String escortEventId = servId.getUid()+"-"+ESCORT_WARMASTER.uid();
		String anchorEventId = servId.getUid()+"-"+SECURE_ANCH.uid();
		String prep1EventId = servId.getUid()+"-"+PREPARE1.uid();
		String prep2EventId = servId.getUid()+"-"+PREPARE2.uid();
		String prep3EventId = servId.getUid()+"-"+PREPARE3.uid();
		String seizeEventId = servId.getUid()+"-"+SEIZE_STEPS.uid();
		String captureEventId = servId.getUid()+"-"+CAPTURE.uid();
		String wizardEventId = servId.getUid()+"-"+WIZARD.uid();
				
		String escortEventStatus = templeEventData.getEventStatus(escortEventId);
		String anchorEventStatus = templeEventData.getEventStatus(anchorEventId);
		String prep1EventStatus = templeEventData.getEventStatus(prep1EventId);
		String prep2EventStatus = templeEventData.getEventStatus(prep2EventId);
		String prep3EventStatus = templeEventData.getEventStatus(prep3EventId);
		String seizeEventStatus = templeEventData.getEventStatus(seizeEventId);
		String captureEventStatus = templeEventData.getEventStatus(captureEventId);
		String wizardEventStatus = templeEventData.getEventStatus(wizardEventId);
		
		int prepStatus = checkPreparationStatus(prep1EventStatus, prep2EventStatus, prep3EventStatus);
		
		boolean playSound = false;
		
		if (wizardEventStatus!=null && (wizardEventStatus.equals("Active"))) {
			time = templeEventData.getEventTime(wizardEventId);
			
			outStatus = WIZARD.toString();
			color = EventStateColor.ACTIVE.color();
			fontWeight = 900;
		} else if (captureEventStatus!=null && (captureEventStatus.equals("Preparation") || captureEventStatus.equals("Active"))) {
			time = templeEventData.getEventTime(captureEventId);
			
			outStatus = CAPTURE.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
			
			playSound = true;
		} else if (seizeEventStatus!=null && (seizeEventStatus.equals("Preparation") || seizeEventStatus.equals("Active"))) {
			time = templeEventData.getEventTime(seizeEventId);
			
			outStatus = SEIZE_STEPS.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
			
			playSound = true;
		} else if (prepStatus != 0) {
			time = templeEventData.getEventTime(prep3EventId);
			
			outStatus = PREPARE1.toString()+prepStatus+"/3";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else if (anchorEventStatus!=null && (anchorEventStatus.equals("Preparation") || anchorEventStatus.equals("Active"))) {
			time = templeEventData.getEventTime(anchorEventId);
			
			outStatus = SECURE_ANCH.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
			
			playSound = true;
		} else if (escortEventStatus!=null && (escortEventStatus.equals("Preparation") || escortEventStatus.equals("Active"))) {
			time = templeEventData.getEventTime(escortEventId);
			
			outStatus = ESCORT_WARMASTER.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
			
			playSound = true;
		} else {
			time = templeEventData.getEventTime(wizardEventId);
			
			outStatus = "Not up";
			color = EventStateColor.FAIL.color();
			fontWeight = 900;
		}
		
		String soundKey = servId.getUid()+"-arah";
		boolean playSoundList = playSound;
		if (PlaySoundStatus.playSoundStatus.containsKey(soundKey) && PlaySoundStatus.playSoundStatus.get(soundKey)) {
			playSound = false;
		}
		
		PlaySoundStatus.playSoundStatus.put(soundKey, playSoundList);
		
		EventStringFormatter.generateEventString(statusList, servId, outStatus, color, fontWeight, time, "Arah", Waypoint.ARAH.toString(), playSound);
	}

	private static int checkPreparationStatus(String prep1EventStatus,
			String prep2EventStatus, String prep3EventStatus) {
		int count = 0;
		
		if ((prep1EventStatus != null) &&
				(prep1EventStatus.equals("Active") || prep1EventStatus.equals("Preparation"))) {
			count++;
		} 
		
		if ((prep2EventStatus != null) &&
				(prep2EventStatus.equals("Active") || prep2EventStatus.equals("Preparation"))) {
			count++;
		}  
		
		if ((prep3EventStatus != null) &&
				(prep3EventStatus.equals("Active") || prep3EventStatus.equals("Preparation"))) {
			count++;
		} 
		
		return count;
	}
}

