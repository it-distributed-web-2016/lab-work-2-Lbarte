package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
/**
* DB connection and data edit
*
* @ author FoxArt
* @ version 3.0
* @ since 11.27.2016
*/
public class DB {

	private static final String JDBC_DRIVER = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://ec2-54-225-246-33.compute-1.amazonaws.com:5432/ddts25mpqtahek";
	private static final String USER = "sbadmpkdcfdlez";
	private static final String PASS = "SdS9l3PQ7VBUadtWrRagSlCG-B";
	private String sql = "";
	private ArrayList<ArrayList<String>> dataLists = new ArrayList<ArrayList<String>>();
	private Writer write = new Writer();
	
	/**
	* Used to connect with db when sql updates data
	*/
	private synchronized void connection() {
		write.logData("Connect to DB");
		Connection conntn = null;
		Statement sttmnt = null;
		try {
			conntn = DriverManager.getConnection(DB_URL, USER, PASS);
			sttmnt = conntn.createStatement();
			sttmnt.executeUpdate(sql);
			sttmnt.close();
			conntn.close();
			write.logData("Connection success with sql: " + sql);
		} catch (SQLException se) {
			se.printStackTrace();
			write.logData("Connection broke with SQLException: " + se);
		} catch (Exception e) {
			e.printStackTrace();
			write.logData("Connection broke with Exception: " + e);
		} finally {
			try {
				if (sttmnt != null) sttmnt.close();
				if (conntn != null) conntn.close();
			} catch (SQLException se) {
				se.printStackTrace();
				write.logData("Connection closing broke with SQLException: " + se);
			}
		}
	}
	
	/**
	*Add SQL code to insert data to measurement table
	*/
	synchronized protected void insertData(int id, String number, String measuredData, String latitude, String longitude) {
		sql = "INSERT INTO Measurement (id, number, measured_data, time, latitude, longitude) VALUES ('";
		sql += String.valueOf(id) + "', '" + number + "', '" + String.valueOf(measuredData)
			+ "', '" + latitude + "', '" + longitude + "');";
		write.logData("INSERT code formed with id " + Integer.toString(id));
		connection();
	}
	
	/**
	* Add SQL code to insert data to measurement table
	* whithout id
	*/
	synchronized protected void insertData(String number, String measuredData, String latitude, String longitude) {
		sql = "INSERT INTO Measurement (number, measured_data, time, latitude, longitude) VALUES ('";
		java.util.Date date = new java.util.Date();
		sql += number + "', '" + String.valueOf(measuredData) + "', '"
				+ String.valueOf(new Timestamp(date.getTime()))
			+ "', '" + latitude + "', '" + longitude + "');";
		write.logData("INSERT code formed with data: " + number + ", " + measuredData + ", " + latitude + ", " + longitude);
		connection();
	}
	
	/**
	* Add MySQL code to get all data from table and launch rest
	* @return data from DB
	*/
	protected ArrayList<ArrayList<String>> getDataFromDB() {
		//dataLists.clear();
		sql = "SELECT * FROM Measurement;";
		write.logData("SELECT ALL code formed");
		getDataFromDBConnection();
		return dataLists;
	}
	
	/**
	 * Add MySQL code to get all data from table where id is defined and launch rest
	 * @param id
	 * @return data from DB
	 */
	protected ArrayList<ArrayList<String>> getDataFromDB(String id) {
		dataLists.clear();
		sql = "SELECT * FROM Measurement WHERE id=" + id + ";";
		write.logData("SELECT WHERE id=" + id + " formed");
		getDataFromDBConnection();
		return dataLists;
	}
	
	/**
	* Connects to DB when here is no need to update it
	*/
	private void getDataFromDBConnection() {
		Connection conntn = null;
		Statement sttmnt = null;
		try {
			conntn = DriverManager.getConnection(DB_URL, USER, PASS);
			sttmnt = conntn.createStatement();
			sttmnt.executeQuery(sql);
			fillData(sttmnt.executeQuery(sql));
			sttmnt.close();
			conntn.close();
			write.logData("Connection success with sql: " + sql);
		} catch (SQLException se) {
			se.printStackTrace();
			write.logData("Connection broke with SQLException: " + se);
		} catch (Exception e) {
			e.printStackTrace();
			write.logData("Connection broke with Exception: " + e);
		} finally {
			try {
				if (sttmnt != null) sttmnt.close();
				if (conntn != null) conntn.close();
			} catch (SQLException se) {
				se.printStackTrace();
				write.logData("Connection closing broke with SQLException: " + se);
			}
		}
	}
	
	/**
	 * Fills dataList with data from DB
	 * @param resSet
	 * @throws SQLException
	 */
	protected void fillData(ResultSet resSet) throws SQLException {
		while (resSet.next()) {
			ArrayList<String> dataList = new ArrayList<String>();
			dataList.add(Integer.toString(resSet.getInt("id")));
			dataList.add(resSet.getString("number"));
			dataList.add(resSet.getString("measured_data"));
			dataList.add(resSet.getString("time"));
			dataList.add(resSet.getString("latitude"));
			dataList.add(resSet.getString("longitude"));
			dataLists.add(dataList);
		}
		resSet.close();
	}
	
	/**
	 * Delete all data from table measurement
	 */
	protected void deleteAllData() {
		sql = "Delete from measurement;";
		write.logData("DELETE ALL code formed");
		connection();
	}
}