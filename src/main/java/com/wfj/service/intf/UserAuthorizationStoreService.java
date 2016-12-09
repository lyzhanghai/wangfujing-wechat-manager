package com.wfj.service.intf;

import com.wfj.dto.UserAuthorizationStoreDto;
import com.wfj.entity.UserAuthorizationStore;

import java.util.List;
import java.util.Map;

/**
 * Created by wangxuan on 2016-12-01 0001.
 */
public interface UserAuthorizationStoreService {

    List<UserAuthorizationStoreDto> selectListByUserId(Map<String,Object> paramMap) throws Exception;


    /**
     * 查询授权门店
     * @param paramMap
     * @return
     * @throws Exception
     */
    List<UserAuthorizationStore> getselectListByUserId(Map<String, Object> paramMap) throws Exception;



}
