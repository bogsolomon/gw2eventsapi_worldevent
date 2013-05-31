package ca.bsolomon.gw2events.worldevent.enums;

import org.joda.time.DateTime;
import org.joda.time.Period;

import ca.bsolomon.gw2events.worldevent.util.EventStringFormatter;
import ca.bsolomon.gw2events.worldevent.util.LowLevelEventData;

public enum MawEvent {

	PROTECT("6F516B2C-BD87-41A9-9197-A209538BB9DF", "Grawl Restless"),
	ESCORT("D5F31E0B-E0E3-42E3-87EC-337B3037F437", "Escort Scholar"),
	TOTEM("6565EFD4-6E37-4C26-A3EA-F47B368C866D", "Destroy Totem"),
	PORTALS("374FC8CB-7AB7-4381-AC71-14BFB30D3019", "Portals"),
	GUARDS("90B241F5-9E59-46E8-B608-2507F8810E00", "Guards"),
	SHAMANS("DB83ABB7-E5FE-4ACB-8916-9876B87D300D", "Shamans"),
	CHIEF("F7D9D427-5E54-4F12-977A-9809B23FBA99", "Active");
	
	
	private String uid;
	private String prettyName;
	
	MawEvent(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public String uid() {return uid;}
	public String toString() {return prettyName;}
	
	public static String formatMawString(LowLevelEventData lowLevelEventData) {
		StringBuffer sb = new StringBuffer();
		
		for (ServerID servId:ServerID.values()) {
			String outStatus = "";
			String color = "";
			DateTime time = null;
		
			String protectEventId = servId.uid()+"-"+MawEvent.PROTECT.uid();
			String escortEventId = servId.uid()+"-"+MawEvent.ESCORT.uid();
			String totemEventId = servId.uid()+"-"+MawEvent.TOTEM.uid();
			
			String portalEventId = servId.uid()+"-"+MawEvent.PORTALS.uid();
			String guardsEventId = servId.uid()+"-"+MawEvent.GUARDS.uid();
			String shamanEventId = servId.uid()+"-"+MawEvent.SHAMANS.uid();
			
			String chiefEventId = servId.uid()+"-"+MawEvent.CHIEF.uid();
		
			String protectStatus = lowLevelEventData.getEventStatus(protectEventId);
			String escortStatus = lowLevelEventData.getEventStatus(escortEventId);
			String totemStatus = lowLevelEventData.getEventStatus(totemEventId);
			String portalStatus = lowLevelEventData.getEventStatus(portalEventId);
			String guardsStatus = lowLevelEventData.getEventStatus(guardsEventId);
			String shamaStatus = lowLevelEventData.getEventStatus(shamanEventId);
			String chiefStatus = lowLevelEventData.getEventStatus(chiefEventId);
			
			if (protectStatus != null && protectStatus.equals("Active")) {
				time = lowLevelEventData.getEventTime(protectEventId);
				
				outStatus = MawEvent.PROTECT.toString();
				color = "<span style='color: #"+EventStateColor.PREPARATION.color()+";'>";
			} else if (escortStatus != null && (escortStatus.equals("Active") || escortStatus.equals("Preparation"))) {
				time = lowLevelEventData.getEventTime(protectEventId);
				
				outStatus = MawEvent.ESCORT.toString();
				color = "<span style='color: #"+EventStateColor.PREPARATION.color()+";'>";
			} else if (totemStatus != null && (totemStatus.equals("Active") || totemStatus.equals("Preparation"))) {
				time = lowLevelEventData.getEventTime(protectEventId);
				
				outStatus = MawEvent.TOTEM.toString();
				color = "<span style='color: #"+EventStateColor.PREPARATION.color()+";'>";
			} else if ((portalStatus !=null && guardsStatus!=null && shamaStatus!=null) && 
					(portalStatus.equals("Active") || guardsStatus.equals("Active") || shamaStatus.equals("Active"))) {
				time = lowLevelEventData.getEventTime(portalEventId);
				
				outStatus = "Portals/Guards";
				color = "<span style='color: #"+EventStateColor.PREPARATION.color()+";'>";
			} else if (chiefStatus != null && (chiefStatus.equals("Active") || chiefStatus.equals("Preparation"))) {
				time = lowLevelEventData.getEventTime(chiefEventId);
				
				outStatus = MawEvent.CHIEF.toString();
				color = "<span style='color: #"+EventStateColor.ACTIVE.color()+";'>";
			}  else {
				time = lowLevelEventData.getEventTime(chiefEventId);
				
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
