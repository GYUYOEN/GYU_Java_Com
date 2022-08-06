package login.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import emps.model.EmpDTO;
import login.model.LoginDAO;

public class LoginService {

	public boolean login(HttpSession session, String empId, String deptId, String empName) {
		LoginDAO dao = new LoginDAO();
		String fullName[] = empName.split(" ");
		
		Map<String, Object> loginMap = new HashMap<String, Object>();
		loginMap.put("empId", empId);
		loginMap.put("deptId", deptId);
		
		// 이름을 공백없이 붙여서 하는 경우 생각(StevenKing)
		if(fullName.length == 2) {
			loginMap.put("firstName", fullName[0]);
			loginMap.put("lastName", fullName[1]);
		} else {
			loginMap.put("firstName", "");
			loginMap.put("lastName", "");
		}
		
		EmpDTO data = dao.selectEmployee(loginMap);
		
		dao.close();
		if(data == null) {
			return false;
		} else {
			session.setAttribute("loginData", data);
			return true;
		}
	}

}
