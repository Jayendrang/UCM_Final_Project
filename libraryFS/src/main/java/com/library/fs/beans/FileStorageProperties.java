package com.library.fs.beans;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="file")
public class FileStorageProperties {

	private String fileUploadDir;
	private String fileDownloadDir;
	
	public String getUploadDir() {
		return fileUploadDir;
	}
	public String getDownloadDir() {
		return fileDownloadDir;
	}
	
}
