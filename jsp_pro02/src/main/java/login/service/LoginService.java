package login.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import emps.model.EmpDTO;
import login.model.LoginDAO;
import login.model.PermDTO;

public class LoginService {

	public boolean login(HttpSession session, String empId, String deptId, String empName) {
		LoginDAO dao = new LoginDAO();
		String fullName[] = empName.split(" ");
		
		Map<String, Object> loginMap = new HashMap<String, Object>();
		loginMap.put("empId", empId);
		loginMap.put("deptId", deptId);
		
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
			Map<String, PermDTO> permData = new HashMap<String, PermDTO>();
			for(PermDTO perm: dao.selectPermission(data.getEmpId())) {
				permData.put(perm.getTableName(), perm);
			}
			
			session.setAttribute("permData", permData);
			session.setAttribute("loginData", data);
			return true;
		}
	}
	
}
