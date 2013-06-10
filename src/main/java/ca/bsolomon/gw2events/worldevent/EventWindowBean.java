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
import ca.bsolomon.gw2events.worldevent.enums.MawEvent;
import ca.bsolomon.gw2events.worldevent.enums.ShadowBehemothEvent;
import ca.bsolomon.gw2events.worldevent.util.DragonData;
import ca.bsolomon.gw2events.worldevent.util.EventData;
import ca.bsolomon.gw2events.worldevent.util.EventWindow;
import ca.bsolomon.gw2events.worldevent.util.LowLevelEventData;
import ca.bsolomon.gw2events.worldevent.util.LowPriorityEventData;

@ManagedBean(name="eventWindowBean")
@SessionScoped
public class EventWindowBean {

	private DragonData dragonData = new DragonData();
	private EventData lowLevelEventData = new LowLevelEventData();
	private LowPriorityEventData lowPriorityEventData = new LowPriorityEventData();
	
	public List<EventWindow> getEventWindows() {
		List<EventWindow> windows = new ArrayList<>();
		
		String eventUid = DragonEvent.TEQUATL.uid();
		windows.add(new EventWindow("Tequatl", dragonData.getMaxPeriod(eventUid), dragonData.getMinPeriod(eventUid)));
		
		eventUid = DragonEvent.SHATTERER_UP.uid();
		windows.add(new EventWindow("RageDragon", dragonData.getMaxPeriod(eventUid), dragonData.getMinPeriod(eventUid)));
		
		eventUid = DragonEvent.JORMAG_UP.uid();
		windows.add(new EventWindow("Jormag", dragonData.getMaxPeriod(eventUid), dragonData.getMinPeriod(eventUid)));
		
		eventUid = MawEvent.CHIEF.uid();
		windows.add(new EventWindow("Maw", lowLevelEventData.getMaxPeriod(eventUid), lowLevelEventData.getMinPeriod(eventUid)));
		
		eventUid = FireEleEvent.FIREELE.uid();
		windows.add(new EventWindow("Fire Elemental", lowLevelEventData.getMaxPeriod(eventUid), lowLevelEventData.getMinPeriod(eventUid)));
		
		eventUid = JungleWurmEvent.WURM.uid();
		windows.add(new EventWindow("Jungle Wurm", lowLevelEventData.getMaxPeriod(eventUid), lowLevelEventData.getMinPeriod(eventUid)));
		
		eventUid = ShadowBehemothEvent.SB.uid();
		windows.add(new EventWindow("SB", lowLevelEventData.getMaxPeriod(eventUid), lowLevelEventData.getMinPeriod(eventUid)));
		
		eventUid = GolemEvent.GOLEM.uid();
		windows.add(new EventWindow("Golem MKII", lowPriorityEventData.getMaxPeriod(eventUid), lowPriorityEventData.getMinPeriod(eventUid)));
		
		eventUid = DredgeEvent.DREDGE.uid();
		windows.add(new EventWindow("Dredge", lowPriorityEventData.getMaxPeriod(eventUid), lowPriorityEventData.getMinPeriod(eventUid)));
		
		eventUid = HarathiChestEvent.CENTAUR.uid();
		windows.add(new EventWindow("Kilava Chest", lowPriorityEventData.getMaxPeriod(eventUid), lowPriorityEventData.getMinPeriod(eventUid)));
		
		eventUid = FoulBearEvent.CHIEF.uid();
		windows.add(new EventWindow("Foulbear", lowPriorityEventData.getMaxPeriod(eventUid), lowPriorityEventData.getMinPeriod(eventUid)));
		
		eventUid = HydraQueenEvent.TAIDHA.uid();
		windows.add(new EventWindow("Hydra Queen", lowPriorityEventData.getMaxPeriod(eventUid), lowPriorityEventData.getMinPeriod(eventUid)));
		
		eventUid = FireShamanEnum.SHAMAN.uid();
		windows.add(new EventWindow("Fire Shaman", lowPriorityEventData.getMaxPeriod(eventUid), lowPriorityEventData.getMinPeriod(eventUid)));
		
		return windows;
	}
	
}
