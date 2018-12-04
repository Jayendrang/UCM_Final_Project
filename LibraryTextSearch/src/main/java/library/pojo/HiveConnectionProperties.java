package library.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class HiveConnectionProperties {

	@Value("${hive.dbconnection}")
	private String dbconnection;
	
	@Value("${hive.username}")
	private String username;
	
	@Value("${hive.password}")
	private String password;
	
	@Value("${hive.database}")
	private String database;

	public HiveConnectionProperties() {
		System.err.println("Constructor-->"+this.dbconnection);
	}

	public String getDbconnection() {
		return dbconnection;
	}

	public void setDbconnection(String dbconnection) {
		this.dbconnection = dbconnection;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

}
