package com.wfj.service.impl;

import com.wfj.dto.MemberInfoReturnDto;
import com.wfj.entity.MemberCard;
import com.wfj.entity.MemberInfo;
import com.wfj.mapper.MemberCardMapper;
import com.wfj.mapper.MemberInfoMapper;
import com.wfj.service.intf.MemberCardService;
import com.wfj.service.intf.MemberInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxuan on 2016-11-22 0022.
 */
@Service
public class MemberInfoServiceImpl implements MemberInfoService {

    private static Logger logger = LoggerFactory.getLogger(MemberInfoServiceImpl.class);

    @Autowired
    private MemberInfoMapper memberInfoMapper;

    @Autowired
    private MemberCardMapper memberCardMapper;

    @Autowired
    private MemberCardService memberCardService;

    /**
     * 生成会员编码
     *
     * @param paramMap
     * @return
     */
    public String generateMemberCode(Map<String, Object> paramMap) {
        logger.info("start com.wfj.service.impl.MemberInfoServiceImpl.generateMemberCode(),para" + paramMap.toString());
        Map<String, Object> objectMap = memberInfoMapper.selectMaxMemberCodeByParam(paramMap);
        Long code = null;
        if (objectMap != null && !objectMap.isEmpty()) {
            Long maxMemberCode = Long.parseLong(objectMap.get("maxMemberCode") + "");
            code = maxMemberCode + 1;
        } else {
            code = 1L;
        }

        logger.info("end com.wfj.service.impl.MemberInfoServiceImpl.generateMemberCode(),return:" + code);
        return code + "";
    }

    /**
     * 注册会员
     *
     * @param memberInfo
     * @return
     */
    @Transactional
    public Map<String, Object> registerMember(MemberInfo memberInfo) throws Exception {
        logger.info("start com.wfj.service.impl.MemberInfoServiceImpl.registerMember(),para:" + memberInfo.toString());
        String storeCode = memberInfo.getStoreCode();
        String openid = memberInfo.getOpenid();

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("storeCode", storeCode);
        paramMap.put("openid", openid);
        List<MemberInfo> memberInfoList = memberInfoMapper.selectListByParam(paramMap);
        Map<String, Object> returnMap = new HashMap<String, Object>();
        if (memberInfoList.size() == 0) {
            //插入会员信息
            paramMap.clear();
            String generateMemberCode = generateMemberCode(paramMap);
            memberInfo.setMemberCode(generateMemberCode);
            int insertSelective = memberInfoMapper.insertSelective(memberInfo);
            if (insertSelective != 1) {
                throw new RuntimeException("会员注册时，数据库插入会员信息（memberinfo）失败！");
            }

            //插入虚拟卡
            MemberCard memberCard = new MemberCard();
            memberCard.setStoreCode(storeCode);
            memberCard.setMemberCode(generateMemberCode);
            paramMap.clear();
            String generateCardCode = memberCardService.generateCardCode(paramMap);
            memberCard.setCardCode(generateCardCode);
            memberCard.setCardType(2);
            memberCard.setStatus(0);
            memberCard.setDelFlag(0);
            memberCardMapper.insertSelective(memberCard);
        } else if (memberInfoList.size() == 1) {
            MemberInfo memberInfo1 = memberInfoList.get(0);
            paramMap.clear();
            paramMap.put("storeCode", storeCode);
            paramMap.put("memberCode", memberInfo1.getMemberCode());
            paramMap.put("cardType", 2);
            List<MemberCard> memberCardList = memberCardMapper.selectListByParam(paramMap);
            if (memberCardList.size() == 0) {
                //插入虚拟卡
                MemberCard memberCard = new MemberCard();
                memberCard.setStoreCode(storeCode);
                memberCard.setMemberCode(memberInfo1.getMemberCode());
                paramMap.clear();
                String generateCardCode = memberCardService.generateCardCode(paramMap);
                memberCard.setCardCode(generateCardCode);
                memberCard.setCardType(2);
                memberCard.setStatus(0);
                memberCard.setDelFlag(0);
                memberCardMapper.insertSelective(memberCard);
            } else if (memberCardList.size() > 0) {
                MemberCard memberCard = memberCardList.get(0);
                memberCard.setStatus(0);
                memberCard.setDelFlag(0);
                memberCardMapper.updateByParaSelective(memberCard);
            }
        } else {
            throw new RuntimeException("会员注册时，会员信息（memberinfo）存在重复信息！");
        }

        returnMap.put("success", "true");
        returnMap.put("desc", "注册成功！");

        logger.info("end com.wfj.service.impl.MemberInfoServiceImpl.registerMember(),return:" + returnMap.toString());
        return returnMap;
    }

