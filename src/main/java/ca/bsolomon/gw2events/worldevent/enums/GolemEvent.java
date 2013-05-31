package ca.bsolomon.gw2events.worldevent.enums;

import org.joda.time.DateTime;
import org.joda.time.Period;

import ca.bsolomon.gw2events.worldevent.util.EventStringFormatter;
import ca.bsolomon.gw2events.worldevent.util.LowPriorityEventData;

public enum GolemEvent {

	KELP(		"A7E0F553-C4E1-452F-B39F-7BDBEC8B0BB1", "Kelp Pre"),
	CONTAINER(	"3ED4FEB4-A976-4597-94E8-8BFD9053522F", "Containers Pre"),
	GOLEM(		"9AA133DC-F630-4A0E-BB5D-EE34A2B306C2", "Active");
	
	private String uid;
	private String prettyName;
	
	GolemEvent(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public String uid() {return uid;}
	public String toString() {return prettyName;}
	
	public static String formatGolemString(LowPriorityEventData lowLevelEventData) {
		StringBuffer sb = new StringBuffer();
		
		for (ServerID servId:ServerID.values()) {
			String outStatus = "";
			String color = "";
			DateTime time = null;
		
			String kelpEventId = servId.uid()+"-"+KELP.uid();
			String containerEventId = servId.uid()+"-"+CONTAINER.uid();
			String golemEventId = servId.uid()+"-"+GOLEM.uid();
			
			String kelpStatus = lowLevelEventData.getEventStatus(kelpEventId);
			String containerStatus = lowLevelEventData.getEventStatus(containerEventId);
			String golemStatus = lowLevelEventData.getEventStatus(golemEventId);
			
			if ((kelpStatus != null) &&
					(kelpStatus.equals("Active"))) {
				time = lowLevelEventData.getEventTime(kelpEventId);
				
				outStatus = KELP.toString();
				color = "<span style='color: #"+EventStateColor.PREPARATION.color()+";'>";
			} else if ((containerStatus != null) &&
					(containerStatus.equals("Active"))) {
				time = lowLevelEventData.getEventTime(containerEventId);
				
				outStatus = CONTAINER.toString();
				color = "<span style='color: #"+EventStateColor.PREPARATION.color()+";'>";
			} else if ((golemStatus != null) &&
					(golemStatus.equals("Warmup") || golemStatus.equals("Preparation"))) {
				time = lowLevelEventData.getEventTime(golemEventId);
				
				outStatus = "Arrival imminent";
				color = "<span style='color: #"+EventStateColor.PREPARATION.color()+";'>";
			} else if ((golemStatus != null) &&
					(golemStatus.equals("Active"))) {
				time = lowLevelEventData.getEventTime(golemEventId);
				
				outStatus = "Active";
				color = "<span style='color: #"+EventStateColor.ACTIVE.color()+";'>";
			}  else {
				time = lowLevelEventData.getEventTime(golemEventId);
				
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
