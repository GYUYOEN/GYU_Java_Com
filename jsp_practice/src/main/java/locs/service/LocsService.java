package locs.service;

import java.util.List;

import locs.model.LocsDAO;
import locs.model.LocsDTO;

public class LocsService {
	private LocsDAO dao;
	
	public LocsService() {
		dao = new LocsDAO();
	}
	
	public List<LocsDTO> getLocsAll() {
		List<LocsDTO> datas = dao.getSelectAll();
		return datas;
	}

	public LocsDTO getId(String id) {
		return _getId(Integer.parseInt(id));
	}
	
	public LocsDTO getId(int id) {
		return _getId(id);
	}
	
	public LocsDTO getId(LocsDTO locsDto) {
		return _getId(locsDto.getLocId());
	}
	
	private LocsDTO _getId(int id) {
		LocsDTO data = dao.getSelectId(id);
		return data;
	}
}
