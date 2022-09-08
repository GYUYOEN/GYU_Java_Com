package com.myhome.web;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.myhome.web.login.model.EmpDTO;

@Mapper
public interface EmpMapper {
	EmpDTO getUserAccount(String empId);
	
	void saveUser(EmpDTO empDto);
}
