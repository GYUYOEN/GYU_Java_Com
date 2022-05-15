package com.join.dao;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.conn.db.DBConn;
import com.join.vo.JoinVo;

/*
 * 회원가입 처리를 위해 데이터베이스에 직접 액세스 하기 위한
 * 객체로 활용
 * JoinDAO -> DBConn
 */
public class JoinDAO {
	private DBConn db;
	
	public JoinDAO() {
		try {
			db = new DBConn(new File(System.getProperty("user.home") + "/oracle_db.conf"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// DBConn -> Oracle Database : Query
	// DBConn <- Oracle Database : Resultset
	// 회원가입 처리를 담당
	public boolean register(JoinVo data) {
//		String query = String.format(
//				"INSERT INTO accounts VALUES('%s', '%s', '%s', '%c', %d, SYSDATE)"
//				, data.getUserid()
//				, data.getUserpw()
//				, data.getUsername()
//				, data.getGender()
//				, data.getAge());
		String query = "INSERT INTO accounts VALUES(?, ?, ?, ?, ?, SYSDATE)";
		try {
			PreparedStatement pstat = db.getPstat(query);
			pstat.setString(1, data.getUserid());
			pstat.setString(2, data.getUserpw());
			pstat.setString(3, data.getUsername());
			pstat.setString(4, Character.toString(data.getGender()));
			pstat.setInt(5, data.getAge());
			
			int rs = db.sendInsertQuery();
			if(rs == 1) {
				db.commit();
				return true;
			}
			db.rollback();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 회원정보 수정을 담당
	public boolean update(JoinVo data) {
//		String query = "UPDATE accounts"
//				+ "        SET USERPW = '" + data.getUserpw() + "'"
//				+ "          , USERNAME = '" + data.getUsername() + "'"
//				+ "          , GENDER = '" + data.getGender() + "'"
//				+ "          , AGE = " + data.getAge()
//				+ "      WHERE USERID = '" + data.getUserid() + "'";
		String query = "UPDATE accounts"
				+ "        SET USERPW = ?"
				+ "          , USERNAME ?"
				+ "          , GENDER = ?"
				+ "          , AGE = ?"
				+ "      WHERE USERID = ?";
		try {
			PreparedStatement pstat = db.getPstat(query);
			pstat.setString(1, data.getUserpw());
			pstat.setString(2, data.getUsername());
			pstat.setString(3, Character.toString(data.getGender()));
			pstat.setInt(4, data.getAge());
			pstat.setString(5, data.getUserid());
			
			int rs = db.sendUpdateQuery();
			if(rs == 1) {
				db.commit();
				return true;
			}
			db.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 회원정보 삭제를 담당
	public boolean remove(JoinVo data) {
//		String query = "DELETE FROM accounts WHERE USERID = '" + data.getUserid() + "'";
		String query = "DELETE FROM accounts WHERE USERID = ?";
		try {
			PreparedStatement pstat = db.getPstat(query);
			pstat.setString(1, data.getUserid());
			
			int rs = db.sendDeleteQuery();
			if(rs == 1) {
				db.commit();
				return true;
			}
			db.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// userId 에 해당하는 회원 정보를 반환
	public JoinVo get(String userid) {
//		String query = String.format("SELECT * FROM accounts WHERE USERID = '%s'", userid);
		String query = "SELECT * FROM accounts WHERE USERID = ?";
		try {
			PreparedStatement pstat = db.getPstat(query);
			pstat.setString(1, userid);
			
			ResultSet rs = db.sendSelectQuery();
			if(rs.next()) {
				JoinVo data = new JoinVo();
				data.setUserid(rs.getString("userid"));
				data.setUserpw(rs.getString("userpw"));
				data.setUsername(rs.getString("username"));
				data.setGender(rs.getString("gender").charAt(0));
				data.setAge(rs.getInt("age"));
				data.setCreateDate(rs.getDate("createdate"));
				return data;
			}
			// select하면 사용
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
