package ca.bsolomon.gw2events.worldevent;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.chrono.GJChronology;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.primefaces.model.Visibility;

@ManagedBean(name="dashboardBean")
@SessionScoped
public class DashboardBean implements Serializable {  

	Chronology gregorianJuian = GJChronology.getInstance(DateTimeZone.UTC);
	
	private static final long serialVersionUID = 1L;
	private DashboardModel model;  
	
	private boolean teqCollapsed = false;
	private boolean shatCollapsed = false;
	private boolean jormagCollapsed = false;
	private boolean mawCollapsed = false;
	private boolean fireEleCollapsed = false;
	private boolean wurmCollapsed = false;
	private boolean sbCollapsed = false;
	private boolean golemCollapsed = false;
	private boolean dredgeCollapsed = false;
	private boolean harathiCollapsed = false;
	private boolean ogreCollapsed = false;
	private boolean hydraCollapsed = false;
	private boolean fireShamCollapsed = false;
	private boolean karkaCollapsed = false;
	
	private DateTime lastChange = new DateTime(gregorianJuian);;
      
    public DashboardBean() {  
        model = new DefaultDashboardModel();  
        DashboardColumn column1 = new DefaultDashboardColumn();  
        DashboardColumn column2 = new DefaultDashboardColumn();  
        DashboardColumn column3 = new DefaultDashboardColumn();  
          
        column1.addWidget("teqPanel");
        column1.addWidget("shatPanel");
        column1.addWidget("jorPanel");
        column1.addWidget("karkaPanel");
        
        column2.addWidget("mawPanel");
        column2.addWidget("fireElePanel");
        column2.addWidget("wurmPanel");
        column2.addWidget("sbPanel");
        
        column3.addWidget("golemPanel");
        column3.addWidget("dredgePanel");
        column3.addWidget("harathiPanel");
        column3.addWidget("ogrePanel");
        column3.addWidget("hydraPanel");
        column3.addWidget("fireShamPanel");
        
  
        model.addColumn(column1);  
        model.addColumn(column2);  
        model.addColumn(column3);  
    }  
      
    public DashboardModel getModel() {  
        return model;  
    }
    
    public void handleKarkaToggle(ToggleEvent event) {
    	lastChange = new DateTime(gregorianJuian);
    	karkaCollapsed = (event.getVisibility() == Visibility.HIDDEN);
    }
    
    public void handleTeqToggle(ToggleEvent event) {
    	lastChange = new DateTime(gregorianJuian);
    	teqCollapsed = (event.getVisibility() == Visibility.HIDDEN);
    }

    public void handleShatToggle(ToggleEvent event) { 
    	lastChange = new DateTime(gregorianJuian);
    	shatCollapsed = (event.getVisibility() == Visibility.HIDDEN);
    }

    public void handleJormagToggle(ToggleEvent event) {
    	lastChange = new DateTime(gregorianJuian);
    	jormagCollapsed = (event.getVisibility() == Visibility.HIDDEN);
    }

    public void handleMawToggle(ToggleEvent event) {
    	lastChange = new DateTime(gregorianJuian);
    	mawCollapsed = (event.getVisibility() == Visibility.HIDDEN);
    }
    
    public void handleFireEleToggle(ToggleEvent event) {
    	lastChange = new DateTime(gregorianJuian);
    	fireEleCollapsed = (event.getVisibility() == Visibility.HIDDEN);
    }
    
    public void handleWurmToggle(ToggleEvent event) {  
    	lastChange = new DateTime(gregorianJuian);
    	wurmCollapsed = (event.getVisibility() == Visibility.HIDDEN);
    }
    
    public void handleSBToggle(ToggleEvent event) {
    	lastChange = new DateTime(gregorianJuian);
    	sbCollapsed = (event.getVisibility() == Visibility.HIDDEN);
    }
    
    public void handleGolemToggle(ToggleEvent event) {  
    	lastChange = new DateTime(gregorianJuian);
    	golemCollapsed = (event.getVisibility() == Visibility.HIDDEN);
    }
    
    public void handleDredgeToggle(ToggleEvent event) {
    	lastChange = new DateTime(gregorianJuian);
    	dredgeCollapsed = (event.getVisibility() == Visibility.HIDDEN);
    }
    
    public void handleHarathiToggle(ToggleEvent event) {  
    	lastChange = new DateTime(gregorianJuian);
    	harathiCollapsed = (event.getVisibility() == Visibility.HIDDEN);
    }
    
    public void handleOgreToggle(ToggleEvent event) {
    	lastChange = new DateTime(gregorianJuian);
    	ogreCollapsed = (event.getVisibility() == Visibility.HIDDEN);
    }
    
    public void handleHydraToggle(ToggleEvent event) {
    	lastChange = new DateTime(gregorianJuian);
    	hydraCollapsed = (event.getVisibility() == Visibility.HIDDEN);
    }
    
