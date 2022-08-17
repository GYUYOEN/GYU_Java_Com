package com.myhome.web.upload.model;

public class UploadFilesDTO {
	private int id;
	private int bId;
	private String fileName;
	private String location;
	private String url;
	private long fileSize;
	
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
		return "UploadFilesDTO [id=" + id + ", bId=" + bId + ", fileName=" + fileName + ", location=" + location
				+ ", url=" + url + ", fileSize=" + fileSize + "]";
	}
}
