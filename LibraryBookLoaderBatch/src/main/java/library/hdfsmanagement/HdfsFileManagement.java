package library.hdfsmanagement;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library.dao.FileLoggerPojo;

@Service
public class HdfsFileManagement {

	@Autowired
	HdfsProperties hdfsProperties;

	public HdfsFileManagement() {
		System.out.println("Hdfs File Management Started");
	}

	public List<FileLoggerPojo> uploadFileToHadoop(List<FileLoggerPojo> destination) throws IOException, Exception {
		Configuration hadoopConfiguration = new Configuration();
		List<FileLoggerPojo> response = new ArrayList<>();
		hadoopConfiguration.set("fs.default.name", hdfsProperties.getResourceManagerAddress());
		hadoopConfiguration.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
		hadoopConfiguration.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());
		hadoopConfiguration.set("hadoop.home.dir","/");
		FileSystem hadoopfs = FileSystem.get(hadoopConfiguration);
		
		for (FileLoggerPojo file : destination) {
			System.err.println(file.getCoverted_path()+"---"+hdfsProperties.getHdfsPath());
			hadoopfs.copyFromLocalFile(new Path(file.getCoverted_path()), new Path(hdfsProperties.getResourceManagerAddress().concat(hdfsProperties.getHdfsPath())));
			file.setStatus("COMPLETED");
			response.add(file);
		}
		return response;
	}

}
