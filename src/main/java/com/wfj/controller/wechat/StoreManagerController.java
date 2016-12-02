package com.wfj.controller.wechat;

import com.alibaba.fastjson.JSONObject;
import com.wfj.annotation.SystemLog;
import com.wfj.controller.index.BaseController;
import com.wfj.dto.ReturnDto;
import com.wfj.entity.DataTableParams;
import com.wfj.entity.DataTableResult;
import com.wfj.entity.StoreInfo;
import com.wfj.mapper.StoreInfoMapper;
import com.wfj.service.intf.StoreInfoService;
import com.wfj.util.Common;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.ArrayList;
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

    private static Logger logger = Logger.getLogger(StoreManagerController.class);

    @Inject
    private StoreInfoMapper storeInfoMapper;

    @Inject
    private StoreInfoService storeInfoService;

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

    /**
     * 查询门店
     *
     * @param
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/findByPage2")
    public DataTableResult findByPage2(DataTableParams dataTableParams, String storeCode, String businessName) throws Exception {
        logger.debug("start com.wfj.controller.wechat.StoreManagerController.findByPage2()");
        Integer displayStart = dataTableParams.getiDisplayStart();
        Integer displayLength = dataTableParams.getiDisplayLength();

        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (displayStart != null) paramMap.put("start", displayStart);
        if (displayLength != null) paramMap.put("limit", displayLength);
        if (Common.isNotEmpty(storeCode)) paramMap.put("storeCode", storeCode.trim());
        if (Common.isNotEmpty(businessName)) paramMap.put("businessName", businessName.trim());
        logger.debug("com.wfj.controller.wechat.StoreManagerController.findByPage2,para:" + paramMap.toString());
        List<StoreInfo> storeInfoList = storeInfoMapper.selectListByParamLike(paramMap);

        DataTableResult<StoreInfo> dataTableResult = new DataTableResult<StoreInfo>();
        dataTableResult.setsEcho(dataTableParams.getsEcho());
        dataTableResult.setAaData(storeInfoList);
        dataTableResult.setiTotalDisplayRecords(storeInfoList.size());
        dataTableResult.setiTotalRecords(storeInfoList.size());
        logger.debug("end com.wfj.controller.wechat.StoreManagerController.findByPage2(),return:" + dataTableResult.toString());
        return dataTableResult;
    }

    /**
     * 跳转添加页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/addUI"})
    public String addUI() throws Exception {
        return Common.BACKGROUND_PATH + "/wechat/storeManager/add";
    }

    /**
     * 添加门店
     *
     * @param
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = {"/addStore"})
    @SystemLog(module = "门店管理", methods = "门店管理-添加门店")
    public JSONObject addStore(StoreInfo storeInfo) throws Exception {
        JSONObject jsonObject = new JSONObject();
        ReturnDto returnDto = storeInfoService.addStore(storeInfo);
        String code = returnDto.getCode();
        String desc = returnDto.getDesc();
        jsonObject.put("msg", desc);
        if ("1".equals(code)) {
            jsonObject.put("success", "true");
        } else {
            jsonObject.put("success", "false");
        }
        return jsonObject;
    }

    /**
     * 查询具体门店信息
     *
     * @param
     * @return
     */
    private StoreInfo getStoreInfo(String storeCode) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        StoreInfo storeInfo = null;
        if (Common.isNotEmpty(storeCode)) {
            paramMap.put("storeCode", storeCode.trim());
            List<StoreInfo> storeInfoList = storeInfoMapper.selectListByParam(paramMap);
            if (storeInfoList.size() == 1) {
                storeInfo = storeInfoList.get(0);
            }
        }
        return storeInfo;
    }

    /**
     * 跳转修改页面
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/editUI")
    public String editUI(Model model, String storeCode) throws Exception {
        StoreInfo storeInfo = getStoreInfo(storeCode);
        model.addAttribute("store", storeInfo);
        return Common.BACKGROUND_PATH + "/wechat/storeManager/edit";
    }

    /**
     * 跳转详情页面
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getDetailUI")
    public String getDetailUI(Model model, String storeCode) throws Exception {
        StoreInfo storeInfo = getStoreInfo(storeCode);
        model.addAttribute("store", storeInfo);
        return Common.BACKGROUND_PATH + "/wechat/storeManager/detail";
    }

    /**
     * 修改门店
     *
     * @param
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/editStore")
    @SystemLog(module = "门店管理", methods = "门店管理-修改门店")
    public String editStore(StoreInfo storeInfo) throws Exception {
        ReturnDto returnDto = storeInfoService.editStore(storeInfo);
        return "success";
    }

    /**
     * 批量删除门店
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/batchDelStore")
    @SystemLog(module = "门店管理", methods = "门店管理-删除门店")
    public String batchDelStore(@RequestParam(value = "storeCodes[]") String[] storeCodes) {
        List<String> storeCodeList = new ArrayList<String>();
        if (storeCodes != null && storeCodes.length != 0) {
            for (String storeCode : storeCodes) {
                if (Common.isNotEmpty(storeCode) && !"undefined".equals(storeCode))
                    storeCodeList.add(storeCode);
            }
        }
        if (storeCodeList.size() > 0) {
            ReturnDto returnDto = storeInfoService.batchDelStore(storeCodeList);
        }
        return "success";
    }
}
