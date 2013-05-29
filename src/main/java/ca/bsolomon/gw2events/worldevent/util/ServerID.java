package ca.bsolomon.gw2events.worldevent.util;

public enum ServerID {

	FC(1020, "Ferguson"),
	SoR(1013, "Home (SoR)"),
	ET(1024, "Eredon");
	
	private int uid;
	private String prettyName;
	
	ServerID(int uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public int uid() {return uid;}
	public String toString() {return prettyName;}
}
