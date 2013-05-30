package ca.bsolomon.gw2events.worldevent.util;

import java.security.InvalidParameterException;

public enum EventStateColor {

	ACTIVE("009933"),
	SUCCESS("660000"),
	WARMUP("CCCC33"),
	FAIL("660000"),
	PREPARATION("CCCC33");
	
	private String color;
	
	private EventStateColor(String color) {
		this.color = color;
	}
	
	public String color() {return color;}
	
	public static EventStateColor getEventStateColor(String val) {
		if (val.equals("Active")) {
			return ACTIVE;
		} else if (val.equals("Success")) {
			return SUCCESS;
		} else if (val.equals("Warmup")) {
			return WARMUP;
		} else if (val.equals("Fail")) {
			return FAIL;
		} else if (val.equals("Preparation")) {
			return PREPARATION;
		}
		
		throw new InvalidParameterException();
	}
}
