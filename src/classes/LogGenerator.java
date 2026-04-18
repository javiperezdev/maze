package classes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogGenerator {
	public static void generateLog(String eventName, String information) {
		StringBuilder sb = new StringBuilder();
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("./assets/syslog.txt", true))) {
			StringBuilder log = sb.append(getDateFormatted()).append(" - ").append(eventName).append(" - ").append(information);
			bw.write(log.toString());
		    bw.newLine();
		} catch (IOException e) {
		 }
	}
	
	public static String getDateFormatted() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDate = now.format(format);
        return formattedDate;
	}
}
