package ca.bsolomon.gw2events.worldevent.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.joda.time.DateTime;

public class TempleEventData implements EventData {

	private static ConcurrentMap<String, String> eventStatus = new ConcurrentHashMap<String, String>(16, 0.9f, 1);
	private static ConcurrentMap<String, DateTime> eventTime = new ConcurrentHashMap<String, DateTime>(16, 0.9f, 1);

	/* (non-Javadoc)
	 * @see ca.bsolomon.gw2events.worldevent.util.EventData#addEventStatus(java.lang.String, java.lang.String, org.joda.time.DateTime)
	 */
	public boolean addEventStatus(String serverId, String eventId, String status, DateTime time) {
		String composedId = serverId+"-"+eventId;
		
		if (eventStatus.containsKey(composedId)) {
			if (!eventStatus.get(composedId).equals(status)) {
				eventStatus.put(composedId, status);
				eventTime.put(composedId, time);
				
				return true;
			}
		} else {
			eventStatus.put(composedId, status);
			eventTime.put(composedId, time);
			
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
	
	
	public String getMaxPeriod(String eventId) {
		return "N/A";
	}
	
	public String getMinPeriod(String eventId) {
		return "N/A";
	}
}
