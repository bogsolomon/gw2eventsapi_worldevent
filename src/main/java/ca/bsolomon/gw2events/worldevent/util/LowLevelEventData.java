package ca.bsolomon.gw2events.worldevent.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.joda.time.DateTime;

public class LowLevelEventData implements EventData {

	private static ConcurrentMap<String, String> eventStatus = new ConcurrentHashMap<String, String>(16, 0.9f, 1);
	private static ConcurrentMap<String, DateTime> eventTime = new ConcurrentHashMap<String, DateTime>(16, 0.9f, 1);
	
	/* (non-Javadoc)
	 * @see ca.bsolomon.gw2events.worldevent.util.EventData#addEventStatus(java.lang.String, java.lang.String, org.joda.time.DateTime)
	 */
	public boolean addEventStatus(String eventId, String status, DateTime time) {
		if (eventStatus.containsKey(eventId)) {
			if (!eventStatus.get(eventId).equals(status)) {
				eventStatus.put(eventId, status);
				eventTime.put(eventId, time);
				
				return true;
			}
		} else {
			eventStatus.put(eventId, status);
			eventTime.put(eventId, time);
			
			return true;
		}
		
		return false;
	}
	
	/* (non-Javadoc)
	 * @see ca.bsolomon.gw2events.worldevent.util.EventData#getEventStatus(java.lang.String)
	 */
	public String getEventStatus(String eventId) {
		return eventStatus.get(eventId);
	}
	
	/* (non-Javadoc)
	 * @see ca.bsolomon.gw2events.worldevent.util.EventData#getEventTime(java.lang.String)
	 */
	public DateTime getEventTime(String eventId) {
		return eventTime.get(eventId);
	}
}
