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
	QUEEN4("4CF7AA6E-4D84-48A6-A3D1-A91B94CCAD56", "Active"),
	
	SET1_DEF("8985FAD1-5B30-4BB9-9F23-C80F73BB3295", "SET1Def"),
	SET1_FAIL("129A1429-361A-4115-94DB-322659A0ABEF", "SET1Fail"),
	SET2_DEF("1A07A31D-90B6-4C94-B6FA-3B27504B188D", "SET2Def"),
	SET2_FAIL("C47E8F27-B9DF-4297-8802-1EC1A088D054", "SET2Fail"),
	SET3_DEF("F6563016-3832-4EBA-82F1-535BFF96422D", "SET3Def"),
	SET3_FAIL("B5021202-8B05-40AF-97C6-9C623EAB2956", "SET3Fail"),
	SET4_DEF("8C512F55-CF6F-4DDD-A80B-7DCBAD0F104C", "SET4Def"),
	SET4_FAIL("10F87D71-E2DA-4EA2-981D-15181CE0D349", "SET3Def");

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
		
		String set1DefId = servId.getUid()+"-"+SET1_DEF.uid();
		String set1FailId = servId.getUid()+"-"+SET1_FAIL.uid();
		String set2DefId = servId.getUid()+"-"+SET2_DEF.uid();
		String set2FailId = servId.getUid()+"-"+SET2_FAIL.uid();
		String set3DefId = servId.getUid()+"-"+SET3_DEF.uid();
		String set3FailId = servId.getUid()+"-"+SET3_FAIL.uid();
		String set4DefId = servId.getUid()+"-"+SET4_DEF.uid();
		String set4FailId = servId.getUid()+"-"+SET4_FAIL.uid();
		
		
		String queenStatus1 = lowLevelEventData.getEventStatus(queenId1);
		String queenStatus2 = lowLevelEventData.getEventStatus(queenId2);
		String queenStatus3 = lowLevelEventData.getEventStatus(queenId3);
		String queenStatus4 = lowLevelEventData.getEventStatus(queenId4);
		
		String set1DefStatus = lowLevelEventData.getEventStatus(set1DefId);
		String set1FailStatus = lowLevelEventData.getEventStatus(set1FailId);
		String set2DefStatus = lowLevelEventData.getEventStatus(set2DefId);
		String set2FailStatus = lowLevelEventData.getEventStatus(set2FailId);
		String set3DefStatus = lowLevelEventData.getEventStatus(set3DefId);
		String set3FailStatus = lowLevelEventData.getEventStatus(set3FailId);
		String set4DefStatus = lowLevelEventData.getEventStatus(set4DefId);
		String set4FailStatus = lowLevelEventData.getEventStatus(set4FailId);
		
		boolean playSound = false;
		
		int settlementCount = 0;
		
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
			 if (set1FailStatus.equals("Success") || set1DefStatus.equals("Success") || set1DefStatus.equals("Active")
					 || set1DefStatus.equals("Warmup") || set1DefStatus.equals("Preparation")) {
				 settlementCount++;
			 }
			 
			 if (set2FailStatus.equals("Success") || set2DefStatus.equals("Success")|| set2DefStatus.equals("Active")
					 || set2DefStatus.equals("Warmup") || set2DefStatus.equals("Preparation")) {
				 settlementCount++;
			 }
			 
			 if (set3FailStatus.equals("Success") || set3DefStatus.equals("Success") || set3DefStatus.equals("Active")
					 || set3DefStatus.equals("Warmup") || set3DefStatus.equals("Preparation")) {
				 settlementCount++;
			 }
			 
			 if (set4FailStatus.equals("Success") || set4DefStatus.equals("Success")|| set4DefStatus.equals("Active")
					 || set4DefStatus.equals("Warmup") || set4DefStatus.equals("Preparation")) {
				 settlementCount++;
			 }
			 
			 time = lowLevelEventData.getEventTime(queenId1);
			
			 outStatus = "Not up: "+settlementCount+"/4";
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
