package ca.bsolomon.gw2events.worldevent;

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
import ca.bsolomon.gw2events.worldevent.enums.MawEvent;
import ca.bsolomon.gw2events.worldevent.enums.ServerID;
import ca.bsolomon.gw2events.worldevent.enums.ShadowBehemothEvent;
import ca.bsolomon.gw2events.worldevent.util.DragonData;
import ca.bsolomon.gw2events.worldevent.util.EventData;
import ca.bsolomon.gw2events.worldevent.util.LowLevelEventData;
import ca.bsolomon.gw2events.worldevent.util.LowPriorityEventData;

@ManagedBean(name="dragonBean")
@SessionScoped
public class DragonBean {

	private DragonData dragonData = new DragonData();
	private EventData lowLevelData = new LowLevelEventData();
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
	
	public String getHydraStatus() {
		return HydraQueenEvent.formatHydraString(lowPriorityData);
	}
	
	public String getFireShamStatus() {
		return FireShamanEnum.formatShamanString(lowPriorityData);
	}
	
	public String getSorStatus() {
		ServerID servId = ServerID.SoR;
		
		StringBuffer sb = new StringBuffer();
		
		formatServer(servId, sb);
		
		return sb.toString();
	}
	
	public String getEtStatus() {
		ServerID servId = ServerID.ET;
		
		StringBuffer sb = new StringBuffer();
		
		formatServer(servId, sb);
		
		return sb.toString();
	}

	public String getFcStatus() {
		ServerID servId = ServerID.FC;
		
		StringBuffer sb = new StringBuffer();
		
		formatServer(servId, sb);
		
		return sb.toString();
	}
	
	public void formatServer(ServerID servId, StringBuffer sb) {
		sb.append("Tequatl - ");
		DragonEvent.formatTequatlString(dragonData, sb, servId);
		sb.append("Shaterrer - ");
		DragonEvent.formatShattererString(dragonData, sb, servId);
		sb.append("Jormag - ");
		DragonEvent.formatJormagString(dragonData, sb, servId);
		
		sb.append("Maw - ");
		MawEvent.formatMawString(lowLevelData, sb, servId);
		sb.append("Fire Ele - ");
		FireEleEvent.formatFireEleString(lowLevelData, sb, servId);
		sb.append("Jungle Wurm - ");
		JungleWurmEvent.formatJungleWurmString(lowLevelData, sb, servId);
		sb.append("SB - ");
		ShadowBehemothEvent.formatShadowBehemothString(lowLevelData, sb, servId);
		
		sb.append("Golem MKII - ");
		GolemEvent.formatGolemString(lowPriorityData, sb, servId);
		sb.append("Dredge - ");
		DredgeEvent.formatDredgeString(lowPriorityData, sb, servId);
		sb.append("Kilava Chest - ");
		HarathiChestEvent.formatHarathiString(lowPriorityData, sb, servId);
		sb.append("Foulbear - ");
		FoulBearEvent.formatOgreString(lowPriorityData, sb, servId);
		sb.append("Hydra Queen - ");
		HydraQueenEvent.formatHydraString(lowPriorityData, sb, servId);
		sb.append("Fire Shaman - ");
		FireShamanEnum.formatShamanString(lowPriorityData, sb, servId);
	}
}
