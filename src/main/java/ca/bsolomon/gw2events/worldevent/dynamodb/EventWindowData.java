package ca.bsolomon.gw2events.worldevent.dynamodb;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.chrono.GJChronology;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import ca.bsolomon.gw2events.worldevent.dynamodb.model.EventWindow;

public class EventWindowData {

	private static Map<String, Period> minEventWindows = new ConcurrentHashMap<String, Period>();
	private static Map<String, Period> maxEventWindows = new ConcurrentHashMap<String, Period>();
	private static DynamoDbService dbService = new DynamoDbService();
	private static boolean initalized = false; 
	private static Object lock = new Object();
	private static DateTimeZone zone = DateTimeZone.forID("America/New_York");
	private static Chronology gregorianJulian = GJChronology.getInstance(zone);
	
	public static PeriodFormatter HHMMSSFormater = new PeriodFormatterBuilder().
			printZeroAlways().minimumPrintedDigits(2).
			appendHours().appendSeparator(":").
			appendMinutes().appendSeparator(":").
			appendSeconds().toFormatter();
	
	public static void loadInitialData() {
		synchronized (lock) {
			if (initalized) {
				return;
			} else {
				initalized = true;
			}
		}
		
		try {
			dbService.initDynamoDbClient();
			List<EventWindow> eventWindows = dbService.getAllEventWindows();
			
			for (EventWindow window:eventWindows) {
				String minWindow = window.getMinWindow();
				String maxWindow = window.getMaxWindow();
				
				Period maxPeriod = new Period(maxWindow);
				Period minPeriod = new Period(minWindow);
				
				minEventWindows.put(window.getEventUID(), minPeriod);
				maxEventWindows.put(window.getEventUID(), maxPeriod);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateMaxEventWindow(String eventUID, Period maxWindow) {
		DateTime time = new DateTime(gregorianJulian);
		
		Period minWindow = minEventWindows.get(eventUID);
		String minWindowStr;
		
		if (minWindow == null)
			minWindowStr = "N/A";
		else
			minWindowStr = minWindow.toString();
		
		if (maxEventWindows.containsKey(eventUID)) {
			Period prevMaxWindow = maxEventWindows.get(eventUID);
			Duration prevDuration = prevMaxWindow.toDurationFrom(time);
			Duration currentDuration = maxWindow.toDurationFrom(time);
			
			if (prevDuration.isShorterThan(currentDuration)) {
				maxEventWindows.put(eventUID, maxWindow);
				
				EventWindow window = new EventWindow(eventUID, maxWindow.toString(), minWindowStr);
				dbService.updateEventWindow(window);
			}
		} else {
			maxEventWindows.put(eventUID, maxWindow);
			
			EventWindow window = new EventWindow(eventUID, maxWindow.toString(), minWindowStr);
			dbService.updateEventWindow(window);
		}
	}
	
	public static void updateMinEventWindow(String eventUID, Period minWindow) {
		DateTime time = new DateTime(gregorianJulian);
		
		Period maxWindow = maxEventWindows.get(eventUID);
		String maxWindowStr;
		
		if (maxWindow == null)
			maxWindowStr = "N/A";
		else
			maxWindowStr = maxWindow.toString();
		
		if (minEventWindows.containsKey(eventUID)) {
			Period prevMinWindow = minEventWindows.get(eventUID);
			Duration prevDuration = prevMinWindow.toDurationFrom(time);
			Duration currentDuration = minWindow.toDurationFrom(time);
			
			if (prevDuration.isLongerThan(currentDuration)) {
				minEventWindows.put(eventUID, minWindow);
				
				EventWindow window = new EventWindow(eventUID, maxWindowStr, minWindow.toString());
				dbService.updateEventWindow(window);
			}
		} else {
			minEventWindows.put(eventUID, minWindow);
			
			EventWindow window = new EventWindow(eventUID, maxWindowStr, minWindow.toString());
			dbService.updateEventWindow(window);
		}
	}

	public static String getMinWindow(String eventUID) {
		if (minEventWindows.containsKey(eventUID)) {
			return HHMMSSFormater.print(minEventWindows.get(eventUID));
		} else {
			return "N/A";
		}
	}
	
	public static String getMaxWindow(String eventUID) {
		if (maxEventWindows.containsKey(eventUID)) {
			return HHMMSSFormater.print(maxEventWindows.get(eventUID));
		} else {
			return "N/A";
		}
	}
}
