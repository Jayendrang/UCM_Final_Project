package library.hdfsmanagement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HdfsProperties {

	@Value("${hadoopProperties.resourcemanager}")
	private String resourceManagerAddress;
	
	@Value("${hadoopProperties.hdfspath}")
	private String hdfsPath;
	
	@Value("${hadoopProperties.localdownloadpath}")
	private String localFileSourcePath;
	
	@Value("${hadoopProperties.localcovertedpath}")
	private String localConvertedFilePath;

	@Value("${hadoopProperties.coresitepath}")
	private String hadoopCoreSiteConfiguration;
	
	@Value("${hadoopProperties.hdfssitepath}")
	private String hadoopHdfsConfiguration;
	
	public String getResourceManagerAddress() {
		return resourceManagerAddress;
	}

	public void setResourceManagerAddress(String resourceManagerAddress) {
		this.resourceManagerAddress = resourceManagerAddress;
	}

	public String getHdfsPath() {
		return hdfsPath;
	}

	public void setHdfsPath(String hdfsPath) {
		this.hdfsPath = hdfsPath;
	}

	public String getLocalFileSourcePath() {
		return localFileSourcePath;
	}

	public void setLocalFileSourcePath(String localFileSourcePath) {
		this.localFileSourcePath = localFileSourcePath;
	}

	public String getLocalConvertedFilePath() {
		return localConvertedFilePath;
	}

	public void setLocalConvertedFilePath(String localConvertedFilePath) {
		this.localConvertedFilePath = localConvertedFilePath;
	}

	public String getHadoopCoreSiteConfiguration() {
		return hadoopCoreSiteConfiguration;
	}

	public void setHadoopCoreSiteConfiguration(String hadoopCoreSiteConfiguration) {
		this.hadoopCoreSiteConfiguration = hadoopCoreSiteConfiguration;
	}

	public String getHadoopHdfsConfiguration() {
		return hadoopHdfsConfiguration;
	}

	public void setHadoopHdfsConfiguration(String hadoopHdfsConfiguration) {
		this.hadoopHdfsConfiguration = hadoopHdfsConfiguration;
	}
	
	
	
	
}
