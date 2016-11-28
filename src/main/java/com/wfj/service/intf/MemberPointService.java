package com.wfj.service.intf;

import com.wfj.dto.MemberPointReturnDto;

import java.util.Map;

/**
 * Created by wangxuan on 2016-11-28 0028.
 */
public interface MemberPointService {
    MemberPointReturnDto findMemberPointByPara(Map<String, Object> paraMap);
}
