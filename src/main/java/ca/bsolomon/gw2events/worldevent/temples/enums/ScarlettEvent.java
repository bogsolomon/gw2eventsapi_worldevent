package ca.bsolomon.gw2events.worldevent.temples.enums;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import ca.bsolomon.gw2events.worldevent.enums.EventStateColor;
import ca.bsolomon.gw2events.worldevent.enums.ServerID;
import ca.bsolomon.gw2events.worldevent.util.EventData;
import ca.bsolomon.gw2events.worldevent.util.EventStatus;
import ca.bsolomon.gw2events.worldevent.util.EventStringFormatter;
import ca.bsolomon.gw2events.worldevent.util.PlaySoundStatus;

public enum ScarlettEvent {

	MOUNT_MAEL(	"FE5F6233-DAD6-4C63-921D-F132DFCF3397", "Maelstrom"),
	TIMBER(		"6195E248-1DD4-452B-A7DD-3472162E0683", "Timberline"),
	HARATHI(	"9795C994-4C12-4E1F-82A6-D541F76D9D37", "Harathi"),
	LORNAR(		"6DEB01AE-675E-4FF9-9789-53CB73FC621E", "Lornar"),
	SPARKFLY(	"92979945-63A4-42D7-8AE5-1EFADC9E636F", "Sparkfly"),
	BLOODTIDE(	"11442531-6B20-411F-B0A6-D2A2C31DD668", "Bloodtide"),
	DREDGEHAUNT("526EFDC9-3F3C-492E-911E-14AFE9EAE70D", "Dredgehaunt"),
	BLAZERIDGE(	"CF657BC1-D5CE-41D3-A630-A3E509451B7A", "Blazeridge"),
	RUIN(		"C7CC535C-81A1-4E84-993B-6384C911399A", "Fields of Ruin"),
	MARCHES(	"D1B8B6D2-5E61-44DE-92C6-D49A9BBBB6E2", "Iron Marches"),
	FROST(		"46BBCFDD-1285-4246-A9FA-620773C7D4C6", "Frostgorge"),
	FIRE(		"5FE50E83-758B-4573-A424-A1661FBC970A", "Fireheart"),
	GENDARREN(	"90FEBBE9-0066-42CF-9C48-703C920AFB9D", "Gendarran");
	
	private String uid;
	private String prettyName;
	
	ScarlettEvent(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public String uid() {return uid;}
	public String toString() {return prettyName;}
	
	private static List<EventStatus> status = new ArrayList<>();
	
	public static List<EventStatus> getStatus() {
		return status;
	}
	
	public static void formatScarlettString(EventData lowLevelEventData) {
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		for (ServerID servId:ServerID.values()) {
			formatScarlettString(lowLevelEventData, status, servId);
		}
		
		ScarlettEvent.status = status;
	}
	
	public static void formatScarlettString(EventData templeEventData,
			List<EventStatus> statusList, ServerID servId) {
		String outStatus = "";
		String color = "";
		DateTime time = null;
		int fontWeight = 400; 

		boolean playSound = false;
		
		DateTime lastTime = null;
		
		for (ScarlettEvent eventId:ScarlettEvent.values()) {
			String servEventId = servId.getUid()+"-"+eventId.uid();
			String servEventStatus = templeEventData.getEventStatus(servEventId);
			
			if (servEventStatus!=null && (servEventStatus.equals("Active"))) {
				time = templeEventData.getEventTime(servEventId);
				
				if ((lastTime != null && lastTime.isBefore(time)) || lastTime == null) {
					lastTime = time;
				}
				
				outStatus = eventId.toString();
				color = EventStateColor.ACTIVE.color();
				fontWeight = 900;
				
				playSound = true;
				
				break;
			}
			
			time = lastTime;
			
			outStatus = "Not up";
			color = EventStateColor.FAIL.color();
			fontWeight = 900;
		}
		
		String soundKey = servId.getUid()+"-scarlett";
		boolean playSoundList = playSound;
		if (PlaySoundStatus.playSoundStatus.containsKey(soundKey) && PlaySoundStatus.playSoundStatus.get(soundKey)) {
			playSound = false;
		}
		
		PlaySoundStatus.playSoundStatus.put(soundKey, playSoundList);
		
		EventStringFormatter.generateEventString(statusList, servId, outStatus, color, fontWeight, time, "Scarlett", "", playSound);
	}
}

