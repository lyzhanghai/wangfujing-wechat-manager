package com.wfj.service;

import com.wfj.dto.MemberInfoReturnDto;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangxuan on 2016-11-25 0025.
 */
public class TestMemberInfoService {

    public static void main(String args[]) {
        test();
    }

    public static void test() {
        MemberInfoReturnDto tempDto = new MemberInfoReturnDto();
        tempDto.setStoreCode("21011");
        tempDto.setMemberCode("222");
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("obj", tempDto);
        MemberInfoReturnDto memberInfoReturnDto = new MemberInfoReturnDto();
        try {
            BeanUtils.copyProperties(memberInfoReturnDto, returnMap.get("obj"));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(memberInfoReturnDto);
        System.out.println(memberInfoReturnDto.getStoreCode());
    }

}
