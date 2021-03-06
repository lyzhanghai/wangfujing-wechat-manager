package com.wfj.service.intf;

import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by wangxuan on 2016-11-23 0023.
 */
public interface MemberCardService {
    String generateCardCode(Map<String, Object> paramMap);

    @Transactional
    Map<String, Object> bindMemberCard(Map<String, Object> paraMap) throws Exception;

    @Transactional
    Map<String,Object> cardCancleBind(Map<String, Object> paraMap) throws Exception;
}