    public void handleFireShamToggle(ToggleEvent event) {  
    	lastChange = new DateTime(gregorianJuian);
    	fireShamCollapsed = (event.getVisibility() == Visibility.HIDDEN);
    }
    
    public boolean isKarkaCollapsed() {
    	DateTime now = new DateTime(gregorianJuian);
    	
    	if (Days.daysBetween(lastChange.toDateMidnight(), now.toDateMidnight()) == Days.ONE) {
    		lastChange = now;
    		karkaCollapsed = false;
    	}
    	
		return karkaCollapsed;
	}
    
    public boolean isTeqCollapsed() {
    	DateTime now = new DateTime(gregorianJuian);
    	
    	if (Days.daysBetween(lastChange.toDateMidnight(), now.toDateMidnight()) == Days.ONE) {
    		lastChange = now;
    		teqCollapsed = false;
    	}
    	
		return teqCollapsed;
	}

	public boolean isShatCollapsed() {
    	DateTime now = new DateTime(gregorianJuian);
    	
    	if (Days.daysBetween(lastChange.toDateMidnight(), now.toDateMidnight()) == Days.ONE) {
    		lastChange = now;
    		shatCollapsed = false;
    	}
		
		return shatCollapsed;
	}

	public boolean isJormagCollapsed() {
    	DateTime now = new DateTime(gregorianJuian);
    	
    	if (Days.daysBetween(lastChange.toDateMidnight(), now.toDateMidnight()) == Days.ONE) {
    		lastChange = now;
    		jormagCollapsed = false;
    	}
		
		return jormagCollapsed;
	}

	public boolean isMawCollapsed() {
    	DateTime now = new DateTime(gregorianJuian);
    	
    	if (Days.daysBetween(lastChange.toDateMidnight(), now.toDateMidnight()) == Days.ONE) {
    		lastChange = now;
    		mawCollapsed = false;
    	}
		
		return mawCollapsed;
	}

	public boolean isFireEleCollapsed() {
    	DateTime now = new DateTime(gregorianJuian);
    	
    	if (Days.daysBetween(lastChange.toDateMidnight(), now.toDateMidnight()) == Days.ONE) {
    		lastChange = now;
    		fireEleCollapsed = false;
    	}
		
		return fireEleCollapsed;
	}

	public boolean isWurmCollapsed() {
    	DateTime now = new DateTime(gregorianJuian);
    	
    	if (Days.daysBetween(lastChange.toDateMidnight(), now.toDateMidnight()) == Days.ONE) {
    		lastChange = now;
    		wurmCollapsed = false;
    	}
		
		return wurmCollapsed;
	}
	
	public boolean isSbCollapsed() {
    	DateTime now = new DateTime(gregorianJuian);
    	
    	if (Days.daysBetween(lastChange.toDateMidnight(), now.toDateMidnight()) == Days.ONE) {
    		lastChange = now;
    		sbCollapsed = false;
    	}
		
		return sbCollapsed;
	}
	
	public boolean isGolemCollapsed() {
    	DateTime now = new DateTime(gregorianJuian);
    	
    	if (Days.daysBetween(lastChange.toDateMidnight(), now.toDateMidnight()) == Days.ONE) {
    		lastChange = now;
    		golemCollapsed = false;
    	}
		
		return golemCollapsed;
	}
	
	public boolean isDredgeCollapsed() {
    	DateTime now = new DateTime(gregorianJuian);
    	
    	if (Days.daysBetween(lastChange.toDateMidnight(), now.toDateMidnight()) == Days.ONE) {
    		lastChange = now;
    		dredgeCollapsed = false;
    	}
		
		return dredgeCollapsed;
	}
	
	public boolean isHarathiCollapsed() {
    	DateTime now = new DateTime(gregorianJuian);
    	
    	if (Days.daysBetween(lastChange.toDateMidnight(), now.toDateMidnight()) == Days.ONE) {
    		lastChange = now;
    		harathiCollapsed = false;
    	}
		
		return harathiCollapsed;
	}
	
	public boolean isOgreCollapsed() {
    	DateTime now = new DateTime(gregorianJuian);
    	
    	if (Days.daysBetween(lastChange.toDateMidnight(), now.toDateMidnight()) == Days.ONE) {
    		lastChange = now;
    		ogreCollapsed = false;
    	}
		
		return ogreCollapsed;
	}

	public boolean isHydraCollapsed() {
    	DateTime now = new DateTime(gregorianJuian);
    	
    	if (Days.daysBetween(lastChange.toDateMidnight(), now.toDateMidnight()) == Days.ONE) {
    		lastChange = now;
    		hydraCollapsed = false;
    	}
		
		return hydraCollapsed;
	}

	public boolean isFireShamCollapsed() {
    	DateTime now = new DateTime(gregorianJuian);
    	
    	if (Days.daysBetween(lastChange.toDateMidnight(), now.toDateMidnight()) == Days.ONE) {
    		lastChange = now;
    		fireShamCollapsed = false;
    	}
		
		return fireShamCollapsed;
	}  
}  
