package ca.bsolomon.gw2events.worldevent;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
	
	private GW2EventsAPI api = new GW2EventsAPI();
	
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
		
		List<String> serverIds = new ArrayList<>();
		
		for (ServerID servId:ServerID.values()) {
			serverIds.add(servId.uid()+"");
		}
	
		//System.out.println("Starting Thread retrieve "+this.toString()+" at "+new DateTime(gregorianJuian));
		
		teqChanged = queryEvent(gregorianJuian, serverIds, DragonEvent.TEQUATL.uid(), dragonData);
		
		shatChanged = queryEvent(gregorianJuian, serverIds, DragonEvent.SHATTERER_ESCORT.uid(), dragonData);
		shatChanged = shatChanged || queryEvent(gregorianJuian, serverIds, DragonEvent.SHATTERER_SIEGE.uid(), dragonData);
		shatChanged = shatChanged || queryEvent(gregorianJuian, serverIds, DragonEvent.SHATTERER_UP.uid(), dragonData);

		jorChanged = queryEvent(gregorianJuian, serverIds, DragonEvent.JORMAG_CRYSTAL1.uid(), dragonData);
		jorChanged = jorChanged || queryEvent(gregorianJuian, serverIds, DragonEvent.JORMAG_CRYSTAL2.uid(), dragonData);
		jorChanged = jorChanged || queryEvent(gregorianJuian, serverIds, DragonEvent.JORMAG_CRYSTAL3.uid(), dragonData);
		jorChanged = jorChanged || queryEvent(gregorianJuian, serverIds, DragonEvent.JORMAG_CRYSTAL4.uid(), dragonData);
		jorChanged = jorChanged || queryEvent(gregorianJuian, serverIds, DragonEvent.JORMAG_CRYSTAL4.uid(), dragonData);
		jorChanged = jorChanged || queryEvent(gregorianJuian, serverIds, DragonEvent.JORMAG_UP.uid(), dragonData);
		
		for (MawEvent eventId:MawEvent.values()) {
			mawChanged = mawChanged || queryEvent(gregorianJuian, serverIds, eventId.uid(), lowLevelEventData); 
		}
		
		for (FireEleEvent eventId:FireEleEvent.values()) {
			fireeleChanged = fireeleChanged || queryEvent(gregorianJuian, serverIds, eventId.uid(), lowLevelEventData); 
		}
		
		for (JungleWurmEvent eventId:JungleWurmEvent.values()) {
			wurmChanged = wurmChanged || queryEvent(gregorianJuian, serverIds, eventId.uid(), lowLevelEventData); 
		}
		
		for (ShadowBehemothEvent eventId:ShadowBehemothEvent.values()) {
			sbChanged = sbChanged || queryEvent(gregorianJuian, serverIds, eventId.uid(), lowLevelEventData); 
		}
		
		for (GolemEvent eventId:GolemEvent.values()) {
			golemChanged = golemChanged || queryEvent(gregorianJuian, serverIds, eventId.uid(), lowPriorityEventData);  
		}
		
		for (DredgeEvent eventId:DredgeEvent.values()) {
			dredgeChanged = dredgeChanged || queryEvent(gregorianJuian, serverIds, eventId.uid(), lowPriorityEventData);  
		}
		
		for (HarathiChestEvent eventId:HarathiChestEvent.values()) {
			harathiChanged = harathiChanged || queryEvent(gregorianJuian, serverIds, eventId.uid(), lowPriorityEventData);  
		}
		
		for (FoulBearEvent eventId:FoulBearEvent.values()) {
			ogreChanged = ogreChanged || queryEvent(gregorianJuian, serverIds, eventId.uid(), lowPriorityEventData);  
		}
		
		for (HydraQueenEvent eventId:HydraQueenEvent.values()) {
			hydraChanged = hydraChanged || queryEvent(gregorianJuian, serverIds, eventId.uid(), lowPriorityEventData);  
		}
		
		for (FireShamanEnum eventId:FireShamanEnum.values()) {
			fireShamChanged = fireShamChanged || queryEvent(gregorianJuian, serverIds, eventId.uid(), lowPriorityEventData);  
		}
		
		for (KarkaEnum eventId:KarkaEnum.values()) {
			karkaChanged = karkaChanged || queryEvent(gregorianJuian, serverIds, eventId.uid(), lowPriorityEventData);  
		}
		
		//System.out.println("Ended Thread retrieve "+this.toString()+" at "+new DateTime(gregorianJuian));
		
		DragonEvent.formatTequatlString(dragonData);
		DragonEvent.formatShattererString(dragonData);
		DragonEvent.formatJormagString(dragonData);
		FireEleEvent.formatFireEleString(lowLevelEventData);
		MawEvent.formatMawString(lowLevelEventData);
		JungleWurmEvent.formatJungleWurmString(lowLevelEventData);
		ShadowBehemothEvent.formatShadowBehemothString(lowLevelEventData);
		GolemEvent.formatGolemString(lowPriorityEventData);
		DredgeEvent.formatDredgeString(lowPriorityEventData);
		FoulBearEvent.formatOgreString(lowPriorityEventData);
		HarathiChestEvent.formatHarathiString(lowPriorityEventData);
		HydraQueenEvent.formatHydraString(lowPriorityEventData);
		FireShamanEnum.formatShamanString(lowPriorityEventData);
		KarkaEnum.formatKarkaString(lowPriorityEventData);
		
		//System.out.println("Ended Thread formating "+this.toString()+" at "+new DateTime(gregorianJuian));
	}

	private boolean queryEvent(Chronology gregorianJuian,
			List<String> serverIds, String eventUID, EventData dataStructure) {
		JSONArray data = api.queryServer(eventUID);
		boolean changed = false;
		
		for (int i=0; i<data.size(); i++) {
			JSONObject obj = data.getJSONObject(i);
			
			String serverId = obj.getString("world_id");
			
			if (serverIds.contains(serverId)) {
				String status = obj.getString("state");
				if (dataStructure.addEventStatus(serverId, eventUID, status, new DateTime(gregorianJuian))) {
					changed = true;
				}
			}
		}
		return changed;
	}
}