package com.myhome.web.mapper;

import java.util.List;

import com.myhome.web.chat.model.ChatRoomDTO;

public interface ChatMapper {
	public int insertChatRoom(String username);

	public List<ChatRoomDTO> selectChatRoom(String username);
}
