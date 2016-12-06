package com.wfj.controller.wechat;

import com.wfj.controller.index.BaseController;
import com.wfj.entity.AppAccountInfo;
import com.wfj.mapper.AppAccountInfoMapper;
import com.wfj.util.Common;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxuan on 2016-12-06 0006.
 * 将后台的门店数据同步到微信
 */
@Controller
@RequestMapping(value = {"/storeSyn"})
public class StoreSynController extends BaseController {

    private static Logger logger = Logger.getLogger(StoreSynController.class);

    @Inject
    private AppAccountInfoMapper appAccountInfoMapper;

    /**
     * 跳转上传图片页面
     *
     * @param
     * @return
     */
    @RequestMapping(value = {"/picUploadUI"})
    public String picUploadUI(Model model, String storeCode) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (Common.isNotEmpty(storeCode)) {
            paramMap.put("storecode", storeCode.trim());
            paramMap.put("delFlag", 0);
            List<AppAccountInfo> appAccountInfoList = appAccountInfoMapper.selectListByParam(paramMap);
            if (appAccountInfoList.size() == 1) model.addAttribute("store", appAccountInfoList.get(0));
        }
        return Common.BACKGROUND_PATH + "/wechat/storeManager/picUpload";
    }


}
