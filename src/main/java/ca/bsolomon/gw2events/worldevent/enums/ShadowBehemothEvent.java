package ca.bsolomon.gw2events.worldevent.enums;

import org.joda.time.DateTime;

import ca.bsolomon.gw2events.worldevent.util.EventData;
import ca.bsolomon.gw2events.worldevent.util.EventStringFormatter;

public enum ShadowBehemothEvent {

	PORTAL_MON(  "E539A5E3-A33B-4D5F-AEED-197D2716F79B", "Monastery"),
	PORTAL_WOOD( "CFBC4A8C-2917-478A-9063-1A8B43CC8C38", "Wood"),
	PORTAL_HILL( "AFCF031A-F71D-4CEA-85E1-957179414B25", "Hill"),
	PORTAL_SWAMP("36330140-7A61-4708-99EB-010B10420E39", "Final"),
	SB(			 "31CEBA08-E44D-472F-81B0-7143D73797F5", "Active");
	
	private String uid;
	private String prettyName;
	
	ShadowBehemothEvent(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public String uid() {return uid;}
	public String toString() {return prettyName;}
	
	private static boolean swampPortalsDestroyed = false;
	
	public static String formatShadowBehemothString(EventData lowLevelEventData) {
		StringBuffer sb = new StringBuffer();
		
		for (ServerID servId:ServerID.values()) {
			formatShadowBehemothString(lowLevelEventData, sb, servId);
		}
		
		return sb.toString();
	}

	public static void formatShadowBehemothString(EventData lowLevelEventData,
			StringBuffer sb, ServerID servId) {
		String outStatus = "";
		String color = "";
		DateTime time = null;
		int fontWeight = 400;

		String hillEventId = servId.uid()+"-"+PORTAL_HILL.uid();
		String monEventId = servId.uid()+"-"+PORTAL_MON.uid();
		String woodEventId = servId.uid()+"-"+PORTAL_WOOD.uid();
		String swampEventId = servId.uid()+"-"+PORTAL_SWAMP.uid();
		String sbEventId = servId.uid()+"-"+SB.uid();
		
		String hillStatus = lowLevelEventData.getEventStatus(hillEventId);
		String monStatus = lowLevelEventData.getEventStatus(monEventId);
		String woodStatus = lowLevelEventData.getEventStatus(woodEventId);
		String swampStatus = lowLevelEventData.getEventStatus(swampEventId);
		String sbStatus = lowLevelEventData.getEventStatus(sbEventId);
		
		if ((hillStatus != null && monStatus != null && woodStatus != null) &&
				(hillStatus.equals("Active") || monStatus.equals("Active") || woodStatus.equals("Active")
						|| hillStatus.equals("Warmup") || monStatus.equals("Warmup") || woodStatus.equals("Warmup"))) {
			time = lowLevelEventData.getEventTime(hillEventId);
			
			outStatus = "Outside Portals Pre";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
		} else if ((swampStatus != null) &&
				(swampStatus.equals("Active"))) {
			time = lowLevelEventData.getEventTime(swampEventId);
			
			outStatus = "Inside Portals Pre";
			color = EventStateColor.PREPARATION.color();
			fontWeight = 600;
			
			swampPortalsDestroyed = true;
		}  else if ((sbStatus != null) &&
				(sbStatus.equals("Active") || (sbStatus.equals("Preparation") && swampPortalsDestroyed))) {
			time = lowLevelEventData.getEventTime(sbEventId);
			
			outStatus = "Active";
			color = EventStateColor.ACTIVE.color();
			fontWeight = 900;
		} else {
			time = lowLevelEventData.getEventTime(sbEventId);
			
			outStatus = "Not up";
			color = EventStateColor.FAIL.color();
			
			swampPortalsDestroyed = false;
		}
		
		EventStringFormatter.generateEventString(sb, servId, outStatus, color, fontWeight, time);
	}
}
