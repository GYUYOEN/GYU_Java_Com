package com.myhome.web.chat.model;

import oracle.sql.DATE;

public class ChatRoomDTO {
	private int chatRoomNo;
	private String empId;
	private String chatTitle;
	private String chatProfile;
	private String chatLastTime;
	private String chatLastCont;
	private String chatRoomType;
	
	public int getChatRoomNo() {
		return chatRoomNo;
	}
	public void setChatRoomNo(int chatRoomNo) {
		this.chatRoomNo = chatRoomNo;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getChatTitle() {
		return chatTitle;
	}
	public void setChatTitle(String chatTitle) {
		this.chatTitle = chatTitle;
	}
	public String getChatProfile() {
		return chatProfile;
	}
	public void setChatProfile(String chatProfile) {
		this.chatProfile = chatProfile;
	}
	public String getChatLastTime() {
		return chatLastTime;
	}
	public void setChatLastTime(String chatLastTime) {
		this.chatLastTime = chatLastTime;
	}
	public String getChatLastCont() {
		return chatLastCont;
	}
	public void setChatLastCont(String chatLastCont) {
		this.chatLastCont = chatLastCont;
	}
	public String getChatRoomType() {
		return chatRoomType;
	}
	public void setChatRoomType(String chatRoomType) {
		this.chatRoomType = chatRoomType;
	}
	@Override
	public String toString() {
		return "ChatRoomDTO [chatRoomNo=" + chatRoomNo + ", empId=" + empId + ", chatTitle=" + chatTitle
				+ ", chatProfile=" + chatProfile + ", chatLastTime=" + chatLastTime + ", chatLastCont=" + chatLastCont
				+ ", ChatRoomType=" + chatRoomType + "]";
	}
}
