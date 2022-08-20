package com.myhome.web.upload.model;

public class FileUploadDTO {
	private int id;
	private int bId;
	private String fileName;
	private String uuidName;
	private String location;
	private String url;
	private long fileSize;
	
	public FileUploadDTO() {}
	
	public FileUploadDTO(int bId, String location, String url) {
		this.bId = bId;
		this.location = location;
		this.url = url;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBId() {
		return bId;
	}
	public void setBId(int bid) {
		this.bId = bid;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUuidName() {
		return uuidName;
	}
	public void setUuidName(String uuidName) {
		this.uuidName = uuidName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	@Override
	public String toString() {
		return "FileUploadDTO [id=" + id + ", bId=" + bId + ", fileName=" + fileName + ", uuidName=" + uuidName
				+ ", location=" + location + ", url=" + url + ", fileSize=" + fileSize + "]";
	}
	
}
