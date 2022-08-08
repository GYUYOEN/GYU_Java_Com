package filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns="/*")
public class ThemeFilter extends HttpFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		Cookie cookies[] = req.getCookies();
		
		req.setAttribute("view", request.getServletContext().getInitParameter("view") + "/");
		
		if(cookies != null) {
			for(Cookie c: cookies) {
				if(c.getName().equals("theme")) {
					String theme = c.getValue();
					
					if(theme.equals("bs5")) {
						req.setAttribute("view", request.getServletContext().getInitParameter("view") + "theme/bs5/");
					}
				}
			}
		}
		
		chain.doFilter(req, resp);
	}
	
}
