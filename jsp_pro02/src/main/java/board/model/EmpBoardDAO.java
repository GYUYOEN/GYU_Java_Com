package board.model;

import java.util.*;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import common.util.Paging;
import conn.db.DBConn;

public class EmpBoardDAO {
	
	private SqlSession session;
	
	public EmpBoardDAO() {
		this.session = DBConn.getSqlSession();
	}
	
	public int getNextSeq() {
		int result = session.selectOne("empBoardsMapper.getNextSeq");
		return result;
	}
	
	public boolean insertData(EmpBoardDTO data) {
		int result = 0;
		if(data.getId() == 0) {
			result = session.insert("empBoardsMapper.insertDataAutoSeq", data);			
		} else {
			result = session.insert("empBoardsMapper.insertData", data);
		}
		return result == 1 ? true : false;
	}
	
	public EmpBoardDTO selectData(int id) {
		EmpBoardDTO result = session.selectOne("empBoardsMapper.selectData", id);
		return result;
	}
	
	public boolean updateViewCnt(EmpBoardDTO data) {
		int result = session.update("empBoardsMapper.updateViewCnt", data);
		return result == 1 ? true : false;
	}
	
	public boolean updateLike(EmpBoardDTO data) {
		int result = session.update("empBoardsMapper.updateLike", data);
		return result == 1 ? true : false;
	}
	
	public EmpBoardStatisDTO selectStatis(EmpBoardStatisDTO data) {
		EmpBoardStatisDTO result = session.selectOne("empBoardsMapper.selectStatis", data);
		return result;
	}
	
	public boolean insertStatis(EmpBoardStatisDTO data) {
		int result = session.insert("empBoardsMapper.insertStatis", data);
		return result == 1 ? true : false;
	}
	
	public boolean updateStatis(EmpBoardStatisDTO data) {
		int result = session.update("empBoardsMapper.updateStatis", data);
		return result == 1 ? true : false;
	}
	
	public boolean updateStatis(EmpBoardStatisDTO data, String type) {
		if(type.equals("like")) {
			int result = session.update("empBoardsMapper.updateLikeStatis", data);
			return result == 1 ? true : false;
		} else {
			return updateStatis(data);
		}
	}
	
	public int getTotalRows() {
		int result = session.selectOne("empBoardsMapper.getTotalRows");
		return result;
	}
	
	public int getTotalRows(String search) {
		int result = session.selectOne("empBoardsMapper.getTotalRows", search);
		return result;
	}
	
	public void selectPage(Paging paging) {
		RowBounds rb = new RowBounds(paging.getOffset(), paging.getLimit());
		Cursor<Object> cursor = session.selectCursor("empBoardsMapper.selectPage", null, rb);
		paging.setPageDatas(cursor.iterator());
	}
	
	public void selectPage(Paging paging, String search) {
		RowBounds rb = new RowBounds(paging.getOffset(), paging.getLimit());
		Cursor<Object> cursor = session.selectCursor("empBoardsMapper.selectPage", search, rb);
		paging.setPageDatas(cursor.iterator());
	}
	
	public boolean deleteData(EmpBoardDTO data) {
		int result = session.delete("empBoardsMapper.deleteData", data.getId());
		return result == 1 ? true : false;
	}
	
	public boolean deleteStatisData(EmpBoardDTO data) {
		int result = session.delete("empBoardsMapper.deleteStatisData", data.getId());
		return result >= 0 ? true : false;
	}
	
	public boolean updateData(EmpBoardDTO data) {
		int result = session.update("empBoardsMapper.updateData", data);
		return result == 1 ? true : false;
	}
	
	public void commit() {
		this.session.commit();
	}
	
	public void rollback() {
		this.session.rollback();
	}
	
	public void close() {
		this.session.close();
	}

}