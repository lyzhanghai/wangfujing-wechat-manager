package com.wfj.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wfj.dto.MediaDto;
import com.wfj.dto.ReturnDto;
import com.wfj.dto.StoreAppReturnDto;
import com.wfj.dto.WechatErrDto;
import com.wfj.entity.AppAccountInfo;
import com.wfj.entity.StoreInfo;
import com.wfj.mapper.AppAccountInfoMapper;
import com.wfj.mapper.StoreInfoMapper;
import com.wfj.service.intf.StoreSynService;
import com.wfj.util.Common;
import com.wfj.util.HttpUtils;
import com.wfj.util.JsonUtil;
import com.wfj.util.WechatUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
            if (StringUtils.isNotBlank(url)) {
                storeInfo.setPhotoList(url);
            }

            storeInfoMapper.updateByParaSelective(storeInfo);
        }
        logger.info("end com.wfj.service.impl.StoreSynServiceImpl.updatePhotoList(),return:" + url);
        return url;
    }

    /**
     * 门店发布到微信，调用微信接口创建门店
     *
     * @param storeCode
     * @return
     */
    @Transactional
    public ReturnDto releaseToWechat(String storeCode) throws Exception {
        logger.info("start com.wfj.service.impl.StoreSynServiceImpl.releaseToWechat(),para:" + storeCode);
        ReturnDto returnDto = new ReturnDto();
        if (Common.isNotEmpty(storeCode)) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("storeCode", storeCode);
            paramMap.put("delFlag", 0);
            List<StoreAppReturnDto> storeAppReturnDtoList = storeInfoMapper.selectStoreAppInfoListByParam(paramMap);
            if (storeAppReturnDtoList.size() == 1) {
                StoreAppReturnDto storeAppReturnDto = storeAppReturnDtoList.get(0);
                String poiId = storeAppReturnDto.getPoiId();
                String appid = storeAppReturnDto.getAppid();
                String appsecret = storeAppReturnDto.getAppsecret();
                String accessToken = wechatUtil.getAccessToken(appid, appsecret);
                if (Common.isEmpty(poiId)) {//还没发布到微信，调用微信接口创建门店
                    String reqUrl = "http://api.weixin.qq.com/cgi-bin/poi/addpoi?access_token=" + accessToken;

//                Map<String, String> params = new HashMap<String, String>();
//                String sendPost = HttpUtil.sendPost(reqUrl, map3);
                    String doPost = HttpUtils.doPost(reqUrl, transformToWechatAddPoi(storeAppReturnDto));
                    logger.info("调用微信接口创建门店返回结果：" + doPost);

                    JSONObject parseObject = JSONObject.parseObject(doPost);
                    Integer errcode = parseObject.getInteger("errcode");
                    String errmsg = parseObject.getString("errmsg");
                    String poi_id = parseObject.getString("poi_id");

                    //更新门店信息(门店poiid)
                    if (errcode == 0 && Common.isNotEmpty(poi_id)) {
                        StoreInfo storeInfo = new StoreInfo();
                        storeInfo.setStoreCode(storeCode);
                        storeInfo.setPoiId(poi_id);
                        storeInfoMapper.updateByParaSelective(storeInfo);
                    }

                    //封装返回信息
                    WechatErrDto wechatErrDto = new WechatErrDto();
                    wechatErrDto.setErrcode(errcode);
                    wechatErrDto.setErrmsg(errmsg);
                    wechatErrDto.setPoiId(poi_id);

                    returnDto.setCode(errcode + "");
                    returnDto.setDesc(errmsg);
                    if (errcode == 0) {
                        returnDto.setDesc("门店发布到微信成功！");
                    }
                    returnDto.setObj(wechatErrDto);
                } else {//已经发送过创建请求，门店是否可用状态：1 表示系统错误、2 表示审核中、3 审核通过、4 审核驳回。
                    String reqUrl = "http://api.weixin.qq.com/cgi-bin/poi/getpoi?access_token=" + accessToken;
                    Map<String, String> para = new HashMap<String, String>();
                    para.put("poi_id", poiId);
                    String doPost = HttpUtils.doPost(reqUrl, JSONObject.toJSONString(para));
                    JSONObject parseObject = JSONObject.parseObject(doPost);
                    Integer errcode = parseObject.getInteger("errcode");
                    if (errcode == 0) {
                        JSONObject business = parseObject.getJSONObject("business");
                        JSONObject base_info = business.getJSONObject("base_info");
                        Integer available_state = base_info.getInteger("available_state");
                        returnDto.setCode(errcode + "");
                        if (available_state == 1) {
                            returnDto.setDesc("门店是否可用状态为1:系统错误");
                        } else if (available_state == 2) {
                            returnDto.setDesc("门店审核中");
                        } else if (available_state == 3) {
                            returnDto.setDesc("门店已经审核过");
                        } else if (available_state == 4) {
                            returnDto.setDesc("门店审核驳回");
                        }
                    }
                }
            }
        } else {
            returnDto.setCode("1");
            returnDto.setDesc("门店编码为空！");
        }
        logger.info("end com.wfj.service.impl.StoreSynServiceImpl.releaseToWechat(),return:" + returnDto.toString());
        return returnDto;
    }

    /**
     * 参数转换
     *
     * @param storeAppReturnDto
     * @return
     */
    private String transformToWechatAddPoi(StoreAppReturnDto storeAppReturnDto) {
        logger.info("start com.wfj.service.impl.StoreSynServiceImpl.transformToWechatAddPoi(),para:" + storeAppReturnDto.toString());

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("sid", storeAppReturnDto.getStoreCode());
        map1.put("business_name", storeAppReturnDto.getBusinessName());
        map1.put("branch_name", storeAppReturnDto.getBranchName());
        map1.put("province", storeAppReturnDto.getProvince());
        map1.put("city", storeAppReturnDto.getCity());
        map1.put("district", storeAppReturnDto.getDistrict());
        map1.put("address", storeAppReturnDto.getAddress());
        map1.put("telephone", storeAppReturnDto.getTelephone());

        String categories = storeAppReturnDto.getCategories();
        List<String> categoriesList = new ArrayList<String>();
        categoriesList.add(categories);
        map1.put("categories", categoriesList);

        map1.put("offset_type", storeAppReturnDto.getOffsetType());
        map1.put("longitude", storeAppReturnDto.getLongitude());
        map1.put("latitude", storeAppReturnDto.getLatitude());

        String photoList = storeAppReturnDto.getPhotoList();
        if (StringUtils.isNotBlank(photoList)) {
            String[] split = photoList.split(",");
            List<Map<String, String>> photo_list = new ArrayList<Map<String, String>>();
            for (String str : split) {
                if (StringUtils.isNotBlank(str)) {
                    Map<String, String> photo_url = new HashMap<String, String>();
                    photo_url.put("photo_url", str);
                    photo_list.add(photo_url);
                }
            }
            map1.put("photo_list", photo_list);
        }

        map1.put("recommend", storeAppReturnDto.getRecommend());
        map1.put("special", storeAppReturnDto.getSpecial());
        map1.put("introduction", storeAppReturnDto.getIntroduction());
        map1.put("open_time", storeAppReturnDto.getOpenTime());
        map1.put("avg_price", storeAppReturnDto.getAvgPrice());

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("base_info", map1);

        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("business", map2);

        String json = JSONObject.toJSONString(map3);
        logger.info("end com.wfj.service.impl.StoreSynServiceImpl.transformToWechatAddPoi(),return:" + json);
        return json;
    }


}
