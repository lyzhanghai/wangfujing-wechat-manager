package com.wfj.controller.wechat;

import com.wfj.controller.index.BaseController;
import com.wfj.entity.DataTableParams;
import com.wfj.entity.DataTableResult;
import com.wfj.entity.StoreInfo;
import com.wfj.mapper.StoreInfoMapper;
import com.wfj.util.Common;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxuan on 2016-11-30 0030.
 * 门店管理
 */
@Controller
@RequestMapping(value = {"/storeManager"})
public class StoreManagerController extends BaseController {

    @Inject
    private StoreInfoMapper storeInfoMapper;

    /**
     * 访问展示页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/list"})
    public String listUI(Model model) {
        model.addAttribute("res", findByRes());
        return Common.BACKGROUND_PATH + "/wechat/storeManager/list";
    }

    @ResponseBody
    @RequestMapping(value = "/findByPage2")
    public DataTableResult findByPage2(DataTableParams dataTableParams, String businessName) throws Exception {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (Common.isNotEmpty(businessName)) paramMap.put("businessName", businessName.trim());
        List<StoreInfo> storeInfoList = storeInfoMapper.selectListByParam(paramMap);

        DataTableResult<StoreInfo> dataTableResult = new DataTableResult<StoreInfo>();
        dataTableResult.setsEcho(dataTableParams.getsEcho());
        dataTableResult.setAaData(storeInfoList);
        dataTableResult.setiTotalDisplayRecords(storeInfoList.size());
        dataTableResult.setiTotalRecords(storeInfoList.size());
        return dataTableResult;
    }


}
