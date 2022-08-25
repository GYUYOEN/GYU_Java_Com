package com.myhome.web.aop;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.myhome.web.emp.model.EmpDTO;
import com.myhome.web.exception.ForbiddenException;
import com.myhome.web.exception.UnauthorizedException;
import com.myhome.web.login.model.PermDTO;
import com.myhome.web.login.model.PermissionDAO;

@Component
@Aspect
public class PermissionAOP {
	
	@Autowired
	private PermissionDAO dao;

	// 이름 통일 해줘야 함
	@Pointcut(value="execution(public * com.myhome.web.*.service.*Service.get*(javax.servlet.http.HttpSession, ..))")
	private void permSelectCut() {}
	
	@Pointcut(value="execution(public * com.myhome.web.*.service.*Service.add*(javax.servlet.http.HttpSession, ..))")
	private void permInsertCut() {}
	
	@Pointcut(value="execution(public * com.myhome.web.*.service.*Service.modify*(javax.servlet.http.HttpSession, ..))")
	private void permUpdateCut() {}
	
	@Pointcut(value="execution(public * com.myhome.web.*.service.*Service.remove*(javax.servlet.http.HttpSession, ..))")
	private void permDeleteCut() {}
	
	private PermDTO getPermission(JoinPoint joinPoint) {
		HttpSession session = (HttpSession)joinPoint.getArgs()[0];
		EmpDTO empData = (EmpDTO)session.getAttribute("loginData");
		
		String name = joinPoint.getSignature().toShortString().split("\\.")[0];
		name = name.split("Service")[0].toLowerCase();
		
		PermDTO data = new PermDTO();
		data.setEmpId(empData.getEmpId());
		data.setTableName(name);
		
		boolean result = dao.selectData(data);
		System.out.println(data);
		if(result) {
			return data;
		} else {
			return null;
		}
	}
	
	@Before(value="permSelectCut()")
	public void beforePermSelect(JoinPoint joinPoint) throws Exception {
		// HttpServletResponse resp = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getResponse();
		PermDTO perm = getPermission(joinPoint);
		if(perm != null) {
			if(!perm.ispRead()) {
				throw new PermissionDeniedDataAccessException("읽기 권한이 없습니다.", null);
			}
		} else {
			throw new PermissionDeniedDataAccessException("로그인 정보가 없습니다.", null);
		}
	}
	
	@Before(value="permInsertCut()")
	public void beforePermInsert(JoinPoint joinPoint) throws Exception {
		PermDTO perm = getPermission(joinPoint);
		if(perm != null) {
			if(!perm.ispAdd()) {
				throw new PermissionDeniedDataAccessException("쓰기 권한이 없습니다.", null);
			}
		} else {
			throw new PermissionDeniedDataAccessException("로그인 정보가 없습니다.", null);
		}
	}
	
	@Before(value="permUpdateCut()")
	public void beforePermUpdate(JoinPoint joinPoint) throws Exception {
		PermDTO perm = getPermission(joinPoint);
		if(perm != null) {
			if(!perm.ispUpdate()) {
				throw new PermissionDeniedDataAccessException("수정 권한이 없습니다.", null);
			}
		} else {
			throw new PermissionDeniedDataAccessException("로그인 정보가 없습니다.", null);
		}
	}
	
	@Before(value="permDeleteCut()")
	public void beforePermDelete(JoinPoint joinPoint) throws Exception {
		PermDTO perm = getPermission(joinPoint);
		if(perm != null) {
			if(!perm.ispDelete()) {
				throw new PermissionDeniedDataAccessException("삭제 권한이 없습니다.", null);
			}
		} else {
			throw new PermissionDeniedDataAccessException("로그인 정보가 없습니다.", null);
		}
	}
}
