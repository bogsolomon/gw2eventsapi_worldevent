package ca.bsolomon.gw2events.worldevent.util;

import java.util.List;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Period;
import org.joda.time.chrono.GJChronology;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import ca.bsolomon.gw2events.worldevent.enums.ServerID;

public class EventStringFormatter {

	public static DateTimeZone zone = DateTimeZone.forID("America/New_York");
	public static Chronology gregorianJuian = GJChronology.getInstance(zone);
	
	public static DateTimeFormatter format = new DateTimeFormatterBuilder().
			appendHourOfDay(2).appendLiteral(":").
			appendMinuteOfHour(2).appendLiteral(":").
			appendSecondOfMinute(2).appendLiteral(" ").
			appendTimeZoneShortName().toFormatter();
	
	public static PeriodFormatter HHMMSSFormater = new PeriodFormatterBuilder().
			printZeroAlways().minimumPrintedDigits(2).
			appendHours().appendSeparator(":").
			appendMinutes().appendSeparator(":").
			appendSeconds().toFormatter();
	

	/*private static void generateEventString(StringBuffer sb, ServerID servId,
			String outStatus, String color, int fontWeight, DateTime time) {
		DateTime now = new DateTime(gregorianJuian);
		Period period = new Period(time, now);
		
		String periodStr = HHMMSSFormater.print(period);
		
		sb.append(servId.toString()+" - "
				+"<span style='font-weight:"+fontWeight+";color: #"+color+";'>"
				+outStatus+"</span> - "+periodStr+"</br>");
	}*/


	public static void generateEventString(List<EventStatus> statusList,
			ServerID servId, String outStatus, String color, int fontWeight,
			DateTime time, String eventName, String waypoint) {
		DateTime now = new DateTime(gregorianJuian);
		Period period = new Period(time, now);
		
		String periodStr = HHMMSSFormater.print(period);
		
		String fullWPName = waypoint+" - "+eventName+" - "+servId.toString()+ ":"+outStatus;
		
		EventStatus newStatus = new EventStatus(servId.toString(), outStatus, periodStr, color, eventName, fullWPName);
		
		statusList.add(newStatus);
	}
}
