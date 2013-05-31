package ca.bsolomon.gw2events.worldevent.enums;

import org.joda.time.DateTime;
import org.joda.time.Period;

import ca.bsolomon.gw2events.worldevent.util.EventStringFormatter;
import ca.bsolomon.gw2events.worldevent.util.LowLevelEventData;

public enum FireEleEvent {

	CHAOTIC("6B897FF9-4BA8-4EBD-9CEC-7DCFDA5361D8", "Chaotic Material Pre"),
	ESCORT("5E4E9CD9-DD7C-49DB-8392-C99E1EF4E7DF", "Escort Golem Pre"),
	DEFEND("2C833C11-5CD5-4D96-A4CE-A74C04C9A278", "Defend Golem Pre"),
	STOLEN("FCB42C06-547F-4DA2-904B-0098E60C47BC", "Destroy Golem Pre"),
	FIREELE("33F76E9E-0BB6-46D0-A3A9-BE4CDFC4A3A4", "Active");
	
	private String uid;
	private String prettyName;
	
	FireEleEvent(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public String uid() {return uid;}
	public String toString() {return prettyName;}
	
	public static String formatFireEleString(LowLevelEventData lowLevelEventData) {
		StringBuffer sb = new StringBuffer();
		
		for (ServerID servId:ServerID.values()) {
			String outStatus = "";
			String color = "";
			DateTime time = null;
		
			String chaoticEventId = servId.uid()+"-"+FireEleEvent.CHAOTIC.uid();
			String defendEventId = servId.uid()+"-"+FireEleEvent.DEFEND.uid();
			String escortEventId = servId.uid()+"-"+FireEleEvent.ESCORT.uid();
			String stolenEventId = servId.uid()+"-"+FireEleEvent.STOLEN.uid();
			String fireEleEventId = servId.uid()+"-"+FireEleEvent.FIREELE.uid();
			
			String chaoticStatus = lowLevelEventData.getEventStatus(chaoticEventId);
			String defendStatus = lowLevelEventData.getEventStatus(defendEventId);
			String escortStatus = lowLevelEventData.getEventStatus(escortEventId);
			String stolenStatus = lowLevelEventData.getEventStatus(stolenEventId);
			String fireEleStatus = lowLevelEventData.getEventStatus(fireEleEventId);
			
			if (chaoticStatus!=null && (chaoticStatus.equals("Active") || chaoticStatus.equals("Preparation"))) {
				time = lowLevelEventData.getEventTime(chaoticEventId);
				
				outStatus = FireEleEvent.CHAOTIC.toString();
				color = "<span style='color: #"+EventStateColor.PREPARATION.color()+";'>";
			} else if (escortStatus!=null && (escortStatus.equals("Active") || escortStatus.equals("Preparation"))) {
				time = lowLevelEventData.getEventTime(escortEventId);
				
				outStatus = FireEleEvent.ESCORT.toString();
				color = "<span style='color: #"+EventStateColor.PREPARATION.color()+";'>";
			} else if (defendStatus!=null && defendStatus.equals("Active")) {
				time = lowLevelEventData.getEventTime(defendEventId);
				
				outStatus = FireEleEvent.DEFEND.toString();
				color = "<span style='color: #"+EventStateColor.PREPARATION.color()+";'>";
			} else if (stolenStatus!=null && (stolenStatus.equals("Active") || stolenStatus.equals("Preparation"))) {
				time = lowLevelEventData.getEventTime(stolenEventId);
				
				outStatus = FireEleEvent.STOLEN.toString();
				color = "<span style='color: #"+EventStateColor.FAIL.color()+";'>";
			} else if (fireEleStatus!=null && (fireEleStatus.equals("Active") || fireEleStatus.equals("Preparation"))) {
				time = lowLevelEventData.getEventTime(fireEleEventId);
				
				outStatus = FireEleEvent.FIREELE.toString();
				color = "<span style='color: #"+EventStateColor.ACTIVE.color()+";'>";
			} else {
				time = lowLevelEventData.getEventTime(fireEleEventId);
				
				outStatus = "Not up";
				color = "<span style='color: #"+EventStateColor.FAIL.color()+";'>";
			}
			
			String timeStr = EventStringFormatter.format.print(time);
			
			DateTime now = new DateTime(EventStringFormatter.gregorianJuian);
			Period period = new Period(time, now);
			
			String periodStr = EventStringFormatter.HHMMSSFormater.print(period);
			
			sb.append(timeStr+" - "+ servId.toString()+" - "+color+outStatus+"</span> - "+periodStr+"</br>");
		}
		
		return sb.toString();
	}
}
