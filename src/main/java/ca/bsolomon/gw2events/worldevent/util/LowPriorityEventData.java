package ca.bsolomon.gw2events.worldevent.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;

import ca.bsolomon.gw2events.worldevent.enums.FireShamanEnum;

public class LowPriorityEventData  implements EventData {

	private static ConcurrentMap<String, String> eventStatus = new ConcurrentHashMap<String, String>(16, 0.9f, 1);
	private static ConcurrentMap<String, DateTime> eventTime = new ConcurrentHashMap<String, DateTime>(16, 0.9f, 1);
	
	private static ConcurrentMap<String, ConcurrentMap<String, DateTime>> lastActiveTime = new ConcurrentHashMap<>(16, 0.9f, 1);
	private static ConcurrentMap<String, ConcurrentMap<String, Period>> maxEventDiff = new ConcurrentHashMap<>(16, 0.9f, 1);
	private static ConcurrentMap<String, ConcurrentMap<String, Period>> minEventDiff = new ConcurrentHashMap<>(16, 0.9f, 1);

	private static Period MAXHOURS = new Period(5,0, 0, 0);
	
	public boolean addEventStatus(String serverId, String eventId, String status, DateTime time) {
		String composedId = serverId+"-"+eventId;
		
		if (eventStatus.containsKey(composedId)) {
			if (!eventStatus.get(composedId).equals(status)) {
				eventStatus.put(composedId, status);
				eventTime.put(composedId, time);
				
				if (status.equals("Active")) {
					EventWindowCalc.computeEventTiming(serverId, eventId, time, lastActiveTime, maxEventDiff, minEventDiff, MAXHOURS);
				} else if (status.equals("Success") || (eventId.equals(FireShamanEnum.SHAMAN.uid()) && status.equals("Warmup"))) {
					if (!lastActiveTime.containsKey(eventId)) {
						lastActiveTime.put(eventId, new ConcurrentHashMap<String, DateTime>(10, 0.9f, 1));
					}
					
					lastActiveTime.get(eventId).put(serverId, time);
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
}
