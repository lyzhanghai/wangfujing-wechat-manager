package com.wfj.service.impl;

import com.wfj.entity.MemberCard;
import com.wfj.entity.MemberInfo;
import com.wfj.mapper.MemberCardMapper;
import com.wfj.mapper.MemberInfoMapper;
import com.wfj.service.intf.MemberCardService;
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

    @Autowired
    private MemberCardMapper memberCardMapper;

    @Autowired
    private MemberCardService memberCardService;

    /**
     * 生成会员编码
     *
     * @param paramMap
     * @return
     */
    public String generateMemberCode(Map<String, Object> paramMap) {
        logger.info("start com.wfj.service.impl.MemberInfoServiceImpl.generateMemberCode(),para" + paramMap.toString());
        Map<String, Object> objectMap = memberInfoMapper.selectMaxMemberCodeByParam(paramMap);
        Long code = null;
        if (objectMap != null && !objectMap.isEmpty()) {
            Long maxMemberCode = Long.parseLong(objectMap.get("maxMemberCode") + "");
            code = maxMemberCode + 1;
        } else {
            code = 1L;
        }

        logger.info("end com.wfj.service.impl.MemberInfoServiceImpl.generateMemberCode(),return:" + code);
        return code + "";
    }

    /**
     * 注册会员
     *
     * @param memberInfo
     * @return
     */
    @Transactional
    public Map<String, Object> registerMember(MemberInfo memberInfo) throws Exception {
        logger.info("start com.wfj.service.impl.MemberInfoServiceImpl.registerMember(),para:" + memberInfo.toString());
        String storeCode = memberInfo.getStoreCode();
        String openid = memberInfo.getOpenid();

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("storeCode", storeCode);
        paramMap.put("openid", openid);
        List<MemberInfo> memberInfoList = memberInfoMapper.selectListByParam(paramMap);
        Map<String, Object> returnMap = new HashMap<String, Object>();
        if (memberInfoList.size() == 0) {
            //插入会员信息
            paramMap.clear();
            String generateMemberCode = generateMemberCode(paramMap);
            memberInfo.setMemberCode(generateMemberCode);
            int insertSelective = memberInfoMapper.insertSelective(memberInfo);
            if (insertSelective == 1) {
                returnMap.put("success", "true");
                returnMap.put("desc", "注册成功！");
            } else {
                throw new RuntimeException("会员注册时，数据库插入会员信息（memberinfo）失败！");
            }

            //插入虚拟卡
            MemberCard memberCard = new MemberCard();
            memberCard.setStoreCode(storeCode);
            memberCard.setMemberCode(generateMemberCode);
            paramMap.clear();
            String generateCardCode = memberCardService.generateCardCode(paramMap);
            memberCard.setCardCode(generateCardCode);
            memberCard.setCardType(2);
            memberCard.setStatus(0);
            memberCard.setDelFlag(0);
            memberCardMapper.insertSelective(memberCard);
        } else if (memberInfoList.size() == 1) {
            returnMap.put("success", "false");
            returnMap.put("desc", "已经注册了！");
        } else {
            throw new RuntimeException("会员注册时，会员信息（memberinfo）存在重复信息！");
        }

        logger.info("end com.wfj.service.impl.MemberInfoServiceImpl.registerMember(),return:" + returnMap.toString());
        return returnMap;
    }

}
