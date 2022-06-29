package dept.model;

import java.util.List;

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
	
	public  DeptDTO searchId(int id) {
		DeptDTO data = session.selectOne("deptMapper.deptSelectId", id);
		return data;
	}
}
