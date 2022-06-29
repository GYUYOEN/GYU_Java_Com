package dept.service;

import java.util.List;

import dept.model.DeptDAO;
import dept.model.DeptDTO;

public class DeptService {
	
	private DeptDAO dao;
	
	public DeptService() {
		dao = new DeptDAO(); 
	}

	public List<DeptDTO> getAll() {
		List<DeptDTO> datas = dao.searchAll();
		return datas;
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
	
	private DeptDTO _getId(int id) {
		DeptDTO data = dao.searchId(id);
		return data;
	}
}
