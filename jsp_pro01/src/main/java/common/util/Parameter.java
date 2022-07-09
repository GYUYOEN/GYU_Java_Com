package common.util;

import javax.servlet.http.HttpServletRequest;

public class Parameter {
	public int defaultIntValue(HttpServletRequest request, String paramName, String defValue) {
		String result = request.getParameter(paramName) == null ? defValue : request.getParameter(paramName);
		result = result.isEmpty() ? defValue: result;
		result = result.matches("\\d+") ? result : defValue;
		return Integer.parseInt(result);
	}
}
