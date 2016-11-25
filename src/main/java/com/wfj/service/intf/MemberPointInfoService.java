package com.wfj.service.intf;

import com.wfj.dto.MemberPointInfoDto;

import java.util.List;
import java.util.Map;

/**
 * Created by wangxuan on 2016-11-25 0025.
 */
public interface MemberPointInfoService {
    List<MemberPointInfoDto> findMemberPointDetailByPara(Map<String, Object> paraMap);
}
