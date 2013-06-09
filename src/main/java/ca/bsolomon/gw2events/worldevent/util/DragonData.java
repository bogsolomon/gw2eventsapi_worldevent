package ca.bsolomon.gw2events.worldevent.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;

import ca.bsolomon.gw2events.worldevent.enums.DragonEvent;

public class DragonData implements EventData{

	private static ConcurrentMap<String, String> dragonStatus = new ConcurrentHashMap<String, String>(16, 0.9f, 1);
	private static ConcurrentMap<String, DateTime> dragonTime = new ConcurrentHashMap<String, DateTime>(16, 0.9f, 1);
	
	private static ConcurrentMap<String, ConcurrentMap<String, DateTime>> lastActiveTime = new ConcurrentHashMap<>(16, 0.9f, 1);
	private static ConcurrentMap<String, ConcurrentMap<String, Period>> maxEventDiff = new ConcurrentHashMap<>(16, 0.9f, 1);
	private static ConcurrentMap<String, ConcurrentMap<String, Period>> minEventDiff = new ConcurrentHashMap<>(16, 0.9f, 1);

	private static Period MAXHOURS = new Period(5,0, 0, 0);

	public boolean addEventStatus(String serverId, String eventId, String status, DateTime time) {
		String composedId = serverId+"-"+eventId;
		
		if (dragonStatus.containsKey(composedId)) {
			if (!dragonStatus.get(composedId).equals(status)) {
				dragonStatus.put(composedId, status);
				dragonTime.put(composedId, time);
				
				if (status.equals("Active")) {
					EventWindowCalc.computeEventTiming(serverId, eventId, time, lastActiveTime, maxEventDiff, minEventDiff, MAXHOURS);
				} else if (status.equals("Success") || (eventId.equals(DragonEvent.TEQUATL.uid()) && status.equals("Warmup"))) {
					if (!lastActiveTime.containsKey(eventId)) {
						lastActiveTime.put(eventId, new ConcurrentHashMap<String, DateTime>(10, 0.9f, 1));
					}
					
					lastActiveTime.get(eventId).put(serverId, time);
				}
				
				return true;
			}
		} else {
			dragonStatus.put(composedId, status);
			dragonTime.put(composedId, time);
			
			return true;
		}
		
		return false;
	}
	
	public void removeDragonStatus(String eventId, DateTime time) {
		dragonStatus.remove(eventId);
		dragonTime.put(eventId, time);
	}
	
	public String getEventStatus(String eventId) {
		return dragonStatus.get(eventId);
	}
	
	public DateTime getEventTime(String eventId) {
		return dragonTime.get(eventId);
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
