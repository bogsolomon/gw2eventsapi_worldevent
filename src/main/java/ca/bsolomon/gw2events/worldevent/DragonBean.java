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
		return KarkaEnum.getStatus();
	}
	
	public List<EventStatus> getTequatlStatus() {
		return DragonEvent.getTeqStatus();
	}
	
	public List<EventStatus> getShattererStatus() {
		return DragonEvent.getShatStatus();
	}
	
	public List<EventStatus> getJormagStatus() {
		return DragonEvent.getJorStatus();
	}
	
	public List<EventStatus> getMawStatus() {
		return MawEvent.getStatus();
	}
	
	public List<EventStatus> getFireEleStatus() {
		return FireEleEvent.getStatus();
	}
	
	public List<EventStatus> getWurmStatus() {
		return JungleWurmEvent.getStatus();
	}
	
	public List<EventStatus> getSbStatus() {
		return ShadowBehemothEvent.getStatus();
	}
	
	public List<EventStatus> getGolemStatus() {
		return GolemEvent.getStatus();
	}
	
	public List<EventStatus> getDredgeStatus() {
		return DredgeEvent.getStatus();
	}
	
	public List<EventStatus> getHarathiStatus() {
		return HarathiChestEvent.getStatus();
	}
	
	public List<EventStatus> getOgreStatus() {
		return FoulBearEvent.getStatus();
	}
	
	public List<EventStatus> getHydraStatus() {
		return HydraQueenEvent.getStatus();
	}
	
	public List<EventStatus> getFireShamStatus() {
		return FireShamanEnum.getStatus();
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
		formatServerEvent(servId, status, "Tequatl", DragonEvent.getTeqStatus());
		formatServerEvent(servId, status, "Shaterrer", DragonEvent.getShatStatus());
		formatServerEvent(servId, status, "Jormag", DragonEvent.getJorStatus());

		formatServerEvent(servId, status, "Maw", MawEvent.getStatus());
		formatServerEvent(servId, status, "Fire Ele", FireEleEvent.getStatus());
		formatServerEvent(servId, status, "Fire Ele", FireEleEvent.getStatus());
		formatServerEvent(servId, status, "Jungle Wurm", JungleWurmEvent.getStatus());
		formatServerEvent(servId, status, "SB", ShadowBehemothEvent.getStatus());
		
		formatServerEvent(servId, status, "Golem MKII", GolemEvent.getStatus());
		formatServerEvent(servId, status, "Dredge", DredgeEvent.getStatus());
		formatServerEvent(servId, status, "Kilava Chest", HarathiChestEvent.getStatus());
		formatServerEvent(servId, status, "Foulbear", FoulBearEvent.getStatus());
		formatServerEvent(servId, status, "Hydra Queen", HydraQueenEvent.getStatus());
		formatServerEvent(servId, status, "Fire Shaman", FireShamanEnum.getStatus());
		formatServerEvent(servId, status, "Karka Queen", KarkaEnum.getStatus());
	}

	private void formatServerEvent(ServerID servId, List<EventStatus> status, 
			String name, List<EventStatus> statusIn) {
		for (EventStatus st:statusIn) {
			if (st.getServer().equals(servId.toString())) {
				status.add(st);
				st.setServer(name);
			}
		}
	}
}
