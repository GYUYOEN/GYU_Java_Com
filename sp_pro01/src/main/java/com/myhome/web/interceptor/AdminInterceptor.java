package com.myhome.web.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.myhome.web.emp.model.EmpDTO;

// 관리자용 전용 페이지를 만들때 Interceptor를 사용 할 수 있다.
// url 주소는 일반 사원과 같게 나오지만 view가 다름
public class AdminInterceptor implements HandlerInterceptor {
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginData") != null) {
			EmpDTO empDto = (EmpDTO)session.getAttribute("loginData");
			
			// 관리부(Administration)
			if(empDto.getDeptId() == 10) {
				String oldView = modelAndView.getViewName(); // getViewName() : 어떠한 뷰 패이지를 사용하는지 알아낼 수 잇음 ->  board/list 
				if(!oldView.startsWith("redirect:")) { // 리다이렉트의 경우 admin/ 이 붙지 않게 하기 위한 작업
					modelAndView.setViewName("admin/" + oldView); // 별도의 관리자 페이지가 사용되게 한다.(관리자 계정으로 접속하면 admin이라는 경로가 항상 들어가게 한다. -> /WEB-INF/views/admin/redirect:/index.jsp)
				}
			} 
		}
	}

}
