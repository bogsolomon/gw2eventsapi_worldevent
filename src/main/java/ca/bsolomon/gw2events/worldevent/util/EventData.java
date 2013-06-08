package ca.bsolomon.gw2events.worldevent.util;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

public interface EventData {
	
	public static PeriodFormatter MMSSFormater = new PeriodFormatterBuilder().
			printZeroAlways().minimumPrintedDigits(2).
			appendMinutes().appendSeparator(":").
			appendSeconds().toFormatter();

	public abstract boolean addEventStatus(String serverId, String eventId, String status,
			DateTime time);

	public abstract String getEventStatus(String eventId);

	public abstract DateTime getEventTime(String eventId);
	
	public String getMaxPeriod(String eventId);
	
	public String getMinPeriod(String eventId);
}