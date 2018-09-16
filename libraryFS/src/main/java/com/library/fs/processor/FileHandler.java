package com.library.fs.processor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.library.fileservices.FileServices;
import com.library.fs.beans.BookProperties;

@Service
public class FileHandler implements FileServices {

	private String SERVER_FILE_PATH = "//home//jayendrang//uploads//";

	public FileHandler() {

	}

	public boolean saveFileInServer(List<MultipartFile> uploadedfiles) {

		boolean process_status = false;
		try {
			for (MultipartFile documents : uploadedfiles) {
				
				
				Path serverpath = Paths.get(SERVER_FILE_PATH + documents.getOriginalFilename());
				System.out.println("File-Name"+documents.getOriginalFilename()+"\t File Size"+documents.getSize());
				
				Files.copy(documents.getInputStream(), serverpath, StandardCopyOption.REPLACE_EXISTING);
				process_status = true;

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return process_status;
	}

	public boolean deleteFileInServer(List<String> filename) throws FileNotFoundException,IOException {
		try {
				
			
			
			
		}catch(Exception generic) {
			generic.printStackTrace();
		}
		
		
		return false;
	}

	public List<BookProperties> getAllFilesInfo(String schoolname) {

		return null;
	}

	@Override
	public boolean deleteFileInServer(List<String> filename, boolean folderflag) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fileSystemSetup(List<String> folderspec, String organizationName) {
		// TODO Auto-generated method stub
		return false;
	}

}
