package com.library.fs.beans;

import java.util.ArrayList;
import java.util.List;

public class FileOpsProperties {

	private String fileID=null;
	private String filePath=null;
	private List<String> folderlist = new ArrayList<String>();
	
	public List<String> getFolderlist() {
		return folderlist;
	}
	public void setFolderlist(List<String> folderlist) {
		this.folderlist = folderlist;
	}
	public String getFileID() {
		return fileID;
	}
	public void setFileID(String fileID) {
		this.fileID = fileID;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	

}
