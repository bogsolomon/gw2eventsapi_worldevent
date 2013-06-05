package ca.bsolomon.gw2events.worldevent.enums;

import java.security.InvalidParameterException;

public enum EventStateColor {

	ACTIVE("green"),
	SUCCESS("red"),
	WARMUP("yellow"),
	FAIL("red"),
	PREPARATION("yellow");
	
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
