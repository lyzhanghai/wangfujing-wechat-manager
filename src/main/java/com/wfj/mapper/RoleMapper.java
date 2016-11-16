package com.wfj.mapper;

import java.util.List;

import com.wfj.entity.RoleFormMap;
import com.wfj.mapper.base.BaseMapper;

public interface RoleMapper extends BaseMapper{
	public List<RoleFormMap> seletUserRole(RoleFormMap map);
	
	public void deleteById(RoleFormMap map);
}
