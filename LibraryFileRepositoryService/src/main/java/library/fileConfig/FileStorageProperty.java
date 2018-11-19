package library.fileConfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="file")
public class FileStorageProperty {

	private String uploadUriPath;

	public String getUploadUriPath() {
		return uploadUriPath;
	}

	public void setUploadUriPath(String uploadUriPath) {
		this.uploadUriPath = uploadUriPath;
	}
	
	
	
}
