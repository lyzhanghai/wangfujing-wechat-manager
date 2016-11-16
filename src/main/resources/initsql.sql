/*
 Navicat Premium Data Transfer

 Source Server         : 10.6.2.43测试
 Source Server Type    : MySQL
 Source Server Version : 50625
 Source Host           : 10.6.2.43
 Source Database       : acldemo

 Target Server Type    : MySQL
 Target Server Version : 50625
 File Encoding         : utf-8

 Date: 11/02/2016 16:08:04 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `ly_buttom`
-- ----------------------------
DROP TABLE IF EXISTS `ly_buttom`;
CREATE TABLE `ly_buttom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `buttom` varchar(200) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `ly_buttom`
-- ----------------------------
BEGIN;
INSERT INTO `ly_buttom` VALUES ('1', '新增', '<button type=\"button\" id=\"addFun\" class=\"btn btn-primary marR10\">新增</button>', ''), ('2', '编辑', '<button type=\"button\" id=\"editFun\" class=\"btn btn-info marR10\">编辑</button>', null), ('3', '删除', '<button type=\"button\" id=\"delFun\" class=\"btn btn-danger marR10\">删除</button>', null), ('4', '上传', '<button type=\"button\" id=\"upLoad\" class=\"btn btn-primary marR10\">上传</button>', null), ('5', '下载', '<button type=\"button\" id=\"downLoad\" class=\"btn btn-primary marR10\">下载</button>', null), ('6', '上移', '<button type=\"button\" id=\"lyGridUp\" class=\"btn btn-success marR10\">上移</button>', null), ('7', '下移', '<button type=\"button\" id=\"lyGridDown\" class=\"btn btn btn-grey marR10\">下移</button>', null), ('8', '分配权限', '<button type=\"button\" id=\"permissions\" class=\"btn btn btn-warning marR10\">分配权限</button>', null);
COMMIT;

-- ----------------------------
--  Table structure for `ly_log`
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
--  Table structure for `ly_res_user`
-- ----------------------------
DROP TABLE IF EXISTS `ly_res_user`;
CREATE TABLE `ly_res_user` (
  `resId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`resId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `ly_res_user`
-- ----------------------------
BEGIN;
INSERT INTO `ly_res_user` VALUES ('1', '3'), ('2', '3'), ('3', '3'), ('4', '3'), ('5', '3'), ('6', '3'), ('7', '3'), ('8', '3'), ('9', '3'), ('10', '3'), ('11', '3'), ('25', '3'), ('26', '3'), ('27', '3'), ('28', '3'), ('29', '3'), ('30', '3'), ('31', '3'), ('32', '3'), ('33', '3'), ('34', '3'), ('35', '3'), ('38', '3');
COMMIT;

-- ----------------------------
--  Table structure for `ly_resources`
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
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `ly_resources`
-- ----------------------------
BEGIN;
INSERT INTO `ly_resources` VALUES ('1', '系统基础管理', '0', 'system', '0', 'system', '1', 'icon-settings', '0', '系统基础管理'), ('2', '用户管理', '1', 'account', '1', '/user/list.shtml', '2', 'icon-settings', '0', null), ('3', '角色管理', '1', 'role', '1', '/role/list.shtml', '7', 'icon-settings', '0', '组管理'), ('4', '菜单管理', '1', 'ly_resources', '1', '/resources/list.shtml', '12', 'icon-settings', '0', '菜单管理'), ('5', '新增用户', '2', 'account_add', '2', '/user/addUI.shtml', '3', null, '0', '&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;addAccount&quot;&nbsp;class=&quot;btn&nbsp;btn-primary&nbsp;marR10&quot;&gt;新增&lt;/button&gt;'), ('6', '修改用户', '2', 'account_edit', '2', '/user/editUI.shtml', '4', null, '0', '&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;editAccount&quot;&nbsp;class=&quot;btn&nbsp;btn-info&nbsp;marR10&quot;&gt;编辑&lt;/button&gt;'), ('7', '删除用户', '2', 'account_delete', '2', '/user/deleteById.shtml', '5', null, '0', '&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;delAccount&quot;&nbsp;class=&quot;btn&nbsp;btn-danger&nbsp;marR10&quot;&gt;删除&lt;/button&gt;'), ('8', '新增角色', '3', 'role_add', '2', '/role/addUI.shtml', '8', null, '0', '&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;addRole&quot;&nbsp;class=&quot;btn&nbsp;btn-primary&nbsp;marR10&quot;&gt;新增&lt;/button&gt;'), ('9', '修改角色', '3', 'role_edit', '2', '/role/editUI.shtml', '9', null, '0', '&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;editRole&quot;&nbsp;class=&quot;btn&nbsp;btn-info&nbsp;marR10&quot;&gt;编辑&lt;/button&gt;'), ('10', '删除角色', '3', 'role_delete', '2', '/role/delete.shtml', '10', null, '0', '&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;delRole&quot;&nbsp;class=&quot;btn&nbsp;btn-danger&nbsp;marR10&quot;&gt;删除&lt;/button&gt;'), ('11', '分配权限', '3', 'role_perss', '2', '/resources/permissions.shtml', '11', '', '0', '&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;permissions&quot;&nbsp;class=&quot;btn&nbsp;btn&nbsp;btn-warning&nbsp;marR10&quot;&gt;分配权限&lt;/button&gt;'), ('25', '登陆信息管理', '0', 'ly_login', '0', 'ly_login', '18', 'icon-notebook', '0', '登陆信息管理'), ('26', '用户登录记录', '25', 'ly_log_list', '1', '/userlogin/listUI.shtml', '19', 'icon-notebook', '0', '用户登录记录'), ('27', '操作日志管理', '0', 'ly_log', '0', 'ly_log', '20', 'icon-logout ', '1', '操作日志管理'), ('28', '日志查询', '27', 'ly_log', '1', '/log/list.shtml', '21', 'icon-logout ', '0', null), ('29', '新增菜单资源', '4', 'ly_resources_add', '2', '/resources/addUI.shtml', '13', null, '0', '&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;addFun&quot;&nbsp;class=&quot;btn&nbsp;btn-primary&nbsp;marR10&quot;&gt;新增&lt;/button&gt;'), ('30', '修改菜单资源', '4', 'ly_resources_edit', '2', '/resources/editUI.shtml', '14', null, '0', '&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;editFun&quot;&nbsp;class=&quot;btn&nbsp;btn-info&nbsp;marR10&quot;&gt;编辑&lt;/button&gt;'), ('31', '删除菜单资源', '4', 'ly_resources_delete', '2', '/resources/delete.shtml', '15', null, '0', '&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;delFun&quot;&nbsp;class=&quot;btn&nbsp;btn-danger&nbsp;marR10&quot;&gt;删除&lt;/button&gt;'), ('32', '系统监控管理', '0', 'monitor', '0', 'monitor', '16', 'icon-screen-desktop', '0', '系统监控管理'), ('33', '实时监控', '32', 'sysmonitor', '1', '/monitor/monitor.shtml', '17', 'icon-screen-desktop', '0', '实时监控'), ('34', '分配权限', '2', 'permissions', '2', '/resources/permissions.shtml', '6', null, '0', '&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;permissions&quot;&nbsp;class=&quot;btn&nbsp;btn&nbsp;btn-warning&nbsp;marR10&quot;&gt;分配权限&lt;/button&gt;'), ('35', '告警列表', '32', 'monitor_warn', '1', '/monitor/list.shtml', null, 'icon-screen-desktop', '0', '告警列表');
COMMIT;

-- ----------------------------
--  Table structure for `ly_role`
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
--  Records of `ly_role`
-- ----------------------------
BEGIN;
INSERT INTO `ly_role` VALUES ('1', '0', '管理员', 'admin', '管理员'), ('2', '0', '普通角色', 'simple', '普通角色'), ('3', '0', '超级管理员', 'SUPER', '超级管理员');
COMMIT;

-- ----------------------------
--  Table structure for `ly_server_info`
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
--  Records of `ly_server_info`
-- ----------------------------
BEGIN;
INSERT INTO `ly_server_info` VALUES ('5', '18', '40', '49', '40', '71', '40', '121261494@qq.com', '2015-04-25 18:07:02', '<font color=\"red\">JVM当前：49%,超出预设值 40%<br>内存当前：71%,超出预设值  40%</font>'), ('6', '3', '40', '50', '40', '71', '40', '121261494@qq.com', '2015-04-25 18:08:03', '<font color=\"red\">JVM当前：50%,超出预设值 40%<br>内存当前：71%,超出预设值  40%</font>'), ('7', '5', '40', '50', '40', '70', '40', '121261494@qq.com', '2015-04-25 18:09:02', '<font color=\"red\">JVM当前：50%,超出预设值 40%<br>内存当前：70%,超出预设值  40%</font>'), ('8', '5', '40', '52', '40', '69', '40', '121261494@qq.com', '2015-04-25 18:10:03', '<font color=\"red\">JVM当前：52%,超出预设值 40%<br>内存当前：69%,超出预设值  40%</font>'), ('9', '2', '40', '52', '40', '68', '40', '121261494@qq.com', '2015-04-25 18:11:02', '<font color=\"red\">JVM当前：52%,超出预设值 40%<br>内存当前：68%,超出预设值  40%</font>'), ('10', '7', '40', '53', '40', '67', '40', '121261494@qq.com', '2015-04-25 18:12:02', '<font color=\"red\">JVM当前：53%,超出预设值 40%<br>内存当前：67%,超出预设值  40%</font>'), ('11', '5', '40', '54', '40', '67', '40', '121261494@qq.com', '2015-04-25 18:13:02', '<font color=\"red\">JVM当前：54%,超出预设值 40%<br>内存当前：67%,超出预设值  40%</font>'), ('12', '16', '40', '55', '40', '66', '40', '121261494@qq.com', '2015-04-25 18:14:02', '<font color=\"red\">JVM当前：55%,超出预设值 40%<br>内存当前：66%,超出预设值  40%</font>'), ('13', '5', '40', '56', '40', '65', '40', '121261494@qq.com', '2015-04-25 18:15:02', '<font color=\"red\">JVM当前：56%,超出预设值 40%<br>内存当前：65%,超出预设值  40%</font>'), ('14', '8', '40', '57', '40', '64', '40', '121261494@qq.com', '2015-04-25 18:16:02', '<font color=\"red\">JVM当前：57%,超出预设值 40%<br>内存当前：64%,超出预设值  40%</font>'), ('15', '3', '40', '58', '40', '63', '40', '121261494@qq.com', '2015-04-25 18:17:02', '<font color=\"red\">JVM当前：58%,超出预设值 40%<br>内存当前：63%,超出预设值  40%</font>'), ('16', '6', '40', '59', '40', '62', '40', '121261494@qq.com', '2015-04-25 18:18:03', '<font color=\"red\">JVM当前：59%,超出预设值 40%<br>内存当前：62%,超出预设值  40%</font>'), ('17', '1', '40', '60', '40', '61', '40', '121261494@qq.com', '2015-04-25 18:19:02', '<font color=\"red\">JVM当前：60%,超出预设值 40%<br>内存当前：61%,超出预设值  40%</font>'), ('18', '5', '40', '61', '40', '61', '40', '121261494@qq.com', '2015-04-25 18:20:02', '<font color=\"red\">JVM当前：61%,超出预设值 40%<br>内存当前：61%,超出预设值  40%</font>'), ('19', '5', '40', '38', '40', '61', '40', '121261494@qq.com', '2015-04-25 18:21:02', '<font color=\"red\">内存当前：61%,超出预设值  40%</font>'), ('20', '5', '40', '39', '40', '60', '40', '121261494@qq.com', '2015-04-25 18:22:02', '<font color=\"red\">内存当前：60%,超出预设值  40%</font>'), ('21', '4', '40', '40', '40', '59', '40', '121261494@qq.com', '2015-04-25 18:23:02', '<font color=\"red\">内存当前：59%,超出预设值  40%</font>'), ('22', '32', '80', '41', '80', '81', '80', '121261494@qq.com', '2015-04-26 01:43:05', '<font color=\"red\">内存当前：81%,超出预设值  80%</font>'), ('23', '55', '80', '55', '80', '81', '80', '121261494@qq.com', '2015-04-26 01:50:03', '<font color=\"red\">内存当前：81%,超出预设值  80%</font>'), ('24', '13', '80', '53', '80', '81', '80', '121261494@qq.com', '2015-04-26 01:59:08', '<font color=\"red\">内存当前：81%,超出预设值  80%</font>'), ('25', '85', '80', '58', '80', '72', '80', '121261494@qq.com', '2015-04-26 02:46:06', '<font color=\"red\">CPU当前：85%,超出预设值  80%<br></font>'), ('26', '34', '80', '59', '80', '81', '80', '121261494@qq.com', '2015-04-27 00:29:06', '<font color=\"red\">内存当前：81%,超出预设值  80%</font>'), ('27', '92', '80', '47', '80', '64', '80', '121261494@qq.com', '2015-04-27 00:44:07', '<font color=\"red\">CPU当前：92%,超出预设值  80%<br></font>'), ('28', '85', '80', '49', '80', '68', '80', '121261494@qq.com', '2015-04-27 23:38:04', '<font color=\"red\">CPU当前：85%,超出预设值  80%<br></font>'), ('29', '94', '80', '69', '80', '73', '80', '121261494@qq.com', '2015-04-28 01:35:03', '<font color=\"red\">CPU当前：94%,超出预设值  80%<br></font>'), ('30', '6', '80', '43', '80', '87', '80', '121261494@qq.com', '2015-05-09 00:00:08', '<font color=\"red\">内存当前：87%,超出预设值  80%</font>'), ('31', '88', '80', '59', '80', '87', '80', '121261494@qq.com', '2015-05-09 00:01:14', '<font color=\"red\">CPU当前：88%,超出预设值  80%<br>内存当前：87%,超出预设值  80%</font>');
COMMIT;

-- ----------------------------
--  Table structure for `ly_user`
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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `ly_user`
-- ----------------------------
BEGIN;
INSERT INTO `ly_user` VALUES ('1', '蓝缘', 'simple', '78e21a6eb88529eab722793a448ed394', '4157c3feef4a6ed91b2c28cf4392f2d1', '0', '1', '2015-05-17 22:23:15', '0'), ('2', '超级管理员', 'ROOT', '78e21a6eb88529eab722793a448ed394', '4157c3feef4a6ed91b2c28cf4392f2d1', '0000', '1', '2015-05-23 17:39:37', '0'), ('3', '管理员', 'admin', '78e21a6eb88529eab722793a448ed394', '4157c3feef4a6ed91b2c28cf4392f2d1', '3434', '1', '2015-05-23 17:39:39', '0'), ('31', 'test', 'test', '4ecc59a763d64820db4e100fd1398f59', '2e47a5c3868bc901d9aa108b92c212dd', null, '0', '2016-10-24 15:51:20', '0');
COMMIT;

-- ----------------------------
--  Table structure for `ly_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `ly_user_role`;
CREATE TABLE `ly_user_role` (
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `ly_user_role`
-- ----------------------------
BEGIN;
INSERT INTO `ly_user_role` VALUES ('1', '2'), ('2', '3'), ('3', '1');
COMMIT;

-- ----------------------------
--  Table structure for `ly_userlogin`
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
) ENGINE=InnoDB AUTO_INCREMENT=267 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `ly_userlogin`
-- ----------------------------
BEGIN;
INSERT INTO `ly_userlogin` VALUES ('143', '3', 'admin', '2016-08-16 16:05:36', '127.0.0.1'), ('144', '3', 'admin', '2016-08-16 16:39:15', '127.0.0.1'), ('145', '3', 'admin', '2016-08-16 17:25:47', '0:0:0:0:0:0:0:1'), ('146', '3', 'admin', '2016-08-17 10:47:16', '0:0:0:0:0:0:0:1'), ('147', '3', 'admin', '2016-08-17 14:20:38', '0:0:0:0:0:0:0:1'), ('148', '3', 'admin', '2016-08-17 14:27:33', '0:0:0:0:0:0:0:1'), ('149', '3', 'admin', '2016-08-17 15:00:14', '0:0:0:0:0:0:0:1'), ('150', '3', 'admin', '2016-08-17 15:33:45', '0:0:0:0:0:0:0:1'), ('151', '3', 'admin', '2016-08-17 15:38:19', '0:0:0:0:0:0:0:1'), ('152', '3', 'admin', '2016-08-17 16:44:49', '0:0:0:0:0:0:0:1'), ('153', '3', 'admin', '2016-08-17 16:54:18', '0:0:0:0:0:0:0:1'), ('154', '3', 'admin', '2016-08-18 14:43:41', '0:0:0:0:0:0:0:1'), ('155', '3', 'admin', '2016-09-08 22:25:43', '127.0.0.1'), ('156', '3', 'admin', '2016-09-08 23:02:34', '127.0.0.1'), ('157', '3', 'admin', '2016-09-08 23:02:46', '0:0:0:0:0:0:0:1'), ('158', '3', 'admin', '2016-09-08 23:25:11', '0:0:0:0:0:0:0:1'), ('159', '3', 'admin', '2016-09-08 23:26:22', '0:0:0:0:0:0:0:1'), ('160', '3', 'admin', '2016-09-08 23:54:14', '0:0:0:0:0:0:0:1'), ('161', '3', 'admin', '2016-09-09 01:18:51', '0:0:0:0:0:0:0:1'), ('162', '3', 'admin', '2016-09-09 06:52:40', '0:0:0:0:0:0:0:1'), ('163', '3', 'admin', '2016-09-09 14:46:04', '0:0:0:0:0:0:0:1'), ('164', '3', 'admin', '2016-09-12 16:31:11', '0:0:0:0:0:0:0:1'), ('165', '3', 'admin', '2016-09-12 19:14:39', '0:0:0:0:0:0:0:1'), ('166', '3', 'admin', '2016-10-20 11:37:14', '0:0:0:0:0:0:0:1'), ('167', '3', 'admin', '2016-10-20 14:15:52', '127.0.0.1'), ('168', '3', 'admin', '2016-10-20 14:54:24', '127.0.0.1'), ('169', '3', 'admin', '2016-10-20 15:38:50', '0:0:0:0:0:0:0:1'), ('170', '3', 'admin', '2016-10-20 15:54:55', '127.0.0.1'), ('171', '3', 'admin', '2016-10-20 16:11:55', '0:0:0:0:0:0:0:1'), ('172', '3', 'admin', '2016-10-20 16:53:48', '0:0:0:0:0:0:0:1'), ('173', '3', 'admin', '2016-10-20 17:49:40', '127.0.0.1'), ('174', '3', 'admin', '2016-10-21 09:36:12', '127.0.0.1'), ('175', '3', 'admin', '2016-10-21 10:05:12', '127.0.0.1'), ('176', '3', 'admin', '2016-10-21 11:40:53', '127.0.0.1'), ('177', '3', 'admin', '2016-10-21 12:58:06', '127.0.0.1'), ('178', '3', 'admin', '2016-10-21 13:31:06', '127.0.0.1'), ('179', '3', 'admin', '2016-10-21 13:59:36', '127.0.0.1'), ('180', '3', 'admin', '2016-10-21 14:41:53', '127.0.0.1'), ('181', '3', 'admin', '2016-10-21 14:57:31', '127.0.0.1'), ('182', '3', 'admin', '2016-10-21 16:05:05', '127.0.0.1'), ('183', '3', 'admin', '2016-10-21 16:53:40', '127.0.0.1'), ('184', '3', 'admin', '2016-10-24 09:31:41', '127.0.0.1'), ('185', '3', 'admin', '2016-10-24 10:46:18', '127.0.0.1'), ('186', '3', 'admin', '2016-10-24 11:36:24', '127.0.0.1'), ('187', '3', 'admin', '2016-10-24 11:36:28', '0:0:0:0:0:0:0:1'), ('188', '3', 'admin', '2016-10-24 12:37:19', '127.0.0.1'), ('189', '3', 'admin', '2016-10-24 12:53:43', '127.0.0.1'), ('190', '3', 'admin', '2016-10-24 14:24:02', '0:0:0:0:0:0:0:1'), ('191', '3', 'admin', '2016-10-24 14:27:09', '127.0.0.1'), ('192', '3', 'admin', '2016-10-24 14:55:48', '0:0:0:0:0:0:0:1'), ('193', '3', 'admin', '2016-10-24 15:10:41', '127.0.0.1'), ('194', '3', 'admin', '2016-10-24 15:23:02', '0:0:0:0:0:0:0:1'), ('195', '3', 'admin', '2016-10-24 15:27:56', '0:0:0:0:0:0:0:1'), ('196', '3', 'admin', '2016-10-24 16:00:54', '0:0:0:0:0:0:0:1'), ('197', '3', 'admin', '2016-10-24 16:16:16', '127.0.0.1'), ('198', '3', 'admin', '2016-10-24 16:54:03', '0:0:0:0:0:0:0:1'), ('199', '3', 'admin', '2016-10-24 17:01:32', '127.0.0.1'), ('200', '3', 'admin', '2016-10-24 17:48:33', '0:0:0:0:0:0:0:1'), ('201', '3', 'admin', '2016-10-24 18:01:52', '127.0.0.1'), ('202', '3', 'admin', '2016-10-25 09:36:10', '127.0.0.1'), ('203', '3', 'admin', '2016-10-25 09:56:41', '127.0.0.1'), ('204', '3', 'admin', '2016-10-25 10:26:31', '127.0.0.1'), ('205', '3', 'admin', '2016-10-25 10:35:07', '0:0:0:0:0:0:0:1'), ('206', '3', 'admin', '2016-10-25 10:39:42', '127.0.0.1'), ('207', '3', 'admin', '2016-10-25 10:44:07', '127.0.0.1'), ('208', '3', 'admin', '2016-10-25 10:50:44', '127.0.0.1'), ('209', '3', 'admin', '2016-10-25 10:53:34', '127.0.0.1'), ('210', '3', 'admin', '2016-10-25 10:59:21', '127.0.0.1'), ('211', '3', 'admin', '2016-10-25 11:00:58', '127.0.0.1'), ('212', '3', 'admin', '2016-10-25 11:04:30', '127.0.0.1'), ('213', '3', 'admin', '2016-10-25 11:06:55', '127.0.0.1'), ('214', '3', 'admin', '2016-10-25 11:23:31', '0:0:0:0:0:0:0:1'), ('215', '3', 'admin', '2016-10-25 11:24:27', '0:0:0:0:0:0:0:1'), ('216', '3', 'admin', '2016-10-25 11:49:29', '127.0.0.1'), ('217', '3', 'admin', '2016-10-25 11:50:49', '127.0.0.1'), ('218', '3', 'admin', '2016-10-25 13:04:47', '127.0.0.1'), ('219', '3', 'admin', '2016-10-25 13:20:20', '127.0.0.1'), ('220', '3', 'admin', '2016-10-25 13:28:28', '127.0.0.1'), ('221', '3', 'admin', '2016-10-25 14:09:05', '127.0.0.1'), ('222', '3', 'admin', '2016-10-25 14:13:37', '127.0.0.1'), ('223', '3', 'admin', '2016-10-25 14:15:58', '127.0.0.1'), ('224', '3', 'admin', '2016-10-25 14:17:35', '127.0.0.1'), ('225', '3', 'admin', '2016-10-25 14:52:43', '127.0.0.1'), ('226', '3', 'admin', '2016-10-25 15:26:00', '127.0.0.1'), ('227', '3', 'admin', '2016-10-25 15:35:05', '127.0.0.1'), ('228', '3', 'admin', '2016-10-25 15:46:31', '127.0.0.1'), ('229', '3', 'admin', '2016-10-25 16:46:42', '127.0.0.1'), ('230', '3', 'admin', '2016-10-25 16:50:18', '127.0.0.1'), ('231', '3', 'admin', '2016-10-25 17:11:59', '127.0.0.1'), ('232', '3', 'admin', '2016-10-25 17:13:30', '127.0.0.1'), ('233', '3', 'admin', '2016-10-25 17:24:27', '127.0.0.1'), ('234', '3', 'admin', '2016-10-25 17:26:00', '127.0.0.1'), ('235', '3', 'admin', '2016-10-25 17:41:30', '127.0.0.1'), ('236', '3', 'admin', '2016-10-26 09:37:42', '127.0.0.1'), ('237', '3', 'admin', '2016-10-26 12:00:20', '127.0.0.1'), ('238', '3', 'admin', '2016-10-26 12:54:43', '127.0.0.1'), ('239', '3', 'admin', '2016-10-26 14:36:09', '127.0.0.1'), ('240', '3', 'admin', '2016-10-26 14:43:12', '127.0.0.1'), ('241', '3', 'admin', '2016-10-26 15:21:15', '127.0.0.1'), ('242', '3', 'admin', '2016-10-26 15:36:53', '0:0:0:0:0:0:0:1'), ('243', '3', 'admin', '2016-10-26 15:40:03', '0:0:0:0:0:0:0:1'), ('244', '3', 'admin', '2016-10-26 16:46:53', '127.0.0.1'), ('245', '3', 'admin', '2016-10-26 17:11:46', '127.0.0.1'), ('246', '3', 'admin', '2016-10-26 17:23:28', '127.0.0.1'), ('247', '3', 'admin', '2016-10-26 17:24:38', '127.0.0.1'), ('248', '3', 'admin', '2016-10-27 09:27:07', '127.0.0.1'), ('249', '3', 'admin', '2016-10-27 11:35:03', '127.0.0.1'), ('250', '3', 'admin', '2016-10-27 13:22:19', '127.0.0.1'), ('251', '3', 'admin', '2016-10-27 14:06:59', '127.0.0.1'), ('252', '3', 'admin', '2016-10-27 14:22:39', '127.0.0.1'), ('253', '3', 'admin', '2016-10-27 16:03:52', '127.0.0.1'), ('254', '3', 'admin', '2016-10-28 14:21:15', '127.0.0.1'), ('255', '3', 'admin', '2016-10-31 14:31:07', '127.0.0.1'), ('256', '3', 'admin', '2016-10-31 15:18:21', '127.0.0.1'), ('257', '3', 'admin', '2016-10-31 15:18:45', '127.0.0.1'), ('258', '3', 'admin', '2016-10-31 15:21:53', '127.0.0.1'), ('259', '3', 'admin', '2016-10-31 15:23:07', '127.0.0.1'), ('260', '3', 'admin', '2016-10-31 15:26:00', '127.0.0.1'), ('261', '3', 'admin', '2016-10-31 15:28:10', '127.0.0.1'), ('262', '3', 'admin', '2016-10-31 15:44:11', '127.0.0.1'), ('263', '3', 'admin', '2016-10-31 15:45:30', '127.0.0.1'), ('264', '3', 'admin', '2016-11-01 10:40:26', '127.0.0.1'), ('265', '3', 'admin', '2016-11-01 15:08:49', '127.0.0.1'), ('266', '3', 'admin', '2016-11-01 15:16:37', '127.0.0.1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
