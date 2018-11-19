package library.pojo;

public class RepoFileOpsResponse {

	private String fileName, fileDownloadUri, fileType, fileStatus;

	public RepoFileOpsResponse(String fName, String downloadUri, String fType, String fStatus) {

		this.fileName = fName;
		this.fileDownloadUri = downloadUri;
		this.fileType = fType;
		this.fileStatus = fStatus;
	}

	public String getFileStatus() {
		return fileStatus;
	}

	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

}
