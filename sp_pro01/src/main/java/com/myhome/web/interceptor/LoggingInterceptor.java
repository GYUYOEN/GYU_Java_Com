package com.myhome.web.interceptor;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

// 데이터를 가로채는 역할 (추가기능을 넣어줌) -> 로깅 작업, 로그인 체크, 권한 체크, 관리자 페이지
// 요청과 응답 사이의 시간 측정 가능 (얼마나 걸렸는지)
public class LoggingInterceptor implements HandlerInterceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

	// controller 로 이동하기 전
	// true -> next Interceptor OR Conroller
	// false
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");

		// dateFormat.format(date) : 언제, request.getRequestURI() : 어떤 url 주소를 요청 헸는지
		logger.info("[{}] {}", dateFormat.format(date), request.getRequestURI());
		
		request.setAttribute("timer", date);
		return true;
	}
	
	// controller 가 완료된 후
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// 요청과 응답 사이의 시간 측정 가능 (얼마나 걸렸는지)
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.sss");
		
		Date oldDate = (Date)request.getAttribute("timer");
		double timer = (date.getTime() - oldDate.getTime()) / 1000.0;
		logger.info("[{}] {} 초", dateFormat.format(date), timer);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
	}
}
