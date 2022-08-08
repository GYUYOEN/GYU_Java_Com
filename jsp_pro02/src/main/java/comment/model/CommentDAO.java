package comment.model;

import java.util.List;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import common.util.Paging;
import conn.db.DBConn;

public class CommentDAO {

	private SqlSession session = DBConn.getSqlSession();
			
	public boolean insertData(CommentDTO data) {
		int res = session.insert("commentMapper.insertData", data);
		return res == 1 ? true : false;
	}
	
	public List<CommentDTO> selectDatas(int bid) {
		List<CommentDTO> res = session.selectList("commentMapper.selectDatas", bid);
		return res;
	}

	public CommentDTO selectData(int id) {
		CommentDTO res = session.selectOne("commentMapper.selectData", id);
		return res;
	}

	public boolean deleteData(CommentDTO data) {
		int res = session.update("commentMapper.deleteData", data);
		return res == 1 ? true : false;
	}
	
	public boolean updateData(CommentDTO data) {
		int res = session.update("commentMapper.updateData", data);
		return res == 1 ? true : false;
	}
	
	public int getTotalRows(int bid) {
		int result = session.selectOne("commentMapper.getTotalRows", bid);
		return result;
	}

	public void selectPage(Paging paging, int bid) {
		RowBounds rb = new RowBounds(paging.getOffset(), paging.getLimit());
		Cursor<Object> cursor = session.selectCursor("commentMapper.selectDatas", bid, rb);
		paging.setPageDatas(cursor.iterator());
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
