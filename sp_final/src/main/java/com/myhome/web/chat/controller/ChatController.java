package com.myhome.web.chat.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myhome.web.chat.model.ChatRoomDTO;
import com.myhome.web.chat.service.ChatService;
import com.myhome.web.login.model.EmpDTO;
import com.myhome.web.login.model.LoginVO;


@Controller
@RequestMapping(value="/chat")
public class ChatController {
	
	private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
	
	@Autowired
	private ChatService chatService;
	
	// 채팅 채널 조회
	@GetMapping(value="")
	public String home(Model model, HttpSession session) {
		LoginVO loginVo = (LoginVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		List<EmpDTO> empDtos = chatService.selectEmployeeAll();
		List<ChatRoomDTO> chatRoom = chatService.selectChatRoom(loginVo.getUsername());
		
		EmpDTO emp = new EmpDTO();
		
//		session.setAttribute("empId", loginVo.getUsername());
		
		model.addAttribute("empDtoDatas", empDtos);
		model.addAttribute("chatRoomDatas", chatRoom);
		model.addAttribute("loginEmp", loginVo);
		
		return "chat/chat";
	}
	
	@PostMapping(value="/room/add", produces="application/json; charset=utf-8")
	@ResponseBody
	public String chatRoonAdd(@RequestParam String id) {
		JSONObject json = new JSONObject();
		chatService.insertChatRoom(id);
		return json.toJSONString();
	}
	
	/*
	@GetMapping(value="/chat")
	public String selectChatRoom(Model model, @AuthenticationPrincipal LoginVO loginVo) {
		String username = loginVo.getUsername();
		System.out.println(username);
		List<ChatRoomDTO> chatRoom = chatService.selectChatRoom(username);
		
		model.addAttribute("chatRoomDatas", chatRoom);
		
		return "chat/chat";
	}
	*/
	/*
	@PostMapping(value="/chat/room")
	public String insertChatRoom(@AuthenticationPrincipal LoginVO loginVo) {
		String username = loginVo.getUsername();
		
		System.out.println(username);
		
		// JSONObject json = new JSONObject();
		boolean result = chatService.insertChatRoom(username);
		
		System.out.println(result);
		
//		if(result) {
//			json.put("code", "success");
//		}
		if(result) {
			return "redirect:/chat/chat";
		} else {
			return "chat/chat";
		}
	}
	*/
}