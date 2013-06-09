package ca.bsolomon.gw2events.worldevent;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ca.bsolomon.gw2events.worldevent.enums.ServerID;
import ca.bsolomon.gw2events.worldevent.util.Server;

@FacesConverter(value = "serverConverter")
public class ServerIDConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
		 ServerID servId = ServerID.getByName(s);
		 
		 return new Server(servId.getUid(), servId.getName());
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object o) {
		if (o == null || o.equals("")) {
            return "";
        } else {
        	return ((Server)o).getServerName();
        }
	}

}
