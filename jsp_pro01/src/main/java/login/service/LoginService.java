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
		
 		Map<String, Object> loginMap = new HashMap<String, Object>(); // 여러개 값을 하나로 만들어 보내주기 위해 map 사용
		loginMap.put("empId", empId);
		loginMap.put("deptId", deptId);
		
		if(fullName.length == 2) { // Steven King 인경우
			loginMap.put("firstName", empName.split(" ")[0]);
			loginMap.put("lastName", empName.split(" ")[1]);
		} else { // StevenKing 인경우
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
			System.out.println(permData);
			session.setAttribute("permData", permData);
			session.setAttribute("loginData", data);
			return true;
		}
	}

}
