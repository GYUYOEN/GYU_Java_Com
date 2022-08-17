package com.myhome.web.board.controller;

import java.sql.SQLDataException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.myhome.web.boar.service.BoardService;
import com.myhome.web.board.model.BoardDTO;
import com.myhome.web.board.vo.BoardVO;
import com.myhome.web.comment.model.CommentDTO;
import com.myhome.web.comment.service.CommentService;
import com.myhome.web.common.util.Paging;
import com.myhome.web.emp.model.EmpDTO;
import com.myhome.web.emp.service.EmpService;
import com.myhome.web.upload.model.UploadFilesDTO;
import com.myhome.web.upload.service.UploadFilesService;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService service;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private EmpService empService;
	
	@Autowired
	private UploadFilesService fileService = new UploadFilesService();
	
	// 조회 목록
	@RequestMapping(value="", method=RequestMethod.GET)
	public String getData(Model model, HttpSession session
			, @RequestParam(defaultValue="1", required=false) int page
			, @RequestParam(defaultValue="0", required=false) int pageCount) { // pageCount : 페이지 목록
		// page 라는 파라미터 필수(required=false)는 아니며 기본값은 1(defaultValue="1")이다.(반드시 값을 지정해주어야지 함)
		// 파라미터가 없더도 page=1로 인식 됨, required는 기본값이 true이고 기본값 설정하면 false로 자동으로 들어간다.
		// spring을 사용하여 복잡한 로직이 없어짐( if(page == null) page = "1"; )
		logger.info("getData(page={}, pageCount={})", page, pageCount);
		
		if(session.getAttribute("pageCount") == null) {
			session.setAttribute("pageCount", 5); // 세션 값이 없을 경우
		}
		if(pageCount > 0) { // pageCount를 설정한 경우
			session.setAttribute("pageCount", pageCount); // 세션에 저장 -> 다음번에도 유지가 될 수 있도록
		}
		
//		List<BoardDTO> datas = service.getAll();
		Paging pageData = service.getPage(page, Integer.parseInt(session.getAttribute("pageCount").toString())); // 형변환 : String -> int
		
		model.addAttribute("pageData", pageData);
		model.addAttribute("datas", pageData.getPageDatas());
		
		return "board/list";
	}
	
	// 조회 상세
	// /detail/{id} : detail/id?=14 -> detail/14
	// 내부에서 사용할 변수 값으로 지정(@PathVariable) -> 요즘 많이 쓰는 추세
	// @RequestMapping(value="/detail/{id}", method=RequestMethod.GET)
	// public String getDetail(Model model
	// 		, @PathVariable int id) {
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public String getDetail(Model model
			, @RequestParam int id
			, @RequestParam(defaultValue="1", required=false) int page
			, @SessionAttribute(name="loginData", required=false) EmpDTO empDto) {
		logger.info("getDetail(empDto={}, id={})", empDto, id);
		
		BoardDTO data = service.getData(id);
		
		if(data == null) {
			model.addAttribute("error", "해당 데이터는 존재하지 않습니다.");
			return "error/noExists";
		} else {
//			List<CommentDTO> commentDatas = commentService.getDatas(data.getId());
			int limit = 5;
			Paging commentPage = commentService.getPage(page, limit, data.getId());
			UploadFilesDTO fileData = new UploadFilesDTO();
			List<UploadFilesDTO> datas = fileService.selectDatas(fileData.getBId());
			service.incViewCnt(empDto, data);
			model.addAttribute("data", data);
//			model.addAttribute("commentDatas", commentDatas);
			model.addAttribute("commentPage", commentPage);
			
			return "board/detail";
		}
	}
	// 이미지를 등록 안했는데 뜨는 이유 : 서버에 동시에 등록했기 때문
	
	// 추가 폼 요청
	@GetMapping(value="/add")
	public String add(@SessionAttribute(name="loginData", required=true) EmpDTO empDto) {
		logger.info("add(empDto={})", empDto);
		return "board/add";
	}
	
	// 추가 저장 요청
	// 메서드를 다르게 해서 Override 해줌
	@PostMapping(value="/add")
	public String add(@ModelAttribute BoardVO boardVo
			, @RequestParam("uploadFile") MultipartFile[] files
			, HttpServletRequest request
			, @SessionAttribute(name="loginData", required=true) EmpDTO empDto) {
			// EmpDTO empDto = (EmpDTO)session.getAttribute("loginData"); -> @SessionAttribute("loginData") EmpDTO empDto : 필요한 세션을 가져옴	
		logger.info("add(boardVo={}, empDto={})", boardVo, empDto);
		
		int id = service.add(empDto, boardVo);
		
		UploadFilesDTO data = new UploadFilesDTO();
		
		String realPath = request.getServletContext().getRealPath("/resources");
		boolean result = false;
		for(MultipartFile file: files) {
			result = fileService.insertData(data, file, realPath, id);
		}
//		List<UploadFilesDTO> datas = fileService.selectDatas(data.getBId());
		
		if(id > 0 && result) {
			return "redirect:/board/detail?id=" + id;
		} else {
			return "board/add";
		}
	}
	
	// 수정 폼 요청
	@GetMapping(value="/modify")
	public String modify(Model model
			, @SessionAttribute(name="loginData", required=true) EmpDTO empDto
			, @RequestParam int id) {
		
		BoardDTO data = service.getData(id);
		if(empDto.getEmpId() == data.getEmpId()) {
			model.addAttribute("data", data);
			return "board/modify";
		} else {
			model.addAttribute("error", "해당 작업을 수행 할 권한이 없습니다.");
			return "error/permission";
		}
	}
	
	
	// 수정 저장 요청
	@PostMapping(value="/modify")
	public String modify(Model model
			, @SessionAttribute(name="loginData", required=true) EmpDTO empDto
			, @ModelAttribute BoardVO boardVo) {
		BoardDTO data = service.getData(boardVo.getId());
		
		if(empDto.getEmpId() == data.getEmpId()) {
			data.setTitle(boardVo.getTitle());
			data.setContent(boardVo.getContent());
			boolean result = service.modify(data);
			
			if(result) {
				return "redirect:/board/detail?id=" + data.getId();
			} else {
				return "board/modify";
			}
		} else {
			model.addAttribute("error", "해당 작업을 수행할 권한이 없습니다.");
			return "error/permissions";
		}
	}
	
	// WEB-INF/views : prefix, .jsp: suffix -> return에는 그 사이값만 써주면 됨 /WEB-INF/views/board/add.jsp = return "board/add"
	// 삭제
