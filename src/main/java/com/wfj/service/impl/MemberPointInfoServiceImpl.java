package com.wfj.service.impl;

import com.wfj.dto.MemberPointInfoDto;
import com.wfj.entity.MemberPointInfo;
import com.wfj.mapper.MemberPointInfoMapper;
import com.wfj.service.intf.MemberPointInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxuan on 2016-11-25 0025.
 */
@Service
public class MemberPointInfoServiceImpl implements MemberPointInfoService {

    private static Logger logger = LoggerFactory.getLogger(MemberPointInfoServiceImpl.class);

    @Autowired
    private MemberPointInfoMapper memberPointInfoMapper;

    /**
     * 查询积分明细
     *
     * @param paraMap
     * @return
     */
    public List<MemberPointInfoDto> findMemberPointDetailByPara(Map<String, Object> paraMap) {
        logger.info("start com.wfj.service.impl.MemberPointInfoServiceImpl.findMemberPointDetailByPara(),para:" + paraMap.toString());
        String storeCode = paraMap.get("storeCode") + "";
        String memberCode = paraMap.get("memberCode") + "";
        String cardCode = paraMap.get("cardCode") + "";

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paraMap.put("storeCode", storeCode);
        paraMap.put("memberCode", memberCode);
        paraMap.put("cardCode", cardCode);
        List<MemberPointInfo> memberPointInfoList = memberPointInfoMapper.selectListByParam(paramMap);
        List<MemberPointInfoDto> returnDtoList = new ArrayList<MemberPointInfoDto>();
        for (MemberPointInfo memberPointInfo : memberPointInfoList) {
            MemberPointInfoDto returnDto = new MemberPointInfoDto();
            BeanUtils.copyProperties(memberPointInfo, returnDto);
            Integer pointType = memberPointInfo.getPointType();
            if (pointType == 1) {
                returnDto.setPointTypeView("消费返增加");
            }
            returnDtoList.add(returnDto);
        }
        logger.info("end com.wfj.service.impl.MemberPointInfoServiceImpl.findMemberPointDetailByPara(),return:" + returnDtoList.toString());
        return returnDtoList;
    }
}
