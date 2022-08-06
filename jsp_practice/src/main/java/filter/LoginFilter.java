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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(
		urlPatterns = {
				"/depts", "/locs", "/emps",
				"/depts/*", "/locs/*", "/emps/*"
		}
)
public class LoginFilter extends HttpFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		if(session.getAttribute("loginData") != null) {
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath() + "/jpr");
		}
	}

}
