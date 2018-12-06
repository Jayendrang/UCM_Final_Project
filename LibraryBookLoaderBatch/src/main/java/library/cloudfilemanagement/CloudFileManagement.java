package library.cloudfilemanagement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import library.dao.FileInfoProcessor;
import library.dao.FileLoggerPojo;
import library.hdfsmanagement.HdfsProperties;

@Service
public class CloudFileManagement {

	@Autowired
	CloudFileProperties awsFileManagement;

	@Autowired
	FileInfoProcessor dbFileManagement;

	@Autowired
	HdfsProperties hdfsProperties;
	
	public List<FileLoggerPojo> downloadFilesToLocal() throws Exception {

		List<FileLoggerPojo> filesInfo = dbFileManagement.getNewFileList();
		List<FileLoggerPojo> processedFile = new ArrayList<>();
		AmazonS3 s3Client = awsFileManagement.getConnection();
		for (FileLoggerPojo file : filesInfo) {
			System.out.println(file.getRepo_path());
			boolean isExists=s3Client.doesObjectExist(awsFileManagement.getBucketname(), file.getRepo_path().concat(".pdf"));
			if (isExists) {
				S3Object s3fileObject = s3Client.getObject(awsFileManagement.getBucketname(), file.getRepo_path().concat(".pdf"));
				S3ObjectInputStream s3InputStream = s3fileObject.getObjectContent();
				String filepath =hdfsProperties.getLocalFileSourcePath().concat("/"+file.getBook_id().concat(".pdf"));
				System.err.println("Cloud files downloaded to "+filepath);
				FileUtils.copyInputStreamToFile(s3InputStream,
						new File(filepath));
				file.setDownload_path(filepath);
				processedFile.add(file);
			}

		}
		return processedFile;
	}

	
}
