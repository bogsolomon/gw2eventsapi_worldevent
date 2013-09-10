package ca.bsolomon.gw2events.worldevent;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import ca.bsolomon.gw2events.worldevent.enums.DragonEvent;
import ca.bsolomon.gw2events.worldevent.enums.DredgeEvent;
import ca.bsolomon.gw2events.worldevent.enums.FireEleEvent;
import ca.bsolomon.gw2events.worldevent.enums.FireShamanEnum;
import ca.bsolomon.gw2events.worldevent.enums.FlameBattleEvent;
import ca.bsolomon.gw2events.worldevent.enums.FoulBearEvent;
import ca.bsolomon.gw2events.worldevent.enums.GolemEvent;
import ca.bsolomon.gw2events.worldevent.enums.HarathiChestEvent;
import ca.bsolomon.gw2events.worldevent.enums.HydraQueenEvent;
import ca.bsolomon.gw2events.worldevent.enums.JungleWurmEvent;
import ca.bsolomon.gw2events.worldevent.enums.KarkaEvent;
import ca.bsolomon.gw2events.worldevent.enums.MawEvent;
import ca.bsolomon.gw2events.worldevent.enums.MegadestroyerEvent;
import ca.bsolomon.gw2events.worldevent.enums.ServerID;
import ca.bsolomon.gw2events.worldevent.enums.ShadowBehemothEvent;
import ca.bsolomon.gw2events.worldevent.temples.enums.BalthazzarEvent;
import ca.bsolomon.gw2events.worldevent.temples.enums.DwaynaEvent;
import ca.bsolomon.gw2events.worldevent.temples.enums.GrenthEvent;
import ca.bsolomon.gw2events.worldevent.temples.enums.LyssaEvent;
import ca.bsolomon.gw2events.worldevent.temples.enums.MelandruEvent;
import ca.bsolomon.gw2events.worldevent.temples.enums.PlinxEvent;
import ca.bsolomon.gw2events.worldevent.temples.enums.ScarlettEvent;
import ca.bsolomon.gw2events.worldevent.util.EventStatus;

@ManagedBean(name="dragonBean")
@SessionScoped
public class DragonBean {
	
	@ManagedProperty(value="#{checkboxBean}")
	private CheckboxBean checkboxBean;
	
	public List<EventStatus> getKarkaStatus() {
		if (!checkboxBean.getSelectedEvents().contains("Karka Queen"))
			return KarkaEvent.getStatus();
		else
			return new ArrayList<>();
	}
	
	public List<EventStatus> getTequatlStatus() {
		if (!checkboxBean.getSelectedEvents().contains("Tequatl"))
			return DragonEvent.getTeqStatus();
		else
			return new ArrayList<>();
	}
	
	public List<EventStatus> getShattererStatus() {
		if (!checkboxBean.getSelectedEvents().contains("RageDragon"))
			return DragonEvent.getShatStatus();
		else
			return new ArrayList<>();
	}
	
	public List<EventStatus> getJormagStatus() {
		if (!checkboxBean.getSelectedEvents().contains("Jormag"))
			return DragonEvent.getJorStatus();
		else
			return new ArrayList<>();
	}
	
	public List<EventStatus> getMawStatus() {
		if (!checkboxBean.getSelectedEvents().contains("Maw"))
			return MawEvent.getStatus();
		else
			return new ArrayList<>();
	}
	
	public List<EventStatus> getFireEleStatus() {
		if (!checkboxBean.getSelectedEvents().contains("Fire Ele"))
			return FireEleEvent.getStatus();
		else
			return new ArrayList<>();
	}
	
	public List<EventStatus> getWurmStatus() {
		if (!checkboxBean.getSelectedEvents().contains("Jungle Wurm"))
			return JungleWurmEvent.getStatus();
		else
			return new ArrayList<>();
	}
	
	public List<EventStatus> getSbStatus() {
		if (!checkboxBean.getSelectedEvents().contains("SB"))
			return ShadowBehemothEvent.getStatus();
		else
			return new ArrayList<>();
	}
	
	public List<EventStatus> getGolemStatus() {
		if (!checkboxBean.getSelectedEvents().contains("Golem MKII"))
			return GolemEvent.getStatus();
		else
			return new ArrayList<>();
	}
	
	public List<EventStatus> getDredgeStatus() {
		if (!checkboxBean.getSelectedEvents().contains("Dredge"))
			return DredgeEvent.getStatus();
		else
			return new ArrayList<>();
	}
	
	public List<EventStatus> getHarathiStatus() {
		if (!checkboxBean.getSelectedEvents().contains("Kilava Chest"))
			return HarathiChestEvent.getStatus();
		else
			return new ArrayList<>();
	}
	
	public List<EventStatus> getOgreStatus() {
		if (!checkboxBean.getSelectedEvents().contains("Foulbear"))
			return FoulBearEvent.getStatus();
		else
			return new ArrayList<>();
	}
	
	public List<EventStatus> getHydraStatus() {
		if (!checkboxBean.getSelectedEvents().contains("Hydra Queen"))
			return HydraQueenEvent.getStatus();
		else
			return new ArrayList<>();
	}
	
	public List<EventStatus> getFireShamStatus() {
		if (!checkboxBean.getSelectedEvents().contains("Fire Shaman"))
			return FireShamanEnum.getStatus();
		else
			return new ArrayList<>();
	}
	
