package com.wfj.service.intf;

import com.wfj.dto.ReturnDto;
import com.wfj.entity.StoreInfo;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wangxuan on 2016-12-01 0001.
 */
public interface StoreInfoService {
    @Transactional
    ReturnDto addStore(StoreInfo storeInfo) throws Exception;

    @Transactional
    ReturnDto editStore(StoreInfo storeInfo) throws Exception;
}
