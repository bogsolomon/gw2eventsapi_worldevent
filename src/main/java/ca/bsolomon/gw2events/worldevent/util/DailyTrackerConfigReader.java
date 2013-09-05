package ca.bsolomon.gw2events.worldevent.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DailyTrackerConfigReader {

	private static final String folderName = "configs";
	private static final String fileName = "daily_tracking.txt";
	
	private static boolean initRead = false;
	private static List<String> dailyTracking = new ArrayList<>();
	
	public static void readConfigFiles() {
		if (!initRead) {
			Path file = Paths.get(folderName+File.separator+fileName);
			
			parseConfigFile(file);
						
			initRead = true;
		}
	}

	private static void parseConfigFile(Path file) {
		try {
			List<String> fileLines = Files.readAllLines(file, Charset.defaultCharset());
			
			for (String eventStateStr:fileLines) {
				dailyTracking.add(eventStateStr);
			}
		} catch (IOException e) {
			System.out.println("File path: "+file+" read error: "+e.getLocalizedMessage());
		}
	}

	public static List<String> getDailyTracking() {
		return dailyTracking;
	}
}
