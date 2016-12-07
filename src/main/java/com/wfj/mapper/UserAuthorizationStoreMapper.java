package com.wfj.mapper;

import com.wfj.entity.UserAuthorizationStore;

public interface UserAuthorizationStoreMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(UserAuthorizationStore record);

    int insertSelective(UserAuthorizationStore record);

    UserAuthorizationStore selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(UserAuthorizationStore record);

    int updateByPrimaryKey(UserAuthorizationStore record);
}