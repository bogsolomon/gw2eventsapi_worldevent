package ca.bsolomon.gw2events.worldevent;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.GJChronology;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import ca.bsolomon.gw2event.api.GW2EventsAPI;
import ca.bsolomon.gw2events.worldevent.enums.DragonEvent;
import ca.bsolomon.gw2events.worldevent.enums.DredgeEvent;
import ca.bsolomon.gw2events.worldevent.enums.FireEleEvent;
import ca.bsolomon.gw2events.worldevent.enums.FireShamanEnum;
import ca.bsolomon.gw2events.worldevent.enums.FoulBearEvent;
import ca.bsolomon.gw2events.worldevent.enums.GolemEvent;
import ca.bsolomon.gw2events.worldevent.enums.HarathiChestEvent;
import ca.bsolomon.gw2events.worldevent.enums.HydraQueenEvent;
import ca.bsolomon.gw2events.worldevent.enums.JungleWurmEvent;
import ca.bsolomon.gw2events.worldevent.enums.KarkaEnum;
import ca.bsolomon.gw2events.worldevent.enums.MawEvent;
import ca.bsolomon.gw2events.worldevent.enums.ServerID;
import ca.bsolomon.gw2events.worldevent.enums.ShadowBehemothEvent;
import ca.bsolomon.gw2events.worldevent.util.EventData;
import ca.bsolomon.gw2events.worldevent.util.DragonData;
import ca.bsolomon.gw2events.worldevent.util.LowLevelEventData;
import ca.bsolomon.gw2events.worldevent.util.LowPriorityEventData;

public class DataRetrieveJob implements Job {
	
	private DragonData dragonData = new DragonData();
	private EventData lowLevelEventData = new LowLevelEventData();
	private LowPriorityEventData lowPriorityEventData = new LowPriorityEventData();
	
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		if (GW2EventsAPI.eventIdToName.size() == 0) {
			System.out.println("Generating Event IDs");
			GW2EventsAPI.generateEventIds();
		}
		
		DateTimeZone zone = DateTimeZone.forID("America/New_York");
		Chronology gregorianJuian = GJChronology.getInstance(zone);
		
		boolean teqChanged = false;
		boolean shatChanged = false;
		boolean jorChanged = false;
		boolean mawChanged = false;
		boolean fireeleChanged = false;
		boolean wurmChanged = false;
		boolean sbChanged = false;
		
		boolean golemChanged = false;
		boolean ogreChanged = false;
		boolean harathiChanged = false;
		boolean hydraChanged = false;
		boolean fireShamChanged = false;
		boolean dredgeChanged = false;
		
		boolean karkaChanged = false;
				
		for (ServerID servId:ServerID.values()) {
			String teqStatus = GW2EventsAPI.queryServer(servId.uid(), DragonEvent.TEQUATL.uid());
			if (dragonData.addEventStatus(servId.uid()+"-"+DragonEvent.TEQUATL.uid(), teqStatus, new DateTime(gregorianJuian))) {
				teqChanged = true;
			}
		}
		
