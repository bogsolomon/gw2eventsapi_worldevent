package ca.bsolomon.gw2events.worldevent;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Ajax;
import org.primefaces.component.panel.Panel;

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
import ca.bsolomon.gw2events.worldevent.enums.ShadowBehemothEvent;
import ca.bsolomon.gw2events.worldevent.util.EventStatus;

@ManagedBean(name="ajaxPollBean")
@SessionScoped
public class AjaxPollBean {

	private ConcurrentMap<String, EventStatus> eventMap = new ConcurrentHashMap<>(70, 0.9f, 1);
	
	public void updateEvents(Panel fePanel, Panel teqPanel, Panel shatPanel, Panel jorPanel, Panel karkaPanel, Panel mawPanel,
			Panel wurmPanel, Panel sbPanel, Panel golemPanel, Panel dredgePanel, Panel harathiPanel, Panel ogrePanel, 
			Panel hydraPanel, Panel fireShamPanel) {
		List<EventStatus> status = DragonEvent.getTeqStatus();
		checkStatusUpdate(teqPanel, status, "-teq");
		
		status = DragonEvent.getShatStatus();
		checkStatusUpdate(shatPanel, status, "-shat");
		
		status = DragonEvent.getJorStatus();
		checkStatusUpdate(jorPanel, status, "-jor");
		
		status = KarkaEnum.getStatus();
		checkStatusUpdate(karkaPanel, status, "-karka");
		
		status = MawEvent.getStatus();
		checkStatusUpdate(mawPanel, status, "-maw");
		
		status = FireEleEvent.getStatus();
		checkStatusUpdate(fePanel, status, "-fe");
		
		status = JungleWurmEvent.getStatus();
		checkStatusUpdate(wurmPanel, status, "-wurm");
		
		status = ShadowBehemothEvent.getStatus();
		checkStatusUpdate(sbPanel, status, "-sb");
		
		status = GolemEvent.getStatus();
		checkStatusUpdate(golemPanel, status, "-golem");
		
		status = DredgeEvent.getStatus();
		checkStatusUpdate(dredgePanel, status, "-dredge");
		
		status = HarathiChestEvent.getStatus();
		checkStatusUpdate(harathiPanel, status, "-harathi");
		
		status = FoulBearEvent.getStatus();
		checkStatusUpdate(ogrePanel, status, "-ogre");
		
		status = HydraQueenEvent.getStatus();
		checkStatusUpdate(hydraPanel, status, "-hydra");
		
		status = FireShamanEnum.getStatus();
		checkStatusUpdate(fireShamPanel, status, "-firesham");
	}

	private void checkStatusUpdate(Panel panel, List<EventStatus> status, String event) {
		boolean toUpdate = false;
		
		for (EventStatus evStat:status) {
			String keyName = evStat.getServer()+event;
			
			if (eventMap.containsKey(keyName)) {
				String oldStatus = eventMap.get(keyName).getStatus();
				String newStatus = evStat.getStatus();
				
				if (!oldStatus.equals(newStatus)) {
					eventMap.put(keyName, evStat);
					toUpdate = true;
				}
			} else {
				eventMap.put(keyName, evStat);
				toUpdate = true;
			}
		}
		
		if (toUpdate) {
			Ajax.update(panel.getClientId());
		}
	}
	
}
