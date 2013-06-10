package ca.bsolomon.gw2events.worldevent;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Ajax;
import org.primefaces.component.datatable.DataTable;
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
import ca.bsolomon.gw2events.worldevent.enums.ServerID;
import ca.bsolomon.gw2events.worldevent.enums.ShadowBehemothEvent;
import ca.bsolomon.gw2events.worldevent.util.EventStatus;

@ManagedBean(name="ajaxPollBean")
@SessionScoped
public class AjaxPollBean {
	
	@ManagedProperty(value="#{checkboxBean}")
	private CheckboxBean checkboxBean;
	
	private ConcurrentMap<String, EventStatus> eventMap = new ConcurrentHashMap<>(70, 0.9f, 1);
	
	private ServerID serv1 = ServerID.SoR;
	private ServerID serv2 = ServerID.FC;
	private ServerID serv3 = ServerID.ET;
	
	public void updateEvents(Panel fePanel, Panel teqPanel, Panel shatPanel, Panel jorPanel, Panel karkaPanel, Panel mawPanel,
			Panel wurmPanel, Panel sbPanel, Panel golemPanel, Panel dredgePanel, Panel harathiPanel, Panel ogrePanel, 
			Panel hydraPanel, Panel fireShamPanel) {
		if (!checkboxBean.getSelectedEvents().contains("Tequatl"))
			checkStatusUpdate(teqPanel, DragonEvent.getTeqStatus(), "-teq");
		
		if (!checkboxBean.getSelectedEvents().contains("RageDragon"))
			checkStatusUpdate(shatPanel, DragonEvent.getShatStatus(), "-shat");
		
		if (!checkboxBean.getSelectedEvents().contains("Jormag"))
			checkStatusUpdate(jorPanel, DragonEvent.getJorStatus(), "-jor");
		
		if (!checkboxBean.getSelectedEvents().contains("Karka Queen"))
			checkStatusUpdate(karkaPanel, KarkaEnum.getStatus(), "-karka");
		
		if (!checkboxBean.getSelectedEvents().contains("Maw"))
			checkStatusUpdate(mawPanel, MawEvent.getStatus(), "-maw");
		
		if (!checkboxBean.getSelectedEvents().contains("Fire Ele"))
			checkStatusUpdate(fePanel, FireEleEvent.getStatus(), "-fe");
		
		if (!checkboxBean.getSelectedEvents().contains("Jungle Wurm"))
			checkStatusUpdate(wurmPanel, JungleWurmEvent.getStatus(), "-wurm");
		
		if (!checkboxBean.getSelectedEvents().contains("SB"))
			checkStatusUpdate(sbPanel, ShadowBehemothEvent.getStatus(), "-sb");
		
		if (!checkboxBean.getSelectedEvents().contains("Golem MKII"))
			checkStatusUpdate(golemPanel, GolemEvent.getStatus(), "-golem");
		
		if (!checkboxBean.getSelectedEvents().contains("Dredge"))
			checkStatusUpdate(dredgePanel, DredgeEvent.getStatus(), "-dredge");
		
		if (!checkboxBean.getSelectedEvents().contains("Kilava Chest"))
			checkStatusUpdate(harathiPanel, HarathiChestEvent.getStatus(), "-harathi");
		
		if (!checkboxBean.getSelectedEvents().contains("Foulbear"))
			checkStatusUpdate(ogrePanel, FoulBearEvent.getStatus(), "-ogre");
		
		if (!checkboxBean.getSelectedEvents().contains("Hydra Queen"))
			checkStatusUpdate(hydraPanel, HydraQueenEvent.getStatus(), "-hydra");
		
		if (!checkboxBean.getSelectedEvents().contains("Fire Shaman"))
			checkStatusUpdate(fireShamPanel, FireShamanEnum.getStatus(), "-firesham");
	}

