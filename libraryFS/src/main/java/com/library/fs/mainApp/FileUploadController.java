package com.library.fs.mainApp;

import java.io.IOException;
import java.util.Arrays;
<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> master

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
=======
import org.springframework.web.bind.annotation.RequestMapping;
>>>>>>> master
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.library.fileservices.FileServices;
<<<<<<< HEAD
import com.library.fs.beans.FileOpsProperties;
=======
>>>>>>> master


@RestController
public class FileUploadController {

	@Autowired
	FileServices fileservices;
	
	//testing
	@RequestMapping("msg")
	public String getMessage() {
		
		return "Hello World!!";
		
	}
	
	
<<<<<<< HEAD
	@PostMapping("/fc/supload")
=======
	@PostMapping("/fc/sUpload")
>>>>>>> master
	public ResponseEntity<?> uploadSingleBook(@RequestParam("file") MultipartFile file) throws IOException{
		
		boolean fileflag=false;
		ResponseEntity response=null;
		
		System.out.println("Single file upload");
		if(file.isEmpty()) {
			return new ResponseEntity<>("Please select the file properly",HttpStatus.OK);
		}
		try {
			fileservices.saveFileInServer(Arrays.asList(file));
			fileflag=true;
		}catch(Exception IOexception) {
			return new ResponseEntity<>("File operation failed",HttpStatus.BAD_REQUEST);
		}
		
		if(fileflag)
			response= new ResponseEntity("File uploaded successfully",new HttpHeaders(),HttpStatus.OK);
		
		return response;
		
	}
	
<<<<<<< HEAD
	@PostMapping("/fc/mupload")
=======
	@PostMapping("/fc/mUpload")
>>>>>>> master
	public ResponseEntity<?> uploadMultiBooks(@RequestParam("file") MultipartFile multifiles) throws IOException{
		boolean fileflag=false;
		ResponseEntity response = null;
		System.out.println("Multiple files upload");
		
		if(multifiles.isEmpty()) {
			return new ResponseEntity<>("Please select the file properly",HttpStatus.OK);
		}
		try {
			fileservices.saveFileInServer(Arrays.asList(multifiles));
			fileflag=true;
			
		}catch(Exception IOException) {
			return new ResponseEntity("File upload operation failed",HttpStatus.BAD_REQUEST);
		}
		if(fileflag)
			response= new ResponseEntity("Files uploaded successfully",new HttpHeaders(), HttpStatus.OK);
		
		return response;
	}
	
<<<<<<< HEAD
	@PostMapping(path="/univsetup",consumes="application/json")
	public boolean universityFolderSetup(@RequestParam String adminId,@RequestParam String univname,@RequestBody List<String> folders) {
		
		System.out.println("Admin ID"+adminId);
		System.out.println("Univname"+univname);
		for(String ll : folders) {
			System.out.println(ll);
		}
		return false;
	}
=======
	
>>>>>>> master
}
