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
      
    public DashboardBean() {  
        model = new DefaultDashboardModel();  
        DashboardColumn column1 = new DefaultDashboardColumn();  
        DashboardColumn column2 = new DefaultDashboardColumn();  
        DashboardColumn column3 = new DefaultDashboardColumn();  
          
        column1.addWidget("teqPanel");
        column1.addWidget("shatPanel");
        column1.addWidget("jorPanel");
        
        column2.addWidget("mawPanel");
  
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
}  
