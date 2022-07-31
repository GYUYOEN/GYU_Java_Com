package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

// @WebFilter("/EncodingFilter") // web.xml 이용할 거라 사용x
public class EncodingFilter extends HttpFilter implements Filter {
	
	// ServletRequest : HttpServletRequest 의 상위 객체
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("필터 확인!!");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		// doFilter 메서드 동작 전: 요청 필터
		chain.doFilter(request, response); 
		// doFilter 메서드 동작 후: 응답 필터
	}

}
