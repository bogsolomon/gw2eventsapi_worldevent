package ca.bsolomon.gw2events.worldevent.util;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Period;
import org.joda.time.chrono.GJChronology;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

public class EventStringFormatter {

	private static DateTimeZone zone = DateTimeZone.forID("America/New_York");
	private static Chronology gregorianJuian = GJChronology.getInstance(zone);
	
	private static DateTimeFormatter format = new DateTimeFormatterBuilder().
			appendHourOfDay(2).appendLiteral(":").
			appendMinuteOfHour(2).appendLiteral(":").
			appendSecondOfMinute(2).appendLiteral(" ").
			appendTimeZoneShortName().toFormatter();
	
	private static PeriodFormatter HHMMSSFormater = new PeriodFormatterBuilder().
			printZeroAlways().minimumPrintedDigits(2).
			appendHours().appendSeparator(":").
			appendMinutes().appendSeparator(":").
			appendSeconds().toFormatter();
	
	public static String formatTequatlString(DragonData data) {
		StringBuffer sb = new StringBuffer();
		
		for (ServerID servId:ServerID.values()) {
			String composedEventId = servId.uid()+"-"+DragonEvent.TEQUATL.uid();
			
			String status = data.getDragonStatus(composedEventId);
			DateTime time = data.getDragonTime(composedEventId);
			
			DateTime now = new DateTime(gregorianJuian);
			
			String outStatus = "";
			String color = "";
			
			String timeStr = format.print(time);
			
			if (status.equals("Active")) {
				color = "<span style='color: #"+EventStateColor.ACTIVE.color()+";'>";
				outStatus = status;
			} else if (status.equals("Preparation")) {
				color = "<span style='color: #"+EventStateColor.PREPARATION.color()+";'>";
				outStatus = "Something in water";
			} else {
				color = "<span style='color: #"+EventStateColor.FAIL.color()+";'>";
				outStatus = "Not up";
			}
			
			Period period = new Period(time, now);
			
			String periodStr = HHMMSSFormater.print(period);
			
			sb.append(timeStr+" - "+ servId.toString()+" - "+color+outStatus+"</span> - "+periodStr+"</br>");
		}
		
		return sb.toString();
	}

	public static String formatShattererString(DragonData dragonData) {
		StringBuffer sb = new StringBuffer();
		
		for (ServerID servId:ServerID.values()) {
			String outStatus = "";
			String color = "";
			DateTime time = null;
			
			String escortEventId = servId.uid()+"-"+DragonEvent.SHATTERER_ESCORT.uid();
			String siegeEventId = servId.uid()+"-"+DragonEvent.SHATTERER_SIEGE.uid();
			String shatEventId = servId.uid()+"-"+DragonEvent.SHATTERER_UP.uid();
			
			String escortStatus = dragonData.getDragonStatus(escortEventId);
			String siegeStatus = dragonData.getDragonStatus(siegeEventId);
			String shatStatus = dragonData.getDragonStatus(shatEventId);
			
			if (escortStatus.equals("Active")) {
				time = dragonData.getDragonTime(escortEventId);
				
				if (siegeStatus.equals("Active")) {
					outStatus = "Escort and Collection Pre";
				} else {
					outStatus = "Escort Pre";
				}
				color = "<span style='color: #"+EventStateColor.PREPARATION.color()+";'>";
			} else if (siegeStatus.equals("Active")) {
				time = dragonData.getDragonTime(siegeEventId);
				
				outStatus = "Collection Pre";
				color = "<span style='color: #"+EventStateColor.PREPARATION.color()+";'>";
			} else if (shatStatus.equals("Active")) {
				time = dragonData.getDragonTime(shatEventId);
				
				outStatus = "Active";
				color = "<span style='color: #"+EventStateColor.ACTIVE.color()+";'>";
			} else if (escortStatus.equals("Success") && siegeStatus.equals("Success")) {
				time = dragonData.getDragonTime(shatEventId);
				
				outStatus = "Ominous Winds";
				color = "<span style='color: #"+EventStateColor.ACTIVE.color()+";'>";
			} else {
				time = dragonData.getDragonTime(shatEventId);
				
				outStatus = "Not up";
				color = "<span style='color: #"+EventStateColor.FAIL.color()+";'>";
			}
			
			String timeStr = format.print(time);
			
			DateTime now = new DateTime(gregorianJuian);
			Period period = new Period(time, now);
			
			String periodStr = HHMMSSFormater.print(period);
			
			sb.append(timeStr+" - "+ servId.toString()+" - "+color+outStatus+"</span> - "+periodStr+"</br>");
		}
		
		return sb.toString();
	}

