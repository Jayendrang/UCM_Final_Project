package com.library.search;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class DBConnectionProperties {

	private static String dbconnection;
	private static String username ;
	private static String password ;
	private static String database;
	
	public static String getDb_connection() {
		return dbconnection;
	}
	public static void setDb_connection(String db_connection) {
		DBConnectionProperties.dbconnection = db_connection;
	}
	public static String getUsername() {
		return username;
	}
	public static void setUsername(String username) {
		DBConnectionProperties.username = username;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		DBConnectionProperties.password = password;
	}
	public static String getDatabase() {
		return database;
	}
	public static void setDatabase(String database) {
		DBConnectionProperties.database = database;
	}
	
}
