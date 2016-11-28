package com.wfj.service.impl;

import com.wfj.dto.MemberPointReturnDto;
import com.wfj.entity.MemberPoint;
import com.wfj.mapper.MemberPointMapper;
import com.wfj.service.intf.MemberPointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxuan on 2016-11-28 0028.
 */
@Service
public class MemberPointServiceImpl implements MemberPointService {

    private static Logger logger = LoggerFactory.getLogger(MemberPointInfoServiceImpl.class);

    @Autowired
    private MemberPointMapper memberPointMapper;

    /**
     * 查询积分（总积分、可用积分）
     *
     * @param paraMap
     * @return
     */
    public MemberPointReturnDto findMemberPointByPara(Map<String, Object> paraMap) {
        logger.info("start com.wfj.service.impl.MemberPointServiceImpl.findMemberPointByPara(),para:" + paraMap.toString());
        String storeCode = paraMap.get("storeCode") + "";
        String memberCode = paraMap.get("memberCode") + "";

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paraMap.put("storeCode", storeCode);
        paraMap.put("memberCode", memberCode);
        List<MemberPoint> memberPointList = memberPointMapper.selectListByParam(paramMap);
        MemberPointReturnDto returnDto = new MemberPointReturnDto();
        if (memberPointList.size() > 0) {
            BeanUtils.copyProperties(memberPointList.get(0), returnDto);
        }

        logger.info("end com.wfj.service.impl.MemberPointServiceImpl.findMemberPointByPara(),return:" + returnDto);
        return returnDto;
    }
}
