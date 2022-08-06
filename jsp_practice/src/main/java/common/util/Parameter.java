package common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Parameter {
	
	public int defaultIntValue(HttpServletRequest request, String paramName, String defValue) {
		String result = request.getParameter(paramName) == null ? defValue : request.getParameter(paramName);
		result = result.isEmpty() ? defValue : result;
		result = result.matches("\\d+") ? result : defValue;
		return Integer.parseInt(result);
	}

	public int defaultSessionValue(HttpServletRequest request, String paramName, String defValue) {
		HttpSession session = request.getSession();
		String defSession = session.getAttribute(paramName) == null ? defValue : session.getAttribute(paramName).toString();
		String result = request.getParameter(paramName) == null ? defSession : request.getParameter(paramName);
		result = result.isEmpty() ? defSession : result;
		result = result.matches("\\d+") ? result : defSession;
		session.setAttribute(paramName, result);
		return Integer.parseInt(result);
	}
	
}
