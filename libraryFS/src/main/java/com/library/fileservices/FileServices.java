package com.library.fileservices;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.library.fs.beans.BookProperties;

public interface FileServices {
	 boolean saveFileInServer(List<MultipartFile> uploadedfiles);
	 boolean deleteFileInServer(List<String> filename,boolean folderflag);
	 List<BookProperties> getAllFilesInfo(String schoolname);
	 boolean fileSystemSetup(List<String> folderspec,String organizationName);
	 
	
}
