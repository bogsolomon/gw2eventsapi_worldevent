package ca.bsolomon.gw2events.worldevent;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ca.bsolomon.gw2events.worldevent.dynamodb.EventWindowData;
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
import ca.bsolomon.gw2events.worldevent.util.EventWindow;

@ManagedBean(name="eventWindowBean")
@SessionScoped
public class EventWindowBean {

	public List<EventWindow> getEventWindows() {
		List<EventWindow> windows = new ArrayList<>();
		
		String eventUid = MawEvent.CHIEF.uid();
		windows.add(new EventWindow("Maw", EventWindowData.getMaxWindow(eventUid), EventWindowData.getMinWindow(eventUid)));
		
		eventUid = FireEleEvent.FIREELE.uid();
		windows.add(new EventWindow("Fire Elemental", EventWindowData.getMaxWindow(eventUid), EventWindowData.getMinWindow(eventUid)));
		
		eventUid = JungleWurmEvent.WURM.uid();
		windows.add(new EventWindow("Jungle Wurm", EventWindowData.getMaxWindow(eventUid), EventWindowData.getMinWindow(eventUid)));
		
		eventUid = ShadowBehemothEvent.SB.uid();
		windows.add(new EventWindow("SB", EventWindowData.getMaxWindow(eventUid), EventWindowData.getMinWindow(eventUid)));
		
		eventUid = FoulBearEvent.CHIEF.uid();
		windows.add(new EventWindow("Foulbear", EventWindowData.getMaxWindow(eventUid), EventWindowData.getMinWindow(eventUid)));
		
		eventUid = HarathiChestEvent.CENTAUR.uid();
		windows.add(new EventWindow("Kilava Chest", EventWindowData.getMaxWindow(eventUid), EventWindowData.getMinWindow(eventUid)));
		
		eventUid = DredgeEvent.DREDGE.uid();
		windows.add(new EventWindow("Dredge", EventWindowData.getMaxWindow(eventUid), EventWindowData.getMinWindow(eventUid)));
		
		eventUid = HydraQueenEvent.TAIDHA.uid();
		windows.add(new EventWindow("Hydra Queen", EventWindowData.getMaxWindow(eventUid), EventWindowData.getMinWindow(eventUid)));
		
		eventUid = DragonEvent.SHATTERER_UP.uid();
		windows.add(new EventWindow("RageDragon", EventWindowData.getMaxWindow(eventUid), EventWindowData.getMinWindow(eventUid)));
		
		eventUid = FireShamanEnum.SHAMAN.uid();
		windows.add(new EventWindow("Fire Shaman", EventWindowData.getMaxWindow(eventUid), EventWindowData.getMinWindow(eventUid)));
		
		eventUid = DragonEvent.TEQUATL.uid();
		windows.add(new EventWindow("Tequatl", EventWindowData.getMaxWindow(eventUid), EventWindowData.getMinWindow(eventUid)));
		
		eventUid = GolemEvent.GOLEM.uid();
		windows.add(new EventWindow("Golem MKII", EventWindowData.getMaxWindow(eventUid), EventWindowData.getMinWindow(eventUid)));
		
		eventUid = DragonEvent.JORMAG_UP.uid();
		windows.add(new EventWindow("Jormag", EventWindowData.getMaxWindow(eventUid), EventWindowData.getMinWindow(eventUid)));
		
		windows.add(new EventWindow("Scarlett", EventWindowData.getMaxWindow(eventUid), EventWindowData.getMinWindow(eventUid)));
		
		return windows;
	}
	
	public void calculateEventWindows() {
		
	}
}
