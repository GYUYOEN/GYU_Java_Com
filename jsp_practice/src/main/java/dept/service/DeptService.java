package dept.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dept.model.DeptDAO;
import dept.model.DeptDTO;

public class DeptService {
	private DeptDAO dao;

	public List<DeptDTO> getAll() {
		dao = new DeptDAO();
		
		List<DeptDTO> datas = dao.searchAll();
		
		dao.close();
		return datas;
	}
	/*
	// Map을 이용한 방법
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
	
	// cursor를 이용한 방법
	public List<DeptDTO> getPage(int page, int pageCount) {
		int start, end;
		start = (page - 1) * pageCount;
		end = pageCount;
		dao = new DeptDAO();
		List<DeptDTO> datas = dao.searchPage(start, end);
		dao.close();
		return datas;
	}
	
	public List<Integer> getPageList(int pageCount) {
		dao = new DeptDAO();
		
		List<Integer> pageList = new ArrayList<Integer>();
		int total = dao.totalRow();
		
		for(int num = 0; num <= (total - 1)/pageCount; num++) {
			pageList.add(num + 1);
		}
		return pageList;
	}

	public DeptDTO getId(String id) {
		return _getId(Integer.parseInt(id));
	}
	
	public DeptDTO getId(int id) {
		return _getId(id);
	}
	
	public DeptDTO getId(DeptDTO deptDto) {
		return _getId(deptDto.getDeptId());
	}
	
	private DeptDTO _getId(int id) {
		dao = new DeptDAO();
		DeptDTO data = dao.searchId(id);
		dao.close();
		return data;
	}

	public DeptDTO addDept(String deptId, String deptName, String mngId, String locId) {
		dao = new DeptDAO();
		DeptDTO data = null;
		if(deptId.matches("\\d+") && mngId.matches("\\d+") && locId.matches("\\d+")) {
			boolean isValid = true;
			data = new DeptDTO();
			data.setDeptId(Integer.parseInt(deptId));
			data.setDeptName(deptName);
			data.setMngId(Integer.parseInt(mngId));
			data.setLocId(Integer.parseInt(locId));
			
			if(dao.searchId(data.getDeptId()) != null) { // deptId 가 없으면 넣어라
				data.setDeptId(-1);
				isValid = false;
			}
			
			if(!dao.existManager(data.getMngId())) { // 있어야 하는 거 이므로
				data.setMngId(-1);
				isValid = false;
			} 
			
			if(!dao.existLocation(data.getLocId())) { 
				data.setLocId(-1);
				isValid = false;
			}
			
			if(isValid) {
				boolean isSaved = dao.insertDept(data);
				if(isSaved) {
					dao.commit();
					dao.close();
					return data;
				} else {
					dao.rollback();
					dao.close();
					return null;
				}
			}
		}
		dao.commit();
		dao.close();
		return data;
	
	}
		

	public int modifyDept(DeptDTO data) {
		dao = new DeptDAO();
		
		if(!dao.existManager(data.getMngId())) { // 있어야 하는 거 이므로
			dao.rollback();
			dao.close();
			return -1;
		} 
		
		if(!dao.existLocation(data.getLocId())) { 
			dao.rollback();
			dao.close();
			return -2;
		}
		
		boolean isSaved = dao.updateDept(data);
		if(isSaved) {
			dao.commit();
			dao.close();
			return 1;
		}
		dao.rollback();
		dao.close();
		return 0;
	}

	public int deleteDept(String id) {
		dao = new DeptDAO();
		
		if(dao.searchId(Integer.parseInt(id)) == null) {
			dao.rollback();
			dao.close();
			return -1; // 삭제 대상 없음
		}
		
		boolean result = dao.deleteDept(Integer.parseInt(id));
		if(result) {
			dao.commit();
			dao.close();
			return 1;
		}
		dao.rollback();
		dao.close();
		return 0;
	}
	

}
