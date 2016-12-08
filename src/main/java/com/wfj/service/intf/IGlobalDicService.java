package com.wfj.service.intf;

import com.wfj.entity.GlobalDic;

import java.util.List;

/**
 * @author kongqf
 * @create 2016-12-08
 */
public interface IGlobalDicService {

    public List<GlobalDic> queryDicList(GlobalDic record);
}
