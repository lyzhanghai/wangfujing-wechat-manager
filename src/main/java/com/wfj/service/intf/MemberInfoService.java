package com.wfj.service.intf;

import com.wfj.entity.MemberInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by wangxuan on 2016-11-22 0022.
 */
public interface MemberInfoService {

    @Transactional
    Map<String, Object> registerMember(MemberInfo memberInfo);
}
