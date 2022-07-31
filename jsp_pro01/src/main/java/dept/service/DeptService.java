package dept.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dept.model.DeptDAO;
import dept.model.DeptDTO;

// 사용자에게 제공할 서비스 구현 (로직)
public class DeptService {
	
	public List<DeptDTO> getAll() {
		DeptDAO dao = new DeptDAO();
		
		List<DeptDTO> datas = dao.searchAll();
		
		dao.close();
		return datas;
	}
	
	public List<DeptDTO> getPage(int page, int pageCount) {
		int pageNumber = page;
		int start, end;
		start = (pageNumber - 1) * pageCount;
		end = pageCount;
		DeptDAO dao = new DeptDAO();
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
	
	public List<Integer> getPageList(int pageCount) {
		DeptDAO dao = new DeptDAO();
		
		List<Integer> pageList = new ArrayList<Integer>();
		int total = dao.totalRow(); // 전체행수를 알아냄
		
		/*
		 * <int num = 1 이고 num <= (total) / 10 일때>
		 * 		25 / 10 -> 2.5 -> 2 : 나머지 5 가지 목록도 나와야하므로 총 3페이지가 되어야함
		 * 		30 / 10 -> 3 
		 * =>
		 * <int num = 0 이고 num <= (total - 1) / 10 일때>
		 * 		(25 - 1) / 10 -> 2.4 -> 2
		 * 		(30 - 1) / 10 -> 2.9 -> 2
		 * 		0부터 시작함으로 0 1 2 -> 1 2 3
		 */
		
		for(int num = 0; num <= (total - 1) / pageCount; num++) { // 알아낸 전체행수로 페이지 갯수가 나오도록 계산함 
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
		DeptDAO dao = new DeptDAO();
		DeptDTO data = dao.searchId(id);
		dao.close();
		return data;
	}

	public DeptDTO addDept(String deptId, String deptName, String mngId, String locId) {
		DeptDAO dao = new DeptDAO();
		DeptDTO deptDto = null;
		if(deptId.matches("\\d+") && mngId.matches("\\d+") && locId.matches("\\d+")) {
			boolean isValid = true;
			deptDto = new DeptDTO();
			// 전달 받은 데이터 DeptDTO에 담아서 전달
			deptDto.setDeptId(Integer.parseInt(deptId));
			deptDto.setDeptName(deptName);
			deptDto.setMngId(Integer.parseInt(mngId));
			deptDto.setLocId(Integer.parseInt(locId));
			
			//SELECT * FROM DEPARTMENTS WHERE DEPARTMENT_ID = #{id} 로 무결성 제약조건 확인
			if(dao.searchId(deptDto.getDeptId()) != null) { // DEPARTMENT_ID 가 존재함으로 해당 ID를 넣을 경우 무결성 제약조건에 위배됨 -> ID 중복
				deptDto.setDeptId(-1);
				isValid = false;
//				dao.rollback();
//				dao.close();
//				return deptDto;
			}
			
			if(!dao.existManager(deptDto.getMngId())) {
				deptDto.setMngId(-1);
				isValid = false;
//				dao.rollback();
//				dao.close();
//				return deptDto;
			}
			
			if(!dao.existLocation(deptDto.getLocId())) {
				deptDto.setLocId(-1);
				isValid = false;
//				dao.rollback();
//				dao.close();
//				return deptDto;
			}
			
			if(isValid) {
				boolean isSaved = dao.insertDept(deptDto);
				if(isSaved) {
					dao.commit();
					dao.close();
					return deptDto;
				} else {
					dao.rollback();
					dao.close();
					return null;
				}
			}
			
//			boolean isSaved = dao.insertDept(deptDto);
//			
//			if(!isSaved) {
//				dao.rollback();
//				dao.close();
//				return null;
//			}
		} 
		
		dao.commit();
		dao.close();
		return deptDto;
	}

	public int modifyDept(DeptDTO data) {
		DeptDAO dao = new DeptDAO();
		
		if(!dao.existManager(data.getMngId())) {
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
		DeptDAO dao = new DeptDAO();
		if(dao.searchId(Integer.parseInt(id)) == null) {
			dao.rollback();
			dao.close();
			return -1; // 삭제 대상이 없음을 알림
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

	public boolean existsManager(String value) {
		DeptDAO dao = new DeptDAO();
		boolean result = dao.selectManager(Integer.parseInt(value));
		dao.close();
		return result;
	}

	public boolean existsLocation(String value) {
		DeptDAO dao = new DeptDAO();
		boolean result = dao.selectLocation(Integer.parseInt(value));
		dao.close();
		return result;
	}
}