	public List<EventStatus> getSorStatus() {
		ServerID servId = ServerID.getByID(checkboxBean.getServerOne().getServerId());
		
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		formatServer(servId, status);
		
		return status;
	}
	
	public List<EventStatus> getEtStatus() {
		ServerID servId = ServerID.getByID(checkboxBean.getServerThree().getServerId());
		
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		formatServer(servId, status);
		
		return status;
	}

	public List<EventStatus> getFcStatus() {
		ServerID servId = ServerID.getByID(checkboxBean.getServerTwo().getServerId());
		
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		formatServer(servId, status);
		
		return status;
	}
	
	public void formatServer(ServerID servId, List<EventStatus> status) {
		if (!checkboxBean.getSelectedEvents().contains("Maw"))//10
			formatServerEvent(servId, status, MawEvent.getStatus());
		if (!checkboxBean.getSelectedEvents().contains("Fire Ele"))//15
			formatServerEvent(servId, status, FireEleEvent.getStatus());
		if (!checkboxBean.getSelectedEvents().contains("Jungle Wurm")) //15
			formatServerEvent(servId, status, JungleWurmEvent.getStatus());
		if (!checkboxBean.getSelectedEvents().contains("SB")) //15
			formatServerEvent(servId, status, ShadowBehemothEvent.getStatus());
		if (!checkboxBean.getSelectedEvents().contains("Flame")) //25
			formatServerEvent(servId, status, FlameBattleEvent.getStatus());
		
		if (!checkboxBean.getSelectedEvents().contains("Foulbear")) //34
			formatServerEvent(servId, status, FoulBearEvent.getStatus());
		if (!checkboxBean.getSelectedEvents().contains("Kilava Chest"))//43
			formatServerEvent(servId, status, HarathiChestEvent.getStatus());
		if (!checkboxBean.getSelectedEvents().contains("Dredge")) //50
			formatServerEvent(servId, status, DredgeEvent.getStatus());
		if (!checkboxBean.getSelectedEvents().contains("Hydra Queen")) //50
			formatServerEvent(servId, status, HydraQueenEvent.getStatus());
		if (!checkboxBean.getSelectedEvents().contains("RageDragon")) //50
			formatServerEvent(servId, status, DragonEvent.getShatStatus());
		if (!checkboxBean.getSelectedEvents().contains("Fire Shaman")) //60
			formatServerEvent(servId, status, FireShamanEnum.getStatus());
		if (!checkboxBean.getSelectedEvents().contains("Tequatl"))//65
			formatServerEvent(servId, status, DragonEvent.getTeqStatus());
		if (!checkboxBean.getSelectedEvents().contains("Megadestroyer"))//66
			formatServerEvent(servId, status, MegadestroyerEvent.getStatus());
		if (!checkboxBean.getSelectedEvents().contains("Golem MKII"))//68
			formatServerEvent(servId, status, GolemEvent.getStatus());
		if (!checkboxBean.getSelectedEvents().contains("Jormag"))//80
			formatServerEvent(servId, status, DragonEvent.getJorStatus());
		if (!checkboxBean.getSelectedEvents().contains("Karka Queen")) //80
			formatServerEvent(servId, status, KarkaEvent.getStatus());
	}

	private void formatServerEvent(ServerID servId, List<EventStatus> status, 
			List<EventStatus> statusIn) {
		for (EventStatus st:statusIn) {
			if (st.getServer().equals(servId.toString())) {
				status.add(st);
			}
		}
	}

	public void setCheckboxBean(CheckboxBean checkboxBean) {
		this.checkboxBean = checkboxBean;
	}
	
	public List<EventStatus> getServ1TempleStatus() {
		ServerID servId = ServerID.getByID(checkboxBean.getServerOne().getServerId());
		
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		formatTempleServer(servId, status);
		
		return status;
	}
	
	public List<EventStatus> getServ2TempleStatus() {
		ServerID servId = ServerID.getByID(checkboxBean.getServerTwo().getServerId());
		
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		formatTempleServer(servId, status);
		
		return status;
	}
	
	public List<EventStatus> getServ3TempleStatus() {
		ServerID servId = ServerID.getByID(checkboxBean.getServerThree().getServerId());
		
		List<EventStatus> status = new ArrayList<EventStatus>();
		
		formatTempleServer(servId, status);
		
		return status;
	}
	
	public void formatTempleServer(ServerID servId, List<EventStatus> status) {
		if (!checkboxBean.getSelectedEvents().contains("Balthazar"))
			formatServerEvent(servId, status, BalthazzarEvent.getStatus());
		if (!checkboxBean.getSelectedEvents().contains("Lyssa"))
			formatServerEvent(servId, status, LyssaEvent.getStatus());
		if (!checkboxBean.getSelectedEvents().contains("Dwayna"))
			formatServerEvent(servId, status, DwaynaEvent.getStatus());
		if (!checkboxBean.getSelectedEvents().contains("Melandru"))
			formatServerEvent(servId, status, MelandruEvent.getStatus());
		if (!checkboxBean.getSelectedEvents().contains("Grenth"))
			formatServerEvent(servId, status, GrenthEvent.getStatus());
		if (!checkboxBean.getSelectedEvents().contains("Plinx"))
			formatServerEvent(servId, status, PlinxEvent.getStatus());
		if (!checkboxBean.getSelectedEvents().contains("Scarlett"))
			formatServerEvent(servId, status, ScarlettEvent.getStatus());
	}
}
