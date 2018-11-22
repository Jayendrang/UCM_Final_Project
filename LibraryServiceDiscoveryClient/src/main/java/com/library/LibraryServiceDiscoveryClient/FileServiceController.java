package com.library.LibraryServiceDiscoveryClient;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.library.service.LibraryFileRepoService;

@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
@RequestMapping("/library/fileOps")
public class FileServiceController {

	@Autowired
	LibraryFileRepoService fileService;

	@PostMapping("/upload")
	public String uploadBooks(@RequestParam(value = "file") MultipartFile file,
			@RequestParam(value = "instRepo") String institutionRepo, @RequestParam(value = "fileId") String fileName) {
		if(fileService.uploadBooks(file, institutionRepo, fileName)) {
			return "SUCCESS";
		}else {
			return "FAILED";
		}

	}

	@GetMapping("/download")
	public ResponseEntity<Resource> downloadBooks(@RequestParam(value = "repoUri") String path,
			@RequestParam(value = "fileId") String fileId, HttpServletRequest httpServletRequest) {

		return fileService.downloadFile(path, fileId, httpServletRequest);
	}

	@PostMapping("/setup")
	public boolean setupRepositoryForUniversity(@RequestParam(value = "name") String name) {
		return fileService.setupInstitutionRepository(name);
	}
}
