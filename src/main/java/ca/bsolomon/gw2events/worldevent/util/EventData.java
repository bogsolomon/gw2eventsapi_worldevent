package ca.bsolomon.gw2events.worldevent.util;

import org.joda.time.DateTime;

public interface EventData {

	public abstract boolean addEventStatus(String eventId, String status,
			DateTime time);

	public abstract String getEventStatus(String eventId);

	public abstract DateTime getEventTime(String eventId);

}