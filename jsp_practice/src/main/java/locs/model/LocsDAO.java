package locs.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import conn.db.DBConn;

public class LocsDAO {
	SqlSession session = null;
	
	public LocsDAO() {
		session = DBConn.getSqlSession();
	}

	public List<LocsDTO> getSelectAll() {
		List<LocsDTO> data = session.selectList("locsMapper.locsSelectAll");
		return data;
	}

	public LocsDTO getSelectId(int id) {
		LocsDTO data = session.selectOne("locsMapper.locsSelectId", id);
		return data;
	}

}
