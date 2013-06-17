package ca.bsolomon.gw2events.worldevent.enums;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import ca.bsolomon.gw2events.worldevent.util.EventData;
import ca.bsolomon.gw2events.worldevent.util.EventStatus;
import ca.bsolomon.gw2events.worldevent.util.EventStringFormatter;
import ca.bsolomon.gw2events.worldevent.util.PlaySoundStatus;

public enum DragonEvent {

	JORMAG_CRYSTAL1("0CA3A7E3-5F66-4651-B0CB-C45D3F0CAD95", "Jormag Crystal"),
	JORMAG_CRYSTAL2("96D736C4-D2C6-4392-982F-AC6B8EF3B1C8", "Jormag Crystal"),
	JORMAG_CRYSTAL3("C957AD99-25E1-4DB0-9938-F54D9F23587B", "Jormag Crystal"),
	JORMAG_CRYSTAL4("429D6F3E-079C-4DE0-8F9D-8F75A222DB36", "Jormag Crystal"),
	JORMAG_CRYSTALF("BFD87D5B-6419-4637-AFC5-35357932AD2C", "Jormag Final Crystal"),
	JORMAG_UP("0464CB9E-1848-4AAA-BA31-4779A959DD71", "Jormag Up"),
	TEQUATL("568A30CF-8512-462F-9D67-647D69BEFAED", "Tequatl Up"),
	SHATTERER_UP("03BF176A-D59F-49CA-A311-39FC6F533F2F", "RageDragon Up"),
	SHATTERER_SIEGE("580A44EE-BAED-429A-B8BE-907A18E36189", "RageDragon Siege Collect"),
	SHATTERER_ESCORT("8E064416-64B5-4749-B9E2-31971AB41783", "RageDragon Escort");
	
	private String uid;
	private String prettyName;
	
