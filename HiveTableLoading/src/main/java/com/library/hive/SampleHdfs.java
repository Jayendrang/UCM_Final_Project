package com.library.hive;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class SampleHdfs {

	//public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		Configuration hdfsConf = new Configuration();
//		hdfsConf.set("fs.defaultFS", "hdfs://localhost:9000");
//		hdfsConf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
//		hdfsConf.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());
//		System.setProperty("HADOOP_USER_NAME", "/");
//		System.setProperty("hadoop.home.dir", "/");
//		
//		try {
//			FileSystem fs = FileSystem.get(URI.create("hdfs://localhost:9000"), hdfsConf);
//			Path path = new Path("/user/library/output/LatestOutput-r-00000.txt");
//			System.out.println(fs.exists(path));
//			System.out.println("File exists for running hive load job.......");
//			FSDataInputStream inputStream = fs.open(path);
//			String text  =null;
//			while((text=inputStream.readLine())!=null) {
//				System.out.println(text);
//			}
//			inputStream.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	public static final String HDFS_ROOT_URL="hdfs://localhost:9000";
	private Configuration conf;	
		public SampleHdfs() {
			// TODO Auto-generated constructor stub
			conf = new Configuration();
			
		}
		

		public static void main(String[] args) throws Exception {
			SampleHdfs demo = new SampleHdfs();
			
			// Reads a file from the user's home directory.
			// Replace jj with the name of your folder
			// Assumes that input.txt is already in HDFS folder
			String uri = HDFS_ROOT_URL;
			
			demo.printHDFSFileContents(uri);
		}
		
		
		// Example - Print hdfs file contents to console using Java
		public void printHDFSFileContents(String uri) throws Exception {
			FileSystem fs = FileSystem.get(URI.create(uri), conf);
			InputStream in = null;
			Path p = new Path("/user/library/output/LatestOutput-r-00000.txt");
			
			try {
				System.out.println(fs.exists(p));
				//in = fs.open(new Path(uri));
				//IOUtils.copyBytes(in, System.out, 4096, false);
			} finally {
				IOUtils.closeStream(in);
			}
		}
	

}
