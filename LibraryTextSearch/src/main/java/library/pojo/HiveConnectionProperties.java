package library.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix="hive")
@EnableConfigurationProperties
public class HiveConnectionProperties {

	private String dbconnection;
	private String username;
	private String password;
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
