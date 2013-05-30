package ca.bsolomon.gw2events.worldevent.util;

public enum MawEvent {

	PROTECT("6F516B2C-BD87-41A9-9197-A209538BB9DF", "Grawl Restless"),
	ESCORT("D5F31E0B-E0E3-42E3-87EC-337B3037F437", "Escort Scholar"),
	TOTEM("6565EFD4-6E37-4C26-A3EA-F47B368C866D", "Destroy Totem"),
	PORTALS("374FC8CB-7AB7-4381-AC71-14BFB30D3019", "Portals"),
	GUARDS("90B241F5-9E59-46E8-B608-2507F8810E00", "Guards"),
	SHAMANS("DB83ABB7-E5FE-4ACB-8916-9876B87D300D", "Shamans"),
	CHIEF("F7D9D427-5E54-4F12-977A-9809B23FBA99", "Active");
	
	
	private String uid;
	private String prettyName;
	
	MawEvent(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}
	
	public String uid() {return uid;}
	public String toString() {return prettyName;}
}
