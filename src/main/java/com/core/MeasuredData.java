package core;

import java.util.ArrayList;

/**
 * Lists for all data crossing between DB and user
 * @author Foxart
 * @since 11.14.2016
 * @version 1.0
 */
public class MeasuredData {

	private ArrayList<ArrayList<String>> dataOutput;
	
	/**
	 * Creates data from 20 samples
	 */
	protected void createData() {
		createData(20);
	}
	
	/**
	 * Creates data with defined number of samples
	 * @param numberOfSamples
	 */
	protected void createData(int numberOfSamples) {
		for (int beforeIndex = 1; beforeIndex < numberOfSamples+1; beforeIndex++) {
			@SuppressWarnings("unused")
			ThreadGenerator threadGeneratorInstance = new ThreadGenerator(beforeIndex);
		}
	}
	
	/**
	 * Delete all data from maps and db
	 */
	protected void cleanData() {
		DB dbInst = new DB();
		dbInst.deleteAllData();
	}
	
	/**
	 * Get all data(temperature/humidity, time, latitude, longitude) with defined id
	 * @param id
	 * @return data
	 */
	protected ArrayList<ArrayList<String>> getAllData(String id) {
		fillOutputData(id);
		return dataOutput;
	}
	
	/**
	 * Get all data(temperature/humidity, time, latitude, longitude) with defined id
	 * @param id
	 * @return data
	 */
	protected ArrayList<ArrayList<String>> getAllData() {
		fillOutputData();
		return dataOutput;
	}
	
	/**
	 * Fill data for output with data from DB with described id
	 * @param id
	 */
	protected void fillOutputData(String id) {
		DB dbInst = new DB();
		dataOutput = dbInst.getDataFromDB(id);
	}
	
	/**
	 * Fill data for output with data from DB
	 */
	protected void fillOutputData() {
		DB dbInst = new DB();
		dataOutput = dbInst.getDataFromDB();
	}
}