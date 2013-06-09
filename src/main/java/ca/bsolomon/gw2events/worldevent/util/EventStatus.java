package ca.bsolomon.gw2events.worldevent.util;

public final class EventStatus {

	private final String server;
	private final String status;
	private final String date;
	private final String color;
	private final String event;
	private final String waypoint;

	private int fHashCode;
	
	public EventStatus(String server, String status, String date, String color, String event, String waypoint) {
		this.server = server;
		this.status = status;
		this.date = date;
		this.color = color;
		this.event = event;
		this.waypoint = waypoint;
	}
	
	public String getEvent() {
		return event;
	}
	public String getColor() {
		return color;
	}
	public String getServer() {
		return server;
	}
	public String getStatus() {
		return status;
	}
	public String getDate() {
		return date;
	}
	public String getWaypoint() {
		return waypoint;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof EventStatus))return false;
	    EventStatus otherObject = (EventStatus)other;
	    
	    if (this.event.equals(otherObject.getEvent()) && 
	    		this.server.equals(otherObject.getServer()) &&
	    		this.status.equals(otherObject.getStatus())) {
	    	return true;
	    }
	    
	    return false;
	}
	@Override
	public int hashCode() {
		if (fHashCode == 0) {
			int result = HashCodeUtil.SEED;
			result = HashCodeUtil.hash(result, event);
			result = HashCodeUtil.hash(result, server);
			result = HashCodeUtil.hash(result, status);
			fHashCode = result;
		}
		
		return fHashCode;
	}
}
