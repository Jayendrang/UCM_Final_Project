package library.dao;

import java.sql.DriverManager;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mysql.jdbc.Connection;

@Component

public class DaoProperties {

	
	
	@Value("${spring.datasource.url}")
	private String dbUri;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	public String getDbUri() {
		return dbUri;
	}

	public void setDbUri(String dbUri) {
		this.dbUri = dbUri;
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

	public void setPassword(String paStringssword) {
		this.password = paStringssword;
	}


	public Connection getConnection() throws Exception {
		System.err.println(this.getDbUri());
		 return (Connection) DriverManager.getConnection(this.getDbUri(),this.getUsername(),this.getPassword());
		 
		
	}
}
