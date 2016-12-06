package com.wfj.controller.wechat;

import com.wfj.controller.index.BaseController;
import com.wfj.entity.AppAccountInfo;
import com.wfj.mapper.AppAccountInfoMapper;
import com.wfj.service.intf.MaterialService;
import com.wfj.util.Common;
import com.wfj.util.PropertiesUtils;
import com.wfj.util.WechatUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
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

    @Inject
    private WechatUtil wechatUtil;

    @Inject
    private MaterialService materialService;

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

    /**
     * 门店图片上传
     *
     * @param
     * @return
     */
    @RequestMapping(value = {"/picUpload"})
    @ResponseBody
    public String picUpload(MultipartFile file, String appid, String appsecret, HttpServletRequest request) throws Exception {
//        String appid = request.getParameter("appid");
//        String appsecret = request.getParameter("appsecret");
        appid = "wx9bf0a9f2f36e4405";
        appsecret = "5c52aad67b44b9f81dd5643500ab0088";
        String accessToken = wechatUtil.getAccessToken(appid, appsecret);
        String uploadStorePicUrl = PropertiesUtils.findPropertiesKey("uploadStorePicUrl");

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("success", "error");
        if (file != null) {// 判断上传的文件是否为空
            String path = null;// 文件路径
            String type = null;// 文件类型
            String fileName = file.getOriginalFilename();// 文件原名称
            logger.info("上传的文件原名称:" + fileName);
            // 判断文件类型
            type = fileName.indexOf(".") != -1
                    ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            if (type != null) {// 判断文件类型是否为空
                if (!"PNG".equals(type.toUpperCase()) && !"JPG".equals(type.toUpperCase())) {
                    logger.info("不是我们想要的文件类型,请按要求重新上传");
                    paramMap.put("errMsg", "不是我们想要的文件类型,请按要求重新上传");
                    return "";
                }
                // 项目在容器中实际发布运行的根路径
                // String realPath =
                // request.getSession().getServletContext().getRealPath("/");
                String realPath = System.getProperty("user.dir") + "/";
                // 自定义的文件名称
                String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
                // 设置存放图片文件的路径
                path = realPath + /* System.getProperty("file.separator")+ */trueFileName;
                logger.info("存放图片文件的路径:" + path);
                // 转存文件到指定的路径
                file.transferTo(new File(path));
                logger.info("文件成功上传到指定目录下");
                String url = materialService.imageInsert(path, "buffer");
                if (url != null) {
                    paramMap.put("success", "success");
                    paramMap.put("url", url);
                    return "";
                }
            } else {
                logger.info("文件类型为空");
                paramMap.put("errorMsg", "文件类型为空");
                return "";
            }
        } else {
            logger.info("没有找到相对应的文件");
            paramMap.put("errorMsg", "没有找到相对应的文件");
            return "";
        }
        return "success";
    }

}
