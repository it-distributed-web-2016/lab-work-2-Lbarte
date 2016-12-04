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

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/Measurement";
	private static final String USER = "root";
	private static final String PASS = "1111";
	private String sql = "";
	private ArrayList<ArrayList<String>> dataLists = new ArrayList<ArrayList<String>>();
	
	/**
	* Used to connect with db when sql updates data
	*/
	private synchronized void connection() {
		Connection conntn = null;
		Statement sttmnt = null;
		try {
			conntn = DriverManager.getConnection(DB_URL, USER, PASS);
			sttmnt = conntn.createStatement();
			sttmnt.executeUpdate(sql);
			sttmnt.close();
			conntn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (sttmnt != null) sttmnt.close();
				if (conntn != null) conntn.close();
			} catch (SQLException se) {
				se.printStackTrace();
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
		connection();
	}
	
	/**
	* Add MySQL code to get all data from table and launch rest
	* @return data from DB
	*/
	protected ArrayList<ArrayList<String>> getDataFromDB() {
		sql = "SELECT * FROM Measurement;";
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
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (sttmnt != null) sttmnt.close();
				if (conntn != null) conntn.close();
			} catch (SQLException se) {
				se.printStackTrace();
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
		connection();
	}
}