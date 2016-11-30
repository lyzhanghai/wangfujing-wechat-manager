package com.wfj.controller.wechat;

import com.wfj.controller.index.BaseController;
import com.wfj.entity.DataTableParams;
import com.wfj.entity.DataTableResult;
import com.wfj.entity.StoreInfoFormMap;
import com.wfj.mapper.StoreInfoMapper;
import com.wfj.util.Common;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

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
    public DataTableResult findByPage2(DataTableParams dataTableParams) throws Exception {
        StoreInfoFormMap storeInfoFormMap = getFormMap(StoreInfoFormMap.class);
        String pageNow = ((dataTableParams.getiDisplayStart() + 1) % dataTableParams.getiDisplayLength() > 0 ? (dataTableParams.getiDisplayStart() + 1) / dataTableParams.getiDisplayLength() + 1 : (dataTableParams.getiDisplayStart() + 1) / dataTableParams.getiDisplayLength()) + "";
        storeInfoFormMap = toFormMap(storeInfoFormMap, pageNow, dataTableParams.getiDisplayLength() + "", storeInfoFormMap.getStr("orderby"));
        List<StoreInfoFormMap> page = storeInfoMapper.findByPage(storeInfoFormMap);
        pageView.setRecords(page);
        DataTableResult<StoreInfoFormMap> dataTableResult = new DataTableResult<StoreInfoFormMap>();
        dataTableResult.setsEcho(dataTableParams.getsEcho());
        dataTableResult.setAaData(page);
        dataTableResult.setiTotalDisplayRecords(pageView.getRowCount());
        dataTableResult.setiTotalRecords(pageView.getRowCount());
        return dataTableResult;
    }


}
