package com.join.controller;

import com.join.dao.JoinDAO;
import com.join.vo.JoinVo;

/*
 * 회원가입 처리를 위한 중간 제어용 객체로 활용
 * 중간 처리/조직 필터링 검사
 */
public class JoinController {
	
	private JoinDAO dao = new JoinDAO();

	public boolean join(JoinVo data) {
		// data에 이미 모든 정보 들어있음
		switch(data.getGender()) {
			case '남' :
				data.setGender('M'); break;
			case '여' :
				data.setGender('F'); break;
			default :
				return false;
		}
		
		if(data.getAge() < 15) {
			return false;
		}
		
		// userid 조회 후 account 에 저장
		JoinVo account = dao.get(data.getUserid());
		
		// 
		if(account != null) {
			return false;
		}
		
		// 가입이 되었는지 안되었는지 알려줌
		boolean result = dao.register(data);
		return result;
	}

	public boolean update(JoinVo data) {
		/* 회원정보 수정
		* 반환 타입과 매개변수는 회원 정보 수정에 
		* 필요한 타입으로 만들어 본다.
		*/
		return dao.update(data);
	}
	
	public boolean remove(JoinVo data) {
		/* 회원정보 탈퇴
		* 반환 타입과 매개변수는 회원 정보 수정에 
		* 필요한 타입으로 만들어 본다.
		*/
		return dao.remove(data);
	}
	
	public JoinVo login(String userid, String userpw) {
		// 사용자가 입력한 userid가 있는지 확인
		JoinVo data = dao.get(userid);
		
		// 패스워드가 일치하는지 확인
		if(data.getUserpw().equals(userpw)) {
			// 참이면 데이터 넘겨줌
			return data;
		}
		// 아니면 NULL로
		return null;
	}

}
