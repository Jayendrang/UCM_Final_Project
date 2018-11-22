package com.library.search;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.hive.jdbc.HiveConnection;
import org.apache.hive.jdbc.HiveQueryResultSet;
import org.apache.hive.jdbc.HiveStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class SparkDAO implements SearchCorpus {

	private static String hive_connection = "jdbc:hive2://localhost:10000/";
	private static String hive_username = "hive_user";
	private static String hive_password = "hive_password";
	private static String driverName = "org.apache.hive.jdbc.HiveDriver";
	private static String database = "library_dataset";
	
	private DBConnectionProperties connectionProperties;
	
	@Autowired
	public SparkDAO(DBConnectionProperties connProps) {
		this.connectionProperties=connProps;
		System.err.println("DBConn1--> "+connProps.getDb_connection());
		System.err.println("DBConn2-->"+connectionProperties.getUsername());
	}
	
	public HashMap<String, List<SearchResultPojo>> searchInCorpus(List<String> input) throws SQLException {
		// TODO Auto-generated method stub
		HashMap<String, List<SearchResultPojo>> response = new HashMap<>();

		try {
			System.err.println("Hive Query::Started");
			Class.forName(driverName);

			HiveConnection hcon = (HiveConnection) DriverManager.getConnection(hive_connection + database,
					hive_username, hive_password);

			for (String keyword : input) {
				// HashMap<String, SearchResultPojo> collect = new HashMap<>();
				List<SearchResultPojo> collect = new ArrayList<>();
				System.err.println("Search Keyword::: " + keyword);
				HiveStatement hstmt = (HiveStatement) hcon.createStatement();
				String sql = "select bookid,tf_idf from prod_library_data where words='" + keyword + "'";
				HiveQueryResultSet rSet = (HiveQueryResultSet) hstmt.executeQuery(sql);
				System.out.println("Running::Hive " + sql);

				while (rSet.next()) {
					SearchResultPojo uData = new SearchResultPojo();
					uData.setBookid(rSet.getString("bookid"));
					uData.setTf_idf(rSet.getString("tf_idf"));
					collect.add(uData);
				}
				for (SearchResultPojo data : collect) {

				}
				response.put(keyword, collect);

			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("Running::Hive Completed");

		return response;
	}

}
