package com.library.fs.processor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.library.fileservices.FileServices;
import com.library.fs.beans.FileOpsProperties;

@Service
public class FileSystemHandler implements FileServices {

	private String SERVER_FILE_PATH = "//home//jayendrang//uploads//";

	public FileSystemHandler() {

	}

	public boolean saveFileInServer(List<MultipartFile> uploadedfiles) {

		boolean process_status = false;
		try {
			for (MultipartFile documents : uploadedfiles) {

				Path serverpath = Paths.get(SERVER_FILE_PATH + documents.getOriginalFilename());
				System.out
						.println("File-Name" + documents.getOriginalFilename() + "\t File Size" + documents.getSize());

				Files.copy(documents.getInputStream(), serverpath, StandardCopyOption.REPLACE_EXISTING);
				process_status = true;

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return process_status;
	}

	public List<FileOpsProperties> getAllFilesInfo(String schoolname) {

		return null;
	}

	@Override
	public boolean deleteFileInServer(List<String> filefoldercollection, String schoolName, boolean folderflag)
			throws NoSuchFileException, DirectoryNotEmptyException, IOException {

		File rootfolder = new File(SERVER_FILE_PATH + "//" + schoolName);
		boolean processflag=false;
		if (folderflag) {
			if ((rootfolder.exists() == true) && (rootfolder.isDirectory() == true)) {
				for (String folders : filefoldercollection) {
					File subfolder = new File(rootfolder + "//" + folders);
					if (subfolder.isDirectory()) {
						File[] filesinsidesubfolder = subfolder.listFiles();
						for (File files : filesinsidesubfolder) {
							boolean flag = files.delete();
							if (!flag) {
								break;
							}
						}
						processflag=subfolder.delete()==true?true:false;
						
					}
				}

			} else {
				throw new FileNotFoundException();
			}

		} else {

			for (String files : filefoldercollection) {
				File tfile = new File(files);
				if (tfile.delete()) {
					processflag=true;
					continue;
				} else {
					processflag=false;
					break;
				}
			}

		}

		return processflag;
	}

	@Override
	public boolean fileSystemSetup(List<String> folderspec, String organizationName) throws IOException {
		File organization = new File(SERVER_FILE_PATH);
		if(organization.mkdir()) {
			for(String subfolders:folderspec) {
				File actionpath = new File(organization.getAbsolutePath()+"//"+folderspec);
					actionpath.mkdirs();
			}
		}
		return false;
	}

}
