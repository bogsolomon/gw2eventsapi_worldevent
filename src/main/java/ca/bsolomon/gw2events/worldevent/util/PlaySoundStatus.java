package ca.bsolomon.gw2events.worldevent.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PlaySoundStatus {

	public static ConcurrentMap<String, Boolean> playSoundStatus = new ConcurrentHashMap<String, Boolean>(500, 0.9f, 1);
	
}
