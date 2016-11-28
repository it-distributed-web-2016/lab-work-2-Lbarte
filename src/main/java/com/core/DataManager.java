package core;

import java.util.ArrayList;

/**
 * Manage all data
 * @author Foxart
 * @version 2.1
 * @since 11.14.2016
 */
public class DataManager {

	/**
	 * Delete all data
	 */
	public void deleteData() {
		MeasuredData dataInst = new MeasuredData();
		dataInst.cleanData();
	}
	
	/**
	 * Create data and inserts it to db and layers
	 */
	public void createData() {
		MeasuredData dataInst = new MeasuredData();
		dataInst.createData();
	}
	
	/**
	 * Create data and inserts it to db and layers
	 * @param number of threads/samples
	 */
	public void createData(int number) {
		MeasuredData dataInst = new MeasuredData();
		dataInst.createData(number);
	}
	
	/**
	 * Gets all data from db
	 * @return data
	 */
	public ArrayList<ArrayList<String>> getData() {
		MeasuredData dataInst = new MeasuredData();
		return dataInst.getAllData();
	}
	
	/**
	 * Gets all data from db with described id
	 * @return data
	 */
	public ArrayList<ArrayList<String>> getData(String id) {
		MeasuredData dataInst = new MeasuredData();
		return dataInst.getAllData(id);
	}
}
