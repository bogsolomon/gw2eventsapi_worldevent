package ca.bsolomon.gw2events.worldevent.util;

public class EventWindow {

	private String eventName;
	private String maxWindow;
	private String minWindow;
	
	public EventWindow(String eventName, String maxWindow, String minWindow) {
		this.eventName = eventName;
		this.maxWindow = maxWindow;
		this.minWindow = minWindow;
	}
	
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getMaxWindow() {
		return maxWindow;
	}
	public void setMaxWindow(String maxWindow) {
		this.maxWindow = maxWindow;
	}
	public String getMinWindow() {
		return minWindow;
	}
	public void setMinWindow(String minWindow) {
		this.minWindow = minWindow;
	}
	
}
