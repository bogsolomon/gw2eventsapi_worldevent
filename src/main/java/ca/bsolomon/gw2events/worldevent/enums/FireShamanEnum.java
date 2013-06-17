package ca.bsolomon.gw2events.worldevent.enums;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import ca.bsolomon.gw2events.worldevent.util.EventStatus;
import ca.bsolomon.gw2events.worldevent.util.EventStringFormatter;
import ca.bsolomon.gw2events.worldevent.util.EventData;
import ca.bsolomon.gw2events.worldevent.util.PlaySoundStatus;

public enum FireShamanEnum {

	SHAMAN("295E8D3B-8823-4960-A627-23E07575ED96", "Active");

	private String uid;
	private String prettyName;

	FireShamanEnum(String uid, String prettyName) {
		this.uid = uid;
		this.prettyName = prettyName;
	}

	public String uid() {
		return uid;
	}

	public String toString() {
		return prettyName;
	}

	private static List<EventStatus> eventStatus = new ArrayList<>();

	public static List<EventStatus> getStatus() {
		return eventStatus;
	}

	public static void formatShamanString(EventData lowLevelEventData) {
		List<EventStatus> status = new ArrayList<EventStatus>();

		for (ServerID servId : ServerID.values()) {
			formatShamanString(lowLevelEventData, status, servId);
		}

		eventStatus = status;
	}

	public static void formatShamanString(EventData lowLevelEventData,
			List<EventStatus> statusList, ServerID servId) {
		String outStatus = "";
		String color = "";
		DateTime time = null;
		int fontWeight = 400;

		String eventId = servId.getUid() + "-" + SHAMAN.uid();

		String status = lowLevelEventData.getEventStatus(eventId);

		boolean playSound = false;

		if ((status != null) && (status.equals("Active"))) {
			time = lowLevelEventData.getEventTime(eventId);

			outStatus = "Active";
			color = EventStateColor.ACTIVE.color();
			fontWeight = 900;

			playSound = true;
		} else {
			time = lowLevelEventData.getEventTime(eventId);

			outStatus = "Not up";
			color = EventStateColor.FAIL.color();
		}

		String soundKey = servId.getUid() + "-firesham";
		boolean playSoundList = playSound;
		if (PlaySoundStatus.playSoundStatus.containsKey(soundKey)
				&& PlaySoundStatus.playSoundStatus.get(soundKey)) {
			playSound = false;
		}

		PlaySoundStatus.playSoundStatus.put(soundKey, playSoundList);

		EventStringFormatter.generateEventString(statusList, servId, outStatus,
				color, fontWeight, time, "Fire Shaman",
				Waypoint.FIRESHAM.toString(), playSound);
	}
}
