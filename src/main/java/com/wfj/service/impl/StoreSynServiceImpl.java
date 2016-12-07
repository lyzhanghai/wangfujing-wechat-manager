package com.wfj.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wfj.dto.MediaDto;
import com.wfj.entity.AppAccountInfo;
import com.wfj.entity.StoreInfo;
import com.wfj.mapper.AppAccountInfoMapper;
import com.wfj.mapper.StoreInfoMapper;
import com.wfj.service.intf.StoreSynService;
import com.wfj.util.Common;
import com.wfj.util.JsonUtil;
import com.wfj.util.WechatUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxuan on 2016-12-06 0006.
 * 门店信息同步到微信
 */
@Service
public class StoreSynServiceImpl implements StoreSynService {

    private static Logger logger = Logger.getLogger(StoreSynServiceImpl.class);

    @Inject
    private WechatUtil wechatUtil;

    @Inject
    private StoreInfoMapper storeInfoMapper;

    @Inject
    private AppAccountInfoMapper appAccountInfoMapper;

    /**
     * 返回url的图片上传
     *
     * @param path
     * @param param buffer/media
     * @return
     */
    public String imageInsert(String appId, String appSecret, String path, String param) {
        logger.info("start-imageInsert,param ,path" + path);
        String reString = null;
        String access_token = wechatUtil.getAccessToken(appId, appSecret);
        String[] cmds = {"curl", "-F", "" + param + "=@" + path,
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

    /**
     * 上传门店图片，更新图片字段
     *
     * @param storeCode
     * @param path
     * @param param
     * @return
     */
    @Transactional
    public String uploadPhotoList(String storeCode, String path, String param) {
        logger.info("start com.wfj.service.impl.StoreSynServiceImpl.updatePhotoList(),para:");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("storecode", storeCode);
        paramMap.put("delFlag", 0);
        List<AppAccountInfo> appAccountInfoList = appAccountInfoMapper.selectListByParam(paramMap);
        String url = "";
        if (appAccountInfoList.size() > 0) {

            AppAccountInfo appAccountInfo = appAccountInfoList.get(0);
            String appid = appAccountInfo.getAppid();
            String appsecret = appAccountInfo.getAppsecret();
            url = imageInsert(appid, appsecret, path, param);//上传图片，调用微信接口
            logger.info("com.wfj.service.impl.StoreSynServiceImpl.imageInsert:" + url);

            //上传图片后更新图片字段
            StoreInfo storeInfo = new StoreInfo();
            storeInfo.setStoreCode(storeCode);

            if (Common.isNotEmpty(url)) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("photo_url", url);
                JSONArray jsonArray = new JSONArray();
                jsonArray.add(jsonObject);
                storeInfo.setPhotoList(jsonArray.toJSONString());
            }

            storeInfoMapper.updateByParaSelective(storeInfo);
        }
        logger.info("end com.wfj.service.impl.StoreSynServiceImpl.updatePhotoList(),return:" + url);
        return url;
    }


}
