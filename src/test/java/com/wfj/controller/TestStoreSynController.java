package com.wfj.controller;

import com.wfj.dto.MediaDto;
import com.wfj.service.impl.MaterialServiceImpl;
import com.wfj.service.intf.MaterialService;
import com.wfj.util.JsonUtil;
import com.wfj.util.WechatUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by wangxuan on 2016-12-06 0006.
 */
public class TestStoreSynController {

    private static WechatUtil wechatUtil = new WechatUtil();

    private static String appId = "wx9bf0a9f2f36e4405";
    private static String appSecret = "5c52aad67b44b9f81dd5643500ab0088";

    public static void main(String args[]) {
        MaterialService materialService = new MaterialServiceImpl();
//        String imageInsert = materialService.imageInsert("1.jpg", "buffer");
        String imageInsert = imageInsert("1.jpg", "buffer");
        System.out.println(imageInsert);
    }

    public static String imageInsert(String path, String param) {
        String reString = null;
        String access_token = wechatUtil.getAccessToken(appId, appSecret);
        String[] cmds = {"curl", "-F", " " + param + "=@" + path,
                "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=" + access_token};
        ProcessBuilder pb = new ProcessBuilder(cmds);
        pb.redirectErrorStream(true);
        Process p;
        try {
            p = pb.start();
            BufferedReader br = null;
            String line = null;
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = br.readLine()) != null) {
                reString = line;
            }
            MediaDto media = JsonUtil.getJacksonDTO(reString, MediaDto.class);
            br.close();
            return media.getUrl();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
