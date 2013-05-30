package ca.bsolomon.gw2events.worldevent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ca.bsolomon.gw2events.worldevent.util.DragonData;
import ca.bsolomon.gw2events.worldevent.util.EventStringFormatter;
import ca.bsolomon.gw2events.worldevent.util.LowLevelEventData;

@ManagedBean(name="dragonBean")
@SessionScoped
public class DragonBean {

	private DragonData dragonData = new DragonData();
	private LowLevelEventData lowLevelData = new LowLevelEventData();
	
	public String getTequatlStatus() {
		return EventStringFormatter.formatTequatlString(dragonData);
	}
	
	public String getShattererStatus() {
		return EventStringFormatter.formatShattererString(dragonData);
	}
	
	public String getJormagStatus() {
		return EventStringFormatter.formatJormagString(dragonData);
	}
	
	public String getMawStatus() {
		return EventStringFormatter.formatMawString(lowLevelData);
	}
	
}
