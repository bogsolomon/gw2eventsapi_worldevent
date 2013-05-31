package ca.bsolomon.gw2events.worldevent;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.GJChronology;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import ca.bsolomon.gw2events.worldevent.util.DragonEvent;
import ca.bsolomon.gw2events.worldevent.util.EventStringFormatter;
import ca.bsolomon.gw2events.worldevent.util.GW2EventsAPI;
import ca.bsolomon.gw2events.worldevent.util.DragonData;
import ca.bsolomon.gw2events.worldevent.util.LowLevelEventData;
import ca.bsolomon.gw2events.worldevent.util.MawEvent;
import ca.bsolomon.gw2events.worldevent.util.ServerID;

public class DataRetrieveJob implements Job {
	
	private DragonData dragonData = new DragonData();
	private LowLevelEventData lowLevelEventData = new LowLevelEventData();
	
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		if (GW2EventsAPI.eventIdToName.size() == 0) {
			System.out.println("Generating Event IDs");
			GW2EventsAPI.generateEventIds();
		}
		
		DateTimeZone zone = DateTimeZone.forID("America/New_York");
		Chronology gregorianJuian = GJChronology.getInstance(zone);
		DateTime time = new DateTime(gregorianJuian);
		
		//format.setCalendar(cal);
		//String time = format.format(cal.getTime());
		
		boolean teqChanged = false;
		boolean shatChanged = false;
		boolean jorChanged = false;
		boolean mawChanged = false;
				
		for (ServerID servId:ServerID.values()) {
			String teqStatus = GW2EventsAPI.queryServer(servId.uid(), DragonEvent.TEQUATL.uid());
			if (dragonData.addDragonStatus(servId.uid()+"-"+DragonEvent.TEQUATL.uid(), teqStatus, time)) {
				teqChanged = true;
			}
		}
		
		for (ServerID servId:ServerID.values()) {
			String shatEscStatus = GW2EventsAPI.queryServer(servId.uid(), DragonEvent.SHATTERER_ESCORT.uid());
			String shatSiegeStatus = GW2EventsAPI.queryServer(servId.uid(), DragonEvent.SHATTERER_SIEGE.uid());
			String shatStatus = GW2EventsAPI.queryServer(servId.uid(), DragonEvent.SHATTERER_UP.uid());
			
			if (dragonData.addDragonStatus(servId.uid()+"-"+DragonEvent.SHATTERER_ESCORT.uid(), shatEscStatus, time)) {
				shatChanged = true;
			} 
			
			if (dragonData.addDragonStatus(servId.uid()+"-"+DragonEvent.SHATTERER_SIEGE.uid(), shatSiegeStatus, time)) {
				shatChanged = true;
			}
			
			if (dragonData.addDragonStatus(servId.uid()+"-"+DragonEvent.SHATTERER_UP.uid(), shatStatus, time)) {
				shatChanged = true;
			}
		}
		
		for (ServerID servId:ServerID.values()) {
			String jorCrystal1 = GW2EventsAPI.queryServer(servId.uid(), DragonEvent.JORMAG_CRYSTAL1.uid());
			String jorCrystal2 = GW2EventsAPI.queryServer(servId.uid(), DragonEvent.JORMAG_CRYSTAL2.uid());
			String jorCrystal3 = GW2EventsAPI.queryServer(servId.uid(), DragonEvent.JORMAG_CRYSTAL3.uid());
			String jorCrystal4 = GW2EventsAPI.queryServer(servId.uid(), DragonEvent.JORMAG_CRYSTAL4.uid());
			String jorCrystalF = GW2EventsAPI.queryServer(servId.uid(), DragonEvent.JORMAG_CRYSTALF.uid());
			String jorUp = GW2EventsAPI.queryServer(servId.uid(), DragonEvent.JORMAG_UP.uid());
			
			if (dragonData.addDragonStatus(servId.uid()+"-"+DragonEvent.JORMAG_CRYSTAL1.uid(), jorCrystal1, time)) {
				jorChanged = true;
			} 

			if (dragonData.addDragonStatus(servId.uid()+"-"+DragonEvent.JORMAG_CRYSTAL2.uid(), jorCrystal2, time)) {
				jorChanged = true;
			} 
			
			if (dragonData.addDragonStatus(servId.uid()+"-"+DragonEvent.JORMAG_CRYSTAL3.uid(), jorCrystal3, time)) {
				jorChanged = true;
			} 
			
			if (dragonData.addDragonStatus(servId.uid()+"-"+DragonEvent.JORMAG_CRYSTAL4.uid(), jorCrystal4, time)) {
				jorChanged = true;
			} 
			
			if (dragonData.addDragonStatus(servId.uid()+"-"+DragonEvent.JORMAG_CRYSTALF.uid(), jorCrystalF, time)) {
				jorChanged = true;
			} 
			
			if (dragonData.addDragonStatus(servId.uid()+"-"+DragonEvent.JORMAG_UP.uid(), jorUp, time)) {
				jorChanged = true;
			}
		}
		
		for (ServerID servId:ServerID.values()) {
			for (MawEvent eventId:MawEvent.values()) {
				String status = GW2EventsAPI.queryServer(servId.uid(), eventId.uid());
				
				if (lowLevelEventData.addEventStatus(servId.uid()+"-"+eventId.uid(), status, time)) {
					mawChanged = true;
				} 
			}
		}
		
		/*if (teqChanged) {
			String teqStatusString = EventStringFormatter.formatTequatlString(dragonData);
			
			pushContext.push("/tequatl", teqStatusString);
		}
		
		if (shatChanged) {
			String shatStatusString = EventStringFormatter.formatShattererString(dragonData);
			
			pushContext.push("/shatterer", shatStatusString);
		}
		
		if (jorChanged) {
			String jormagStatusString = EventStringFormatter.formatJormagString(dragonData);
			
			pushContext.push("/jormag", jormagStatusString);
		}
		
		if (mawChanged) {
			String mawStatusString = EventStringFormatter.formatMawString(lowLevelEventData);
			
			pushContext.push("/maw", mawStatusString);
		}*/
	}
}

