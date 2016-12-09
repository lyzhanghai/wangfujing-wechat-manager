package com.wfj.controller.coupon;

import com.wfj.controller.index.BaseController;
import com.wfj.entity.GlobalDic;
import com.wfj.service.intf.IGlobalDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * dic
 *
 * @author kongqf
 * @create 2016-12-08
 */
@Controller
@RequestMapping("dic")
public class GlobalDicController extends BaseController {

    @Autowired
    private IGlobalDicService globalDicService;

    @RequestMapping(value = {"/queryDicList"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> queryDicList(Model model) {
        GlobalDic dic = new GlobalDic();
        dic.setKeyword(getPara("key"));
        List<GlobalDic> list = globalDicService.queryDicList(dic);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (list != null && list.size() > 0) {
            resultMap.put("success", true);
            resultMap.put("list", list);
        } else {
            resultMap.put("success", false);
            resultMap.put("list", "");
        }
        return resultMap;
    }
}
