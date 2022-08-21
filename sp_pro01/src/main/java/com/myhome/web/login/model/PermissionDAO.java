package com.myhome.web.login.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PermissionDAO {
	
	@Autowired
	private SqlSession session;

	public boolean selectData(PermDTO data) {
		PermDTO result = session.selectOne("permissionMapper.selectData", data);
		if(result != null) {
			data.setpRead(result.ispRead());
			data.setpAdd(result.ispAdd());
			data.setpUpdate(result.ispUpdate());
			data.setpDelete(result.ispDelete());
			return true;
		}
		return false;
	}
}
