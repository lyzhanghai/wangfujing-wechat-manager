package com.wfj.service.intf;

import com.wfj.dto.UserAuthorizationStoreDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by wangxuan on 2016-12-01 0001.
 */
public interface UserAuthorizationStoreService {
    @Transactional
    List<UserAuthorizationStoreDto> selectListByUserId(Map<String,Object> paramMap) throws Exception;



}
