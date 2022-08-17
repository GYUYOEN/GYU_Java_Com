package com.myhome.web.emp.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmpDAO {
	
	@Autowired
	private SqlSession session;

	private String mapper = "empMapper.%s";
	
	public EmpDTO selectId(int id) {
		String mapId = String.format(mapper, "selectId");
		EmpDTO data = session.selectOne(mapId, id);
		return data;
	}
	
	public List<EmpDTO> selectAll() {
		String mapId = String.format(mapper, "selectAll");
		List<EmpDTO> datas = session.selectList(mapId);
		return datas;
	}

	public List<EmpDTO> selectPage(EmpDTO data, int offset, int count) {
		String mapId = String.format(mapper, "selectAll");
		RowBounds rb = new RowBounds(offset, count);
		
		Cursor<EmpDTO> cursor = session.selectCursor(mapId, data, rb);
		
		List<EmpDTO> datas = new ArrayList<EmpDTO>();
		Iterator<EmpDTO> iter = cursor.iterator();
		while(iter.hasNext()) {
			datas.add(iter.next());
		}
		
		return datas;
	}

	public int totalRow(EmpDTO data) {
		String mapId = String.format(mapper, "totalRow");
		int rowCount = session.selectOne(mapId, data);
		return rowCount;
	}

	public EmpDetailDTO selectDetail(int empId) {
		String mapId = String.format(mapper, "selectDetail");
		EmpDetailDTO data = session.selectOne(mapId, empId);
		return data;
	}
	
	public boolean updateEmployee(EmpDTO updateEmpData) {
		String mapId = String.format(mapper, "updateEmployee");
		int result = session.update(mapId, updateEmpData);
		return result == 1 ? true : false;
	}
	
	public boolean updateEmployeeDetail(EmpDetailDTO updateEmpDetailData) {
		String mapId = String.format(mapper, "updateEmployeeDetail");
		int result = session.update(mapId, updateEmpDetailData);
		return result == 1 ? true : false;
	}

	public boolean insertEmployee(EmpDTO empData) {
		String mapId = String.format(mapper, "insertEmployee");
		int result = session.insert(mapId, empData);
		return result == 1 ? true : false;
	}

	public boolean deleteId(int id) {
		String mapId = String.format(mapper, "deleteId");
		int result = session.delete(mapId, id);
		return result == 1 ? true : false;
	}

}
