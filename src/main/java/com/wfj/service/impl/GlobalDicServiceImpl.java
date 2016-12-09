package com.wfj.service.impl;

import com.wfj.entity.GlobalDic;
import com.wfj.mapper.GlobalDicMapper;
import com.wfj.service.intf.IGlobalDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * dic
 *
 * @author kongqf
 * @create 2016-12-08
 */
@Service
public class GlobalDicServiceImpl implements IGlobalDicService {

    @Autowired
    private GlobalDicMapper globalDicMapper;

    /**
     * 查询字典列表
     *
     * @param record
     * @return
     */
    public List<GlobalDic> queryDicList(GlobalDic record) {
        return globalDicMapper.selectDicListByType(record);
    }
}
