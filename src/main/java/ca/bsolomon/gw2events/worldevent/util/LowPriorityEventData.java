package ca.bsolomon.gw2events.worldevent.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.joda.time.DateTime;
import org.joda.time.Period;

public class LowPriorityEventData  implements EventData {

	private static ConcurrentMap<String, String> eventStatus = new ConcurrentHashMap<String, String>(16, 0.9f, 1);
	private static ConcurrentMap<String, DateTime> eventTime = new ConcurrentHashMap<String, DateTime>(16, 0.9f, 1);
	
	private static ConcurrentMap<String, DateTime> lastActiveTime = new ConcurrentHashMap<String, DateTime>(16, 0.9f, 1);
	private static ConcurrentMap<String, Period> maxEventDiff = new ConcurrentHashMap<String, Period>(16, 0.9f, 1);
	private static ConcurrentMap<String, Period> minEventDiff = new ConcurrentHashMap<String, Period>(16, 0.9f, 1);

	private static Period MAXHOURS = new Period(5,0, 0, 0);
	
	public boolean addEventStatus(String serverId, String eventId, String status, DateTime time) {
		String composedId = serverId+"-"+eventId;
		
		if (eventStatus.containsKey(composedId)) {
			if (!eventStatus.get(composedId).equals(status)) {
				eventStatus.put(composedId, status);
				eventTime.put(composedId, time);
				
				if (status.equals("Active")) {
					EventWindowCalc.computeEventTiming(eventId, time, lastActiveTime, maxEventDiff, minEventDiff, MAXHOURS);
				}
				
				return true;
			}
		} else {
			eventStatus.put(composedId, status);
			eventTime.put(composedId, time);
			
			return true;
		}
		
		return false;
	}
	
	public String getEventStatus(String eventId) {
		return eventStatus.get(eventId);
	}
	
	public DateTime getEventTime(String eventId) {
		return eventTime.get(eventId);
	}
	
	public String getMaxPeriod(String eventId) {
		if (maxEventDiff.get(eventId)!=null) {
			return MMSSFormater.print(maxEventDiff.get(eventId));
		} else {
			return "N/A";
		}
	}
	
	public String getMinPeriod(String eventId) {
		if (minEventDiff.get(eventId)!=null) {
			return MMSSFormater.print(minEventDiff.get(eventId));
		} else {
			return "N/A";
		}
	}
}
