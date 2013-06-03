package ca.bsolomon.gw2events.worldevent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="checkboxBean")
@SessionScoped
public class CheckboxBean {

	 private List<String> selectedEvents;
  
    private Map<String,String> events;  
  
    public CheckboxBean() {  
    	events = new HashMap<String, String>();  
    	events.put("Tequatl", "Tequatl");  
    	events.put("Shatterer", "Shatterer");  
    	events.put("Jormag", "Jormag");  
    	
    	events.put("Maw", "Maw");
    	events.put("Fire Ele", "Fire Ele");  
    	events.put("Wurm", "Wurm");  
    	events.put("SB", "SB");
    	
    	events.put("Golem", "Golem");
    	events.put("Dredge", "Dredge");
    	events.put("Kilava Chest", "Kilava Chest");
    	events.put("Foulbear", "Foulbear");
    	events.put("Hydra Queen", "Hydra Queen");
    	events.put("Fire Shaman", "Fire Shaman");
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
