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
import ca.bsolomon.gw2events.worldevent.enums.DragonEvent;
import ca.bsolomon.gw2events.worldevent.enums.MegadestroyerEvent;
import ca.bsolomon.gw2events.worldevent.enums.ServerID;
import ca.bsolomon.gw2events.worldevent.util.DragonData;

@DisallowConcurrentExecution
public class DragonDataRetrieveJob extends DataRetrieveJob implements Job {
	
	private DragonData dragonData = new DragonData();
	
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		EventWindowData.loadInitialData();
		
		DateTimeZone zone = DateTimeZone.forID("America/New_York");
		Chronology gregorianJuian = GJChronology.getInstance(zone);
		
		List<String> serverIds = new ArrayList<>();
		
		for (ServerID servId:ServerID.values()) {
			serverIds.add(servId.getUid()+"");
		}
	
		//System.out.println("Starting Dragon Thread retrieve "+this.toString()+" at "+new DateTime(gregorianJuian));
		
		for (DragonEvent eventId:DragonEvent.values()) {
			queryEvent(gregorianJuian, serverIds, eventId.uid(), dragonData);
		}
		
		for (MegadestroyerEvent eventId:MegadestroyerEvent.values()) {
			queryEvent(gregorianJuian, serverIds, eventId.uid(), dragonData);  
		}
		
		//System.out.println("Ended Dragon Thread retrieve "+this.toString()+" at "+new DateTime(gregorianJuian));
		
		DragonEvent.formatTequatlString(dragonData);
		DragonEvent.formatShattererString(dragonData);
		DragonEvent.formatJormagString(dragonData);
		MegadestroyerEvent.formatMegadestroyerString(dragonData);
		
		//System.out.println("Ended Dragon Thread formating "+this.toString()+" at "+new DateTime(gregorianJuian));
	}
}