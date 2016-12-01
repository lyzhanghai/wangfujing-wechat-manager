package com.wfj.service.impl;

import com.wfj.dto.ReturnDto;
import com.wfj.entity.StoreInfo;
import com.wfj.mapper.StoreInfoMapper;
import com.wfj.service.intf.StoreInfoService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxuan on 2016-12-01 0001.
 */
@Service
public class StoreInfoServiceImpl implements StoreInfoService {

    private static Logger logger = Logger.getLogger(StoreInfoService.class);

    @Inject
    private StoreInfoMapper storeInfoMapper;

    /**
     * 添加门店
     *
     * @param
     * @return
     */
    @Transactional
    public ReturnDto addStore(StoreInfo storeInfo) throws Exception {
        logger.debug("start com.wfj.service.impl.StoreInfoServiceImpl.addStore(),para:" + storeInfo.toString());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("storeCode", storeInfo.getStoreCode());
        List<StoreInfo> storeInfoList = storeInfoMapper.selectListByParam(paramMap);
        ReturnDto returnDto = new ReturnDto();
        if (storeInfoList.size() == 0) {
            int i = storeInfoMapper.insertSelective(storeInfo);
            if (i == 1) {
                returnDto.setCode("1");
                returnDto.setDesc("添加成功！");
            } else {
                returnDto.setCode("0");
                returnDto.setDesc("添加失败！");
                throw new RuntimeException("com.wfj.service.impl.StoreInfoServiceImpl.addStore:添加门店操作数据库失败！");
            }
        } else {
            returnDto.setCode("0");
            returnDto.setDesc("门店编码已经存在！");
        }

        logger.debug("end com.wfj.service.impl.StoreInfoServiceImpl.addStore(),return:" + returnDto);
        return returnDto;
    }

    /**
     * 修改门店
     *
     * @param storeInfo
     * @return
     * @throws Exception
     */
    @Transactional
    public ReturnDto editStore(StoreInfo storeInfo) throws Exception {
        logger.debug("start com.wfj.service.impl.StoreInfoServiceImpl.editStore,para:" + storeInfo.toString());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("storeCode", storeInfo.getStoreCode());
        List<StoreInfo> storeInfoList = storeInfoMapper.selectListByParam(paramMap);

        ReturnDto returnDto = new ReturnDto();
        if (storeInfoList.size() == 1) {
            storeInfoMapper.updateByParaSelective(storeInfo);
            returnDto.setCode("1");
            returnDto.setDesc("修改成功！");
        } else {
            returnDto.setCode("0");
            returnDto.setDesc("修改的门店不存在！");
        }

        logger.debug("end com.wfj.service.impl.StoreInfoServiceImpl.editStore,para:" + returnDto.toString());
        return returnDto;
    }
}
