package library.dao;

public class FileLoggerPojo {
private String file_log_id,book_id,repo_path,status,download_path,coverted_path;

public String getDownload_path() {
	return download_path;
}

public void setDownload_path(String download_path) {
	this.download_path = download_path;
}

public String getCoverted_path() {
	return coverted_path;
}

public void setCoverted_path(String coverted_path) {
	this.coverted_path = coverted_path;
}

public String getFile_log_id() {
	return file_log_id;
}

public void setFile_log_id(String file_log_id) {
	this.file_log_id = file_log_id;
}

public String getBook_id() {
	return book_id;
}

public void setBook_id(String book_id) {
	this.book_id = book_id;
}

public String getRepo_path() {
	return repo_path;
}

public void setRepo_path(String repo_path) {
	this.repo_path = repo_path;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}
}
