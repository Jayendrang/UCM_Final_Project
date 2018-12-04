package library.LibraryFileRepositoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;

import library.RepoStorageService.CloudFileStorageService;
import library.pojo.FileInfo;
import library.utils.AppUtils;

@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
@RequestMapping("/repo/cloud/")
public class CloudFileOperationController {

	private CloudFileStorageService cloudFileOps;

	@Autowired
	public CloudFileOperationController(CloudFileStorageService storageservice) {
		this.cloudFileOps = storageservice;
	}

	@PostMapping("upload")
	@ResponseBody
	public String fileUpload( @RequestPart MultipartFile file, @RequestParam(value="path")String path,@RequestParam(value="name")String filename)throws Exception {
		
		return this.cloudFileOps.uploadFileToS3(file,path,filename );
	}

	@PostMapping("remove")
	public String deleteFile(@RequestParam(value="path") String path, @RequestParam(value="name") String fileName) {
		return this.cloudFileOps.deleteFileInS3(path,fileName);
	}
	
	@PostMapping("create")
	public String createUniversityBookRepository(@RequestParam(value="name") String name) {
		System.err.println("inside controller");
		return this.cloudFileOps.createFolderInS3(name);
	}
	
}
