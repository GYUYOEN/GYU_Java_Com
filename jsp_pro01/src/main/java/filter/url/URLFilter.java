package filter.url;

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

// navigation 메뉴를 만드는 과정

@WebFilter("/*") // 모든 페이지 마다 필터를 걸치게함
public class URLFilter extends HttpFilter implements Filter {
	// filter : ServletRequest(상위객체), 일반 controller : HttpServletRequest(하위객체), 변수 request 에는 사용자 요청 정보가 담겨있음
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// http://localhost:8080/jsp01/depts?page=1 : page = 1 이 query string parameter
		// ServletRequest 는 getRequestURI() 를 사용할 수 없음 -> 다운캐스팅
		String url = ((HttpServletRequest)request).getRequestURI(); // ServletRequest에는 getRequestURI() 가 없으므로 다운 캐스팅 해줌
		request.setAttribute("url", url);
		chain.doFilter(request, response);
	}

}
