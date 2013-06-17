package ca.bsolomon.gw2events.worldevent.enums;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import ca.bsolomon.gw2events.worldevent.util.EventData;
import ca.bsolomon.gw2events.worldevent.util.EventStatus;
import ca.bsolomon.gw2events.worldevent.util.EventStringFormatter;
import ca.bsolomon.gw2events.worldevent.util.PlaySoundStatus;

public enum KarkaEvent {

	QUEEN1("E1CC6E63-EFFE-4986-A321-95C89EA58C07", "Active"),
	QUEEN2("5282B66A-126F-4DA4-8E9D-0D9802227B6D", "Active"),
	QUEEN3("F479B4CF-2E11-457A-B279-90822511B53B", "Active"),
	QUEEN4("4CF7AA6E-4D84-48A6-A3D1-A91B94CCAD56", "Active");

	private String uid;
	private String prettyName;
	
	KarkaEvent(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public String uid() {return uid;}
	public String toString() {return prettyName;}
	
	private static List<EventStatus> eventStatus = new ArrayList<>();
	
	public static List<EventStatus> getStatus() {
		return eventStatus;
	}
	
	public static void formatKarkaString(EventData lowLevelEventData) {
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		for (ServerID servId:ServerID.values()) {
			formatKarkaString(lowLevelEventData, status, servId);
		}
			
		eventStatus = status;
	}
	
	public static void formatKarkaString(
			EventData lowLevelEventData, List<EventStatus> statusList,
			ServerID servId) {
		String outStatus = "";
		String color = "";
		DateTime time = null;
		int fontWeight = 400; 

		String queenId1 = servId.getUid()+"-"+QUEEN1.uid();
		String queenId2 = servId.getUid()+"-"+QUEEN2.uid();
		String queenId3 = servId.getUid()+"-"+QUEEN3.uid();
		String queenId4 = servId.getUid()+"-"+QUEEN4.uid();
		
		String queenStatus1 = lowLevelEventData.getEventStatus(queenId1);
		String queenStatus2 = lowLevelEventData.getEventStatus(queenId2);
		String queenStatus3 = lowLevelEventData.getEventStatus(queenId3);
		String queenStatus4 = lowLevelEventData.getEventStatus(queenId4);
		
		boolean playSound = false;
		
		if ((queenStatus1 != null) && (queenStatus1.equals("Active")) ||
				 (queenStatus2 != null) && (queenStatus2.equals("Active")) ||
				 (queenStatus3 != null) && (queenStatus3.equals("Active")) ||
				 (queenStatus4 != null) && (queenStatus4.equals("Active"))) {
			 time = lowLevelEventData.getEventTime(queenId1);
			
			 playSound = true;
			 
			 outStatus = "Active";
			 color = EventStateColor.ACTIVE.color();
			 fontWeight = 900;
		 }  else {
			 time = lowLevelEventData.getEventTime(queenId1);
			
			 outStatus = "Not up";
			 color = EventStateColor.FAIL.color();
		 }
		
		String soundKey = servId.getUid()+"-karka";
		boolean playSoundList = playSound;
		if (PlaySoundStatus.playSoundStatus.containsKey(soundKey) && PlaySoundStatus.playSoundStatus.get(soundKey)) {
			playSound = false;
		}
		
		PlaySoundStatus.playSoundStatus.put(soundKey, playSoundList);
		
		 EventStringFormatter.generateEventString(statusList, servId, outStatus, color, fontWeight, time, "Karka Queen", Waypoint.KARKA.toString(), playSound);
	}
}
