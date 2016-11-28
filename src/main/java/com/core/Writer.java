package core;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Random;

/**
* Writes data and launch data inserts
*
* @author FoxArt
* @version 2.2
* @since 11.15.2016
*/
public class Writer {

	/**
	*Define data and launch insert to db
	*/
	synchronized protected void insertDataToDB() {
		int temperature = 0, humidity = 0;
		Random randInstance = new Random();
		boolean sensorNumberBool = randInstance.nextBoolean();
		String number = sensorNumberBool?"T20I345":"B486EE";
		Sensor sensorInstance = new Sensor();
		boolean coldSeasonBool = randInstance.nextBoolean();
		if(sensorNumberBool) {
			number = "T20I345";
			temperature = sensorInstance.getTemperature(coldSeasonBool);
		} else {
			number = "B486EE";
			humidity = sensorInstance.getHumidity();
		}
		String latitude = sensorInstance.getLatitude();
		String longitude = sensorInstance.getLongitude();
		DB db = new DB();
		db.insertData(number, (sensorNumberBool?String.valueOf(temperature):(String.valueOf(humidity + "%"))), latitude, longitude);
	}
	
	/**
	 * Writes line to the log file
	 * @param line
	 */
	synchronized protected void logData(String line) {
		try {
			FileWriter fileWr = new FileWriter("log.txt", true);
			BufferedWriter buffWr = new BufferedWriter(fileWr);
			PrintWriter out = new PrintWriter(buffWr);
			java.util.Date date = new java.util.Date();
			out.print(new Timestamp(date.getTime()) + ":   ");
			System.out.print(new Timestamp(date.getTime()) + ":   ");
			System.out.println(line);
			out.println(line);
		} catch (IOException e) {
			System.out.println("IOException: write to log file: " + line);
		}
	}
}