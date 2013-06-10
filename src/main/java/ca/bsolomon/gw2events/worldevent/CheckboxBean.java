package ca.bsolomon.gw2events.worldevent;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Ajax;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.layout.LayoutUnit;
import org.primefaces.component.panel.Panel;
import org.primefaces.event.ResizeEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;

import ca.bsolomon.gw2events.worldevent.enums.ServerID;
import ca.bsolomon.gw2events.worldevent.util.Server;

@ManagedBean(name="checkboxBean")
@SessionScoped
public class CheckboxBean {

	private List<String> selectedEvents;
  
    private Map<String,String> events;  
    
    private List<Server> serverIds = new ArrayList<>();
    
    private Server serverOne;
    private Server serverTwo;
    private Server serverThree;

	private int eastSize = 300;
    private boolean eastCollapsed;
	
	public CheckboxBean() {  
    	events = new LinkedHashMap<String, String>();  
    	events.put("Tequatl", "Tequatl");  
    	events.put("RageDragon", "RageDragon");  
    	events.put("Jormag", "Jormag");  
    	
    	events.put("Maw", "Maw");
    	events.put("Fire Ele", "Fire Ele");  
    	events.put("Jungle Wurm", "Jungle Wurm");  
    	events.put("SB", "SB");
    	
    	events.put("Golem MKII", "Golem MKII");
    	events.put("Dredge", "Dredge");
    	events.put("Kilava Chest", "Kilava Chest");
    	events.put("Foulbear", "Foulbear");
    	events.put("Hydra Queen", "Hydra Queen");
    	events.put("Fire Shaman", "Fire Shaman");
    	events.put("Karka Queen", "Karka Queen");
    	
		for (ServerID servId:ServerID.values()) {
			serverIds.add(new Server(servId.getUid(), servId.getName()));
		}
		serverOne = new Server(ServerID.SoR.getUid(), ServerID.SoR.getName());
		serverTwo = new Server(ServerID.STORMBLUFF.getUid(), ServerID.STORMBLUFF.getName());
		serverThree =new Server(ServerID.MAGUM.getUid(), ServerID.MAGUM.getName());
    }
	
	public void handleToggle(ToggleEvent event) {
		if (((LayoutUnit)event.getComponent()).getPosition().equals("east")) {
			eastCollapsed = (event.getVisibility() == Visibility.HIDDEN);
		}
	}
	
    public void handleResize(ResizeEvent event) {
    	int newWidth = event.getWidth();
    	if (((LayoutUnit)event.getComponent()).getPosition().equals("east")) {
    		eastSize = newWidth;
    	}
    }
    
    public int getEastSize() {
		return eastSize;
	}

	public void setEastSize(int eastSize) {
		this.eastSize = eastSize;
	}
    
	public List<String> getSelectedEvents() {
		if (selectedEvents == null)
			return new ArrayList<>();
		return selectedEvents;
	}

	public void setSelectedEvents(List<String> selectedEvents) {
		this.selectedEvents = selectedEvents;
	}

	public Map<String, String> getEvents() {
		return events;
	}

	public void setEvents(Map<String, String> events) {
		this.events = events;
	}  
	
	public void handleCheckbox(DataTable serv1Table, DataTable serv2Table, DataTable serv3Table) {  
		Ajax.update(serv1Table.getClientId());
		Ajax.update(serv2Table.getClientId());
		Ajax.update(serv3Table.getClientId());
	}
	
	public void handleServChange() {  
		
	}
	
	public void handleCheckbox(Panel fePanel, Panel teqPanel, Panel shatPanel, Panel jorPanel, Panel karkaPanel, Panel mawPanel,
			Panel wurmPanel, Panel sbPanel, Panel golemPanel, Panel dredgePanel, Panel harathiPanel, Panel ogrePanel, 
			Panel hydraPanel, Panel fireShamPanel) {  
		Ajax.update(fePanel.getClientId());
		Ajax.update(teqPanel.getClientId());
		Ajax.update(shatPanel.getClientId());
		Ajax.update(jorPanel.getClientId());
		Ajax.update(karkaPanel.getClientId());
		Ajax.update(mawPanel.getClientId());
		Ajax.update(wurmPanel.getClientId());
		Ajax.update(sbPanel.getClientId());
		Ajax.update(golemPanel.getClientId());
		Ajax.update(dredgePanel.getClientId());
		Ajax.update(harathiPanel.getClientId());
		Ajax.update(ogrePanel.getClientId());
		Ajax.update(hydraPanel.getClientId());
		Ajax.update(fireShamPanel.getClientId());
	}
	
	public void clearAll(DataTable serv1Table, DataTable serv2Table, DataTable serv3Table) {  
		selectedEvents.clear();
		
		Ajax.update(serv1Table.getClientId());
		Ajax.update(serv2Table.getClientId());
		Ajax.update(serv3Table.getClientId());
	}
	
	public void clearAll(Panel fePanel, Panel teqPanel, Panel shatPanel, Panel jorPanel, Panel karkaPanel, Panel mawPanel,
			Panel wurmPanel, Panel sbPanel, Panel golemPanel, Panel dredgePanel, Panel harathiPanel, Panel ogrePanel, 
			Panel hydraPanel, Panel fireShamPanel) {  
		selectedEvents.clear();
		
		Ajax.update(fePanel.getClientId());
		Ajax.update(teqPanel.getClientId());
		Ajax.update(shatPanel.getClientId());
		Ajax.update(jorPanel.getClientId());
		Ajax.update(karkaPanel.getClientId());
		Ajax.update(mawPanel.getClientId());
		Ajax.update(wurmPanel.getClientId());
		Ajax.update(sbPanel.getClientId());
		Ajax.update(golemPanel.getClientId());
		Ajax.update(dredgePanel.getClientId());
		Ajax.update(harathiPanel.getClientId());
		Ajax.update(ogrePanel.getClientId());
		Ajax.update(hydraPanel.getClientId());
		Ajax.update(fireShamPanel.getClientId());
	}

	public boolean isEastCollapsed() {
		return eastCollapsed;
	}

	public void setEastCollapsed(boolean eastCollapsed) {
		this.eastCollapsed = eastCollapsed;
	}
	
    public List<Server> getServerIds() {
		return serverIds;
	}

	public void setServerIds(List<Server> serverIds) {
		this.serverIds = serverIds;
	}

	public Server getServerOne() {
		return serverOne;
	}

	public void setServerOne(Server server) {
		if (server.equals(serverTwo) || server.equals(serverThree))
			return;
		
		this.serverOne = server;
	}

	public Server getServerTwo() {
		return serverTwo;
	}

	public void setServerTwo(Server server) {
		if (server.equals(serverOne) || server.equals(serverThree))
			return;
		this.serverTwo = server;
	}

	public Server getServerThree() {
		return serverThree;
	}

	public void setServerThree(Server server) {
		if (server.equals(serverOne) || server.equals(serverTwo))
			return;
		this.serverThree = server;
	}
}
