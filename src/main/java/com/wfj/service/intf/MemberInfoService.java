package com.wfj.service.intf;

import com.wfj.entity.MemberInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by wangxuan on 2016-11-22 0022.
 */
public interface MemberInfoService {

    String generateMemberCode(Map<String, Object> paramMap);

    @Transactional
    Map<String, Object> registerMember(MemberInfo memberInfo) throws Exception;

    Map<String,Object> getMemberInfo(Map<String, Object> paraMap);

    @Transactional
    Map<String,Object> changePayPassword(Map<String, Object> paraMap) throws Exception;
}
