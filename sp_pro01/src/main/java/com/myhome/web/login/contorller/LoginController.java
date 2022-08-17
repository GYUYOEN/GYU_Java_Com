package com.myhome.web.login.contorller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myhome.web.HomeController;
import com.myhome.web.dept.model.DeptDTO;
import com.myhome.web.dept.service.DeptService;
import com.myhome.web.login.service.LoginService;
import com.myhome.web.login.vo.LoginVO;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginService service;
	
	@Autowired
	private DeptService deptService;
	
	/*
	@RequestMapping(value="/login", method=RequestMethod.POST)
	// @RequestParam("empId") : jsp와 일치하지 않을 떄 ""에 jsp에 들어간 걸로 맞춰줌
	public String login(Model model
			, @RequestParam("id") int empId
			, @RequestParam("deptId") int deptId
			, @RequestParam("name") String empName) {
//	public String login(Model model, int empId, int deptId, String empName) { // 알아서 형변환 헤줌
//	public String login(Model model, HttpServletRequest res) {
		
//		logger.info("empId: {}", res.getParameter("empId"));
//		logger.info("deptId: {}", res.getParameter("deptId"));
//		logger.info("empName: {}", res.getParameter("empName"));
		
		logger.info("empId: {}", empId);
		logger.info("deptId: {}", deptId);
		logger.info("empName: {}", empName);
		
		return "";
	}
	*/
	
	@GetMapping(value="/login")
	public String login(Model model) {
		List<DeptDTO> deptDatas = deptService.getAll();
		model.addAttribute("deptDatas", deptDatas);
		return "login/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	// 메서드에 필요하면 계속 넣을 수 있음 -> 다 전달해줌 (순서 상관 없음)
	public String login(Model model, LoginVO loginVo, String deptRe, HttpSession session, HttpServletResponse response) { // 알아서 LoginVO에 넣어줌. parameter 명과 일치해야함
		
//		logger.info("empId: {}", loginVo.getEmpId());
//		logger.info("deptId: {}", loginVo.getDeptId());
//		logger.info("empName: {}", loginVo.getEmpName());
		
		logger.info("login({}, {}, {}, {})", loginVo.getEmpId(), loginVo.getDeptId(), loginVo.getEmpName(), deptRe);
		
		boolean result = service.login(session, loginVo);
		
		if(result) {
			// 로그인 성공
			Cookie cookie;
			if(deptRe != null) {
				cookie = new Cookie("deptRe", String.valueOf(loginVo.getDeptId()));
				cookie.setMaxAge(60*60*24*5);
			} else {
				cookie = new Cookie("deptRe", "");
				cookie.setMaxAge(0);
			}
			response.addCookie(cookie);
			return "redirect:/index";
		} else {
			// 로그인 실패
			List<DeptDTO> deptDatas = deptService.getAll(); // dept 목록 나오개 하기
			model.addAttribute("deptDatas", deptDatas);
			return "/login/login"; // 로그인 페이지
		}
		
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		if(session.getAttribute("loginData") != null) {
			// 세션을 완전히 만료 시켜 새로운 세견이 만들어 지게 한다.
//			session.invalidate();
			
			// loginData 삭제 -> 새로운 세션이 만들어자는 게 아니고 기존 세션은 유지하되 로그인 정보만 제거하고 로그아웃
			session.removeAttribute("loginData");
		}
		return "redirect:/index";
		
	}
}
