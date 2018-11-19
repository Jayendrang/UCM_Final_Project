package com.library.hive;

import java.io.IOException;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HiveHdfsBatch {
	private String hdfsURI = "hdfs://localhost:9000";
	private String libraryfolder = "/user/library/output/";
	private String defaultfileName="LatestOutput-r-00000";
	
	
	public String readFilesFromHDFS() throws ClassNotFoundException {
		String filepath = null;
		try {
			// Environment settings
			Configuration hdfsConf = new Configuration();
			hdfsConf.set("fs.defaultFS", hdfsURI);
			//hdfsConf.set("fs.hdfs.impl",  org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
			hdfsConf.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());

			// File system settings
			System.setProperty("HADOOP_USER_NAME", "/");
			System.setProperty("hadoop.home.dir", "/");

			// processing folder
			String processingfolder =  getProcessingFolder();
			
			FileSystem hdfilesystem = FileSystem.get(URI.create(hdfsURI), hdfsConf);
			
			Path tfFile = new Path(libraryfolder.concat(getProcessingFolder()).concat(defaultfileName));
			
			System.out.println(tfFile.toString());
			if (hdfilesystem.exists(tfFile)) {
				System.out.println("File exists for running hive load job.......");
				runHiveLoadQuery(tfFile.toString());
					
			}else {
				System.out.println("File not found");
			}

			
		} catch(Exception e) {
			e.getMessage();
		}
		return filepath;
	}

	

	public String getProcessingFolder() {
		SimpleDateFormat folderName = new SimpleDateFormat("MM_dd_yyyy");
		Date creationdate = new Date();

		String batch = null;
		if (creationdate.getHours() < 12) {
			batch = "Batch1";
		} else if (creationdate.getHours() > 12 && creationdate.getHours() <= 18) {
			batch = "Batch2";
		} else {
			batch = "Batch3";
		}

		return folderName.format(creationdate).toString().concat("/").concat(batch).concat("/");
	}

	public void runHiveLoadQuery(String filepath) {
		String hiveDriver = "org.apache.hive.jdbc.HiveDriver";
		String hiveDatabaseName = "library_dataset";
		String hiveUsername="";
		String hivePassword="";
		String hiveserver = "jdbc:hive2://localhost:10000/"+hiveDatabaseName;	
		
		try {
			Class.forName(hiveDriver);
			Connection connection = DriverManager.getConnection(hiveserver, hiveUsername,hivePassword);
			Statement stmt = connection.createStatement();
			System.out.println(filepath);
			System.out.println("Loading data in hive table");
			stmt.execute("LOAD DATA INPATH '"+filepath+"' OVERWRITE INTO TABLE tf_table_temp");
			stmt.execute("INSERT INTO tf_table select bookid,words,tf_count from tf_table_temp");
			System.out.println("data file loaded succesfully");	
			
			stmt.close();
			connection.close();
		}catch(SQLException sqlexception) {
			sqlexception.printStackTrace();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		HiveHdfsBatch bb = new HiveHdfsBatch();
		System.out.println(bb.getProcessingFolder());
		bb.readFilesFromHDFS();
	}

}
