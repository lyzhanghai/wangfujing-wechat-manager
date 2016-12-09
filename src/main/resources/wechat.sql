/*
Navicat MySQL Data Transfer

Source Server         : wfj-sit-pcm写-10.6.2.50
Source Server Version : 50626
Source Host           : 10.6.2.50:3306
Source Database       : wechat

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2016-12-09 15:21:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for appaccountinfo
-- ----------------------------
DROP TABLE IF EXISTS `appaccountinfo`;
CREATE TABLE `appaccountinfo` (
  `sid` bigint(20) NOT NULL AUTO_INCREMENT,
  `appid` varchar(50) DEFAULT NULL,
  `appsecret` varchar(50) DEFAULT NULL,
  `storecode` varchar(20) DEFAULT '0' COMMENT '是否删除：0否，1是',
  `del_flag` int(11) DEFAULT '0' COMMENT '是否删除，1：是，0：否',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='公众号管理';

-- ----------------------------
-- Table structure for coupon_info
-- ----------------------------
DROP TABLE IF EXISTS `coupon_info`;
CREATE TABLE `coupon_info` (
  `sid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '卡券的商户logo，建议像素为300*300。',
  `store_code` varchar(20) DEFAULT NULL,
  `card_type` varchar(24) DEFAULT NULL,
  `logo_url` varchar(128) DEFAULT NULL,
  `code_type` varchar(16) DEFAULT NULL COMMENT 'Code展示类型，"CODE_TYPE_TEXT"，文本；"CODE_TYPE_BARCODE"，一维码 ；"CODE_TYPE_QRCODE"，二维码；"CODE_TYPE_ONLY_QRCODE",二维码无code显示；"CODE_TYPE_ONLY_BARCODE",一维码无code显示；CODE_TYPE_NONE，不显示code和条形码类型，须开发者传入"立即使用"自定义cell完成线上券核销。',
  `brand_name` varchar(36) DEFAULT NULL COMMENT '商户名字,字数上限为12个汉字。',
  `title` varchar(27) DEFAULT NULL COMMENT '卡券名，字数上限为9个汉字。(建议涵盖卡券属性、服务及金额)。',
  `sub_title` varchar(54) DEFAULT NULL COMMENT '券名，字数上限为18个汉字。',
  `color` varchar(16) DEFAULT NULL COMMENT '券颜色',
  `notice` varchar(48) DEFAULT NULL COMMENT '卡券使用提醒，字数上限为16个汉字。',
  `description` text COMMENT '卡券使用说明，字数上限为1024个汉字',
  `quantity` int(11) DEFAULT NULL COMMENT '卡券库存的数量，上限为100000000',
  `type` varchar(50) DEFAULT NULL COMMENT '使用时间的类型',
  `begin_timestamp` timestamp NULL DEFAULT NULL,
  `end_timestamp` timestamp NULL DEFAULT NULL,
  `get_limit` int(11) DEFAULT NULL COMMENT '卡类型:1.GROUPON-团购券;2.CASH-代金券;3.DISCOUNT-折扣券;4.GIFT-兑换券;5.GENERAL_COUPON-优惠券',
  `fixed_term` int(11) DEFAULT NULL,
  `fixed_begin_term` int(11) DEFAULT NULL,
  `coupon_status` varchar(45) DEFAULT NULL,
  `approval_userId` varchar(45) DEFAULT NULL,
  `approval_userName` varchar(45) DEFAULT NULL,
  `approval_time` timestamp NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `create_userId` varchar(45) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_userId` varchar(45) DEFAULT NULL,
  `app_system` varchar(45) DEFAULT NULL COMMENT '使用系统',
  `tpl_sid` int(11) DEFAULT NULL COMMENT '卡券模板SID',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='电子券';

-- ----------------------------
-- Table structure for coupon_rule
-- ----------------------------
DROP TABLE IF EXISTS `coupon_rule`;
CREATE TABLE `coupon_rule` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `NoLength` int(11) DEFAULT NULL,
  `PrefixStr` varchar(45) DEFAULT NULL,
  `StartNo` varchar(45) DEFAULT NULL,
  `SuffixLength` varchar(45) DEFAULT NULL,
  `DjBackground` varchar(45) DEFAULT NULL COMMENT '代金券背景',
  `ZkBackground` varchar(45) DEFAULT NULL COMMENT ' 折扣券背景',
  `LpBackground` varchar(45) DEFAULT NULL COMMENT '礼品券背景',
  `YqBackground` varchar(45) DEFAULT NULL COMMENT ' 邀请券背景',
  `CxBackground` varchar(45) DEFAULT NULL COMMENT '促销券背景',
  `YEBackground` varchar(45) DEFAULT NULL COMMENT '异业券背景 ',
  `create_userId` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_userId` varchar(45) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `filed1` varchar(45) DEFAULT NULL COMMENT '门店编号',
  `filed2` varchar(45) DEFAULT NULL,
  `filed3` varchar(45) DEFAULT NULL COMMENT '门店编号',
  `store_code` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='电子券规则维护';

-- ----------------------------
-- Table structure for coupon_template
-- ----------------------------
DROP TABLE IF EXISTS `coupon_template`;
CREATE TABLE `coupon_template` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `coupon_type` varchar(45) DEFAULT NULL COMMENT '券类型: 1.GROUPON-团购券;2.CASH-代金券;3.DISCOUNT-折扣券;4.GIFT-兑换券;5.GENERAL_COUPON-优惠券',
  `coupon_value` varchar(45) DEFAULT NULL COMMENT '券面值',
  `coupon_price_limit` varchar(45) DEFAULT NULL COMMENT '券使用范围',
  `create_userId` varchar(45) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `ifdel` int(11) DEFAULT NULL COMMENT '是否删除:0否,1是',
  `store_code` varchar(45) DEFAULT NULL,
  `coupon_name` varchar(45) DEFAULT NULL,
  `filed3` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='券模版';

-- ----------------------------
-- Table structure for coupon_type
-- ----------------------------
DROP TABLE IF EXISTS `coupon_type`;
CREATE TABLE `coupon_type` (
  `sid` bigint(20) NOT NULL,
  `coupon_code` varchar(20) DEFAULT NULL,
  `coupon_name` varchar(50) DEFAULT NULL,
  `coupon_bg` varchar(20) DEFAULT NULL,
  `coupon_status` int(255) DEFAULT NULL,
  `filed1` varchar(45) DEFAULT NULL,
  `filed2` varchar(45) DEFAULT NULL,
  `filed3` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for global_dic
-- ----------------------------
DROP TABLE IF EXISTS `global_dic`;
CREATE TABLE `global_dic` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `keyword` varchar(50) DEFAULT NULL COMMENT '数据类型',
  `code` varchar(50) DEFAULT NULL COMMENT '数据编码',
  `name` varchar(50) DEFAULT NULL COMMENT '数据名称',
  `ifdel` int(11) DEFAULT '0' COMMENT '是否删除:0否,1是',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='全局字典';

-- ----------------------------
-- Table structure for ly_buttom
-- ----------------------------
DROP TABLE IF EXISTS `ly_buttom`;
CREATE TABLE `ly_buttom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `buttom` varchar(200) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ly_log
-- ----------------------------
DROP TABLE IF EXISTS `ly_log`;
CREATE TABLE `ly_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountName` varchar(30) DEFAULT NULL,
  `module` varchar(30) DEFAULT NULL,
  `methods` varchar(30) DEFAULT NULL,
  `actionTime` varchar(30) DEFAULT NULL,
  `userIP` varchar(30) DEFAULT NULL,
  `operTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `description` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ly_res_user
-- ----------------------------
DROP TABLE IF EXISTS `ly_res_user`;
CREATE TABLE `ly_res_user` (
  `resId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`resId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ly_resources
-- ----------------------------
DROP TABLE IF EXISTS `ly_resources`;
CREATE TABLE `ly_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `resKey` varchar(50) DEFAULT NULL,
  `type` varchar(40) DEFAULT NULL,
  `resUrl` varchar(200) DEFAULT NULL,
  `level` int(4) DEFAULT NULL,
  `icon` varchar(100) DEFAULT NULL,
  `ishide` int(3) DEFAULT '0',
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ly_role
-- ----------------------------
DROP TABLE IF EXISTS `ly_role`;
CREATE TABLE `ly_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `state` varchar(3) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `roleKey` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ly_server_info
-- ----------------------------
DROP TABLE IF EXISTS `ly_server_info`;
CREATE TABLE `ly_server_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `cpuUsage` varchar(255) DEFAULT NULL,
  `setCpuUsage` varchar(255) DEFAULT NULL,
  `jvmUsage` varchar(255) DEFAULT NULL,
  `setJvmUsage` varchar(255) DEFAULT NULL,
  `ramUsage` varchar(255) DEFAULT NULL,
  `setRamUsage` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `operTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `mark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ly_user
-- ----------------------------
DROP TABLE IF EXISTS `ly_user`;
CREATE TABLE `ly_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `accountName` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `credentialsSalt` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `locked` varchar(3) DEFAULT '0',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deletestatus` int(1) DEFAULT '0' COMMENT '逻辑删除状态0:存在1:删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ly_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ly_user_role`;
CREATE TABLE `ly_user_role` (
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ly_userlogin
-- ----------------------------
DROP TABLE IF EXISTS `ly_userlogin`;
CREATE TABLE `ly_userlogin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `accountName` varchar(20) DEFAULT NULL,
  `loginTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `loginIP` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ly_user_loginlist` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1064 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for member_card
-- ----------------------------
DROP TABLE IF EXISTS `member_card`;
CREATE TABLE `member_card` (
  `sid` bigint(20) NOT NULL AUTO_INCREMENT,
  `store_code` varchar(20) DEFAULT NULL,
  `card_code` varchar(50) DEFAULT NULL,
  `member_code` varchar(20) DEFAULT NULL,
  `card_type` int(11) DEFAULT NULL,
  `card_level` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for member_point
-- ----------------------------
DROP TABLE IF EXISTS `member_point`;
CREATE TABLE `member_point` (
  `sid` bigint(20) NOT NULL AUTO_INCREMENT,
  `store_code` varchar(20) DEFAULT NULL,
  `member_code` varchar(50) DEFAULT NULL,
  `available_points` double DEFAULT NULL,
  `total_points` double DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL COMMENT '删除标记.0:已删除，1未删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for member_point_info
-- ----------------------------
DROP TABLE IF EXISTS `member_point_info`;
CREATE TABLE `member_point_info` (
  `sid` bigint(20) NOT NULL AUTO_INCREMENT,
  `store_code` varchar(20) DEFAULT NULL,
  `member_code` varchar(50) DEFAULT NULL,
  `card_code` varchar(20) DEFAULT NULL,
  `point_type` int(20) DEFAULT NULL COMMENT '积分类型',
  `points` double DEFAULT NULL,
  `point_time` timestamp NULL DEFAULT NULL COMMENT '积分消费时间',
  `status` int(11) DEFAULT NULL,
  `serial_number` varchar(50) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL COMMENT '删除标记.0:已删除，1未删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for memberinfo
-- ----------------------------
DROP TABLE IF EXISTS `memberinfo`;
CREATE TABLE `memberinfo` (
  `sid` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_code` varchar(20) DEFAULT NULL COMMENT '会员编号',
  `store_code` varchar(20) DEFAULT NULL COMMENT '门店编号',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  `subscribe` int(11) DEFAULT NULL COMMENT '1:是;0:否',
  `openid` varchar(50) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `province` varchar(50) DEFAULT NULL,
  `language` varchar(20) DEFAULT NULL,
  `headimgurl` varchar(600) DEFAULT NULL,
  `subscribe_time` varchar(20) DEFAULT NULL,
  `unionid` varchar(30) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `groupid` int(11) DEFAULT NULL,
  `id_card` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for msg_reply
-- ----------------------------
DROP TABLE IF EXISTS `msg_reply`;
CREATE TABLE `msg_reply` (
  `sid` int(11) NOT NULL AUTO_INCREMENT COMMENT '1/关注事件2/文本事件',
  `event_type` varchar(20) DEFAULT NULL COMMENT '事件类型subscribe关注',
  `rule_name` varchar(50) DEFAULT NULL,
  `msg_key` varchar(255) DEFAULT NULL COMMENT '关键字key',
  `msg_type` int(11) NOT NULL DEFAULT '0' COMMENT '回复类型(0文本,1图片,2语音,3视频,4音频,5图文)',
  `content` varchar(255) DEFAULT NULL COMMENT '文本内容',
  `media_id` varchar(255) DEFAULT NULL COMMENT '素材ID',
  `title` varchar(255) DEFAULT NULL COMMENT '视频/音频/图文消息的标题',
  `description` varchar(255) DEFAULT NULL COMMENT '视频/音频/图文消息的描述',
  `music_url` varchar(255) DEFAULT NULL COMMENT '音乐链接',
  `hqmusic_url` varchar(255) DEFAULT NULL COMMENT '高质量音乐链接',
  `thumb_mediald` varchar(255) DEFAULT NULL COMMENT '缩略图素材id',
  `article_count` varchar(255) DEFAULT NULL COMMENT '图文消息个数，限制为10条以内',
  `pic_url` varchar(255) DEFAULT NULL COMMENT '图片链接',
  `url` varchar(255) DEFAULT NULL COMMENT '点击图文消息跳转链接',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for storeinfo
-- ----------------------------
DROP TABLE IF EXISTS `storeinfo`;
CREATE TABLE `storeinfo` (
  `sid` bigint(20) NOT NULL AUTO_INCREMENT,
  `store_code` varchar(20) DEFAULT NULL,
  `poi_id` varchar(20) DEFAULT NULL,
  `business_name` varchar(50) DEFAULT NULL,
  `branch_name` varchar(50) DEFAULT NULL,
  `province` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `district` varchar(50) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `categories` varchar(50) DEFAULT NULL,
  `offset_type` int(11) DEFAULT NULL,
  `longitude` varchar(20) DEFAULT NULL,
  `latitude` varchar(20) DEFAULT NULL,
  `photo_list` varchar(500) DEFAULT NULL,
  `special` varchar(50) DEFAULT NULL,
  `open_time` varchar(50) DEFAULT NULL,
  `avg_price` varchar(20) DEFAULT NULL,
  `introduction` varchar(600) DEFAULT NULL,
  `recommend` varchar(200) DEFAULT NULL,
  `channel_type` int(11) DEFAULT NULL COMMENT '0：非全渠道，1：全渠道',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_authorization_store
-- ----------------------------
DROP TABLE IF EXISTS `user_authorization_store`;
CREATE TABLE `user_authorization_store` (
  `sid` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户名称',
  `store_code` varchar(20) DEFAULT NULL COMMENT '门店编码',
  `business_name` varchar(20) DEFAULT NULL COMMENT '门店名称',
  `is_lose_efficacy` int(2) DEFAULT NULL COMMENT '是否生效 0有效 1无效',
  `user_number` varchar(20) DEFAULT NULL COMMENT '导购工号',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wechat_menu
-- ----------------------------
DROP TABLE IF EXISTS `wechat_menu`;
CREATE TABLE `wechat_menu` (
  `sid` bigint(20) NOT NULL AUTO_INCREMENT,
  `appid` varchar(50) DEFAULT NULL COMMENT 'appid',
  `parent_sid` varchar(50) DEFAULT '0' COMMENT '上级',
  `name` varchar(45) DEFAULT NULL COMMENT '名称',
  `type` varchar(45) DEFAULT NULL COMMENT '类型:0click,1view',
  `clickkey` varchar(255) DEFAULT NULL COMMENT 'key',
  `viewUrl` varchar(500) DEFAULT NULL COMMENT 'URL',
  `order_by` int(11) DEFAULT NULL COMMENT '排序',
  `ifdel` int(11) DEFAULT '0' COMMENT '是否删除：0否，1是',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8;
