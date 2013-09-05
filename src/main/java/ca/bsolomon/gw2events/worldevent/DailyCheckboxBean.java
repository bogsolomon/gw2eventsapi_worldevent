package ca.bsolomon.gw2events.worldevent;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ca.bsolomon.gw2events.worldevent.util.DailyTrackerConfigReader;

@ManagedBean(name="dailyCheckboxBean")
@SessionScoped
public class DailyCheckboxBean {

	private List<String> selectedEvents = new ArrayList<>();
	  
    private Map<String,String> events;  
	
    public DailyCheckboxBean() {  
    	events = new LinkedHashMap<String, String>();
    	
    	DailyTrackerConfigReader.readConfigFiles();
    	
    	List<String> dailyTrackingEvents = DailyTrackerConfigReader.getDailyTracking();
    	
    	for (String event: dailyTrackingEvents) {
    		events.put(event, event);
    	}
    }

	public List<String> getSelectedEvents() {
		return selectedEvents;
	}

	public void setSelectedEvents(List<String> selectedEvents) {
		this.selectedEvents = selectedEvents;
	}

	public Map<String, String> getEvents() {
		return events;
	}

	public void setEvents(Map<String, String> events) {
		this.events = events;
	}
	
	public void handleCheckbox() {
		
	}
	
	public void clearAll() {
		selectedEvents.clear();
	}
}
