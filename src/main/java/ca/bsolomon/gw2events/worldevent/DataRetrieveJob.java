package ca.bsolomon.gw2events.worldevent;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.joda.time.Chronology;
import org.joda.time.DateTime;

import ca.bsolomon.gw2event.api.GW2EventsAPI;
import ca.bsolomon.gw2events.worldevent.util.EventData;

public class DataRetrieveJob {

	private GW2EventsAPI api = new GW2EventsAPI();
	
	protected boolean queryEvent(Chronology gregorianJuian,
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
