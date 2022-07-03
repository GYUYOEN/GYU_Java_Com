package dept.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dept.model.DeptDAO;
import dept.model.DeptDTO;

// 사용자에게 제공할 서비스 구현 (로직)
public class DeptService {
	
	private DeptDAO dao;
	
	public List<DeptDTO> getAll() {
		dao = new DeptDAO();
		
		List<DeptDTO> datas = dao.searchAll();
		
		dao.close();
		return datas;
	}
	
	public List<DeptDTO> getPage(int pageNumber) {
		int start, end;
		start = (pageNumber - 1) * 10;
		end = 10;
		dao = new DeptDAO();
		List<DeptDTO> datas = dao.searchPage(start, end);
		dao.close();
		return datas;
	}
	
	/*
	public List<DeptDTO> getPage(int pageNumber) {
		Map<String, Integer> page = new HashMap<String, Integer>();
		page.put("start", (pageNumber - 1) * 10 + 1);
		page.put("end", (pageNumber - 1) * 10 + 10);
		
		dao = new DeptDAO();
		List<DeptDTO> datas = dao.searchPage(page);
		dao.close();
		
		return datas;
	}
	*/
	
	public List<Integer> getPageList() {
		dao = new DeptDAO();
		
		List<Integer> pageList = new ArrayList<Integer>();
		int total = dao.totalRow();
		
		for(int num = 0; num <= (total - 1) / 10; num++) {
			pageList.add(num + 1);
		}
		
		return pageList;
	}
	
	public DeptDTO getId(String id) {
//		int deptId = Integer.parseInt(id);
//		DeptDTO data = dao.searchId(deptId);
		return _getId(Integer.parseInt(id));
	}
	
	public DeptDTO getId(int id) {
//		DeptDTO data = dao.searchId(id);
		return _getId(id);
	}

	public DeptDTO getId(DeptDTO deptDto) {
//		DeptDTO data = dao.searchId(deptDto.getDeptId());
		return _getId(deptDto.getDeptId());
	}
	
	// 실질적인 로직
	private DeptDTO _getId(int id) {
		dao = new DeptDAO();
		DeptDTO data = dao.searchId(id);
		dao.close();
		return data;
	}

	public DeptDTO addDept(String deptId, String deptName, String mngId, String locId) {
		dao = new DeptDAO();
		DeptDTO deptDto = null;
		if(deptId.matches("\\d+") && mngId.matches("\\d+") && locId.matches("\\d+")) {
			deptDto = new DeptDTO();
			// 전달 받은 데이터 DeptDTO에 담아서 전달
			deptDto.setDeptId(Integer.parseInt(deptId));
			deptDto.setDeptName(deptName);
			deptDto.setMngId(Integer.parseInt(mngId));
			deptDto.setLocId(Integer.parseInt(locId));
			
			//SELECT * FROM DEPARTMENTS WHERE DEPARTMENT_ID = #{id} 로 무결성 제약조건 확인
			if(dao.searchId(deptDto.getDeptId()) != null) { // DEPARTMENT_ID 가 존재함으로 해당 ID를 넣을 경우 무결성 제약조건에 위배됨 -> ID 중복
				deptDto.setDeptId(-1);
				dao.close();
				return deptDto;
			}
			
			if(!dao.existManager(deptDto.getMngId())) {
				deptDto.setMngId(-1);
				dao.close();
				return deptDto;
			}
			
			if(!dao.existLocation(deptDto.getLocId())) {
				deptDto.setLocId(-1);
				dao.close();
				return deptDto;
			}
			
			boolean isSaved = dao.insertDept(deptDto);
			if(!isSaved) {
				dao.close();
				return null;
			}
		} 
		dao.close();
		return deptDto;
	}

	public int modifyDept(DeptDTO data) {
		dao = new DeptDAO();
		
		if(!dao.existManager(data.getMngId())) {
			dao.close();
			return -1;
		}
		
		if(!dao.existLocation(data.getLocId())) {
			dao.close();
			return -2;
		}
		
		boolean isSaved = dao.updateDept(data);
		dao.close();
		if(isSaved) {
			return 1;
		}
		return 0;
	}

	public int deleteDept(String id) {
		dao = new DeptDAO();
		if(dao.searchId(Integer.parseInt(id)) == null) {
			dao.close();
			return -1; // 삭제 대상이 없음을 알림
		}
		
		boolean result = dao.deleteDept(Integer.parseInt(id));
		dao.close();
		if(result) {
			return 1;
		}
		return 0;
	}
}
