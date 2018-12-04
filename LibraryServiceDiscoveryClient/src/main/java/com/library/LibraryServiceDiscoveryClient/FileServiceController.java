package com.library.LibraryServiceDiscoveryClient;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
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
	public String uploadBooks(@RequestPart(required=true) MultipartFile file,
			@RequestParam(value = "repo") String institutionRepo, @RequestParam(value = "filename") String fileName) {
		
		
		return fileService.uploadBooks(file, institutionRepo, fileName);

	}

	@GetMapping("/download")
	public ResponseEntity<Resource> downloadBooks(@RequestParam(value = "repoUri") String path,
			@RequestParam(value = "fileId") String fileId, HttpServletRequest httpServletRequest) {

		return fileService.downloadFile(path, fileId, httpServletRequest);
	}

	@PostMapping("/setup")
	public String setupRepositoryForUniversity(@RequestParam(value = "name") String name) {
		return fileService.setupInstitutionRepository(name);
	}
}