		for (ServerID servId:ServerID.values()) {
			String shatEscStatus = GW2EventsAPI.queryServer(servId.uid(), DragonEvent.SHATTERER_ESCORT.uid());
			String shatSiegeStatus = GW2EventsAPI.queryServer(servId.uid(), DragonEvent.SHATTERER_SIEGE.uid());
			String shatStatus = GW2EventsAPI.queryServer(servId.uid(), DragonEvent.SHATTERER_UP.uid());
			
			if (dragonData.addEventStatus(servId.uid()+"-"+DragonEvent.SHATTERER_ESCORT.uid(), shatEscStatus, new DateTime(gregorianJuian))) {
				shatChanged = true;
			} 
			
			if (dragonData.addEventStatus(servId.uid()+"-"+DragonEvent.SHATTERER_SIEGE.uid(), shatSiegeStatus, new DateTime(gregorianJuian))) {
				shatChanged = true;
			}
			
			if (dragonData.addEventStatus(servId.uid()+"-"+DragonEvent.SHATTERER_UP.uid(), shatStatus, new DateTime(gregorianJuian))) {
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
			
			if (dragonData.addEventStatus(servId.uid()+"-"+DragonEvent.JORMAG_CRYSTAL1.uid(), jorCrystal1, new DateTime(gregorianJuian))) {
				jorChanged = true;
			} 

			if (dragonData.addEventStatus(servId.uid()+"-"+DragonEvent.JORMAG_CRYSTAL2.uid(), jorCrystal2, new DateTime(gregorianJuian))) {
				jorChanged = true;
			} 
			
			if (dragonData.addEventStatus(servId.uid()+"-"+DragonEvent.JORMAG_CRYSTAL3.uid(), jorCrystal3, new DateTime(gregorianJuian))) {
				jorChanged = true;
			} 
			
			if (dragonData.addEventStatus(servId.uid()+"-"+DragonEvent.JORMAG_CRYSTAL4.uid(), jorCrystal4, new DateTime(gregorianJuian))) {
				jorChanged = true;
			} 
			
			if (dragonData.addEventStatus(servId.uid()+"-"+DragonEvent.JORMAG_CRYSTALF.uid(), jorCrystalF, new DateTime(gregorianJuian))) {
				jorChanged = true;
			} 
			
			if (dragonData.addEventStatus(servId.uid()+"-"+DragonEvent.JORMAG_UP.uid(), jorUp, new DateTime(gregorianJuian))) {
				jorChanged = true;
			}
		}
		
		for (ServerID servId:ServerID.values()) {
			for (MawEvent eventId:MawEvent.values()) {
				String status = GW2EventsAPI.queryServer(servId.uid(), eventId.uid());
				
				if (lowLevelEventData.addEventStatus(servId.uid()+"-"+eventId.uid(), status, new DateTime(gregorianJuian))) {
					mawChanged = true;
				} 
			}
			
			for (FireEleEvent eventId:FireEleEvent.values()) {
				String status = GW2EventsAPI.queryServer(servId.uid(), eventId.uid());
				
				if (lowLevelEventData.addEventStatus(servId.uid()+"-"+eventId.uid(), status, new DateTime(gregorianJuian))) {
					fireeleChanged = true;
				} 
			}
			
			for (JungleWurmEvent eventId:JungleWurmEvent.values()) {
				String status = GW2EventsAPI.queryServer(servId.uid(), eventId.uid());
				
				if (lowLevelEventData.addEventStatus(servId.uid()+"-"+eventId.uid(), status, new DateTime(gregorianJuian))) {
					wurmChanged = true;
				} 
			}
			
			for (ShadowBehemothEvent eventId:ShadowBehemothEvent.values()) {
				String status = GW2EventsAPI.queryServer(servId.uid(), eventId.uid());
				
				if (lowLevelEventData.addEventStatus(servId.uid()+"-"+eventId.uid(), status, new DateTime(gregorianJuian))) {
					sbChanged = true;
				} 
			}
		}
		
		for (ServerID servId:ServerID.values()) {
			for (GolemEvent eventId:GolemEvent.values()) {
				String status = GW2EventsAPI.queryServer(servId.uid(), eventId.uid());
				
				if (lowPriorityEventData.addEventStatus(servId.uid()+"-"+eventId.uid(), status, new DateTime(gregorianJuian))) {
					golemChanged = true;
				} 
			}
			
			for (DredgeEvent eventId:DredgeEvent.values()) {
				String status = GW2EventsAPI.queryServer(servId.uid(), eventId.uid());
				
				if (lowPriorityEventData.addEventStatus(servId.uid()+"-"+eventId.uid(), status, new DateTime(gregorianJuian))) {
					dredgeChanged = true;
				} 
			}
			
			for (HarathiChestEvent eventId:HarathiChestEvent.values()) {
				String status = GW2EventsAPI.queryServer(servId.uid(), eventId.uid());
				
				if (lowPriorityEventData.addEventStatus(servId.uid()+"-"+eventId.uid(), status, new DateTime(gregorianJuian))) {
					harathiChanged = true;
				} 
			}
			
			for (FoulBearEvent eventId:FoulBearEvent.values()) {
				String status = GW2EventsAPI.queryServer(servId.uid(), eventId.uid());
				
				if (lowPriorityEventData.addEventStatus(servId.uid()+"-"+eventId.uid(), status, new DateTime(gregorianJuian))) {
					ogreChanged = true;
				} 
			}
			
			for (HydraQueenEvent eventId:HydraQueenEvent.values()) {
				String status = GW2EventsAPI.queryServer(servId.uid(), eventId.uid());
				
				if (lowPriorityEventData.addEventStatus(servId.uid()+"-"+eventId.uid(), status, new DateTime(gregorianJuian))) {
					hydraChanged = true;
				} 
			}
			
			for (FireShamanEnum eventId:FireShamanEnum.values()) {
				String status = GW2EventsAPI.queryServer(servId.uid(), eventId.uid());
				
				if (lowPriorityEventData.addEventStatus(servId.uid()+"-"+eventId.uid(), status, new DateTime(gregorianJuian))) {
					fireShamChanged = true;
				} 
			}
			
			for (KarkaEnum eventId:KarkaEnum.values()) {
				String status = GW2EventsAPI.queryServer(servId.uid(), eventId.uid());
				
				if (lowPriorityEventData.addEventStatus(servId.uid()+"-"+eventId.uid(), status, new DateTime(gregorianJuian))) {
					karkaChanged = true;
				} 
			}
		}
		
		if (teqChanged) {
			DragonEvent.formatTequatlString(dragonData);
		}
		
		if (shatChanged) {
			DragonEvent.formatShattererString(dragonData);
		}
		
		if (jorChanged) {
			DragonEvent.formatJormagString(dragonData);
		}
		
		if (fireeleChanged) {
			FireEleEvent.formatFireEleString(lowLevelEventData);
		}
		
		if (mawChanged) {
			MawEvent.formatMawString(lowLevelEventData);
		}
		
		if (wurmChanged) {
			JungleWurmEvent.formatJungleWurmString(lowLevelEventData);
		}
		
		if (sbChanged) {
			ShadowBehemothEvent.formatShadowBehemothString(lowLevelEventData);
		}
		
		if (golemChanged) {
			GolemEvent.formatGolemString(lowPriorityEventData);
		}
		
		if (dredgeChanged) {
			DredgeEvent.formatDredgeString(lowPriorityEventData);
		}
		
		if (ogreChanged) {
			FoulBearEvent.formatOgreString(lowPriorityEventData);
		}
		
		if (harathiChanged) {
			HarathiChestEvent.formatHarathiString(lowPriorityEventData);
		}
		
		if (hydraChanged) {
			HydraQueenEvent.formatHydraString(lowPriorityEventData);
		}
		
		if (fireShamChanged) {
			FireShamanEnum.formatShamanString(lowPriorityEventData);
		}
		
		if (karkaChanged) {
			KarkaEnum.formatKarkaString(lowPriorityEventData);
		}
		
		
	}
}