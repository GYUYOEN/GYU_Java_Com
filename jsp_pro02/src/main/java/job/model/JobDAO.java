package job.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import common.util.AbstractDAO;
import conn.db.DBConn;

public class JobDAO extends AbstractDAO {
	private SqlSession session = DBConn.getSqlSession();
	private String mapper = "jobMapper.%s";
	
	public List<JobDTO> selectAll() {
		String mapId = String.format(mapper, "selectAll");
		List<JobDTO> datas = session.selectList(mapId);
		return datas;
	}

}
