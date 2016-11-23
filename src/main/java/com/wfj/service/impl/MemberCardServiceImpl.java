package com.wfj.service.impl;

import com.wfj.mapper.MemberCardMapper;
import com.wfj.mapper.MemberInfoMapper;
import com.wfj.service.intf.MemberCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by wangxuan on 2016-11-23 0023.
 */
@Service
public class MemberCardServiceImpl implements MemberCardService {

    private static Logger logger = LoggerFactory.getLogger(MemberCardService.class);

    @Autowired
    private MemberCardMapper memberCardMapper;

    @Autowired
    private MemberInfoMapper memberInfoMapper;

    /**
     * 绑定卡
     *
     * @param paramMap
     * @return
     */
    @Transactional
    public Map<String, Object> bindMemberCard(Map<String, Object> paramMap) {
        logger.info("start com.wfj.service.impl.MemberCardServiceImpl.bindMemberCard(),para:" + paramMap.toString());
        String cardType = paramMap.get("cardType") + "";
        if ("1".equals(cardType)) {//实体卡绑定

        }
        logger.info("end com.wfj.service.impl.MemberCardServiceImpl.bindMemberCard(),return:");
        return null;
    }
}