	public void updateEventServer(DataTable serv1Table, DataTable serv2Table, DataTable serv3Table) {
		if (!checkboxBean.getSelectedEvents().contains("Tequatl"))
			checkServerEvent(serv1Table, serv2Table, serv3Table, DragonEvent.getTeqStatus(), "-teq");
		if (!checkboxBean.getSelectedEvents().contains("RageDragon"))
			checkServerEvent(serv1Table, serv2Table, serv3Table, DragonEvent.getShatStatus(), "-shat");
		if (!checkboxBean.getSelectedEvents().contains("Jormag"))
			checkServerEvent(serv1Table, serv2Table, serv3Table, DragonEvent.getJorStatus(), "-jor");
		
		if (!checkboxBean.getSelectedEvents().contains("Maw"))
			checkServerEvent(serv1Table, serv2Table, serv3Table, MawEvent.getStatus(), "-maw");
		if (!checkboxBean.getSelectedEvents().contains("Fire Ele"))
			checkServerEvent(serv1Table, serv2Table, serv3Table, FireEleEvent.getStatus(), "-fe");
		if (!checkboxBean.getSelectedEvents().contains("Jungle Wurm"))
			checkServerEvent(serv1Table, serv2Table, serv3Table, JungleWurmEvent.getStatus(), "-wurm");
		if (!checkboxBean.getSelectedEvents().contains("SB"))
			checkServerEvent(serv1Table, serv2Table, serv3Table, ShadowBehemothEvent.getStatus(), "-sb");
		
		if (!checkboxBean.getSelectedEvents().contains("Golem MKII"))
			checkServerEvent(serv1Table, serv2Table, serv3Table, GolemEvent.getStatus(), "-golem");
		if (!checkboxBean.getSelectedEvents().contains("Dredge"))
			checkServerEvent(serv1Table, serv2Table, serv3Table, DredgeEvent.getStatus(), "-dredge");
		if (!checkboxBean.getSelectedEvents().contains("Kilava Chest"))
			checkServerEvent(serv1Table, serv2Table, serv3Table, HarathiChestEvent.getStatus(), "-harathi");
		if (!checkboxBean.getSelectedEvents().contains("Foulbear"))
			checkServerEvent(serv1Table, serv2Table, serv3Table, FoulBearEvent.getStatus(), "-ogre");
		if (!checkboxBean.getSelectedEvents().contains("Hydra Queen"))
			checkServerEvent(serv1Table, serv2Table, serv3Table, HydraQueenEvent.getStatus(), "-hydra");
		if (!checkboxBean.getSelectedEvents().contains("Fire Shaman"))
			checkServerEvent(serv1Table, serv2Table, serv3Table, FireShamanEnum.getStatus(), "-firesham");
		if (!checkboxBean.getSelectedEvents().contains("Karka Queen"))
			checkServerEvent(serv1Table, serv2Table, serv3Table, KarkaEnum.getStatus(), "-karka");
	}

	private void checkServerEvent(DataTable serv1Table, DataTable serv2Table,
			DataTable serv3Table, List<EventStatus> status,  String event) {
		ServerID oldServ1 = serv1;
		ServerID oldServ2 = serv2;
		ServerID oldServ3 = serv3;
		
		serv1 = ServerID.getByID(checkboxBean.getServerOne().getServerId());
		serv2 = ServerID.getByID(checkboxBean.getServerTwo().getServerId());
		serv3 = ServerID.getByID(checkboxBean.getServerThree().getServerId());
		
		if (serv1 == oldServ1)
			checkStatusUpdate(serv1Table, status, event, serv1);
		else
			Ajax.update(serv1Table.getClientId());
			
		if (serv2 == oldServ2)
			checkStatusUpdate(serv2Table, status, event, serv2);
		else
			Ajax.update(serv2Table.getClientId());
		
		if (serv3 == oldServ3)
			checkStatusUpdate(serv3Table, status, event, serv3);
		else
			Ajax.update(serv3Table.getClientId());
	}
	
	private void checkStatusUpdate(DataTable servTable,
			List<EventStatus> status, String event, ServerID serv) {
		boolean toUpdate = false;
		//boolean toUpdateRow = false;
		
		String keyName = serv.toString()+event;
		
		if (eventMap.containsKey(keyName)) {
			String oldStatus = eventMap.get(keyName).getStatus();
			
			for (EventStatus evStat:status) {
				if (evStat.getServer().equals(serv.toString())) {
					String newStatus = evStat.getStatus();
					
					if (!oldStatus.equals(newStatus)) {
						eventMap.put(keyName, evStat);
						//toUpdateRow = true;
						toUpdate = true;
					}
				}
			}
		} else {
			for (EventStatus evStat:status) {
				if (evStat.getServer().equals(serv.toString())) {
					eventMap.put(keyName, evStat);
					toUpdate = true;
				}
			}
		}
		
		if (toUpdate) {
			Ajax.update(servTable.getClientId());
		}/* else if (toUpdateRow) {
			Ajax.updateRow(servTable, rowCount);
		}*/
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

	public void setCheckboxBean(CheckboxBean checkboxBean) {
		this.checkboxBean = checkboxBean;
	}
}