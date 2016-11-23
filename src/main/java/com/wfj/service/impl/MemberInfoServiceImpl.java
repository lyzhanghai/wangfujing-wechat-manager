package com.wfj.service.impl;

import com.wfj.entity.MemberInfo;
import com.wfj.mapper.MemberInfoMapper;
import com.wfj.service.intf.MemberInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxuan on 2016-11-22 0022.
 */
@Service
public class MemberInfoServiceImpl implements MemberInfoService {

    private static Logger logger = LoggerFactory.getLogger(MemberInfoServiceImpl.class);

    @Autowired
    private MemberInfoMapper memberInfoMapper;

    public String generateMemberCode(Map<String, Object> paramMap) {
        Map<String, Object> objectMap = memberInfoMapper.selectMaxMemberCodeByParam(paramMap);
        Long maxMemberCode = Long.parseLong(objectMap.get("maxMemberCode") + "");
        Long code = maxMemberCode + 1;
        return code + "";
    }

    /**
     * 注册会员
     *
     * @param memberInfo
     * @return
     */
    @Transactional
    public Map<String, Object> registerMember(MemberInfo memberInfo) {
        logger.info("start com.wfj.service.impl.MemberInfoServiceImpl.registerMember(),para:" + memberInfo.toString());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("storeCode", memberInfo.getStoreCode());
        paramMap.put("mobile", memberInfo.getMobile());
        List<MemberInfo> memberInfoList = memberInfoMapper.selectListByParam(paramMap);
        Map<String, Object> returnMap = new HashMap<String, Object>();
        if (memberInfoList.size() == 0) {
            paramMap.clear();
            String generateMemberCode = generateMemberCode(paramMap);
            memberInfo.setMemberCode(generateMemberCode);
            int insertSelective = memberInfoMapper.insertSelective(memberInfo);
            if (insertSelective == 1) {
                returnMap.put("success", "true");
                returnMap.put("desc", "注册成功！");
            } else {
                returnMap.put("success", "false");
                returnMap.put("desc", "注册失败！");
            }
        } else {
            returnMap.put("success", "false");
            returnMap.put("desc", "已经注册了！");
        }

        logger.info("end com.wfj.service.impl.MemberInfoServiceImpl.registerMember(),return:" + returnMap.toString());
        return returnMap;
    }

}
