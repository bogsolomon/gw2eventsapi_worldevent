package ca.bsolomon.gw2events.worldevent.enums;

import org.joda.time.DateTime;

import ca.bsolomon.gw2events.worldevent.util.EventStringFormatter;
import ca.bsolomon.gw2events.worldevent.util.EventData;

public enum FoulBearEvent {

	
	SAPPER(		"724343EA-B32C-4AE1-AB7E-E5FC160C26F9", "Sapper Delve"),
	SAPPERDEF(	"8D45B410-B614-4008-8A5C-E8D8230CEB40", "Sapper Delve Defense"),
	BLOODGORGE(	"44ABC8F1-ED5C-4E00-9338-5A8C40B228B4", "Capture Bloodgorge"),
	ASSAULT(	"D9F1CF48-B1CB-49F5-BFAF-4CEC5E68C9CF", "Animal Trainers"),
	DESTROY(	"4B478454-8CD2-4B44-808C-A35918FA86AA", "Destroy Houses"),
	CHIEF(		"B4E6588F-232C-4F68-9D58-8803D67E564D", "Active");
	
	private String uid;
	private String prettyName;
	
	FoulBearEvent(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public String uid() {return uid;}
	public String toString() {return prettyName;}
	
	public static String formatOgreString(EventData lowLevelEventData) {
		StringBuffer sb = new StringBuffer();
		
		for (ServerID servId:ServerID.values()) {
			formatOgreString(lowLevelEventData, sb, servId);
		}
		
		return sb.toString();
	}

	public static void formatOgreString(EventData lowLevelEventData,
			StringBuffer sb, ServerID servId) {
		String outStatus = "";
		String color = "";
		DateTime time = null;
		int fontWeight = 400;

		String sapperEventId = servId.uid()+"-"+SAPPER.uid();
		String sapperDefEventId = servId.uid()+"-"+SAPPERDEF.uid();
		String bloodEventId = servId.uid()+"-"+BLOODGORGE.uid();
		String assaultEventId = servId.uid()+"-"+ASSAULT.uid();
		String destroyEventId = servId.uid()+"-"+DESTROY.uid();
		String chiefEventId = servId.uid()+"-"+CHIEF.uid();
		
		String sapperStatus = lowLevelEventData.getEventStatus(sapperEventId);
		String sapperDefStatus = lowLevelEventData.getEventStatus(sapperDefEventId);
		String bloodStatus = lowLevelEventData.getEventStatus(bloodEventId);
		String assaultStatus = lowLevelEventData.getEventStatus(assaultEventId);
		String destroyStatus = lowLevelEventData.getEventStatus(destroyEventId);
		String chiefStatus = lowLevelEventData.getEventStatus(chiefEventId);
		
		
		if ((chiefStatus != null) &&
				(chiefStatus.equals("Active") || chiefStatus.equals("Preparation"))) {
			time = lowLevelEventData.getEventTime(chiefEventId);
			
			outStatus = "Active";
			color = EventStateColor.ACTIVE.color();
			fontWeight = 900;
		} else if ((destroyStatus != null) &&
				(destroyStatus.equals("Active") || destroyStatus.equals("Preparation"))) {
			time = lowLevelEventData.getEventTime(destroyEventId);
			
			outStatus = DESTROY.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else if ((assaultStatus != null) &&
				(assaultStatus.equals("Active") || assaultStatus.equals("Preparation"))) {
			time = lowLevelEventData.getEventTime(assaultEventId);
			
			outStatus = ASSAULT.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else if ((bloodStatus != null) &&
				(bloodStatus.equals("Active")  || bloodStatus.equals("Preparation"))) {
			time = lowLevelEventData.getEventTime(bloodEventId);
			
			outStatus = BLOODGORGE.toString();
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else if ((sapperStatus != null && sapperDefStatus != null) &&
				(sapperStatus.equals("Active")  || sapperStatus.equals("Preparation")
						|| sapperDefStatus.equals("Active")  || sapperDefStatus.equals("Preparation"))) {
			time = lowLevelEventData.getEventTime(sapperEventId);
			
			outStatus = "Sapper Delve";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else {
			time = lowLevelEventData.getEventTime(chiefEventId);
			
			outStatus = "Not up";
			color = EventStateColor.FAIL.color();
		}
		
		EventStringFormatter.generateEventString(sb, servId, outStatus, color, fontWeight, time);
	}
}