	public static String formatJormagString(DragonData dragonData) {
		StringBuffer sb = new StringBuffer();
		
		for (ServerID servId:ServerID.values()) {
			String outStatus = "";
			String color = "";
			DateTime time = null;
			
			String crystal1EventId = servId.uid()+"-"+DragonEvent.JORMAG_CRYSTAL1.uid();
			String crystal2EventId = servId.uid()+"-"+DragonEvent.JORMAG_CRYSTAL2.uid();
			String crystal3EventId = servId.uid()+"-"+DragonEvent.JORMAG_CRYSTAL3.uid();
			String crystal4EventId = servId.uid()+"-"+DragonEvent.JORMAG_CRYSTAL4.uid();
			String crystalFEventId = servId.uid()+"-"+DragonEvent.JORMAG_CRYSTALF.uid();
			String jormagEventId = servId.uid()+"-"+DragonEvent.JORMAG_UP.uid();
			
			String crystal1Status = dragonData.getDragonStatus(crystal1EventId);
			String crystal2Status = dragonData.getDragonStatus(crystal2EventId);
			String crystal3Status = dragonData.getDragonStatus(crystal3EventId);
			String crystal4Status = dragonData.getDragonStatus(crystal4EventId);
			String crystalFStatus = dragonData.getDragonStatus(crystalFEventId);
			String jormagStatus = dragonData.getDragonStatus(jormagEventId);
			
			if (crystal1Status.equals("Active") || crystal2Status.equals("Active") ||
					crystal3Status.equals("Active") || crystal4Status.equals("Active")) {
				time = dragonData.getDragonTime(crystal1EventId);
				
				outStatus = "Four Crystals Pre";
				color = "<span style='color: #"+EventStateColor.PREPARATION.color()+";'>";
			} else if (crystalFStatus.equals("Active")) {
				time = dragonData.getDragonTime(crystalFEventId);
				
				outStatus = "Final Crystal Pre";
				color = "<span style='color: #"+EventStateColor.PREPARATION.color()+";'>";
			} else if (jormagStatus.equals("Preparation")) {
				time = dragonData.getDragonTime(jormagEventId);
				
				outStatus = "About to Land";
				color = "<span style='color: #"+EventStateColor.ACTIVE.color()+";'>";
			} else if (jormagStatus.equals("Active")) {
				time = dragonData.getDragonTime(jormagEventId);
				
				outStatus = "Active";
				color = "<span style='color: #"+EventStateColor.ACTIVE.color()+";'>";
			} else {
				time = dragonData.getDragonTime(jormagEventId);
				
				outStatus = "Not up";
				color = "<span style='color: #"+EventStateColor.FAIL.color()+";'>";	
			}
			
			String timeStr = format.print(time);
			
			DateTime now = new DateTime(gregorianJuian);
			Period period = new Period(time, now);
			
			String periodStr = HHMMSSFormater.print(period);
			
			sb.append(timeStr+" - "+ servId.toString()+" - "+color+outStatus+"</span> - "+periodStr+"</br>");
		}
		
		return sb.toString();
	}

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
			
			if (protectStatus.equals("Active")) {
				time = lowLevelEventData.getEventTime(protectEventId);
				
				outStatus = MawEvent.PROTECT.toString();
				color = "<span style='color: #"+EventStateColor.PREPARATION.color()+";'>";
			} else if (escortStatus.equals("Active") || escortStatus.equals("Preparation")) {
				time = lowLevelEventData.getEventTime(protectEventId);
				
				outStatus = MawEvent.ESCORT.toString();
				color = "<span style='color: #"+EventStateColor.PREPARATION.color()+";'>";
			} else if (totemStatus.equals("Active") || totemStatus.equals("Preparation")) {
				time = lowLevelEventData.getEventTime(protectEventId);
				
				outStatus = MawEvent.TOTEM.toString();
				color = "<span style='color: #"+EventStateColor.PREPARATION.color()+";'>";
			} else if (portalStatus.equals("Active") || guardsStatus.equals("Active") || shamaStatus.equals("Active")) {
				time = lowLevelEventData.getEventTime(portalStatus);
				
				outStatus = "Portals/Guards";
				color = "<span style='color: #"+EventStateColor.PREPARATION.color()+";'>";
			} else if (chiefStatus.equals("Active") || chiefStatus.equals("Preparation")) {
				time = lowLevelEventData.getEventTime(chiefStatus);
				
				outStatus = MawEvent.CHIEF.toString();
				color = "<span style='color: #"+EventStateColor.ACTIVE.color()+";'>";
			}  else {
				time = lowLevelEventData.getEventTime(chiefStatus);
				
				outStatus = "Not up";
				color = "<span style='color: #"+EventStateColor.FAIL.color()+";'>";	
			}
			
			String timeStr = format.print(time);
			
			DateTime now = new DateTime(gregorianJuian);
			Period period = new Period(time, now);
			
			String periodStr = HHMMSSFormater.print(period);
			
			sb.append(timeStr+" - "+ servId.toString()+" - "+color+outStatus+"</span> - "+periodStr+"</br>");
		}
		
		return sb.toString();
	}
	
}
