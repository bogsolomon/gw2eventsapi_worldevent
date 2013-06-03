package ca.bsolomon.gw2events.worldevent.enums;

import org.joda.time.DateTime;

import ca.bsolomon.gw2events.worldevent.util.EventStringFormatter;
import ca.bsolomon.gw2events.worldevent.util.EventData;

public enum HarathiChestEvent {

	WARCOUNCIL(	"A3101CDC-A4A0-4726-85C0-147EF8463A50", "War Council Pre"),
	DEFENSE(	"DA465AE1-4D89-4972-AD66-A9BE3C5A1823", "Defend Kingsgate"),
	CENTAUR(	"E6872A86-E434-4FC1-B803-89921FF0F6D6", "Fight the Wind");
	
	private String uid;
	private String prettyName;
	
	HarathiChestEvent(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public String uid() {return uid;}
	public String toString() {return prettyName;}
	
	public static String formatHarathiString(EventData lowPriorityEventData) {
		StringBuffer sb = new StringBuffer();
		
		for (ServerID servId:ServerID.values()) {
			formatHarathiString(lowPriorityEventData, sb, servId);
		}
		
		return sb.toString();
	}

	public static void formatHarathiString(
			EventData lowPriorityEventData, StringBuffer sb,
			ServerID servId) {
		String outStatus = "";
		String color = "";
		DateTime time = null;
		int fontWeight = 400; 

		String warCouncilEventId = servId.uid()+"-"+WARCOUNCIL.uid();
		String defenseEventId = servId.uid()+"-"+DEFENSE.uid();
		String centaurEventId = servId.uid()+"-"+CENTAUR.uid();
		
		String warCouncilStatus = lowPriorityEventData.getEventStatus(warCouncilEventId);
		String defenseStatus = lowPriorityEventData.getEventStatus(defenseEventId);
		String centaurStatus = lowPriorityEventData.getEventStatus(centaurEventId);
		
		if ((warCouncilStatus != null) &&
				(warCouncilStatus.equals("Active") || warCouncilStatus.equals("Preparation"))) {
			time = lowPriorityEventData.getEventTime(warCouncilEventId);
			
			outStatus = WARCOUNCIL.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else if ((defenseStatus != null) &&
				(defenseStatus.equals("Active") || defenseStatus.equals("Preparation") || defenseStatus.equals("Warmup"))) {
			time = lowPriorityEventData.getEventTime(defenseEventId);
			
			outStatus = DEFENSE.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else if ((centaurStatus != null) &&
				(centaurStatus.equals("Active") || centaurStatus.equals("Preparation") || centaurStatus.equals("Warmup"))) {
			time = lowPriorityEventData.getEventTime(centaurEventId);
			
			outStatus = CENTAUR.toString();
			color = EventStateColor.ACTIVE.color();
			fontWeight = 900;
		} else {
			time = lowPriorityEventData.getEventTime(centaurEventId);
			
			outStatus = "Not up";
			color = EventStateColor.FAIL.color();
		}
		
		EventStringFormatter.generateEventString(sb, servId, outStatus, color, fontWeight, time);
	}
}
