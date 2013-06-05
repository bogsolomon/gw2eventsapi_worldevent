package ca.bsolomon.gw2events.worldevent;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.component.layout.LayoutUnit;
import org.primefaces.event.ResizeEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;

@ManagedBean(name="checkboxBean")
@SessionScoped
public class CheckboxBean {

	private List<String> selectedEvents;
  
    private Map<String,String> events;  
  
    private int eastSize;
    private boolean eastCollapsed;
	
	public CheckboxBean() {  
    	events = new LinkedHashMap<String, String>();  
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
    	events.put("Karka", "Karka");
    }
	
	public void handleToggle(ToggleEvent event) {
		if (((LayoutUnit)event.getComponent()).getPosition().equals("east")) {
			eastCollapsed = (event.getVisibility() == Visibility.HIDDEN);
		}
	}
	
    public void handleResize(ResizeEvent event) {
    	int newWidth = event.getWidth();
    	if (((LayoutUnit)event.getComponent()).getPosition().equals("east")) {
    		eastSize = newWidth;
    	}
    }
    
    public int getEastSize() {
		return eastSize;
	}

	public void setEastSize(int eastSize) {
		this.eastSize = eastSize;
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

	public boolean isEastCollapsed() {
		return eastCollapsed;
	}

	public void setEastCollapsed(boolean eastCollapsed) {
		this.eastCollapsed = eastCollapsed;
	}
	
}
