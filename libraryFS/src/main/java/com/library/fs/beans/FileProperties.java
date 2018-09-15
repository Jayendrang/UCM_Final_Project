package com.library.fs.beans;

import org.springframework.web.multipart.MultipartFile;

public class FileProperties {

	private String fileextension = null;
	private MultipartFile[] files = null;

	public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

}
