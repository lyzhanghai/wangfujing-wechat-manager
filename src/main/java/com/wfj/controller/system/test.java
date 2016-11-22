package com.wfj.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kongqf on 16-11-22.
 */
@Controller
@RequestMapping(value = "/test")
public class test {


@ResponseBody
    @RequestMapping("/test")
    public Map<String,Object> testdata()
    {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("1111","222222222");
        return  map;
    }

}
