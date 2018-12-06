package library.RepoStorageService;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class CloudFileStorageService {

	private AmazonS3 awsFileStorageClient;

	@Value("${awsProperties.s3endpoint}")
	private String endPointURI;

	@Value("${awsProperties.accesskey}")
	private String cloudAccessKey;

	@Value("${awsProperties.secretkey}")
	private String secretKey;

	@Value("${awsProperties.bucketname}")
	private String storageName;

	private String thumbnail = "thumbnails";

	public CloudFileStorageService() {

	}

	@PostConstruct
	private void intializeS3Client() {
		AWSCredentials creds = new BasicAWSCredentials(this.cloudAccessKey.trim(), this.secretKey.trim());
		this.awsFileStorageClient = new AmazonS3Client(creds);
	}

	private File convertMultipartToFile(MultipartFile file) throws IOException {
		File uploadFile = new File(file.getOriginalFilename());
		FileOutputStream localOutputStream = new FileOutputStream(uploadFile);
		localOutputStream.write(file.getBytes());
		localOutputStream.close();
		return uploadFile;
	}

	public boolean createFolderInS3(String foldername) {
		boolean response = false;
		try {
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(0);
			metadata.setContentType("application/pdf");
			InputStream dataStream = new ByteArrayInputStream(new byte[0]);
			if (this.awsFileStorageClient.doesBucketExist(storageName)) {
				// pdf folder
				PutObjectRequest universityRepoObject = new PutObjectRequest(storageName, foldername.concat("/"),
						dataStream, metadata);
				this.awsFileStorageClient.putObject(universityRepoObject);

				// thumbnail folder
				metadata.setContentType("image/jpeg");
				PutObjectRequest universityRepoThumbnailObject = new PutObjectRequest(storageName,
						foldername.concat("/").concat(thumbnail).concat("/"), dataStream, metadata);
				this.awsFileStorageClient.putObject(universityRepoThumbnailObject);

				if (this.awsFileStorageClient.doesBucketExist(storageName.concat("/").concat(foldername))) {
					response = true;

				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response = false;
		}
		return response;
	}

	public synchronized boolean uploadFileToS3(MultipartFile multipartFile, String s3Path, String fileName) {
		String fileUri = null;
		String thumbnailUri = null;
		boolean response = true;
		try {

			File file = convertMultipartToFile(multipartFile);
			fileUri = storageName.concat("/").concat(s3Path);
			thumbnailUri = fileUri.concat("/").concat(thumbnail);
			ObjectMetadata metaData = new ObjectMetadata();
			metaData.setContentType("application/pdf");
			System.err.println(fileUri);
			if (this.awsFileStorageClient.doesBucketExist(fileUri)) {
				System.err.println("folder exists-->" + fileUri);
				this.awsFileStorageClient
						.putObject(new PutObjectRequest(fileUri, fileName.concat(".pdf"), file).withMetadata(metaData));

				// thumbnail generation
				PDDocument document = PDDocument.load(file);
				PDFRenderer renderer = new PDFRenderer(document);
				BufferedImage jpegImage = renderer.renderImageWithDPI(0, 50, ImageType.RGB);
				boolean img = ImageIOUtil.writeImage(jpegImage, "jpeg", "temp/"+"buffer", 300);
				document.close();
				File imgFile = new File("temp/buffer.jpeg");
				this.awsFileStorageClient
						.putObject(new PutObjectRequest(thumbnailUri, fileName.concat(".jpeg"), imgFile));
				
				imgFile.delete();

				response = true;
			}

			file.delete();

		} catch (Exception e) {
			e.printStackTrace();
			response = false;
		}
		return response;
	}

	public boolean deleteFileInS3(String s3Path, String fileName) {
		String fileUri = null;
		boolean response = false;
		try {
			fileUri = storageName.concat("/").concat(s3Path);
			if (this.awsFileStorageClient.doesBucketExist(fileUri)) {
				System.err.println("repo exists-->" + fileUri);

				this.awsFileStorageClient.deleteObject(new DeleteObjectRequest(fileUri, fileName));
				response = true;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			response = false;
		}
		return response;
	}

}
