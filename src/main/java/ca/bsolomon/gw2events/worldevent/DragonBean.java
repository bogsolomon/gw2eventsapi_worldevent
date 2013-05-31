package ca.bsolomon.gw2events.worldevent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ca.bsolomon.gw2events.worldevent.enums.DragonEvent;
import ca.bsolomon.gw2events.worldevent.enums.DredgeEvent;
import ca.bsolomon.gw2events.worldevent.enums.FireEleEvent;
import ca.bsolomon.gw2events.worldevent.enums.FoulBearEvent;
import ca.bsolomon.gw2events.worldevent.enums.GolemEvent;
import ca.bsolomon.gw2events.worldevent.enums.HarathiChestEvent;
import ca.bsolomon.gw2events.worldevent.enums.JungleWurmEvent;
import ca.bsolomon.gw2events.worldevent.enums.MawEvent;
import ca.bsolomon.gw2events.worldevent.enums.ShadowBehemothEvent;
import ca.bsolomon.gw2events.worldevent.util.DragonData;
import ca.bsolomon.gw2events.worldevent.util.LowLevelEventData;
import ca.bsolomon.gw2events.worldevent.util.LowPriorityEventData;

@ManagedBean(name="dragonBean")
@SessionScoped
public class DragonBean {

	private DragonData dragonData = new DragonData();
	private LowLevelEventData lowLevelData = new LowLevelEventData();
	private LowPriorityEventData lowPriorityData = new LowPriorityEventData();
	
	public String getTequatlStatus() {
		return DragonEvent.formatTequatlString(dragonData);
	}
	
	public String getShattererStatus() {
		return DragonEvent.formatShattererString(dragonData);
	}
	
	public String getJormagStatus() {
		return DragonEvent.formatJormagString(dragonData);
	}
	
	public String getMawStatus() {
		return MawEvent.formatMawString(lowLevelData);
	}
	
	public String getFireEleStatus() {
		return FireEleEvent.formatFireEleString(lowLevelData);
	}
	
	public String getWurmStatus() {
		return JungleWurmEvent.formatJungleWurmString(lowLevelData);
	}
	
	public String getSbStatus() {
		return ShadowBehemothEvent.formatShadowBehemothString(lowLevelData);
	}
	
	public String getGolemStatus() {
		return GolemEvent.formatGolemString(lowPriorityData);
	}
	
	public String getDredgeStatus() {
		return DredgeEvent.formatDredgeString(lowPriorityData);
	}
	
	public String getHarathiStatus() {
		return HarathiChestEvent.formatHarathiString(lowPriorityData);
	}
	
	public String getOgreStatus() {
		return FoulBearEvent.formatOgreString(lowPriorityData);
	}
	
}
