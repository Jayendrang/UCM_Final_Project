package com.library.hivetableloader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class HiveTableLoader {
	public static final String HDFS_ROOT_URL="hdfs://localhost:9000";
	public static void main(String[] args) {
		Configuration hdfsConf = new Configuration();
		hdfsConf.set("fs.defaultFS", "hdfs://localhost:9000");
		hdfsConf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
		hdfsConf.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());
		System.setProperty("HADOOP_USER_NAME", "/");
		System.setProperty("hadoop.home.dir", "/");
		
		try {
			FileSystem fs = FileSystem.get(URI.create("hdfs://localhost:9000"), hdfsConf);
			Path path = new Path("/user/library/output/LatestOutput-r-00000.txt");
			System.out.println(fs.exists(path));
			System.out.println("File exists for running hive load job.......");
			FSDataInputStream inputStream = fs.open(path);
			String text  =null;
			while((text=inputStream.readLine())!=null) {
				System.out.println(text);
			}
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
