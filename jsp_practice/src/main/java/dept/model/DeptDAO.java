package dept.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import conn.db.DBConn;

public class DeptDAO {
	SqlSession session = null;
	
	public DeptDAO() {
		session = DBConn.getSqlSession();
	}
	
	public List<DeptDTO> searchAll() {
		List<DeptDTO> datas = session.selectList("deptMapper.deptSelectAll");
		return datas;
	}
	
	// Map을 이용한 방법
	public List<DeptDTO> searchPage(Map<String, Integer>page) {
		List<DeptDTO> datas = session.selectList("deptMapper.deptSelectPage", page);
		return datas;
	}
	
	// cursor를 이용한 방법
	public List<DeptDTO> searchPage(int start, int end) {
		RowBounds rb = new RowBounds(start, end);
		Cursor<DeptDTO> cursor = session.selectCursor("deptMapper.deptSelectAll", null, rb);
		
		List<DeptDTO> datas = new ArrayList<DeptDTO>();
		Iterator<DeptDTO> iter = cursor.iterator();
		while(iter.hasNext()) {
			datas.add(iter.next());
		}
		
		return datas;
	}
	
	public int totalRow() {
		int rowCount = session.selectOne("deptMapper.deptTotalRow");
		return rowCount;
	}

	public DeptDTO searchId(int id) {
		DeptDTO data = session.selectOne("deptMapper.deptSelectId", id);
		return data;
	}

	public boolean insertDept(DeptDTO data) {
		int result = session.insert("deptMapper.deptInsert", data);
		if(result == 1) {
			return true;
		} 
		return false;
		
	}

	public boolean existManager(int id) {
		int result = session.selectOne("deptMapper.existManager", id);
		if(result == 1) {
			return true;
		}
		return false;
	}

	public boolean existLocation(int id) {
		int result = session.selectOne("deptMapper.existLocation", id);
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean updateDept(DeptDTO data) {
		int result = session.update("deptMapper.deptUpdate", data);
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean deleteDept(int id) {
		int result = session.delete("deptMapper.deptDelete", id);
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	public void commit() {
		session.commit();
	}
	
	public void rollback() {
		session.rollback();
	}
	
	public void close() {
		session.close();
	}


}