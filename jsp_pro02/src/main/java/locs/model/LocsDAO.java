package locs.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import conn.db.DBConn;
import locs.model.LocsDTO;

public class LocsDAO {
	
	SqlSession session = null;
	
	public LocsDAO() {
		session = DBConn.getSqlSession();
	}
	
	public List<LocsDTO> searchAll() {
		List<LocsDTO> datas = session.selectList("locsMapper.locsSelectAll");
		return datas;
	}
	
	public LocsDTO searchId(int id) {
		LocsDTO data = session.selectOne("locsMapper.locsSelectId", id);
		return data;
	}
}
