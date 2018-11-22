package library.LibraryFileRepositoryService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import library.RepoStorageService.RepoFileStorageService;
import library.fileException.RepoStorageException;

@RestController
@RequestMapping("/repo/fileOps")
public class RepositoryController {

	@Autowired
	private RepoFileStorageService fileServices;

	@PostMapping("/setupfolder")
	public boolean setupInstitution(@RequestParam("name") String institution_name) throws IOException {
		return fileServices.setupInstitutionStorageFolder(institution_name);
	}

		@PostMapping("/uploadFile")
		public String uploadFile(@RequestParam("file") MultipartFile file,
				@RequestParam("repoLocation") String repoLocation, @RequestParam("fileId") String fileId) {
		
			boolean process = false;
			try {
			System.out.println("---->"+fileId);
			process = fileServices.saveFile(file, repoLocation, fileId);
			} catch (RepoStorageException e) {
	
				e.printStackTrace();
			}
			if (process) {
				// return new RepoFileOpsResponse(file.getOriginalFilename(), repoLocation,
				// file.getContentType(), "SUCCESS");
				return "File Uploaded Successfully";
			}
			return "Operation failed";
	
		}

	// public List<RepoFileOpsResponse> multipleUploads(@RequestParam("files")
	// MultipartFile[] files,
	// @RequestParam("repoLocation") String repoLocation, @RequestParam("fileId")
	// String fileId) {
	// List<RepoFileOpsResponse> response = Arrays.asList(files).stream()
	// .map(file -> uploadFile(file, repoLocation,
	// fileId)).collect(Collectors.toList());
	// return response;
	// }

	@GetMapping("/downloadFile")
	public ResponseEntity<Resource> downloadFile(@RequestParam(value = "repo") String repo,
			@RequestParam(value = "fileId") String fileId, HttpServletRequest servletRequest) throws IOException {

		Resource resource = fileServices.loadFileAsResource(repo, fileId);
		String contentType = null;
		try {
			contentType = servletRequest.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\\" + resource.getFilename() + "\\")
				.body(resource);
	}
}