	DragonEvent(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public String uid() {return uid;}
	public String toString() {return prettyName;}
	
	public static DragonEvent getEvent(String val) {
		if (val.equals("0CA3A7E3-5F66-4651-B0CB-C45D3F0CAD95")) {
			return JORMAG_CRYSTAL1;
		} else if (val.equals("96D736C4-D2C6-4392-982F-AC6B8EF3B1C8")) {
			return JORMAG_CRYSTAL2;
		} else if (val.equals("C957AD99-25E1-4DB0-9938-F54D9F23587B")) {
			return JORMAG_CRYSTAL3;
		} else if (val.equals("429D6F3E-079C-4DE0-8F9D-8F75A222DB36")) {
			return JORMAG_CRYSTAL4;
		} else if (val.equals("BFD87D5B-6419-4637-AFC5-35357932AD2C")) {
			return JORMAG_CRYSTALF;
		} else if (val.equals("0464CB9E-1848-4AAA-BA31-4779A959DD71")) {
			return JORMAG_UP;
		} else if (val.equals("568A30CF-8512-462F-9D67-647D69BEFAED")) {
			return TEQUATL;
		} else if (val.equals("03BF176A-D59F-49CA-A311-39FC6F533F2F")) {
			return SHATTERER_UP;
		} else if (val.equals("580A44EE-BAED-429A-B8BE-907A18E36189")) {
			return SHATTERER_SIEGE;
		} else if (val.equals("8E064416-64B5-4749-B9E2-31971AB41783")) {
			return SHATTERER_ESCORT;
		}
		
		 throw new IllegalArgumentException("Incorrect Event UID");
	}
	
	private static List<EventStatus> teqStatus = new ArrayList<>();
	private static List<EventStatus> shatStatus = new ArrayList<>();
	private static List<EventStatus> jorStatus = new ArrayList<>();
	
	public static List<EventStatus> getTeqStatus() {
		return teqStatus;
	}
	
	public static List<EventStatus> getShatStatus() {
		return shatStatus;
	}
	
	public static List<EventStatus> getJorStatus() {
		return jorStatus;
	}
	
	public static void formatTequatlString(EventData data) {
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		for (ServerID servId:ServerID.values()) {
			formatTequatlString(data, status, servId);
		}
		
		teqStatus = status;
	}

	public static void formatTequatlString(EventData data, List<EventStatus> statusList,
			ServerID servId) {
		String composedEventId = servId.getUid()+"-"+DragonEvent.TEQUATL.uid();
		
		String status = data.getEventStatus(composedEventId);
		DateTime time = data.getEventTime(composedEventId);
		
		String outStatus = "";
		String color = "";
		int fontWeight = 400;
		boolean playSound = false;
		
		if (status==null) {
			color = EventStateColor.FAIL.color();
			outStatus = "Not up";
		} else if (status.equals("Active")) {
			color = EventStateColor.ACTIVE.color();
			fontWeight = 900;
			outStatus = status;
		} else if (status.equals("Preparation")) {
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
			outStatus = "Something in water";
			playSound = true;
		} else {
			color = EventStateColor.FAIL.color();
			outStatus = "Not up";
		}
		
		String soundKey = servId.getUid()+"-teq";
		boolean playSoundList = playSound;
		if (PlaySoundStatus.playSoundStatus.containsKey(soundKey) && PlaySoundStatus.playSoundStatus.get(soundKey)) {
			playSound = false;
		}
		
		PlaySoundStatus.playSoundStatus.put(soundKey, playSoundList);
		
		EventStringFormatter.generateEventString(statusList, servId, outStatus, color, fontWeight, time, "Tequatl", Waypoint.TEQUATL.toString(), playSound);
	}

	public static void formatShattererString(EventData dragonData) {
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		for (ServerID servId:ServerID.values()) {
			formatShattererString(dragonData, status, servId);
		}
		
		shatStatus = status;
	}

	public static void formatShattererString(EventData dragonData,
			List<EventStatus> statusList, ServerID servId) {
		String outStatus = "";
		String color = "";
		DateTime time = null;
		int fontWeight = 400;
		
		String escortEventId = servId.getUid()+"-"+DragonEvent.SHATTERER_ESCORT.uid();
		String siegeEventId = servId.getUid()+"-"+DragonEvent.SHATTERER_SIEGE.uid();
		String shatEventId = servId.getUid()+"-"+DragonEvent.SHATTERER_UP.uid();
		
		String escortStatus = dragonData.getEventStatus(escortEventId);
		String siegeStatus = dragonData.getEventStatus(siegeEventId);
		String shatStatus = dragonData.getEventStatus(shatEventId);
		
		boolean playSound = false;
		
		if (escortStatus!=null && escortStatus.equals("Active")) {
			time = dragonData.getEventTime(escortEventId);
			
			if (siegeStatus.equals("Active")) {
				outStatus = "Escort and Collection Pre";
			} else {
				outStatus = "Escort Pre";
			}
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
			
			playSound = true;
		} else if (siegeStatus!=null && siegeStatus.equals("Active")) {
			time = dragonData.getEventTime(siegeEventId);
			
			outStatus = "Collection Pre";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else if (shatStatus!=null && shatStatus.equals("Active")) {
			time = dragonData.getEventTime(shatEventId);
			
			outStatus = "Active";
			color = EventStateColor.ACTIVE.color();
			fontWeight = 900;
		} else if (escortStatus!=null && siegeStatus!=null && 
				escortStatus.equals("Success") && siegeStatus.equals("Success")) {
			time = dragonData.getEventTime(siegeEventId);
			
			outStatus = "Ominous Winds";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 900;
		} else {
			time = dragonData.getEventTime(shatEventId);
			
			outStatus = "Not up";
			color = EventStateColor.FAIL.color();
		}
		
		String soundKey = servId.getUid()+"-shat";
		boolean playSoundList = playSound;
		if (PlaySoundStatus.playSoundStatus.containsKey(soundKey) && PlaySoundStatus.playSoundStatus.get(soundKey)) {
			playSound = false;
		}
		
		PlaySoundStatus.playSoundStatus.put(soundKey, playSoundList);
		
		EventStringFormatter.generateEventString(statusList, servId, outStatus, color, fontWeight, time, "RageDragon", Waypoint.SHATERRER.toString(), playSound);
	}

	public static void formatJormagString(EventData dragonData) {
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		for (ServerID servId:ServerID.values()) {
			formatJormagString(dragonData, status, servId);
		}
		
		jorStatus = status;
	}

	public static void formatJormagString(EventData dragonData,
			List<EventStatus> statusList, ServerID servId) {
		String outStatus = "";
		String color = "";
		DateTime time = null;
		int fontWeight = 400;
		
		String crystal1EventId = servId.getUid()+"-"+DragonEvent.JORMAG_CRYSTAL1.uid();
		String crystal2EventId = servId.getUid()+"-"+DragonEvent.JORMAG_CRYSTAL2.uid();
		String crystal3EventId = servId.getUid()+"-"+DragonEvent.JORMAG_CRYSTAL3.uid();
		String crystal4EventId = servId.getUid()+"-"+DragonEvent.JORMAG_CRYSTAL4.uid();
		String crystalFEventId = servId.getUid()+"-"+DragonEvent.JORMAG_CRYSTALF.uid();
		String jormagEventId = servId.getUid()+"-"+DragonEvent.JORMAG_UP.uid();
		
		String crystal1Status = dragonData.getEventStatus(crystal1EventId);
		String crystal2Status = dragonData.getEventStatus(crystal2EventId);
		String crystal3Status = dragonData.getEventStatus(crystal3EventId);
		String crystal4Status = dragonData.getEventStatus(crystal4EventId);
		String crystalFStatus = dragonData.getEventStatus(crystalFEventId);
		String jormagStatus = dragonData.getEventStatus(jormagEventId);
		
		boolean playSound = false;
		
		if ((crystal1Status != null && crystal2Status != null
				&& crystal3Status != null && crystal4Status != null) && 
				(crystal1Status.equals("Active") || crystal2Status.equals("Active") ||
				crystal3Status.equals("Active") || crystal4Status.equals("Active"))) {
			time = dragonData.getEventTime(crystal1EventId);
			
			outStatus = "Four Crystals Pre";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else if (crystalFStatus != null && (crystalFStatus.equals("Active") || crystalFStatus.equals("Preparation"))) {
			time = dragonData.getEventTime(crystalFEventId);
			
			outStatus = "Final Crystal Pre";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else if (jormagStatus != null && jormagStatus.equals("Preparation")) {
			time = dragonData.getEventTime(jormagEventId);
			
			outStatus = "About to Land";
			color = EventStateColor.ACTIVE.color();
			fontWeight = 900;
			
			playSound = true;
		} else if (jormagStatus != null && jormagStatus.equals("Active")) {
			time = dragonData.getEventTime(jormagEventId);
			
			outStatus = "Active";
			color = EventStateColor.ACTIVE.color();
			fontWeight = 900;
		} else {
			time = dragonData.getEventTime(jormagEventId);
			
			outStatus = "Not up";
			color = EventStateColor.FAIL.color();	
		}
		
		String soundKey = servId.getUid()+"-jor";
		boolean playSoundList = playSound;
		if (PlaySoundStatus.playSoundStatus.containsKey(soundKey) && PlaySoundStatus.playSoundStatus.get(soundKey)) {
			playSound = false;
		}
		
		PlaySoundStatus.playSoundStatus.put(soundKey, playSoundList);
		
		EventStringFormatter.generateEventString(statusList, servId, outStatus, color, fontWeight, time, "Jormag", Waypoint.JORMAG.toString(), playSound);
	}
}
