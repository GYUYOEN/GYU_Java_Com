package com.myhome.web.aop;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAOP {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggingAOP.class);
	
	// 조인포인트 설정
	// getData -> 메서드, 조인포인트 / 풀네이밍을 써줘야 함, 매개변수까지 지정(Model, HttpSession)
	// * : 여러개 지정, 매칭 (공통된 값들) / 모든 문자열에 대응
	// @PointCut(value="execution(public String com.myhome.web.board.controller.BoardController.getData*(org.springframework.ui.Model, javax.servlet.http.HttpSession)") // execution() : 포인트 컷 식
	// 접근 제한자는 생략 가능
	@Pointcut(value="execution(* com.myhome.web.*.controller.*Controller.*(..))") // execution() : 포인트 컷 식
	private void loggingControllerCut() {}
	
	@Pointcut(value="execution(* com.myhome.web.*.service.*Service.*(..))") // execution() : 포인트 컷 식
	private void loggingServiceCut() {}
	
	@Pointcut(value="execution(* com.myhome.web.*.model.*DAO.*(..))") // execution() : 포인트 컷 식
	private void loggingDaoCut() {}
	
	@Pointcut(value="loggingControllerCut() || loggingServiceCut() || loggingDaoCut()")
	private void loggingMvcCut() {}
	
	// 공통 기능(어드바이스) 구현
	@Before(value="loggingMvcCut()") // 어드바이스 언제 실행하게 할것인지 지정 (지정한 거 실행되기 전에 실행)
	public void beforeLogging(JoinPoint joinPoint) throws Exception {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");

		logger.info("[{}] BEFORE {} {}", dateFormat.format(date), joinPoint.getThis().toString(), joinPoint.getSignature().getName());
//		logger.info("joinPoint:{}", joinPoint);
		
		for(Object arg: joinPoint.getArgs()) {
			logger.info("	{}:{}", arg.getClass().getName(), arg.toString());
		}
		
//		logger.info("joinPoint.getTarget():{}", joinPoint.getTarget());
//		logger.info("joinPoint.getKind():{}", joinPoint.getKing());
//		logger.info("joinPoint.getThis():{}", joinPoint.getThis());
//		logger.info("joinPoint.getSignature():{}", joinPoint.getSignature().getName());
	}
	
	// 컨트롤러가 완전히 끝나고 나옴
	@After(value="loggingMvcCut()") // 어드바이스 언제 실행하게 할것인지 지정 (지정한 거 실행되기 전에 실행)
	public void aftweLogging(JoinPoint joinPoint) throws Exception {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");

		logger.info("[{}] AFTER {} {}", dateFormat.format(date), joinPoint.getThis().toString(), joinPoint.getSignature().getName());
		
		for(Object arg: joinPoint.getArgs()) {
			logger.info("	{}:{}", arg.getClass().getName(), arg.toString());
		}
		
	}
}