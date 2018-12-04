package library.cloudfilemanagement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Component
public class CloudFileProperties {

	@Value("${awsProperties.s3endpoint}")
	private String s3endpoint;
	@Value("${awsProperties.accesskey}")
	private String accesskey;
	@Value("${awsProperties.secretkey}")
	private String secretkey;
	@Value("${awsProperties.bucketname}")
	private String bucketname;

	
	
	public CloudFileProperties() {
		
	}

	public String getS3endpoint() {
		return s3endpoint;
	}

	public void setS3endpoint(String s3endpoint) {
		this.s3endpoint = s3endpoint;
	}

	public String getAccesskey() {
		return accesskey;
	}

	public void setAccesskey(String accesskey) {
		this.accesskey = accesskey;
	}

	public String getSecretkey() {
		return secretkey;
	}

	public void setSecretkey(String secretkey) {
		this.secretkey = secretkey;
	}

	public String getBucketname() {
		return bucketname;
	}

	public void setBucketname(String bucketname) {
		this.bucketname = bucketname;
	}

	public AmazonS3 getConnection() throws Exception {
		
		AWSCredentials credentials = new BasicAWSCredentials(this.getAccesskey(),this.getSecretkey());
		AmazonS3 awsClient = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.US_EAST_1).build();
		System.err.println("Cloud connection established");
		return awsClient;
		
	}
}
