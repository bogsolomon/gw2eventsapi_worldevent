package ca.bsolomon.gw2events.worldevent.util;

import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.GJChronology;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

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
}
