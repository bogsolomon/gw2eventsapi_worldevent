package ca.bsolomon.gw2events.worldevent.enums;

import org.joda.time.DateTime;

import ca.bsolomon.gw2events.worldevent.util.EventData;
import ca.bsolomon.gw2events.worldevent.util.EventStringFormatter;

public enum DragonEvent {

	JORMAG_CRYSTAL1("0CA3A7E3-5F66-4651-B0CB-C45D3F0CAD95", "Jormag Crystal"),
	JORMAG_CRYSTAL2("96D736C4-D2C6-4392-982F-AC6B8EF3B1C8", "Jormag Crystal"),
	JORMAG_CRYSTAL3("C957AD99-25E1-4DB0-9938-F54D9F23587B", "Jormag Crystal"),
	JORMAG_CRYSTAL4("429D6F3E-079C-4DE0-8F9D-8F75A222DB36", "Jormag Crystal"),
	JORMAG_CRYSTALF("BFD87D5B-6419-4637-AFC5-35357932AD2C", "Jormag Final Crystal"),
	JORMAG_UP("0464CB9E-1848-4AAA-BA31-4779A959DD71", "Jormag Up"),
	TEQUATL("568A30CF-8512-462F-9D67-647D69BEFAED", "Tequatl Up"),
	SHATTERER_UP("03BF176A-D59F-49CA-A311-39FC6F533F2F", "Shaterrer Up"),
	SHATTERER_SIEGE("580A44EE-BAED-429A-B8BE-907A18E36189", "Shaterrer Siege Collect"),
	SHATTERER_ESCORT("8E064416-64B5-4749-B9E2-31971AB41783", "Shaterrer Escort");
	
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
	
	public static String formatTequatlString(EventData data) {
		StringBuffer sb = new StringBuffer();
		
		for (ServerID servId:ServerID.values()) {
			formatTequatlString(data, sb, servId);
		}
		
		return sb.toString();
	}

	public static void formatTequatlString(EventData data, StringBuffer sb,
			ServerID servId) {
		String composedEventId = servId.uid()+"-"+DragonEvent.TEQUATL.uid();
		
		String status = data.getEventStatus(composedEventId);
		DateTime time = data.getEventTime(composedEventId);
		
		String outStatus = "";
		String color = "";
		int fontWeight = 400;
		
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
		} else {
			color = EventStateColor.FAIL.color();
			outStatus = "Not up";
		}
		
		EventStringFormatter.generateEventString(sb, servId, outStatus, color, fontWeight, time);
	}

	public static String formatShattererString(EventData dragonData) {
		StringBuffer sb = new StringBuffer();
		
		for (ServerID servId:ServerID.values()) {
			formatShattererString(dragonData, sb, servId);
		}
		
		return sb.toString();
	}

	public static void formatShattererString(EventData dragonData,
			StringBuffer sb, ServerID servId) {
		String outStatus = "";
		String color = "";
		DateTime time = null;
		int fontWeight = 400;
		
		String escortEventId = servId.uid()+"-"+DragonEvent.SHATTERER_ESCORT.uid();
		String siegeEventId = servId.uid()+"-"+DragonEvent.SHATTERER_SIEGE.uid();
		String shatEventId = servId.uid()+"-"+DragonEvent.SHATTERER_UP.uid();
		
		String escortStatus = dragonData.getEventStatus(escortEventId);
		String siegeStatus = dragonData.getEventStatus(siegeEventId);
		String shatStatus = dragonData.getEventStatus(shatEventId);
		
		if (escortStatus!=null && escortStatus.equals("Active")) {
			time = dragonData.getEventTime(escortEventId);
			
			if (siegeStatus.equals("Active")) {
				outStatus = "Escort and Collection Pre";
			} else {
				outStatus = "Escort Pre";
			}
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
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
			time = dragonData.getEventTime(shatEventId);
			
			outStatus = "Ominous Winds";
			color = EventStateColor.ACTIVE.color();
			fontWeight = 900;
		} else {
			time = dragonData.getEventTime(shatEventId);
			
			outStatus = "Not up";
			color = EventStateColor.FAIL.color();
		}
		
		EventStringFormatter.generateEventString(sb, servId, outStatus, color, fontWeight, time);
	}

	public static String formatJormagString(EventData dragonData) {
		StringBuffer sb = new StringBuffer();
		
		for (ServerID servId:ServerID.values()) {
			formatJormagString(dragonData, sb, servId);
		}
		
		return sb.toString();
	}

	public static void formatJormagString(EventData dragonData,
			StringBuffer sb, ServerID servId) {
		String outStatus = "";
		String color = "";
		DateTime time = null;
		int fontWeight = 400;
		
		String crystal1EventId = servId.uid()+"-"+DragonEvent.JORMAG_CRYSTAL1.uid();
		String crystal2EventId = servId.uid()+"-"+DragonEvent.JORMAG_CRYSTAL2.uid();
		String crystal3EventId = servId.uid()+"-"+DragonEvent.JORMAG_CRYSTAL3.uid();
		String crystal4EventId = servId.uid()+"-"+DragonEvent.JORMAG_CRYSTAL4.uid();
		String crystalFEventId = servId.uid()+"-"+DragonEvent.JORMAG_CRYSTALF.uid();
		String jormagEventId = servId.uid()+"-"+DragonEvent.JORMAG_UP.uid();
		
		String crystal1Status = dragonData.getEventStatus(crystal1EventId);
		String crystal2Status = dragonData.getEventStatus(crystal2EventId);
		String crystal3Status = dragonData.getEventStatus(crystal3EventId);
		String crystal4Status = dragonData.getEventStatus(crystal4EventId);
		String crystalFStatus = dragonData.getEventStatus(crystalFEventId);
		String jormagStatus = dragonData.getEventStatus(jormagEventId);
		
		
		if ((crystal1Status != null && crystal2Status != null
				&& crystal3Status != null && crystal4Status != null) && 
				(crystal1Status.equals("Active") || crystal2Status.equals("Active") ||
				crystal3Status.equals("Active") || crystal4Status.equals("Active"))) {
			time = dragonData.getEventTime(crystal1EventId);
			
			outStatus = "Four Crystals Pre";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else if (crystalFStatus != null && crystalFStatus.equals("Active")) {
			time = dragonData.getEventTime(crystalFEventId);
			
			outStatus = "Final Crystal Pre";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else if (jormagStatus != null && jormagStatus.equals("Preparation")) {
			time = dragonData.getEventTime(jormagEventId);
			
			outStatus = "About to Land";
			color = EventStateColor.ACTIVE.color();
			fontWeight = 900;
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
		
		EventStringFormatter.generateEventString(sb, servId, outStatus, color, fontWeight, time);
	}
}
