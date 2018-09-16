package com.library.fileservices;

<<<<<<< HEAD
import java.io.FileNotFoundException;
import java.io.IOException;
=======
>>>>>>> master
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

<<<<<<< HEAD
import com.library.fs.beans.FileOpsProperties;

public interface FileServices {
	 boolean saveFileInServer(List<MultipartFile> uploadedfiles);
	 boolean deleteFileInServer(List<String> filename,String schoolName,boolean folderflag) throws IOException;
	 List<FileOpsProperties> getAllFilesInfo(String schoolname);
	 boolean fileSystemSetup(List<String> folderspec,String organizationName) throws IOException;
=======
import com.library.fs.beans.BookProperties;

public interface FileServices {
	 boolean saveFileInServer(List<MultipartFile> uploadedfiles);
	 boolean deleteFileInServer(List<String> filename,boolean folderflag);
	 List<BookProperties> getAllFilesInfo(String schoolname);
	 boolean fileSystemSetup(List<String> folderspec,String organizationName);
>>>>>>> master
	 
	
}
