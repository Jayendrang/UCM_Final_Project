package com.library.fileservices;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.library.fs.beans.FileOpsProperties;

public interface FileServices {
	 boolean saveFileInServer(List<MultipartFile> uploadedfiles);
	 boolean deleteFileInServer(List<String> filename,String schoolName,boolean folderflag) throws IOException;
	 List<FileOpsProperties> getAllFilesInfo(String schoolname);
	 boolean fileSystemSetup(List<String> folderspec,String organizationName) throws IOException;
	 
	
} 	
