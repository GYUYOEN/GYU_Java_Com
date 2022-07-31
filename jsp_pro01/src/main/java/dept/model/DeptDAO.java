package dept.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import conn.db.DBConn;

// 데이터 조회만 신경씀
// DBConn 에 main 에서 사용했던 로직이 여기서 그대로 쓰임
public class DeptDAO {
	
	SqlSession session = null;
	
	public DeptDAO() {
		session = DBConn.getSqlSession();
	}
	
	public List<DeptDTO> searchAll() {
		List<DeptDTO> datas = session.selectList("deptMapper.deptSelectAll");
		return datas;
	}
	
	public List<DeptDTO> searchPage(Map<String, Integer> page) {
		List<DeptDTO> datas = session.selectList("deptMapper.deptSelectPage", page);
		return datas;
	}
	
	public List<DeptDTO> searchPage(int start, int end) {
		// start : 데이터를 가져오는 시작점에서 얼마나 떨어진 데이터인지
		// end : 몇 개의 값을 가져올 것 인지 
		RowBounds rb = new RowBounds(start, end);
		
		// null : 넘길 파라미터
		Cursor<DeptDTO> cursor = session.selectCursor("deptMapper.deptSelectAll", null, rb); // selectCursor : mybatis에 있는 메서드
		
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
	
	public  DeptDTO searchId(int id) {
		DeptDTO data = session.selectOne("deptMapper.deptSelectId", id);
		return data;
	}

	public boolean insertDept(DeptDTO deptDto) {
		int result = session.insert("deptMapper.deptInsert", deptDto);
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

	public boolean updateDept(DeptDTO deptDto) {
		int result = session.update("deptMapper.deptUpdate", deptDto);
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

	public boolean selectManager(int id) {
		int result = session.selectOne("deptMapper.existsManager", id);
		if(result >= 1) {
			return true;
		}
		return false;
	}

	public boolean selectLocation(int id) {
		int result = session.selectOne("deptMapper.existsLocation", id);
		if(result >= 1) {
			return true;
		}
		return false;
	}
}
