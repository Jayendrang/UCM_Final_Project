package library.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import library.cloudfilemanagement.CloudFileManagement;
import library.cloudfilemanagement.CloudFileProperties;
import library.dao.DaoProperties;
import library.dao.FileInfoProcessor;
import library.dao.FileLoggerPojo;
import library.hdfsmanagement.HdfsFileManagement;
import library.hdfsmanagement.PdfToTextConverter;

import java.sql.Connection;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;


@SpringBootApplication(scanBasePackages= {"library.cloudfilemanagement","library.dao","library.hdfsmanagement"})
public class LibraryBookLoaderBatchApplication implements CommandLineRunner {

	
	@Autowired
	CloudFileManagement cloudFileManagement;
	
	@Autowired
	PdfToTextConverter textConverter;
	
	@Autowired
	FileInfoProcessor fileProcessor;
	
	@Autowired
	HdfsFileManagement hdfsFileManagement;
	
	
	public static void main(String[] args) {
		SpringApplication.run(LibraryBookLoaderBatchApplication.class, args);
		
	}
	
	public void run(String...strings) throws Exception{
	List<FileLoggerPojo> cloudFilesList=cloudFileManagement.downloadFilesToLocal();
	List<FileLoggerPojo> processedFileList = textConverter.convertToText(cloudFilesList);
	List<FileLoggerPojo> tableUpdateList =  hdfsFileManagement.uploadFileToHadoop(processedFileList);
	fileProcessor.setBookStatus(tableUpdateList);
	}
}
