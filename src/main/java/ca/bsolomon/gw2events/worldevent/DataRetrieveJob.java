package ca.bsolomon.gw2events.worldevent;

import java.util.List;

import org.joda.time.Chronology;
import org.joda.time.DateTime;

import ca.bsolomon.gw2event.api.GW2EventsAPI;
import ca.bsolomon.gw2event.api.dao.Event;
import ca.bsolomon.gw2events.worldevent.util.EventData;

public class DataRetrieveJob {

	private GW2EventsAPI api = new GW2EventsAPI();
	
	protected boolean queryEvent(Chronology gregorianJuian,
			List<String> serverIds, String eventUID, EventData dataStructure) {
		List<Event> data = api.queryServer(eventUID);
		boolean changed = false;
		
		if (data != null) {
			for (int i=0; i<data.size(); i++) {
				Event obj = data.get(i);
				
				String serverId = obj.getWorldId();
				
				if (serverIds.contains(serverId)) {
					String status = obj.getState();
					if (dataStructure.addEventStatus(serverId, eventUID, status, new DateTime(gregorianJuian))) {
						changed = true;
					}
				}
			}
		}
		return changed;
	}
}
