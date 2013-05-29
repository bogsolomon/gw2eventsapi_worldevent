package ca.bsolomon.gw2events.worldevent.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DragonData {

	private static ConcurrentMap<String, String> dragonStatus = new ConcurrentHashMap<String, String>();
	private static ConcurrentMap<String, String> dragonTime = new ConcurrentHashMap<String, String>();

	public boolean addDragonStatus(String eventId, String status, String time) {
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
	
	public void removeDragonStatus(String eventId, String time) {
		dragonStatus.remove(eventId);
		dragonTime.put(eventId, time);
	}
	
	public String getDragonStatus(String eventId) {
		return dragonStatus.get(eventId);
	}
	
	public String getDragomTime(String eventId) {
		return dragonTime.get(eventId);
	}
}
