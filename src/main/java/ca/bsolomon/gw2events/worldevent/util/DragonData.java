package ca.bsolomon.gw2events.worldevent.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.joda.time.DateTime;

public class DragonData {

	private static ConcurrentMap<String, String> dragonStatus = new ConcurrentHashMap<String, String>(16, 0.9f, 1);
	private static ConcurrentMap<String, DateTime> dragonTime = new ConcurrentHashMap<String, DateTime>(16, 0.9f, 1);

	public boolean addDragonStatus(String eventId, String status, DateTime time) {
		if (dragonStatus.containsKey(eventId)) {
			if (!dragonStatus.get(eventId).equals(status)) {
				dragonStatus.put(eventId, status);
				dragonTime.put(eventId, time);
				
				return true;
			}
		} else {
			dragonStatus.put(eventId, status);
			dragonTime.put(eventId, time);
			
			return true;
		}
		
		return false;
	}
	
	public void removeDragonStatus(String eventId, DateTime time) {
		dragonStatus.remove(eventId);
		dragonTime.put(eventId, time);
	}
	
	public String getDragonStatus(String eventId) {
		return dragonStatus.get(eventId);
	}
	
	public DateTime getDragonTime(String eventId) {
		return dragonTime.get(eventId);
	}
}
