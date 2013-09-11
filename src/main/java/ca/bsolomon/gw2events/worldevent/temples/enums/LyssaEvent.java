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

public enum LyssaEvent {

	SEAL_DEF("590364E0-0053-4933-945E-21D396B10B20", "Defend Seal of Lyss"),
	CANON_DEF("B6D6D060-4974-4385-AB08-F641B6F32823", "Defend Canon"),
	GOLEM_DEF("F66922B5-B4BD-461F-8EC5-03327BD2B558", "Protect Golem"),
	CONTAIN_DEF("A3BEF1D9-10B0-44C7-8B4B-600BEC0F0316", "Container Defense"),
	RISEN_FORT("35997B10-179B-4E39-AD7F-54E131ECDD57", "Risen Fortification"),
	RISEN_FORT_DEF("F5436671-8934-4BD4-AEF7-4F3741A9CDA4", "Risen Fortification Defense"),
	SEAL_CORRUP("ADC3AA4C-0212-4AE6-98FA-4F59F3C9BCFA", "Seal of Union Corruption"),
	PRIESTESS("0372874E-59B7-4A8F-B535-2CF57B8E67E4", "1. Priestess");
	
	private String uid;
	private String prettyName;
	
	LyssaEvent(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public String uid() {return uid;}
	public String toString() {return prettyName;}
	
	private static List<EventStatus> status = new ArrayList<>();
	
	public static List<EventStatus> getStatus() {
		return status;
	}
	
	public static void formatLyssaString(EventData lowLevelEventData) {
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		for (ServerID servId:ServerID.values()) {
			formatLyssaString(lowLevelEventData, status, servId);
		}
		
		LyssaEvent.status = status;
	}
	
	public static void formatLyssaString(EventData templeEventData,
			List<EventStatus> statusList, ServerID servId) {
		String outStatus = "";
		String color = "";
		DateTime time = null;
		int fontWeight = 400; 

		String sealDefEventId = servId.getUid()+"-"+SEAL_DEF.uid();
		String cannonDefEventId = servId.getUid()+"-"+CANON_DEF.uid();
		String containerDefEventId = servId.getUid()+"-"+CONTAIN_DEF.uid();
		String golemDefEventId = servId.getUid()+"-"+GOLEM_DEF.uid();
		String priestessEventId = servId.getUid()+"-"+PRIESTESS.uid();
		String risenFortEventId = servId.getUid()+"-"+RISEN_FORT.uid();
		String risenFortDefEventId = servId.getUid()+"-"+RISEN_FORT_DEF.uid();
		String sealDefCorruptEventId = servId.getUid()+"-"+SEAL_CORRUP.uid();
		
		String sealDefStatus = templeEventData.getEventStatus(sealDefEventId);
		String cannonDefStatus = templeEventData.getEventStatus(cannonDefEventId);
		String containerStatus = templeEventData.getEventStatus(containerDefEventId);
		String golemDefStatus = templeEventData.getEventStatus(golemDefEventId);
		String priestessStatus = templeEventData.getEventStatus(priestessEventId);
		String risenFortStatus = templeEventData.getEventStatus(risenFortEventId);
		String risenFortDefStatus = templeEventData.getEventStatus(risenFortDefEventId);
		String sealDefCorruptStatus = templeEventData.getEventStatus(sealDefCorruptEventId);
		
		boolean playSound = false;
		
		if (priestessStatus!=null && priestessStatus.equals("Success")) {
			time = templeEventData.getEventTime(priestessEventId);
			
			outStatus = "0. Under Pact Control";
			color = EventStateColor.ACTIVE.color();
			fontWeight = 900;
		} else if (priestessStatus!=null && priestessStatus.equals("Active")) {
			int sealCount = 0;
			
			if ((containerStatus!=null && containerStatus.equals("Success"))
					|| (golemDefStatus!=null && golemDefStatus.equals("Success"))) {
				sealCount++;
			}
			
			if ((risenFortDefStatus!=null && risenFortDefStatus.equals("Success"))
					|| (risenFortStatus!=null && risenFortStatus.equals("Success"))
					|| (sealDefCorruptStatus!=null && sealDefCorruptStatus.equals("Success"))) {
				sealCount++;
			}
			
			if ((sealDefStatus!=null && sealDefStatus.equals("Success"))
					|| (cannonDefStatus!=null && cannonDefStatus.equals("Success"))) {
				sealCount++;
			}
			
			time = templeEventData.getEventTime(priestessEventId);
			
			if (sealCount == 3) {
				outStatus = "1. Seals: 3/3 controlled";
				color = EventStateColor.ACTIVE.color();
			} else {
				outStatus = "2. Seals: "+sealCount+"/3 controlled";
				color = EventStateColor.PREPARATION.color();
			}
			
			fontWeight = 900;
		} else {
			time = templeEventData.getEventTime(priestessEventId);
			
			outStatus = "Not up";
			color = EventStateColor.FAIL.color();
			fontWeight = 900;
		}
		
		String soundKey = servId.getUid()+"-lyssa";
		boolean playSoundList = playSound;
		if (PlaySoundStatus.playSoundStatus.containsKey(soundKey) && PlaySoundStatus.playSoundStatus.get(soundKey)) {
			playSound = false;
		}
		
		PlaySoundStatus.playSoundStatus.put(soundKey, playSoundList);
		
		EventStringFormatter.generateEventString(statusList, servId, outStatus, color, fontWeight, time, "Lyssa", Waypoint.LYSSA.toString(), playSound);
	}
}

