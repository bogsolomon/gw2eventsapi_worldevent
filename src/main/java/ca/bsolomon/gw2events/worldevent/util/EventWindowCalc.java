package ca.bsolomon.gw2events.worldevent.util;

import java.util.concurrent.ConcurrentMap;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;

public class EventWindowCalc {

	public static void computeEventTiming(String eventId, DateTime time, ConcurrentMap<String, DateTime> lastActiveTime,
			ConcurrentMap<String, Period> maxEventDiff, ConcurrentMap<String, Period> minEventDiff,  Period MAXHOURS) {
		if (lastActiveTime.containsKey(eventId)) {
			DateTime startEvent = lastActiveTime.get(eventId);
			
			Period p = new Period(startEvent, time);
						
			if (maxEventDiff.containsKey(eventId)) {
				if (maxEventDiff.get(eventId) != null) {
					Duration maxD = maxEventDiff.get(eventId).toDurationFrom(time);
					Duration currD = p.toDurationFrom(time);
					Duration threeHoursD = MAXHOURS.toDurationFrom(time);
					
					if (currD.isLongerThan(maxD) && currD.isShorterThan(threeHoursD)) {
						maxEventDiff.put(eventId, p);
					}
				}
			} else {
				maxEventDiff.put(eventId, p);
			}
			
			if (minEventDiff.containsKey(eventId)) {
				if (minEventDiff.get(eventId) != null) {
					Duration minD = minEventDiff.get(eventId).toDurationFrom(time);
					Duration currD = p.toDurationFrom(time);
					
					if (currD.isShorterThan(minD)) {
						minEventDiff.put(eventId, p);
					}
				}
			} else {
				minEventDiff.put(eventId, p);
			}
		}
		
		lastActiveTime.put(eventId, time);
	}
	
}
