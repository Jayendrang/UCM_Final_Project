package com.library.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface LibraryFileRepoService {

	public boolean setupInstitutionRepository(String institutionName);
	public boolean uploadBooks(MultipartFile file, String fileLocation, String fileName );
	public ResponseEntity<Resource> downloadFile(String serverPath, String fileName,HttpServletRequest postRequest);
	public boolean removeBook(String path,String fileName);
}
