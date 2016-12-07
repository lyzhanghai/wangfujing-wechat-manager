package com.wfj.service.impl;

import com.wfj.dto.UserAuthorizationStoreDto;
import com.wfj.entity.StoreInfo;
import com.wfj.entity.UserAuthorizationStore;
import com.wfj.mapper.StoreInfoMapper;
import com.wfj.mapper.UserAuthorizationStoreMapper;
import com.wfj.service.intf.UserAuthorizationStoreService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaocj on 2016-12-07 0001.
 */
@Service
public class UserAuthorizationStoreServiceImpl implements UserAuthorizationStoreService {

    @Autowired
    private UserAuthorizationStoreMapper userAuthorizationStoreMapper;
    @Autowired
    private StoreInfoMapper storeInfoMapper;

    private static Logger logger = Logger.getLogger(UserAuthorizationStoreService.class);


    public List<UserAuthorizationStoreDto> selectListByUserId(Map<String, Object> paramMap) throws Exception {
        List<UserAuthorizationStoreDto> userAuthStoreList = new ArrayList<UserAuthorizationStoreDto>();
        Map<String, Object> paraMap1 = new HashMap<String, Object>();
        List<StoreInfo> storeList = storeInfoMapper.selectListByParam(paraMap1);
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("userId", paramMap.get("userId") + "");
        List<UserAuthorizationStore> userAuthorizationStoreList = userAuthorizationStoreMapper.selectListByParam(paraMap);
        Map<String, UserAuthorizationStore> userAuthorizationStoreMap = new HashMap<String, UserAuthorizationStore>();
        if (userAuthorizationStoreList != null && userAuthorizationStoreList.size() > 0) {
            for (UserAuthorizationStore userAuthorizationStore : userAuthorizationStoreList) {
                userAuthorizationStoreMap.put(userAuthorizationStore.getStoreCode(), userAuthorizationStore);
            }
            for (StoreInfo storeInfo : storeList) {
                UserAuthorizationStoreDto userAuthorizationStoreDto = new UserAuthorizationStoreDto();
                userAuthorizationStoreDto.setBusinessName(storeInfo.getBusinessName());
                userAuthorizationStoreDto.setStoreCode(storeInfo.getStoreCode());
                userAuthorizationStoreDto.setUserId(paramMap.get("userId") + "");
                //若绑定门店且未失败，则值为0，否则为1
                userAuthorizationStoreDto.setIsLoseEfficacy(userAuthorizationStoreMap.get(storeInfo.getStoreCode()) != null && userAuthorizationStoreMap.get(storeInfo.getStoreCode()).getIsLoseEfficacy() == 0 ? 0 : 1);
                userAuthStoreList.add(userAuthorizationStoreDto);
            }

            return userAuthStoreList;
        } else {
            for (StoreInfo storeInfo : storeList) {
                UserAuthorizationStoreDto userAuthorizationStoreDto = new UserAuthorizationStoreDto();
                userAuthorizationStoreDto.setBusinessName(storeInfo.getBusinessName());
                userAuthorizationStoreDto.setStoreCode(storeInfo.getStoreCode());
                userAuthorizationStoreDto.setUserId(paramMap.get("userId") + "");
                userAuthorizationStoreDto.setIsLoseEfficacy(0);
                userAuthStoreList.add(userAuthorizationStoreDto);
            }
        }
        return userAuthStoreList;
    }

    /**
     * 查询授权门店
     * @param paramMap
     * @return
     * @throws Exception
     */
    public List<UserAuthorizationStore> getselectListByUserId(Map<String, Object> paramMap) throws Exception {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("userId", paramMap.get("userId") + "");
        paraMap.put("isLoseEfficacy","0");

        List<UserAuthorizationStore> userAuthorizationStoreList = userAuthorizationStoreMapper.selectListByParam(paraMap);
        return userAuthorizationStoreList;
    }
}
