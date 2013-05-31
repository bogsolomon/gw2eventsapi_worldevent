package ca.bsolomon.gw2events.worldevent.enums;

import org.joda.time.DateTime;
import org.joda.time.Period;

import ca.bsolomon.gw2events.worldevent.util.EventStringFormatter;
import ca.bsolomon.gw2events.worldevent.util.LowPriorityEventData;

public enum DredgeEvent {

	MOLEKCAPTURE(		"141654A6-D42E-415E-A3C4-918A1E664AF3", "Capture Molek Pre"),
	MOLEKDEFENSE(	"64B94537-00D5-4CB6-8558-44987A9C5F76", "Protect Molek Pre"),
	DREDGE(		"95CA969B-0CC6-4604-B166-DBCCE125864F", "Active");
	
	private String uid;
	private String prettyName;
	
	DredgeEvent(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public String uid() {return uid;}
	public String toString() {return prettyName;}
	
	public static String formatDredgeString(LowPriorityEventData lowLevelEventData) {
		StringBuffer sb = new StringBuffer();
		
		for (ServerID servId:ServerID.values()) {
			String outStatus = "";
			String color = "";
			DateTime time = null;
		
			String molekCEventId = servId.uid()+"-"+MOLEKCAPTURE.uid();
			String molekDEventId = servId.uid()+"-"+MOLEKDEFENSE.uid();
			String dredgeEventId = servId.uid()+"-"+DREDGE.uid();
			
			String molekCStatus = lowLevelEventData.getEventStatus(molekCEventId);
			String molekDStatus = lowLevelEventData.getEventStatus(molekDEventId);
			String dredgeStatus = lowLevelEventData.getEventStatus(dredgeEventId);
			
			if ((molekDStatus != null) &&
					(molekDStatus.equals("Active") || molekDStatus.equals("Preparation"))) {
				time = lowLevelEventData.getEventTime(molekDEventId);
				
				outStatus = MOLEKDEFENSE.toString();
				color = "<span style='color: #"+EventStateColor.PREPARATION.color()+";'>";
			} else if ((molekCStatus != null) &&
					(molekCStatus.equals("Active"))) {
				time = lowLevelEventData.getEventTime(molekCEventId);
				
				outStatus = MOLEKCAPTURE.toString();
				color = "<span style='color: #"+EventStateColor.PREPARATION.color()+";'>";
			} else if ((dredgeStatus != null) &&
					(dredgeStatus.equals("Active"))) {
				time = lowLevelEventData.getEventTime(dredgeEventId);
				
				outStatus = "Active";
				color = "<span style='color: #"+EventStateColor.ACTIVE.color()+";'>";
			}  else {
				time = lowLevelEventData.getEventTime(dredgeEventId);
				
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
