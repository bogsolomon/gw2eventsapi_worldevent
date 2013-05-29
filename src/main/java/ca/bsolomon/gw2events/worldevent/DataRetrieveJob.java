package ca.bsolomon.gw2events.worldevent;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import ca.bsolomon.gw2events.worldevent.util.DragonEvent;
import ca.bsolomon.gw2events.worldevent.util.GW2EventsAPI;
import ca.bsolomon.gw2events.worldevent.util.DragonData;
import ca.bsolomon.gw2events.worldevent.util.ServerID;

public class DataRetrieveJob implements Job {
	
	private DragonData dragonData = new DragonData();
	
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		PushContext pushContext = PushContextFactory.getDefault().getPushContext();
		
		if (GW2EventsAPI.eventIdToName.size() == 0) {
			System.out.println("Generating Event IDs");
			GW2EventsAPI.generateEventIds();
			
			System.out.println("Generating World IDs");
			GW2EventsAPI.generateNAWorldIds();
		}
		
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));
		
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss z");
		format.setCalendar(cal);
		String time = format.format(cal.getTime());
		
		String status = GW2EventsAPI.queryServer(ServerID.SoR.uid(), DragonEvent.TEQUATL.uid());
		
		if (status.equals("Active")) {
			
		}
	}
}
