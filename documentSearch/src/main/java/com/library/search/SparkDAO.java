package com.library.search;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hive.jdbc.HiveConnection;
import org.apache.hive.jdbc.HiveQueryResultSet;
import org.apache.hive.jdbc.HiveStatement;

public class SparkDAO {

	private static String hive_connection = "jdbc:hive2://localhost:10000/";
	private static String hive_username = "hive_user";
	private static String hive_password = "hive_password";
	private static String driverName = "org.apache.hive.jdbc.HiveDriver";
	private static String database = "library_dataset";

	public List<SearchResultPojo> getData(List<String> input) throws SQLException {
		// TODO Auto-generated method stub
		List<SearchResultPojo> rData = new ArrayList<SearchResultPojo>();

		try {
			System.err.println("Hive Query::Started");
			Class.forName(driverName);

			HiveConnection hcon = (HiveConnection) DriverManager
					.getConnection(hive_connection+database, hive_username, hive_password);
			String tableName = "testHiveDriverTable";

			//String sql = "select bookid,words,tf_idf from prod_library_data where words='";
			
			for (String keyword : input) {
				System.err.println("Search Keyword::: "+keyword);
				HiveStatement hstmt = (HiveStatement) hcon.createStatement();
				String sql = "select bookid,words,tf_idf from prod_library_data where words='"+keyword+"'";		
				HiveQueryResultSet rSet = (HiveQueryResultSet) hstmt.executeQuery(sql);
				System.out.println("Running::Hive " + sql);

				while (rSet.next()) {
					SearchResultPojo uData = new SearchResultPojo();
					uData.setBookid(rSet.getString("bookid"));
					uData.setWords(rSet.getString("words"));
					uData.setTf_idf(rSet.getString("tf_idf"));
					rData.add(uData);
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("Running::Hive Completed");

		return rData;
	}
	
	
}
