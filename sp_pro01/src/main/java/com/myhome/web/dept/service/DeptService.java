package com.myhome.web.dept.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myhome.web.dept.model.DeptDAO;
import com.myhome.web.dept.model.DeptDTO;

@Service
public class DeptService {
	
	@Autowired
	private DeptDAO dao;
	
	public List<DeptDTO> getAll() {
		List<DeptDTO> datas = dao.searchAll();
		
		return datas;
	}
	
	public List<DeptDTO> getPage(int page, int pageCount) {
		int pageNumber = page;
		int start, end;
		start = (pageNumber - 1) * pageCount;
		end = pageCount;
		List<DeptDTO> datas = dao.searchPage(start, end);
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
	
	public List<Integer> getPageList(int pageCount) {
		List<Integer> pageList = new ArrayList<Integer>();
		int total = dao.totalRow();
		
		for(int num = 0; num <= (total - 1) / pageCount; num++) {
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
		DeptDTO data = dao.searchId(id);
		
		return data;
	}

	public DeptDTO addDept(String deptId, String deptName, String mngId, String locId) {
		DeptDTO deptDto = null;
		if(deptId.matches("\\d+") && mngId.matches("\\d+") && locId.matches("\\d+")) {
			boolean isValid = true;
			deptDto = new DeptDTO();
			deptDto.setDeptId(Integer.parseInt(deptId));
			deptDto.setDeptName(deptName);
			deptDto.setMngId(Integer.parseInt(mngId));
			deptDto.setLocId(Integer.parseInt(locId));
			
			if(dao.searchId(deptDto.getDeptId()) != null) {
				deptDto.setDeptId(-1);
				isValid = false;
			}
			
			if(!dao.existManager(deptDto.getMngId())) {
				deptDto.setMngId(-1);
				isValid = false;
			}
			
			if(!dao.existLocation(deptDto.getLocId())) {
				deptDto.setLocId(-1);
				isValid = false;
			}
			
			if(isValid) {
				boolean isSaved = dao.insertDept(deptDto);
				if(isSaved) {
					dao.commit();
					return deptDto;
				} else {
					dao.rollback();
					return null;
				}
			}
		}
		dao.rollback();
		return deptDto;
	}

	public int modifyDept(DeptDTO data) {
		if(!dao.existManager(data.getMngId())) {
			dao.rollback();
			return -1;
		}
		
		if(!dao.existLocation(data.getLocId())) {
			dao.rollback();
			return -2;
		}
		
		boolean isSaved = dao.updateDept(data);
		
		if(isSaved) {
			dao.commit();
			return 1;
		}
		
		dao.rollback();
		return 0;
	}

	public int deleteDept(String id) {
		if(dao.searchId(Integer.parseInt(id)) == null) {
			dao.rollback();
			return -1;	// 삭제 대상이 없음을 알림
		}
		
		boolean result = dao.deleteDept(Integer.parseInt(id));
		if(result) {
			dao.commit();
			return 1;
		}
		dao.rollback();
		return 0;
	}

	public boolean existsManager(String value) {
		boolean result = dao.selectManager(Integer.parseInt(value));
		return result;
	}

	public boolean existsLocation(String value) {
		boolean result = dao.selectLocation(Integer.parseInt(value));
		return result;
	}
}
