package library.RepoStorageService;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import library.fileConfig.FileStorageProperty;
import library.fileException.RepoStorageException;

@Component
public class RepoFileStorageService {

	private Path uriLocation;

	@Autowired
	public RepoFileStorageService(FileStorageProperty fileStorageProperty) throws RepoStorageException {
		this.uriLocation = Paths.get(fileStorageProperty.getUploadUriPath()).toAbsolutePath().normalize();

	}

	public boolean setupInstitutionStorageFolder(String folderName) throws IOException {
		boolean response = false;
		try {
			String folder = uriLocation.toString().concat("/").concat(folderName);
			String thumbnail = folder.concat("/").concat("thumbnails");
			Path filePath = Paths.get(folder);
			Path thumbnailpath = Paths.get(thumbnail);
			System.out.println(filePath.getFileName().toString());
			if (!Files.exists(filePath)) {
				Files.createDirectory(filePath);
				Files.createDirectory(thumbnailpath);
			} else {
				throw new FileAlreadyExistsException(folder);
			}
			response = true;
		} catch (FileAlreadyExistsException fileAlreadyExistsException) {
			response = false;
			fileAlreadyExistsException.printStackTrace();
		} catch (Exception ex) {
			response = false;
			ex.printStackTrace();
		}
		return response;
	}

	public synchronized boolean saveFile(MultipartFile file, String repoLocation, String fileId) throws RepoStorageException {
		String fileName = org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (fileName.contains("..")) {
				throw new FileSystemException(file.getOriginalFilename() + "Improper file path");
			}
			Path serverLocation = this.uriLocation.resolve(repoLocation.concat("/").concat(fileId));
			long bytesize = Files.copy(file.getInputStream(), serverLocation, StandardCopyOption.REPLACE_EXISTING);
			if (bytesize > 0) {
				PDDocument document = PDDocument.load(serverLocation.toFile());//new File(serverLocation.toFile().toString()));
				PDFRenderer renderer = new PDFRenderer(document);
				BufferedImage image = renderer.renderImageWithDPI(0, 300, ImageType.RGB);
				@SuppressWarnings("deprecation")
				boolean result = ImageIOUtil.writeImage(image, "png",
						repoLocation.concat("/thumbnails/").concat(fileId), 300);
				document.close();
				return result;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new RepoStorageException("File operation failed..." + fileName + " cannot be stored");
		}
		return false;

	}


	public synchronized Resource loadFileAsResource(String RepoPath,String fileName)
			throws FileNotFoundException, MalformedURLException {
		Path filePath = this.uriLocation.resolve(RepoPath.concat("/").concat(fileName));
		System.err.println(filePath.toAbsolutePath().toString());
		Resource resource;
		try {
			resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new FileNotFoundException(fileName + "is not available");
			}
		} catch (MalformedURLException exception) {
			throw new MalformedURLException(fileName + "is not available");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
