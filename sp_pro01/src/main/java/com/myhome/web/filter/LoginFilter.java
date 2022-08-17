package com.myhome.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(
		urlPatterns = {
			"/board", "/board/*"	
		}
)
public class LoginFilter extends HttpFilter implements Filter {
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		
		// localhost:8080/spring/board/detail?id=16&name=other
		// getQueryString() : parameter를 가져옴 -> id=16&name=other
		// getRequestURI() : /spring/board/detail
		
		String qs = "";
		if(((HttpServletRequest)request).getQueryString() != null) {
			qs = "?" + ((HttpServletRequest)request).getQueryString();
		}
		String path = ((HttpServletRequest)request).getRequestURI();
		
		if(session.getAttribute("loginData") != null) {
			chain.doFilter(request, response);
		} else {
			// 로그인 성공 시 메인으로 말고 요청한 url 페이지로 갈 수 있도록 함 (로그인 페이지 jsp에 input을 hidden으로 해서 해두어야 함)
			((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath() + "/login?url=" + path + qs);
		}
	}
	
}
