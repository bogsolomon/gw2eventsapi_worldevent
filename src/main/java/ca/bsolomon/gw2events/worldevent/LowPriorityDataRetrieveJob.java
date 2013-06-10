package ca.bsolomon.gw2events.worldevent;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.GJChronology;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import ca.bsolomon.gw2events.worldevent.enums.DredgeEvent;
import ca.bsolomon.gw2events.worldevent.enums.FireShamanEnum;
import ca.bsolomon.gw2events.worldevent.enums.FoulBearEvent;
import ca.bsolomon.gw2events.worldevent.enums.GolemEvent;
import ca.bsolomon.gw2events.worldevent.enums.HarathiChestEvent;
import ca.bsolomon.gw2events.worldevent.enums.HydraQueenEvent;
import ca.bsolomon.gw2events.worldevent.enums.KarkaEnum;
import ca.bsolomon.gw2events.worldevent.enums.ServerID;
import ca.bsolomon.gw2events.worldevent.util.LowPriorityEventData;

@DisallowConcurrentExecution
public class LowPriorityDataRetrieveJob extends DataRetrieveJob implements Job {
	
	private LowPriorityEventData lowPriorityEventData = new LowPriorityEventData();
	
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		
		DateTimeZone zone = DateTimeZone.forID("America/New_York");
		Chronology gregorianJuian = GJChronology.getInstance(zone);
			
		List<String> serverIds = new ArrayList<>();
		
		for (ServerID servId:ServerID.values()) {
			serverIds.add(servId.getUid()+"");
		}
	
		//System.out.println("Starting LowPriority Thread retrieve "+this.toString()+" at "+new DateTime(gregorianJuian));
		
		for (GolemEvent eventId:GolemEvent.values()) {
			queryEvent(gregorianJuian, serverIds, eventId.uid(), lowPriorityEventData);  
		}
		
		for (DredgeEvent eventId:DredgeEvent.values()) {
			queryEvent(gregorianJuian, serverIds, eventId.uid(), lowPriorityEventData);  
		}
		
		for (HarathiChestEvent eventId:HarathiChestEvent.values()) {
			queryEvent(gregorianJuian, serverIds, eventId.uid(), lowPriorityEventData);  
		}
		
		for (FoulBearEvent eventId:FoulBearEvent.values()) {
			queryEvent(gregorianJuian, serverIds, eventId.uid(), lowPriorityEventData);  
		}
		
		for (HydraQueenEvent eventId:HydraQueenEvent.values()) {
			queryEvent(gregorianJuian, serverIds, eventId.uid(), lowPriorityEventData);  
		}
		
		for (FireShamanEnum eventId:FireShamanEnum.values()) {
			queryEvent(gregorianJuian, serverIds, eventId.uid(), lowPriorityEventData);  
		}
		
		for (KarkaEnum eventId:KarkaEnum.values()) {
			queryEvent(gregorianJuian, serverIds, eventId.uid(), lowPriorityEventData);  
		}
		
		//System.out.println("Ended LowPriority Thread retrieve "+this.toString()+" at "+new DateTime(gregorianJuian));

		GolemEvent.formatGolemString(lowPriorityEventData);
		DredgeEvent.formatDredgeString(lowPriorityEventData);
		FoulBearEvent.formatOgreString(lowPriorityEventData);
		HarathiChestEvent.formatHarathiString(lowPriorityEventData);
		HydraQueenEvent.formatHydraString(lowPriorityEventData);
		FireShamanEnum.formatShamanString(lowPriorityEventData);
		KarkaEnum.formatKarkaString(lowPriorityEventData);
		
		//System.out.println("Ended LowPriority Thread formating "+this.toString()+" at "+new DateTime(gregorianJuian));
	}
}
