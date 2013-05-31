package ca.bsolomon.gw2events.worldevent;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.primefaces.model.Visibility;

@ManagedBean(name="dashboardBean")
@SessionScoped
public class DashboardBean implements Serializable {  

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
      
    public DashboardBean() {  
        model = new DefaultDashboardModel();  
        DashboardColumn column1 = new DefaultDashboardColumn();  
        DashboardColumn column2 = new DefaultDashboardColumn();  
        DashboardColumn column3 = new DefaultDashboardColumn();  
          
        column1.addWidget("teqPanel");
        column1.addWidget("shatPanel");
        column1.addWidget("jorPanel");
        
        column2.addWidget("mawPanel");
        column2.addWidget("fireElePanel");
        column2.addWidget("wurmPanel");
        column2.addWidget("sbPanel");
        
        column3.addWidget("golemPanel");
        column3.addWidget("dredgePanel");
        column3.addWidget("harathiPanel");
        column3.addWidget("ogrePanel");
  
        model.addColumn(column1);  
        model.addColumn(column2);  
        model.addColumn(column3);  
    }  
      
    public DashboardModel getModel() {  
        return model;  
    }
    
    public void handleTeqToggle(ToggleEvent event) {  
    	teqCollapsed = (event.getVisibility() == Visibility.HIDDEN);
    }

    public void handleShatToggle(ToggleEvent event) {  
    	shatCollapsed = (event.getVisibility() == Visibility.HIDDEN);
    }

    public void handleJormagToggle(ToggleEvent event) {  
    	jormagCollapsed = (event.getVisibility() == Visibility.HIDDEN);
    }

    public void handleMawToggle(ToggleEvent event) {  
    	mawCollapsed = (event.getVisibility() == Visibility.HIDDEN);
    }
    
    public void handleFireEleToggle(ToggleEvent event) {  
    	fireEleCollapsed = (event.getVisibility() == Visibility.HIDDEN);
    }
    
    public void handleWurmToggle(ToggleEvent event) {  
    	wurmCollapsed = (event.getVisibility() == Visibility.HIDDEN);
    }
    
    public void handleSBToggle(ToggleEvent event) {  
    	sbCollapsed = (event.getVisibility() == Visibility.HIDDEN);
    }
    
    public void handleGolemToggle(ToggleEvent event) {  
    	golemCollapsed = (event.getVisibility() == Visibility.HIDDEN);
    }
    
    public void handleDredgeToggle(ToggleEvent event) {  
    	dredgeCollapsed = (event.getVisibility() == Visibility.HIDDEN);
    }
    
    public void handleHarathiToggle(ToggleEvent event) {  
    	harathiCollapsed = (event.getVisibility() == Visibility.HIDDEN);
    }
    
    public void handleOgreToggle(ToggleEvent event) {  
    	ogreCollapsed = (event.getVisibility() == Visibility.HIDDEN);
    }
    
    public boolean isTeqCollapsed() {
		return teqCollapsed;
	}

	public boolean isShatCollapsed() {
		return shatCollapsed;
	}

	public boolean isJormagCollapsed() {
		return jormagCollapsed;
	}

	public boolean isMawCollapsed() {
		return mawCollapsed;
	}

	public boolean isFireEleCollapsed() {
		return fireEleCollapsed;
	}

	public boolean isWurmCollapsed() {
		return wurmCollapsed;
	}
	
	public boolean isSbCollapsed() {
		return sbCollapsed;
	}
	
	public boolean isGolemCollapsed() {
		return golemCollapsed;
	}
	
	public boolean isDredgeCollapsed() {
		return dredgeCollapsed;
	}
	
	public boolean isHarathiCollapsed() {
		return harathiCollapsed;
	}
	
	public boolean isOgreCollapsed() {
		return ogreCollapsed;
	}  
}  
