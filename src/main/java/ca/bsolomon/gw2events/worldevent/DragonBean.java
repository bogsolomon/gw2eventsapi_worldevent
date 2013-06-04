package ca.bsolomon.gw2events.worldevent;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ca.bsolomon.gw2events.worldevent.enums.DragonEvent;
import ca.bsolomon.gw2events.worldevent.enums.DredgeEvent;
import ca.bsolomon.gw2events.worldevent.enums.FireEleEvent;
import ca.bsolomon.gw2events.worldevent.enums.FireShamanEnum;
import ca.bsolomon.gw2events.worldevent.enums.FoulBearEvent;
import ca.bsolomon.gw2events.worldevent.enums.GolemEvent;
import ca.bsolomon.gw2events.worldevent.enums.HarathiChestEvent;
import ca.bsolomon.gw2events.worldevent.enums.HydraQueenEvent;
import ca.bsolomon.gw2events.worldevent.enums.JungleWurmEvent;
import ca.bsolomon.gw2events.worldevent.enums.KarkaEnum;
import ca.bsolomon.gw2events.worldevent.enums.MawEvent;
import ca.bsolomon.gw2events.worldevent.enums.ServerID;
import ca.bsolomon.gw2events.worldevent.enums.ShadowBehemothEvent;
import ca.bsolomon.gw2events.worldevent.util.DragonData;
import ca.bsolomon.gw2events.worldevent.util.EventData;
import ca.bsolomon.gw2events.worldevent.util.EventStatus;
import ca.bsolomon.gw2events.worldevent.util.LowLevelEventData;
import ca.bsolomon.gw2events.worldevent.util.LowPriorityEventData;

@ManagedBean(name="dragonBean")
@SessionScoped
public class DragonBean {

	private DragonData dragonData = new DragonData();
	private EventData lowLevelData = new LowLevelEventData();
	private LowPriorityEventData lowPriorityData = new LowPriorityEventData();
	
	public List<EventStatus> getKarkaStatus() {
		return KarkaEnum.formatKarkaString(lowPriorityData);
	}
	
	public List<EventStatus> getTequatlStatus() {
		return DragonEvent.formatTequatlString(dragonData);
	}
	
	public List<EventStatus> getShattererStatus() {
		return DragonEvent.formatShattererString(dragonData);
	}
	
	public List<EventStatus> getJormagStatus() {
		return DragonEvent.formatJormagString(dragonData);
	}
	
	public List<EventStatus> getMawStatus() {
		return MawEvent.formatMawString(lowLevelData);
	}
	
	public List<EventStatus> getFireEleStatus() {
		return FireEleEvent.formatFireEleString(lowLevelData);
	}
	
	public List<EventStatus> getWurmStatus() {
		return JungleWurmEvent.formatJungleWurmString(lowLevelData);
	}
	
	public List<EventStatus> getSbStatus() {
		return ShadowBehemothEvent.formatShadowBehemothString(lowLevelData);
	}
	
	public List<EventStatus> getGolemStatus() {
		return GolemEvent.formatGolemString(lowPriorityData);
	}
	
	public List<EventStatus> getDredgeStatus() {
		return DredgeEvent.formatDredgeString(lowPriorityData);
	}
	
	public List<EventStatus> getHarathiStatus() {
		return HarathiChestEvent.formatHarathiString(lowPriorityData);
	}
	
	public List<EventStatus> getOgreStatus() {
		return FoulBearEvent.formatOgreString(lowPriorityData);
	}
	
	public List<EventStatus> getHydraStatus() {
		return HydraQueenEvent.formatHydraString(lowPriorityData);
	}
	
	public List<EventStatus> getFireShamStatus() {
		return FireShamanEnum.formatShamanString(lowPriorityData);
	}
	
	public List<EventStatus> getSorStatus() {
		ServerID servId = ServerID.SoR;
		
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		formatServer(servId, status);
		
		return status;
	}
	
	public List<EventStatus> getEtStatus() {
		ServerID servId = ServerID.ET;
		
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		formatServer(servId, status);
		
		return status;
	}

	public List<EventStatus> getFcStatus() {
		ServerID servId = ServerID.FC;
		
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		formatServer(servId, status);
		
		return status;
	}
	
	public void formatServer(ServerID servId, List<EventStatus> status) {
		DragonEvent.formatTequatlString(dragonData, status, servId);
		status.get(status.size()-1).setServer("Tequatl");
		DragonEvent.formatShattererString(dragonData, status, servId);
		status.get(status.size()-1).setServer("Shaterrer");
		DragonEvent.formatJormagString(dragonData, status, servId);
		status.get(status.size()-1).setServer("Jormag");
		
		MawEvent.formatMawString(lowLevelData, status, servId);
		status.get(status.size()-1).setServer("Maw");
		FireEleEvent.formatFireEleString(lowLevelData, status, servId);
		status.get(status.size()-1).setServer("Fire Ele");
		JungleWurmEvent.formatJungleWurmString(lowLevelData, status, servId);
		status.get(status.size()-1).setServer("Jungle Wurm");
		ShadowBehemothEvent.formatShadowBehemothString(lowLevelData, status, servId);
		status.get(status.size()-1).setServer("SB");
		
		GolemEvent.formatGolemString(lowPriorityData, status, servId);
		status.get(status.size()-1).setServer("Golem MKII");
		DredgeEvent.formatDredgeString(lowPriorityData, status, servId);
		status.get(status.size()-1).setServer("Dredge");
		HarathiChestEvent.formatHarathiString(lowPriorityData, status, servId);
		status.get(status.size()-1).setServer("Kilava Chest");
		FoulBearEvent.formatOgreString(lowPriorityData, status, servId);
		status.get(status.size()-1).setServer("Foulbear");
		HydraQueenEvent.formatHydraString(lowPriorityData, status, servId);
		status.get(status.size()-1).setServer("Hydra Queen");
		FireShamanEnum.formatShamanString(lowPriorityData, status, servId);
		status.get(status.size()-1).setServer("Fire Shaman");
		KarkaEnum.formatKarkaString(lowPriorityData, status, servId);
		status.get(status.size()-1).setServer("Karka Queen");
	}
}
