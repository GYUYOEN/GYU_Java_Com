package filter;

import java.io.IOException;
import java.util.Map;

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

import login.model.PermDTO;

//@WebFilter(
//		filterName = "PermFilter",
//		urlPatterns = {
//				"/emps", "/emps/*",
//				"/depts", "/depts/*"
//		}
//)
public class PermFilter extends HttpFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		Map<String, PermDTO> perm = (Map<String, PermDTO>)session.getAttribute("permData");
		
		String uri = ((HttpServletRequest)request).getRequestURI();
		
		if(uri.equals("/emps")) {
			if(!perm.get("employees").ispRead()) {
				((HttpServletResponse)response).sendError(HttpServletResponse.SC_FORBIDDEN);
				return;
			}
		} else if(uri.equals("/depts")) {
			if(!perm.get("departments").ispRead()) {
				((HttpServletResponse)response).sendError(HttpServletResponse.SC_FORBIDDEN);
				return;
			}
		}
		
		chain.doFilter(request, response);
	}

}
