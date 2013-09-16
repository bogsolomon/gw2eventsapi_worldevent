package ca.bsolomon.gw2events.worldevent;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Ajax;
import org.primefaces.component.datatable.DataTable;

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
import ca.bsolomon.gw2events.worldevent.temples.enums.GatesofArahEvent;
import ca.bsolomon.gw2events.worldevent.temples.enums.GrenthEvent;
import ca.bsolomon.gw2events.worldevent.temples.enums.LyssaEvent;
import ca.bsolomon.gw2events.worldevent.temples.enums.MelandruEvent;
import ca.bsolomon.gw2events.worldevent.temples.enums.PlinxEvent;
import ca.bsolomon.gw2events.worldevent.temples.enums.ScarlettEvent;
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
	
	public void updateEventServer(DataTable serv1Table, DataTable serv2Table, DataTable serv3Table) {
		int rowCount = 0;
		
		if (!checkboxBean.getSelectedEvents().contains("Maw")) {
			checkServerEvent(serv1Table, serv2Table, serv3Table, MawEvent.getStatus(), "-maw", rowCount);
			rowCount++;
		}
		if (!checkboxBean.getSelectedEvents().contains("Fire Ele")) {
			checkServerEvent(serv1Table, serv2Table, serv3Table, FireEleEvent.getStatus(), "-fe", rowCount);
			rowCount++;
		}
		if (!checkboxBean.getSelectedEvents().contains("Jungle Wurm")) {
			checkServerEvent(serv1Table, serv2Table, serv3Table, JungleWurmEvent.getStatus(), "-wurm", rowCount);
			rowCount++;
		}
		if (!checkboxBean.getSelectedEvents().contains("SB")) {
			checkServerEvent(serv1Table, serv2Table, serv3Table, ShadowBehemothEvent.getStatus(), "-sb", rowCount);
			rowCount++;
		}
		if (!checkboxBean.getSelectedEvents().contains("Flame")) {
			checkServerEvent(serv1Table, serv2Table, serv3Table, FlameBattleEvent.getStatus(), "-flamebattle", rowCount);
			rowCount++;
		}

		if (!checkboxBean.getSelectedEvents().contains("Foulbear")) {
			checkServerEvent(serv1Table, serv2Table, serv3Table, FoulBearEvent.getStatus(), "-ogre", rowCount);
			rowCount++;
		}
		if (!checkboxBean.getSelectedEvents().contains("Kilava Chest")) {
			checkServerEvent(serv1Table, serv2Table, serv3Table, HarathiChestEvent.getStatus(), "-harathi", rowCount);
			rowCount++;
		}
		if (!checkboxBean.getSelectedEvents().contains("Dredge")) {
			checkServerEvent(serv1Table, serv2Table, serv3Table, DredgeEvent.getStatus(), "-dredge", rowCount);
			rowCount++;
		}
		if (!checkboxBean.getSelectedEvents().contains("Hydra Queen")) {
			checkServerEvent(serv1Table, serv2Table, serv3Table, HydraQueenEvent.getStatus(), "-hydra", rowCount);
			rowCount++;
		}
		

		if (!checkboxBean.getSelectedEvents().contains("RageDragon")) {
			checkServerEvent(serv1Table, serv2Table, serv3Table, DragonEvent.getShatStatus(), "-shat", rowCount);
			rowCount++;
		}
		if (!checkboxBean.getSelectedEvents().contains("Fire Shaman")) {
			checkServerEvent(serv1Table, serv2Table, serv3Table, FireShamanEnum.getStatus(), "-firesham", rowCount);
			rowCount++;
		}
		if (!checkboxBean.getSelectedEvents().contains("Tequatl")) {
			checkServerEvent(serv1Table, serv2Table, serv3Table, DragonEvent.getTeqStatus(), "-teq", rowCount);
			rowCount++;
		}
		if (!checkboxBean.getSelectedEvents().contains("Megadestroyer")) {
			checkServerEvent(serv1Table, serv2Table, serv3Table, MegadestroyerEvent.getStatus(), "-megad", rowCount);
			rowCount++;
		}
		if (!checkboxBean.getSelectedEvents().contains("Golem MKII")) {
			checkServerEvent(serv1Table, serv2Table, serv3Table, GolemEvent.getStatus(), "-golem", rowCount);
			rowCount++;
		}
		if (!checkboxBean.getSelectedEvents().contains("Jormag")) {
			checkServerEvent(serv1Table, serv2Table, serv3Table, DragonEvent.getJorStatus(), "-jor", rowCount);
			rowCount++;
		}
		if (!checkboxBean.getSelectedEvents().contains("Karka Queen")) {
			checkServerEvent(serv1Table, serv2Table, serv3Table, KarkaEvent.getStatus(), "-karka", rowCount);
			rowCount++;
		}
	}
	
	public void updateEventTempleServer(DataTable serv1Table, DataTable serv2Table, DataTable serv3Table) {
		int rowCount = 0;
		
		if (!checkboxBean.getSelectedEvents().contains("Balthazar")) {
			checkServerEvent(serv1Table, serv2Table, serv3Table, BalthazzarEvent.getStatus(), "-balth", rowCount);
			rowCount++;
		}
		if (!checkboxBean.getSelectedEvents().contains("Lyssa")) {
			checkServerEvent(serv1Table, serv2Table, serv3Table, LyssaEvent.getStatus(), "-lyssa", rowCount);
			rowCount++;
		}
		if (!checkboxBean.getSelectedEvents().contains("Dwayna")) {
			checkServerEvent(serv1Table, serv2Table, serv3Table, DwaynaEvent.getStatus(), "-dwayna", rowCount);
			rowCount++;
		}
		if (!checkboxBean.getSelectedEvents().contains("Melandru")) {
			checkServerEvent(serv1Table, serv2Table, serv3Table, MelandruEvent.getStatus(), "-melandru", rowCount);
			rowCount++;
		}
		if (!checkboxBean.getSelectedEvents().contains("Grenth")) {
			checkServerEvent(serv1Table, serv2Table, serv3Table, GrenthEvent.getStatus(), "-grenth", rowCount);
			rowCount++;
		}
		if (!checkboxBean.getSelectedEvents().contains("Scarlett")) {
			checkServerEvent(serv1Table, serv2Table, serv3Table, ScarlettEvent.getStatus(), "-scarlett", rowCount);
			rowCount++;
		}
		if (!checkboxBean.getSelectedEvents().contains("Plinx")) {
			checkServerEvent(serv1Table, serv2Table, serv3Table, PlinxEvent.getStatus(), "-plinx", rowCount);
			rowCount++;
		}
		if (!checkboxBean.getSelectedEvents().contains("Arah")) {
			checkServerEvent(serv1Table, serv2Table, serv3Table, GatesofArahEvent.getStatus(), "-arah", rowCount);
			rowCount++;
		}
		
	}

	private void checkServerEvent(DataTable serv1Table, DataTable serv2Table,
			DataTable serv3Table, List<EventStatus> status,  String event, int rowCount) {
		ServerID oldServ1 = serv1;
		ServerID oldServ2 = serv2;
		ServerID oldServ3 = serv3;
		
		serv1 = ServerID.getByID(checkboxBean.getServerOne().getServerId());
		serv2 = ServerID.getByID(checkboxBean.getServerTwo().getServerId());
		serv3 = ServerID.getByID(checkboxBean.getServerThree().getServerId());
		
		if (serv1 == oldServ1)
			checkStatusUpdate(serv1Table, status, event, serv1, rowCount);
		else
			Ajax.update(serv1Table.getClientId());
			
		if (serv2 == oldServ2)
			checkStatusUpdate(serv2Table, status, event, serv2, rowCount);
		else
			Ajax.update(serv2Table.getClientId());
		
		if (serv3 == oldServ3)
			checkStatusUpdate(serv3Table, status, event, serv3, rowCount);
		else
			Ajax.update(serv3Table.getClientId());
	}
	
	private void checkStatusUpdate(DataTable servTable,
			List<EventStatus> status, String event, ServerID serv, int rowCount) {
		boolean toUpdate = false;
		boolean toUpdateRow = false;
		
		String keyName = serv.toString()+event;
		
		if (eventMap.containsKey(keyName)) {
			String oldStatus = eventMap.get(keyName).getStatus();
			
			for (EventStatus evStat:status) {
				if (evStat.getServer().equals(serv.toString())) {
					String newStatus = evStat.getStatus();
					
					if (!oldStatus.equals(newStatus)) {
						eventMap.put(keyName, evStat);
						toUpdateRow = true;
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
		} else if (toUpdateRow) {
			Ajax.updateRow(servTable, rowCount);
		}
	}

	public void setCheckboxBean(CheckboxBean checkboxBean) {
		this.checkboxBean = checkboxBean;
	}
}