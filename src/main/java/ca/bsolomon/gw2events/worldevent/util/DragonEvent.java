package ca.bsolomon.gw2events.worldevent.util;

public enum DragonEvent {

	JORMAG_CRYSTAL1("0CA3A7E3-5F66-4651-B0CB-C45D3F0CAD95", "Jormag Crystal"),
	JORMAG_CRYSTAL2("96D736C4-D2C6-4392-982F-AC6B8EF3B1C8", "Jormag Crystal"),
	JORMAG_CRYSTAL3("C957AD99-25E1-4DB0-9938-F54D9F23587B", "Jormag Crystal"),
	JORMAG_CRYSTAL4("429D6F3E-079C-4DE0-8F9D-8F75A222DB36", "Jormag Crystal"),
	JORMAG_CRYSTALF("BFD87D5B-6419-4637-AFC5-35357932AD2C", "Jormag Final Crystal"),
	JORMAG_UP("0464CB9E-1848-4AAA-BA31-4779A959DD71", "Jormag Up"),
	TEQUATL("568A30CF-8512-462F-9D67-647D69BEFAED", "Tequatl Up"),
	SHATTERER_UP("03BF176A-D59F-49CA-A311-39FC6F533F2F", "Shaterrer Up"),
	SHATTERER_SIEGE("580A44EE-BAED-429A-B8BE-907A18E36189", "Shaterrer Siege Collect"),
	SHATTERER_ESCORT("8E064416-64B5-4749-B9E2-31971AB41783", "Shaterrer Escort");
	
	private String uid;
	private String prettyName;
	
	DragonEvent(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public String uid() {return uid;}
	public String toString() {return prettyName;}
	
	public DragonEvent getEvent(String val) {
		if (val.equals("0CA3A7E3-5F66-4651-B0CB-C45D3F0CAD95")) {
			return JORMAG_CRYSTAL1;
		} else if (val.equals("96D736C4-D2C6-4392-982F-AC6B8EF3B1C8")) {
			return JORMAG_CRYSTAL2;
		} else if (val.equals("C957AD99-25E1-4DB0-9938-F54D9F23587B")) {
			return JORMAG_CRYSTAL3;
		} else if (val.equals("429D6F3E-079C-4DE0-8F9D-8F75A222DB36")) {
			return JORMAG_CRYSTAL4;
		} else if (val.equals("BFD87D5B-6419-4637-AFC5-35357932AD2C")) {
			return JORMAG_CRYSTALF;
		} else if (val.equals("0464CB9E-1848-4AAA-BA31-4779A959DD71")) {
			return JORMAG_UP;
		} else if (val.equals("568A30CF-8512-462F-9D67-647D69BEFAED")) {
			return TEQUATL;
		} else if (val.equals("03BF176A-D59F-49CA-A311-39FC6F533F2F")) {
			return SHATTERER_UP;
		} else if (val.equals("580A44EE-BAED-429A-B8BE-907A18E36189")) {
			return SHATTERER_SIEGE;
		} else if (val.equals("8E064416-64B5-4749-B9E2-31971AB41783")) {
			return SHATTERER_ESCORT;
		}
		
		 throw new IllegalArgumentException("Incorrect Event UID");
	}
}
