package ca.bsolomon.gw2events.worldevent.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;

import ca.bsolomon.gw2events.worldevent.temples.enums.ScarlettEvent;

public class TempleEventData implements EventData {

	private static ConcurrentMap<String, String> eventStatus = new ConcurrentHashMap<String, String>(16, 0.9f, 1);
	private static ConcurrentMap<String, DateTime> eventTime = new ConcurrentHashMap<String, DateTime>(16, 0.9f, 1);
	
	private static ConcurrentMap<String, ConcurrentMap<String, DateTime>> lastActiveTime = new ConcurrentHashMap<>(16, 0.9f, 1);
	private static ConcurrentMap<String, ConcurrentMap<String, Period>> maxEventDiff = new ConcurrentHashMap<>(16, 0.9f, 1);
	private static ConcurrentMap<String, ConcurrentMap<String, Period>> minEventDiff = new ConcurrentHashMap<>(16, 0.9f, 1);

	private static Period MAXHOURS = new Period(12,0, 0, 0);
	
	/* (non-Javadoc)
	 * @see ca.bsolomon.gw2events.worldevent.util.EventData#addEventStatus(java.lang.String, java.lang.String, org.joda.time.DateTime)
	 */
	public boolean addEventStatus(String serverId, String eventId, String status, DateTime time) {
		String composedId = serverId+"-"+eventId;
		
		if (eventStatus.containsKey(composedId)) {
			if (!eventStatus.get(composedId).equals(status)) {
				eventStatus.put(composedId, status);
				eventTime.put(composedId, time);
				
				boolean isScarlettEvent = false;
				
				for (ScarlettEvent event:ScarlettEvent.values()) {
					if (event.uid().equals(eventId)) {
						isScarlettEvent = true;
						break;
					}
				}
				
				if (isScarlettEvent) {
					if (status.equals("Active")) {
						EventWindowCalc.computeEventTiming(serverId, "Scarlett", time, lastActiveTime, maxEventDiff, minEventDiff, MAXHOURS);
					}
					
					if (!lastActiveTime.containsKey("Scarlett")) {
						lastActiveTime.put("Scarlett", new ConcurrentHashMap<String, DateTime>(10, 0.9f, 1));
					}
					
					lastActiveTime.get("Scarlett").put(serverId, time);
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
		if (maxEventDiff.get(eventId)!=null) {
			DateTime time = new DateTime(gregorianJuian);
			
			Duration maxD = null;
			Period maxP = null;
			
			for (Period p:maxEventDiff.get(eventId).values()) {
				Duration currD = p.toDurationFrom(time);
				
				if (maxD == null) {
					maxD = currD;
					maxP = p;
				} else if (currD.isLongerThan(maxD)) {
					maxD = currD;
					maxP = p;
				}
			}
			
			if (maxP != null) {
				return HHMMSSFormater.print(maxP);
			}
		} 
		
		return "N/A";
	}
	
	public String getMinPeriod(String eventId) {
		if (minEventDiff.get(eventId)!=null) {
			DateTime time = new DateTime(gregorianJuian);
			
			Duration minD = null;
			Period minP = null;
			
			for (Period p:minEventDiff.get(eventId).values()) {
				Duration currD = p.toDurationFrom(time);
				
				if (minD == null) {
					minD = currD;
					minP = p;
				} else if (currD.isShorterThan(minD)) {
					minD = currD;
					minP = p;
				}
			}
			if (minP!= null) {
				return HHMMSSFormater.print(minP);
			}
		}
		
		return "N/A";
	}
}