//	@SuppressWarnings("unchecked") // json의 주의 표시가 거슬리면 해당 어노테이션 작성
	@PostMapping(value="/delete", produces="application/json; charset=utf-8") // produces = response.setContentType
	@ResponseBody // return 값의 응답 데이터, ajax를 이용할 때는 반드시 해당 어노테이션을 작성해야함
	public String delete(@SessionAttribute(name="loginData", required=true) EmpDTO empDto
			, @RequestParam int id) {
		
		BoardDTO data = service.getData(id);
		
		JSONObject json = new JSONObject();
		
		if(data == null) {
			// 이미 삭제가 되었음
			json.put("title", "삭제가 된 데이터");
			json.put("message", "해당 데이터는 이미 삭제가 되었습니다.");
			return json.toJSONString();
		} else {
			if(data.getEmpId() == empDto.getEmpId()) {
				// 삭제 가능
				boolean result = service.remove(data);
				if(result) {
					// 삭제 성공
					json.put("title", "삭제 완료");
					json.put("message", "삭제 처리가 완료되었습니다.");
					return json.toJSONString();
				} else {
					// 삭제 실패
					json.put("title", "삭제 실패");
					json.put("message", "삭제 작업 중 알 수 없는 문제가 발생하였습니다.");
					return json.toJSONString();
				}
			} else {
				// 작성자 불일치 - 삭제 불가 - 권한 없음
				json.put("title", "삭제 불가");
				json.put("message", "해당 데이터를 삭제할 권한이 없습니다.");
				return json.toJSONString();
			}
		}
	}
	
	// 좋아요
	@PostMapping(value="/like", produces="application/json; charset=utf-8")
	@ResponseBody
	public String like(@SessionAttribute("loginData") EmpDTO empDto
			, @RequestParam int id) {
		BoardDTO data = service.getData(id);
		
		JSONObject json = new JSONObject();
		
		if(data == null) {
			json.put("code", "noData");
			json.put("message", "데이터가 존재하지 않습니다.");
		} else {
			try {
				service.incLike(empDto, data);
				json.put("code", "success");
				json.put("message", "데이터 처리가 완료되었습니다.");
				json.put("likeCnt", data.getLike()); // 현재 추천 수가 얼마인지 알려줌
			} catch (SQLDataException e) {
				json.put("code", "fail");
				json.put("title", "오류");
				json.put("message", "데이터 처리 중 문제가 발생하였습니다.");
			}
			
		}
		return json.toJSONString();
	}
	
	// 댓글 추가
	@PostMapping(value="/comment/add")
	public String comment(Model model, HttpSession session
			, @SessionAttribute("loginData") EmpDTO empDto
			, @RequestParam int bid
			, @RequestParam String content) {
		CommentDTO data= new CommentDTO();
		data.setbId(bid);
		data.setContent(content);
		data.setEmpId(empDto.getEmpId());
		
		boolean result = commentService.add(data);
		
		if(result) {
			return "redirect:/board/detail?id=" + bid;
		} else {
			session.setAttribute("commentError", "댓글 추가 작업 중 문제가 발생하였습니다.");
			return "redirect:/board/detail?id=" + bid;
		}
	}
	
	// 댓글 수정
	@PostMapping(value="/comment/modify", produces="application/json; charset=utf-8")
	@ResponseBody
	public String comment(@SessionAttribute("loginData") EmpDTO empDto
			, @RequestParam int id
			, @RequestParam String content) {
		CommentDTO commentData = commentService.getData(id);
		
		JSONObject json = new JSONObject();
		
		if(commentData.getEmpId() == empDto.getEmpId()) {
			String backupContent = commentData.getContent();
			commentData.setContent(content);
			boolean result = commentService.modify(commentData);
			
			if(result) {
				json.put("code", "success");
				json.put("value", commentData.getContent());
			} else {
				json.put("code", "fail");
				json.put("value", backupContent);
			}
		}
		
		return json.toJSONString();
	}
	
	// 댓글 삭제
	@PostMapping(value="/comment/delete", produces="application/json; charset=utf-8")
	@ResponseBody
	public String comment(@SessionAttribute("loginData") EmpDTO empDto
			, @RequestParam int id) {
		CommentDTO commentData = commentService.getData(id);
		
		JSONObject json = new JSONObject();
		
		if(commentData.getEmpId() == empDto.getEmpId()) {
			boolean result = commentService.remove(commentData);
			json.put("code", "success");
		}
		
		return json.toJSONString();
	}
}
