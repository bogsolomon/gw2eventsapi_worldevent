package ca.bsolomon.gw2events.worldevent;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.GJChronology;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import ca.bsolomon.gw2events.worldevent.enums.FireEleEvent;
import ca.bsolomon.gw2events.worldevent.enums.JungleWurmEvent;
import ca.bsolomon.gw2events.worldevent.enums.MawEvent;
import ca.bsolomon.gw2events.worldevent.enums.ServerID;
import ca.bsolomon.gw2events.worldevent.enums.ShadowBehemothEvent;
import ca.bsolomon.gw2events.worldevent.util.EventData;
import ca.bsolomon.gw2events.worldevent.util.LowLevelEventData;

@DisallowConcurrentExecution
public class LowLevelDataRetrieveJob extends DataRetrieveJob implements Job {
	
	private EventData lowLevelEventData = new LowLevelEventData();
	
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		
		DateTimeZone zone = DateTimeZone.forID("America/New_York");
		Chronology gregorianJuian = GJChronology.getInstance(zone);
		
		List<String> serverIds = new ArrayList<>();
		
		for (ServerID servId:ServerID.values()) {
			serverIds.add(servId.getUid()+"");
		}
	
		//System.out.println("Starting LowLevel Thread retrieve "+this.toString()+" at "+new DateTime(gregorianJuian));
		
		for (MawEvent eventId:MawEvent.values()) {
			queryEvent(gregorianJuian, serverIds, eventId.uid(), lowLevelEventData); 
		}
		
		for (FireEleEvent eventId:FireEleEvent.values()) {
			queryEvent(gregorianJuian, serverIds, eventId.uid(), lowLevelEventData); 
		}
		
		for (JungleWurmEvent eventId:JungleWurmEvent.values()) {
			queryEvent(gregorianJuian, serverIds, eventId.uid(), lowLevelEventData); 
		}
		
		for (ShadowBehemothEvent eventId:ShadowBehemothEvent.values()) {
			queryEvent(gregorianJuian, serverIds, eventId.uid(), lowLevelEventData); 
		}
		
		//System.out.println("Ended LowLevel Thread retrieve "+this.toString()+" at "+new DateTime(gregorianJuian));
		
		FireEleEvent.formatFireEleString(lowLevelEventData);
		MawEvent.formatMawString(lowLevelEventData);
		JungleWurmEvent.formatJungleWurmString(lowLevelEventData);
		ShadowBehemothEvent.formatShadowBehemothString(lowLevelEventData);
		
		//System.out.println("Ended LowLevel Thread formating "+this.toString()+" at "+new DateTime(gregorianJuian));
	}
}