    /**
     * 查询会员及会员卡信息
     *
     * @param
     * @return
     */
    public Map<String, Object> getMemberInfo(Map<String, Object> paraMap) {
        logger.info("start com.wfj.service.impl.MemberInfoServiceImpl.getMemberInfo(),para:" + paraMap.toString());
        String appid = paraMap.get("appid") + "";
        String openid = paraMap.get("openid") + "";
        String storeCode = paraMap.get("storeCode") + "";

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("storeCode", storeCode);
        paramMap.put("openid", openid);
        List<MemberInfoReturnDto> memberInfoReturnDtoList = memberInfoMapper.selectMemberAndCardInfoListByParam(paramMap);

        Map<String, Object> returnMap = new HashMap<String, Object>();
        if (memberInfoReturnDtoList.size() == 0) {
            returnMap.put("code", "0");
            returnMap.put("desc", "未注册会员信息！");
        } else if (memberInfoReturnDtoList.size() == 1) {
            returnMap.put("code", "1");
            returnMap.put("desc", "查询到会员及会员卡信息！");
            returnMap.put("obj", memberInfoReturnDtoList.get(0));
        } else {
            throw new RuntimeException("查询会员及会员卡信息（com.wfj.service.impl.MemberInfoServiceImpl.getMemberInfo）：一个门店下一个会员有两张及以上会员！");
        }

        logger.info("end com.wfj.service.impl.MemberInfoServiceImpl.getMemberInfo(),return:" + returnMap.toString());
        return returnMap;
    }

    /**
     * 修改支付密码
     *
     * @param
     * @return
     */
    @Transactional
    public Map<String, Object> changePayPassword(Map<String, Object> paraMap) throws Exception {
        logger.info("start com.wfj.service.impl.MemberInfoServiceImpl.changePayPassword(),para:" + paraMap.toString());
        String openid = paraMap.get("openid") + "";
        String storeCode = paraMap.get("storeCode") + "";
        String oldPayPwd = paraMap.get("oldPayPwd") + "";
        String newPayPwd = paraMap.get("newPayPwd") + "";

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("openid", openid);
        paramMap.put("storeCode", storeCode);
        List<MemberInfo> memberInfoList = memberInfoMapper.selectListByParam(paramMap);

        Map<String, Object> returnMap = new HashMap<String, Object>();
        if (memberInfoList.size() == 1) {
            MemberInfo memberInfo = memberInfoList.get(0);
            String password = memberInfo.getPassword();
            if (oldPayPwd.equals(password)) {
                memberInfo.setPassword(newPayPwd);//修改密码
                memberInfoMapper.updateByPrimaryKeySelective(memberInfo);
                returnMap.put("success", "true");
                returnMap.put("desc", "修改密码成功！");
            } else {
                returnMap.put("success", "false");
                returnMap.put("desc", "旧密码不正确！");
            }
        } else if (memberInfoList.size() == 0) {
            returnMap.put("success", "false");
            returnMap.put("desc", "未注册会员信息！");
        } else {
            throw new RuntimeException("修改支付密码（com.wfj.service.impl.MemberInfoServiceImpl.changePayPassword）时出现重复会员！");
        }

        logger.info("end com.wfj.service.impl.MemberInfoServiceImpl.changePayPassword(),return:" + returnMap.toString());
        return returnMap;
    }

    /**
     * 查询个人资料(会员信息以及门店信息)
     *
     * @param paraMap
     * @return
     */
    public MemberInfoReturnDto findMemberAndStoreInfoByPara(Map<String, Object> paraMap) {
        logger.info("start com.wfj.service.impl.MemberInfoServiceImpl.findMemberInfoByPara(),para:" + paraMap.toString());
        String openid = paraMap.get("openid") + "";
        String storeCode = paraMap.get("storeCode") + "";

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("openid", openid);
        paramMap.put("storeCode", storeCode);
        List<MemberInfoReturnDto> returnDtoList = memberInfoMapper.findMemberAndStoreInfoByPara(paramMap);
        MemberInfoReturnDto returnDto = new MemberInfoReturnDto();
        if (returnDtoList.size() > 0) {
            returnDto = returnDtoList.get(0);
        }
        logger.info("end com.wfj.service.impl.MemberInfoServiceImpl.findMemberInfoByPara(),return:" + returnDto.toString());
        return returnDto;
    }


    /**
     * 修改会员信息(修改个人资料)
     *
     * @param paraMap
     * @return
     * @throws Exception
     */
    @Transactional
    public Map<String, Object> modifyMemberInfo(MemberInfo memberInfo) throws Exception {
        logger.info("start com.wfj.service.impl.MemberInfoServiceImpl.modifyMemberInfo(),para:" + memberInfo.toString());
        String openid = memberInfo.getOpenid();
        String storeCode = memberInfo.getStoreCode();
        String memberCode = memberInfo.getMemberCode();

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("openid", openid);
        paramMap.put("storeCode", storeCode);
        List<MemberInfo> memberInfoList = memberInfoMapper.selectListByParam(paramMap);

        Map<String, Object> returnMap = new HashMap<String, Object>();
        if (memberInfoList.size() == 1) {
            int i = memberInfoMapper.updateByParaSelective(memberInfo);
            if (i == 1) {
                returnMap.put("success", "true");
                returnMap.put("desc", "修改个人资料成功！");
            } else {
                throw new RuntimeException("修改个人资料（com.wfj.service.impl.MemberInfoServiceImpl.changePayPassword）时操作数据库失败！");
            }
        } else if (memberInfoList.size() == 0) {
            returnMap.put("success", "false");
            returnMap.put("desc", "未注册会员信息！");
        } else {
            throw new RuntimeException("修改会员信息（com.wfj.service.impl.MemberInfoServiceImpl.modifyMemberInfo）时出现重复会员！");
        }

        logger.info("end com.wfj.service.impl.MemberInfoServiceImpl.modifyMemberInfo(),return:" + returnMap.toString());
        return returnMap;
    }

}
