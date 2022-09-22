package com.myhome.web.mapper;

import java.util.List;

import com.myhome.web.login.model.EmpDTO;

public interface EmpMapper {
	public EmpDTO selectEmployee(EmpDTO empDto);

	public int insertEmployee(EmpDTO empDto);
	
	public List<EmpDTO> selectEmployeeAll();
}
