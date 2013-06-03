package ca.bsolomon.gw2events.worldevent;

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
public class ServerDashboardBean {

	Chronology gregorianJuian = GJChronology.getInstance(DateTimeZone.UTC);
	
	private static final long serialVersionUID = 1L;
	private DashboardModel model;  
	
	private boolean sorCollapsed = false;
	private boolean etCollapsed = false;
	private boolean fcCollapsed = false;
	
	private DateTime lastChange = new DateTime(gregorianJuian);
	
    public ServerDashboardBean() {  
        model = new DefaultDashboardModel();  
        DashboardColumn column1 = new DefaultDashboardColumn();  
        DashboardColumn column2 = new DefaultDashboardColumn();  
        DashboardColumn column3 = new DefaultDashboardColumn();  
          
        column1.addWidget("sorPanel");
        column2.addWidget("fcPanel");
        column3.addWidget("etPanel");
  
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
    	
    	if (Days.daysBetween(lastChange, now) == Days.ONE) {
    		lastChange = now;
    		sorCollapsed = false;
    	}
    	
		return sorCollapsed;
	}
    
    public boolean isEtCollapsed() {
    	DateTime now = new DateTime(gregorianJuian);
    	
    	if (Days.daysBetween(lastChange, now) == Days.ONE) {
    		lastChange = now;
    		etCollapsed = false;
    	}
    	
		return etCollapsed;
	}
    
    public boolean isFcCollapsed() {
    	DateTime now = new DateTime(gregorianJuian);
    	
    	if (Days.daysBetween(lastChange, now) == Days.ONE) {
    		lastChange = now;
    		fcCollapsed = false;
    	}
    	
		return fcCollapsed;
	}
}
