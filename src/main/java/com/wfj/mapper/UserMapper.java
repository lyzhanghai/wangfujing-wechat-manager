package com.wfj.mapper;

import java.util.List;

import com.wfj.entity.UserFormMap;
import com.wfj.mapper.base.BaseMapper;


public interface UserMapper extends BaseMapper{

	public List<UserFormMap> findUserPage(UserFormMap userFormMap);
	
}
