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

import ca.bsolomon.gw2events.worldevent.dynamodb.EventWindowData;
import ca.bsolomon.gw2events.worldevent.enums.ServerID;
import ca.bsolomon.gw2events.worldevent.temples.enums.BalthazzarEvent;
import ca.bsolomon.gw2events.worldevent.temples.enums.DwaynaEvent;
import ca.bsolomon.gw2events.worldevent.temples.enums.GatesofArahEvent;
import ca.bsolomon.gw2events.worldevent.temples.enums.GrenthEvent;
import ca.bsolomon.gw2events.worldevent.temples.enums.LyssaEvent;
import ca.bsolomon.gw2events.worldevent.temples.enums.MelandruEvent;
import ca.bsolomon.gw2events.worldevent.temples.enums.PlinxEvent;
import ca.bsolomon.gw2events.worldevent.temples.enums.ScarlettEvent;
import ca.bsolomon.gw2events.worldevent.util.EventData;
import ca.bsolomon.gw2events.worldevent.util.TempleEventData;

@DisallowConcurrentExecution
public class TempleDataRetrieveJob extends DataRetrieveJob implements Job {
	
	private EventData templeEventData = new TempleEventData();
	
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		EventWindowData.loadInitialData();
		
		DateTimeZone zone = DateTimeZone.forID("America/New_York");
		Chronology gregorianJuian = GJChronology.getInstance(zone);
		
		List<String> serverIds = new ArrayList<>();
		
		for (ServerID servId:ServerID.values()) {
			serverIds.add(servId.getUid()+"");
		}
	
		//System.out.println("Starting LowLevel Thread retrieve "+this.toString()+" at "+new DateTime(gregorianJuian));
		
		for (BalthazzarEvent eventId:BalthazzarEvent.values()) {
			queryEvent(gregorianJuian, serverIds, eventId.uid(), templeEventData); 
		}
		
		for (LyssaEvent eventId:LyssaEvent.values()) {
			queryEvent(gregorianJuian, serverIds, eventId.uid(), templeEventData); 
		}
		
		for (DwaynaEvent eventId:DwaynaEvent.values()) {
			queryEvent(gregorianJuian, serverIds, eventId.uid(), templeEventData); 
		}
		
		for (GrenthEvent eventId:GrenthEvent.values()) {
			queryEvent(gregorianJuian, serverIds, eventId.uid(), templeEventData); 
		}
		
		for (MelandruEvent eventId:MelandruEvent.values()) {
			queryEvent(gregorianJuian, serverIds, eventId.uid(), templeEventData); 
		}
		
		for (PlinxEvent eventId:PlinxEvent.values()) {
			queryEvent(gregorianJuian, serverIds, eventId.uid(), templeEventData); 
		}
		
		for (ScarlettEvent eventId:ScarlettEvent.values()) {
			queryEvent(gregorianJuian, serverIds, eventId.uid(), templeEventData); 
		}
		
		for (GatesofArahEvent eventId:GatesofArahEvent.values()) {
			queryEvent(gregorianJuian, serverIds, eventId.uid(), templeEventData); 
		}
		
		//System.out.println("Ended LowLevel Thread retrieve "+this.toString()+" at "+new DateTime(gregorianJuian));
		
		BalthazzarEvent.formatBalthazarString(templeEventData);
		LyssaEvent.formatLyssaString(templeEventData);
		DwaynaEvent.formatDwaynaString(templeEventData);
		GrenthEvent.formatGrenthString(templeEventData);
		MelandruEvent.formatMelandruString(templeEventData);
		PlinxEvent.formatPlinxString(templeEventData);
		ScarlettEvent.formatScarlettString(templeEventData);
		GatesofArahEvent.formatArahString(templeEventData);
		
		//System.out.println("Ended LowLevel Thread formating "+this.toString()+" at "+new DateTime(gregorianJuian));
	}
}
