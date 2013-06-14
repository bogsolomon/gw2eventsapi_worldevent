package ca.bsolomon.gw2events.worldevent;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.chrono.GJChronology;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.primefaces.model.Visibility;

@ManagedBean(name="serverDashboardBean")
@SessionScoped
public class ServerDashboardBean implements Serializable {

	Chronology gregorianJuian = GJChronology.getInstance(DateTimeZone.UTC);
	
	private static final long serialVersionUID = 1L;
	private DashboardModel model;  
	
	private boolean sorCollapsed = false;
	private boolean etCollapsed = false;
	private boolean fcCollapsed = false;
	
	private boolean serv1Collapsed = false;
	private boolean serv2Collapsed = false;
	private boolean serv3Collapsed = false;
	
	private DateTime lastChange = new DateTime(gregorianJuian);
	
    public ServerDashboardBean() {  
        model = new DefaultDashboardModel();  
        DashboardColumn column1 = new DefaultDashboardColumn();  
        DashboardColumn column2 = new DefaultDashboardColumn();  
        DashboardColumn column3 = new DefaultDashboardColumn();  
          
        column1.addWidget("sorPanel");
        column1.addWidget("serv1TemplePanel");
        
        column2.addWidget("fcPanel");
        column2.addWidget("serv2TemplePanel");
        
        column3.addWidget("etPanel");
        column3.addWidget("serv3TemplePanel");
  
        model.addColumn(column1);  
        model.addColumn(column2);  
        model.addColumn(column3);  
    }
    
    public DashboardModel getModel() {  
        return model;  
    }

	public void handleSorToggle(ToggleEvent event) {
    	lastChange = new DateTime(gregorianJuian);
    	sorCollapsed = (event.getVisibility() == Visibility.HIDDEN);
    }
    
	public void handleEtToggle(ToggleEvent event) {
    	lastChange = new DateTime(gregorianJuian);
    	etCollapsed = (event.getVisibility() == Visibility.HIDDEN);
    }
    
    public void handleFcToggle(ToggleEvent event) {
    	lastChange = new DateTime(gregorianJuian);
    	fcCollapsed = (event.getVisibility() == Visibility.HIDDEN);
    }
    
    public boolean isSorCollapsed() {
    	DateTime now = new DateTime(gregorianJuian);
    	
    	if (Days.daysBetween(lastChange.toDateMidnight(), now.toDateMidnight()) == Days.ONE) {
    		lastChange = now;
    		sorCollapsed = false;
    	}
    	
		return sorCollapsed;
	}
    
    public boolean isEtCollapsed() {
    	DateTime now = new DateTime(gregorianJuian);
    	
    	if (Days.daysBetween(lastChange.toDateMidnight(), now.toDateMidnight()) == Days.ONE) {
    		lastChange = now;
    		etCollapsed = false;
    	}
    	
		return etCollapsed;
	}
    
    public boolean isFcCollapsed() {
    	DateTime now = new DateTime(gregorianJuian);
    	
    	if (Days.daysBetween(lastChange.toDateMidnight(), now.toDateMidnight()) == Days.ONE) {
    		lastChange = now;
    		fcCollapsed = false;
    	}
    	
		return fcCollapsed;
	}

	public boolean isServ1Collapsed() {
		return serv1Collapsed;
	}
	
	public void handleServ1Toggle(ToggleEvent event) {
    	serv1Collapsed = (event.getVisibility() == Visibility.HIDDEN);
    }
    
	public boolean isServ2Collapsed() {
		return serv2Collapsed;
	}
	
	public void handleServ2Toggle(ToggleEvent event) {
    	serv2Collapsed = (event.getVisibility() == Visibility.HIDDEN);
    }
	
	public boolean isServ3Collapsed() {
		return serv3Collapsed;
	}
	
	public void handleServ3Toggle(ToggleEvent event) {
    	serv3Collapsed = (event.getVisibility() == Visibility.HIDDEN);
    }
}
