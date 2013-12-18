package ca.bsolomon.gw2events.worldevent.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;

import ca.bsolomon.gw2events.worldevent.dynamodb.EventWindowData;

public class EventWindowCalc {

	public static void computeEventTiming(String serverId, String eventId, DateTime time, ConcurrentMap<String, ConcurrentMap<String, DateTime>> lastActiveTime,
			ConcurrentMap<String, ConcurrentMap<String, Period>>  maxEventDiff, ConcurrentMap<String, ConcurrentMap<String, Period>>  minEventDiff,  Period MAXHOURS) {
		if (lastActiveTime.containsKey(eventId)) {
			ConcurrentMap<String, DateTime> servTimes = lastActiveTime.get(eventId);
			DateTime startEvent = servTimes.get(serverId); 
			
			if (startEvent != null) {
				Period p = new Period(startEvent, time);
							
				if (maxEventDiff.containsKey(eventId) && maxEventDiff.get(eventId) != null) {
					if (maxEventDiff.get(eventId).get(serverId) != null) {
						Duration maxD = maxEventDiff.get(eventId).get(serverId).toDurationFrom(time);
						Duration currD = p.toDurationFrom(time);
						Duration threeHoursD = MAXHOURS.toDurationFrom(time);
						
						if (currD.isLongerThan(maxD) && currD.isShorterThan(threeHoursD)) {
							maxEventDiff.get(eventId).put(serverId, p);
							EventWindowData.updateMaxEventWindow(eventId, p);
						}
					} else {
						maxEventDiff.get(eventId).put(serverId, p);
						EventWindowData.updateMaxEventWindow(eventId, p);
					}
				} else {
					maxEventDiff.put(eventId, new ConcurrentHashMap<String, Period>(10, 0.9f, 1));
					maxEventDiff.get(eventId).put(serverId, p);
					EventWindowData.updateMaxEventWindow(eventId, p);
				}
				
				if (minEventDiff.containsKey(eventId) && minEventDiff.get(eventId) != null) {
					if (minEventDiff.get(eventId).get(serverId) != null) {
						Duration minD = minEventDiff.get(eventId).get(serverId).toDurationFrom(time);
						Duration currD = p.toDurationFrom(time);
						
						if (currD.isShorterThan(minD)) {
							minEventDiff.get(eventId).put(serverId, p);
							EventWindowData.updateMinEventWindow(eventId, p);
						}
					} else {
						minEventDiff.get(eventId).put(serverId, p);
						EventWindowData.updateMinEventWindow(eventId, p);
					}
				} else {
					minEventDiff.put(eventId, new ConcurrentHashMap<String, Period>(10, 0.9f, 1));
					minEventDiff.get(eventId).put(serverId, p);
					EventWindowData.updateMinEventWindow(eventId, p);
				}
			}
		}
	}
	
}
