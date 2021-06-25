/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : zuhao

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2020-05-08 13:30:20
*/

SET
FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for gm_answering
-- ----------------------------
DROP TABLE IF EXISTS `gm_answering`;
CREATE TABLE `gm_answering`
(
    `id`       varchar(36) COLLATE utf8_bin NOT NULL,
    `content`  varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `time`     datetime                      DEFAULT NULL,
    `userName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `userImg`  varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `leaveId`  varchar(36) COLLATE utf8_bin  DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gm_answering
-- ----------------------------
INSERT INTO `gm_answering`
VALUES ('0f4fc0c3eb604e9593ccdec5e9ed3d7c', 'dddd', '2020-05-07 18:51:07', 'user456', '/image/game2.jpg',
        '375fefa16dbc4d92af9497e1ba5c984f');

-- ----------------------------
-- Table structure for gm_arguments
-- ----------------------------
DROP TABLE IF EXISTS `gm_arguments`;
CREATE TABLE `gm_arguments`
(
    `id`        varchar(36) COLLATE utf8_bin NOT NULL,
    `colorMain` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gm_arguments
-- ----------------------------
INSERT INTO `gm_arguments`
VALUES ('1', 'sunflower');

-- ----------------------------
-- Table structure for gm_chdclassify
-- ----------------------------
DROP TABLE IF EXISTS `gm_chdclassify`;
CREATE TABLE `gm_chdclassify`
(
    `id`   varchar(36) COLLATE utf8_bin NOT NULL,
    `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `ptId` varchar(36) COLLATE utf8_bin  DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gm_chdclassify
-- ----------------------------
INSERT INTO `gm_chdclassify`
VALUES ('0f1c1026f16145f58ccdedc0a5a2efe5', '西宁', '40e4776bba9442a49df2ac069714b54b');
INSERT INTO `gm_chdclassify`
VALUES ('75d205bd8fcb417da3a8b7acb53b0b8b', '西安', '1bd91c7d958b4f1993c68870c52e26d7');
INSERT INTO `gm_chdclassify`
VALUES ('814a2b6edd804131a384a6a30eccfee5', '大理', '8845f3d1b88a4632b4532a55c62a6b7e');
INSERT INTO `gm_chdclassify`
VALUES ('87a0f2b0ba0c495782bff6c5f3a8cd5a', '门源', '40e4776bba9442a49df2ac069714b54b');
INSERT INTO `gm_chdclassify`
VALUES ('f7e1171bbaeb47d49e0263da90522181', '咸阳', '1bd91c7d958b4f1993c68870c52e26d7');

-- ----------------------------
-- Table structure for gm_classify
-- ----------------------------
DROP TABLE IF EXISTS `gm_classify`;
CREATE TABLE `gm_classify`
(
    `id`   varchar(36) COLLATE utf8_bin NOT NULL,
    `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gm_classify
-- ----------------------------
INSERT INTO `gm_classify`
VALUES ('1bd91c7d958b4f1993c68870c52e26d7', '陕西');
INSERT INTO `gm_classify`
VALUES ('40e4776bba9442a49df2ac069714b54b', '青海');
INSERT INTO `gm_classify`
VALUES ('8845f3d1b88a4632b4532a55c62a6b7e', '云南');

-- ----------------------------
-- Table structure for gm_collect
-- ----------------------------
DROP TABLE IF EXISTS `gm_collect`;
CREATE TABLE `gm_collect`
(
    `id`     varchar(36) COLLATE utf8_bin NOT NULL,
    `userId` varchar(36) COLLATE utf8_bin DEFAULT NULL,
    `postId` varchar(36) COLLATE utf8_bin DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gm_collect
-- ----------------------------
INSERT INTO `gm_collect`
VALUES ('38eb62b6906946ab9be50a377796ce43', '1', '705fd3adb7f34acf968e2a9bc10ee846');
INSERT INTO `gm_collect`
VALUES ('ffa93a3d338e4e66a146c882b8aab6d6', 'ff868bf2bade4a388771e7015db99fd7', 'adb602103f924f1082b700a0ce3d2c9b');

-- ----------------------------
-- Table structure for gm_comment
-- ----------------------------
DROP TABLE IF EXISTS `gm_comment`;
CREATE TABLE `gm_comment`
(
    `id`      varchar(36) COLLATE utf8_bin NOT NULL,
    `content` longtext COLLATE utf8_bin,
    `time`    datetime                     DEFAULT NULL,
    `userId`  varchar(36) COLLATE utf8_bin DEFAULT NULL,
    `postId`  varchar(36) COLLATE utf8_bin DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gm_comment
-- ----------------------------
INSERT INTO `gm_comment`
VALUES ('aa957d51eda24b488ee4454270b70354', 0x616161, '2020-05-07 18:52:25', 'ff868bf2bade4a388771e7015db99fd7',
        'adb602103f924f1082b700a0ce3d2c9b');

-- ----------------------------
-- Table structure for gm_leaveword
-- ----------------------------
DROP TABLE IF EXISTS `gm_leaveword`;
CREATE TABLE `gm_leaveword`
(
    `id`       varchar(36) COLLATE utf8_bin NOT NULL,
    `content`  longtext COLLATE utf8_bin,
    `time`     datetime                      DEFAULT NULL,
    `userName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `userImg`  varchar(255) COLLATE utf8_bin DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gm_leaveword
-- ----------------------------
INSERT INTO `gm_leaveword`
VALUES ('375fefa16dbc4d92af9497e1ba5c984f', 0xE59F83E8BE9BE794A8E688B7E6B58BE8AF95E79599E8A880, '2020-04-05 20:44:21',
        '埃辛123', '/image/da6f5ef9bc0a617bca6d1ecafb29ee1d.jpg');
INSERT INTO `gm_leaveword`
VALUES ('9d64406fa54a4d4aa431d5f6721cf1fc', 0x6164617364617364, '2020-05-07 18:51:02', 'user456', '/image/game2.jpg');

-- ----------------------------
-- Table structure for gm_log
-- ----------------------------
DROP TABLE IF EXISTS `gm_log`;
CREATE TABLE `gm_log`
(
    `id`        varchar(36) COLLATE utf8_bin NOT NULL,
    `userName`  varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `operation` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `time`      datetime                      DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gm_log
-- ----------------------------
INSERT INTO `gm_log`
VALUES ('104a06f7e78b4c90aa4fa716cb94462d', 'user123', '登录操作', '2020-05-07 18:51:27');
INSERT INTO `gm_log`
VALUES ('425dee4837294c6898931ce42df10eb8', 'user123', '登录操作', '2020-05-07 18:47:50');
INSERT INTO `gm_log`
VALUES ('47e066dbb1144521ab8509af37e7c3b9', 'user456', '登录操作', '2020-05-07 18:50:59');
INSERT INTO `gm_log`
VALUES ('4c299b1f97ca46908ee1bd24b1ef66b1', 'admin', '登录操作', '2020-05-07 18:50:09');
INSERT INTO `gm_log`
VALUES ('722d8ebbe5ca41f48938143a76b3d9e6', 'user456', '登录操作', '2020-05-07 18:51:48');
INSERT INTO `gm_log`
VALUES ('c6ffdd24c4884f77a2c88a1b7b0dae8b', 'admin', '登录操作', '2020-05-07 18:53:26');

-- ----------------------------
-- Table structure for gm_loginreg
-- ----------------------------
DROP TABLE IF EXISTS `gm_loginreg`;
CREATE TABLE `gm_loginreg`
(
    `id`            varchar(36) COLLATE utf8_bin NOT NULL,
    `logBackground` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `regBackground` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `isImg`         varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `isRealName`    varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `isSex`         varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `isEmail`       varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `isAddress`     varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `isIdCard`      varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `isPhone`       varchar(255) COLLATE utf8_bin DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gm_loginreg
-- ----------------------------
INSERT INTO `gm_loginreg`
VALUES ('1', '/image/f4d15da679701e4aaeedcb5d9f7b1178.jpeg', '/image/f4d15da679701e4aaeedcb5d9f7b1178.jpeg', 'on', 'on',
        'on', 'on', 'on', 'off', 'on');

-- ----------------------------
-- Table structure for gm_order
-- ----------------------------
DROP TABLE IF EXISTS `gm_order`;
CREATE TABLE `gm_order`
(
    `id`         varchar(36) COLLATE utf8_bin NOT NULL,
    `postId`     varchar(36) COLLATE utf8_bin  DEFAULT NULL,
    `category`   varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `hours`      int(11) DEFAULT NULL,
    `totalPrice` float(11, 1
) DEFAULT NULL,
  `price` float(11,1) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `userName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gm_order
-- ----------------------------
INSERT INTO `gm_order`
VALUES ('9138774c6f7545b48a41cf0c7ab5f3f2', 'adb602103f924f1082b700a0ce3d2c9b', '英雄联盟', '2', '30.0', '15.0',
        '2020-05-07 18:53:01', 'user456');

-- ----------------------------
-- Table structure for gm_permission
-- ----------------------------
DROP TABLE IF EXISTS `gm_permission`;
CREATE TABLE `gm_permission`
(
    `permissionId`   varchar(36)  NOT NULL,
    `permissionName` varchar(255) NOT NULL,
    `permissionMark` varchar(255) DEFAULT NULL,
    `permissionType` varchar(255) DEFAULT NULL,
    `parentId`       varchar(255) DEFAULT NULL,
    `url`            varchar(255) DEFAULT NULL,
    `priority`       int(11) DEFAULT NULL,
    `available`      int(11) DEFAULT NULL,
    `createTime`     datetime     DEFAULT NULL,
    `lastTime`       datetime     DEFAULT NULL,
    `description`    varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gm_permission
-- ----------------------------
INSERT INTO `gm_permission`
VALUES ('3', '系统设置', 'menu:sysSet', 'menu', '1', '/wbem/', '5', '1', '2017-10-03 15:22:50', '2017-10-03 15:22:50', '');
INSERT INTO `gm_permission`
VALUES ('5', '资源管理', 'menu:sysSet:org:org', 'menu', '3', '/menu/menuManagerHouse.do', '0', '1', '2018-01-22 11:39:03',
        '2018-01-22 11:39:03', '');
INSERT INTO `gm_permission`
VALUES ('6', '权限管理', 'menu:sysSet:module:module:module', 'menu', '3', '/module/moduleManagerHouse.do', '30', '1',
        '2018-01-22 11:38:43', '2018-01-22 11:38:43', '');
INSERT INTO `gm_permission`
VALUES ('7', '用户管理', 'menu:sysSet:user:user', 'menu', '3', '/user/userManagerHouse.do', '5', '1', '2016-06-13 16:23:30',
        '2016-06-13 16:23:30', '');
INSERT INTO `gm_permission`
VALUES ('1', '菜单顶级', 'subsystem:system', 'subsystem', '0', '', '1000', '1', '2018-01-22 11:37:36',
        '2018-01-22 11:37:36', 'nothing');
INSERT INTO `gm_permission`
VALUES ('4', '日志管理', 'menu:sysSet:log', 'menu', '3', '/log/index.do', null, '1', '2019-02-27 18:34:48', null, null);
INSERT INTO `gm_permission`
VALUES ('8', '内容管理', 'menu:info', 'menu', '1', '', null, '1', '2019-12-22 18:55:35', null, null);
INSERT INTO `gm_permission`
VALUES ('10', '网站设置', 'menu:sysSet:webSite', 'menu', '3', '/wbeSet/wbeParameter.do', null, '1', '2020-03-06 22:21:00',
        null, null);
INSERT INTO `gm_permission`
VALUES ('20', '留言反馈', 'menu:business:leaveWord', 'menu', '8', '/leaveWord/index.do', null, '1', '2020-03-29 12:06:34',
        null, null);
INSERT INTO `gm_permission`
VALUES ('21', '开发设置', 'menu:exploit', 'menu', '1', null, null, '1', '2020-04-03 14:17:52', null, null);
INSERT INTO `gm_permission`
VALUES ('22', '登录注册', 'menu:exploit:loginReg', 'menu', '21', '/wbeSet/loginReg.do', null, '1', '2020-04-03 14:41:25',
        null, null);
INSERT INTO `gm_permission`
VALUES ('23', '主题设置', 'menu:exploit:arguments', 'menu', '21', '/wbeSet/arguments.do', null, '1', '2020-04-03 15:44:17',
        null, null);
INSERT INTO `gm_permission`
VALUES ('24', '账号管理', 'menu:postInfo', 'menu', '1', null, null, '1', '2020-04-03 17:41:26', null, null);
INSERT INTO `gm_permission`
VALUES ('25', '标签分类', 'menu:postInfo:category', 'menu', '24', '/postInfo/categoryIndex.do', null, '1',
        '2020-04-03 17:42:04', null, null);
INSERT INTO `gm_permission`
VALUES ('26', '列表管理', 'menu:postInfo:index', 'menu', '24', '/postInfo/index.do', null, '1', '2020-04-03 18:09:31', null,
        null);
INSERT INTO `gm_permission`
VALUES ('27', '订单列表', 'menu:postInfo:order', 'menu', '24', '/postInfo/orderIndex.do', null, '1', '2020-05-07 17:36:09',
        null, null);

-- ----------------------------
-- Table structure for gm_postcategory
-- ----------------------------
DROP TABLE IF EXISTS `gm_postcategory`;
CREATE TABLE `gm_postcategory`
(
    `id`   varchar(36) COLLATE utf8_bin NOT NULL,
    `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gm_postcategory
-- ----------------------------
INSERT INTO `gm_postcategory`
VALUES ('23de3afd395a4874a46226eefbbdb52b', '穿越火线');
INSERT INTO `gm_postcategory`
VALUES ('25687427cf8c42f4b7f648af3a3b77db', '英雄联盟');
INSERT INTO `gm_postcategory`
VALUES ('4b459641f101430daff28e96e2d52dbb', '问道');
INSERT INTO `gm_postcategory`
VALUES ('6006e8581309418fb5fdb39255df6e55', '地下城与勇士');
INSERT INTO `gm_postcategory`
VALUES ('e6a58aba10554efd82844f9cb4016d8f', '龙之谷');
INSERT INTO `gm_postcategory`
VALUES ('fc2a7bed43d849f89830390263ed5c17', '天涯明月刀');

-- ----------------------------
-- Table structure for gm_postinfo
-- ----------------------------
DROP TABLE IF EXISTS `gm_postinfo`;
CREATE TABLE `gm_postinfo`
(
    `id`           varchar(36) COLLATE utf8_bin NOT NULL,
    `name`         varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `content`      longtext COLLATE utf8_bin,
    `img`          varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `introduce`    longtext COLLATE utf8_bin,
    `pageView`     int(11) DEFAULT NULL,
    `observer`     int(11) DEFAULT NULL,
    `createTime`   datetime                      DEFAULT NULL,
    `userId`       varchar(36) COLLATE utf8_bin  DEFAULT NULL,
    `userName`     varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `categoryName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `state`        varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `price`        float(11, 1
) DEFAULT NULL,
  `idName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `pass` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gm_postinfo
-- ----------------------------
INSERT INTO `gm_postinfo`
VALUES ('adb602103f924f1082b700a0ce3d2c9b', 'lol钻石账号,全英雄,400个皮肤',
        0x3C696D67207372633D222F696D6167652F753D313032383038333230352C33303034333837323326616D703B666D3D323626616D703B67703D302E6A70672220616C743D22753D313032383038333230352C33303034333837323326616D703B666D3D323626616D703B67703D302E6A7067223E,
        '/image/game1.jpg',
        0x6C6F6CE992BBE79FB3E8B4A6E58FB72CE585A8E88BB1E99B842C343030E4B8AAE79AAEE882A42CE8839CE78E87E799BEE58886E4B98B3730,
        '0', '1', '2020-05-07 18:49:53', '7a34697fc0b44439b05337e5ff4c3f3f', 'user123', '英雄联盟', '通过', '15.0',
        '2323041524', 'adminadminadmin');

-- ----------------------------
-- Table structure for gm_record
-- ----------------------------
DROP TABLE IF EXISTS `gm_record`;
CREATE TABLE `gm_record`
(
    `id`       varchar(36) COLLATE utf8_bin NOT NULL,
    `ptId`     varchar(36) COLLATE utf8_bin  DEFAULT NULL,
    `ptName`   varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `subName`  varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `userName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `time`     datetime                      DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gm_record
-- ----------------------------
INSERT INTO `gm_record`
VALUES ('25a67e5863594d50a68d48cde198ed66', '3ed4f8558030404a8c338394bbd7e9a1', '政法战线改革之星', '子项测试3', '埃辛123',
        '2020-04-05 20:42:26');
INSERT INTO `gm_record`
VALUES ('9c43a930c8a74ff3b7d072e784fae582', '3ed4f8558030404a8c338394bbd7e9a1', '政法战线改革之星', 'user报名测试', 'user1',
        '2020-04-05 20:40:43');
INSERT INTO `gm_record`
VALUES ('e7bc3dd68898462eac090a485e8b1d0c', 'fbd789491fac48628bb6a99357ee3a7c', '西湖·多啦杯”创意绘画大赛投票', '22', '埃辛123',
        '2020-04-05 20:42:35');

-- ----------------------------
-- Table structure for gm_report
-- ----------------------------
DROP TABLE IF EXISTS `gm_report`;
CREATE TABLE `gm_report`
(
    `id`       varchar(36) COLLATE utf8_bin NOT NULL,
    `ptId`     varchar(36) COLLATE utf8_bin  DEFAULT NULL,
    `content`  text COLLATE utf8_bin,
    `time`     datetime                      DEFAULT NULL,
    `userName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gm_report
-- ----------------------------
INSERT INTO `gm_report`
VALUES ('2a313c8de52d45bf89d7a85f8b94d31a', 'adb602103f924f1082b700a0ce3d2c9b', 0x616161, '2020-05-07 18:52:30',
        'ff868bf2bade4a388771e7015db99fd7');

-- ----------------------------
-- Table structure for gm_role
-- ----------------------------
DROP TABLE IF EXISTS `gm_role`;
CREATE TABLE `gm_role`
(
    `id`          varchar(36) COLLATE utf8_bin NOT NULL,
    `role`        varchar(500) COLLATE utf8_bin DEFAULT NULL,
    `description` varchar(500) COLLATE utf8_bin DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gm_role
-- ----------------------------
INSERT INTO `gm_role`
VALUES ('1', '管理员', '所拥有权限由超级管理员决定');
INSERT INTO `gm_role`
VALUES ('2', '用户', null);
INSERT INTO `gm_role`
VALUES ('3', '员工', null);

-- ----------------------------
-- Table structure for gm_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `gm_role_permission`;
CREATE TABLE `gm_role_permission`
(
    `id`           int(200) NOT NULL AUTO_INCREMENT,
    `roleId`       varchar(200) COLLATE utf8_bin DEFAULT NULL,
    `permissionId` varchar(200) COLLATE utf8_bin DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=626 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gm_role_permission
-- ----------------------------
INSERT INTO `gm_role_permission`
VALUES ('1', '1', '1');
INSERT INTO `gm_role_permission`
VALUES ('2', '1', '2');
INSERT INTO `gm_role_permission`
VALUES ('3', '1', '3');
INSERT INTO `gm_role_permission`
VALUES ('4', '1', '4');
INSERT INTO `gm_role_permission`
VALUES ('5', '1', '5');
INSERT INTO `gm_role_permission`
VALUES ('6', '1', '6');
INSERT INTO `gm_role_permission`
VALUES ('7', '1', '7');
INSERT INTO `gm_role_permission`
VALUES ('586', '1', '10');
INSERT INTO `gm_role_permission`
VALUES ('588', '1', '12');
INSERT INTO `gm_role_permission`
VALUES ('589', '1', '13');
INSERT INTO `gm_role_permission`
VALUES ('590', '1', '14');
INSERT INTO `gm_role_permission`
VALUES ('596', '1', '16');
INSERT INTO `gm_role_permission`
VALUES ('597', '1', '19');
INSERT INTO `gm_role_permission`
VALUES ('598', '1', '20');
INSERT INTO `gm_role_permission`
VALUES ('599', '1', '8');
INSERT INTO `gm_role_permission`
VALUES ('602', '1', '15');
INSERT INTO `gm_role_permission`
VALUES ('603', '1', '18');
INSERT INTO `gm_role_permission`
VALUES ('605', '1', '22');
INSERT INTO `gm_role_permission`
VALUES ('606', '1', '23');
INSERT INTO `gm_role_permission`
VALUES ('607', '1', '24');
INSERT INTO `gm_role_permission`
VALUES ('608', '1', '25');
INSERT INTO `gm_role_permission`
VALUES ('609', '1', '26');
INSERT INTO `gm_role_permission`
VALUES ('619', '1', '27');
INSERT INTO `gm_role_permission`
VALUES ('620', '3', '1');
INSERT INTO `gm_role_permission`
VALUES ('621', '3', '8');
INSERT INTO `gm_role_permission`
VALUES ('622', '3', '20');
INSERT INTO `gm_role_permission`
VALUES ('623', '3', '24');
INSERT INTO `gm_role_permission`
VALUES ('624', '3', '26');
INSERT INTO `gm_role_permission`
VALUES ('625', '3', '27');

-- ----------------------------
-- Table structure for gm_user
-- ----------------------------
DROP TABLE IF EXISTS `gm_user`;
CREATE TABLE `gm_user`
(
    `id`          varchar(36) COLLATE utf8_bin NOT NULL,
    `userName`    varchar(500) COLLATE utf8_bin DEFAULT NULL,
    `password`    varchar(500) COLLATE utf8_bin DEFAULT NULL,
    `salt`        varchar(500) COLLATE utf8_bin DEFAULT NULL,
    `iphone`      varchar(500) COLLATE utf8_bin DEFAULT NULL,
    `email`       varchar(500) COLLATE utf8_bin DEFAULT NULL,
    `idCard`      varchar(500) COLLATE utf8_bin DEFAULT NULL,
    `createdDate` datetime                      DEFAULT NULL,
    `updatedDate` datetime                      DEFAULT NULL,
    `status`      int(11) DEFAULT NULL COMMENT '0表示已删除',
    `realName`    varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `sex`         varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `address`     varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `imgUrl`      varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `balance`     float(11, 0
) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gm_user
-- ----------------------------
INSERT INTO `gm_user`
VALUES ('1', 'admin', 'admin', '', '18966554411', '115@qq.com', '36', '2018-10-24 17:07:35', '2020-05-07 18:53:26', '1',
        '王毓雅', '男', 'xx省mm市ss街道', '/image/timg (2).jpg', '3');
INSERT INTO `gm_user`
VALUES ('7a34697fc0b44439b05337e5ff4c3f3f', 'user123', '123456', null, '18744556622', '115@qq.ocm', null,
        '2020-05-07 18:47:39', '2020-05-07 18:51:27', '1', '胡歌', '男', 'xxxx', '/image/game3.jpg', '0');
INSERT INTO `gm_user`
VALUES ('b980df1389e74972a02c0be5ba916b71', 'user1', '123456', null, '18744556622', '115@qq.com', '630105199501241655',
        '2020-04-03 15:13:28', '2020-05-07 18:41:50', '1', '王钰尧', '男', 'xxxx', '/image/loginBanner2.jpg', '0');
INSERT INTO `gm_user`
VALUES ('ff868bf2bade4a388771e7015db99fd7', 'user456', '123456', null, '18744551122', '546156@qq.com', null,
        '2020-05-07 18:50:52', '2020-05-07 18:51:48', '1', '王苏娅', '女', 'xxxx', '/image/game2.jpg', '100');

-- ----------------------------
-- Table structure for gm_user_role
-- ----------------------------
DROP TABLE IF EXISTS `gm_user_role`;
CREATE TABLE `gm_user_role`
(
    `id`     int(200) NOT NULL AUTO_INCREMENT,
    `userId` varchar(200) COLLATE utf8_bin DEFAULT NULL,
    `roleId` varchar(200) COLLATE utf8_bin DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gm_user_role
-- ----------------------------
INSERT INTO `gm_user_role`
VALUES ('72', '1', '1');
INSERT INTO `gm_user_role`
VALUES ('73', '0854415ddf0142d7aa9d3e754c766596', '2');
INSERT INTO `gm_user_role`
VALUES ('74', '04d4945eec234e1ab00936f28e3663f5', '2');
INSERT INTO `gm_user_role`
VALUES ('75', 'd0cef80706614447bdb1927093584d62', '2');
INSERT INTO `gm_user_role`
VALUES ('78', 'b980df1389e74972a02c0be5ba916b71', '3');
INSERT INTO `gm_user_role`
VALUES ('79', '7a34697fc0b44439b05337e5ff4c3f3f', '2');
INSERT INTO `gm_user_role`
VALUES ('80', 'ff868bf2bade4a388771e7015db99fd7', '2');

-- ----------------------------
-- Table structure for gm_wbeparameter
-- ----------------------------
DROP TABLE IF EXISTS `gm_wbeparameter`;
CREATE TABLE `gm_wbeparameter`
(
    `id`       varchar(36) COLLATE utf8_bin NOT NULL,
    `name`     varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `iphone`   varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `img`      varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `otherImg` varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `logoImg`  varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `height`   varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `width`    varchar(255) COLLATE utf8_bin DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of gm_wbeparameter
-- ----------------------------
INSERT INTO `gm_wbeparameter`
VALUES ('1', '游戏租号服务平台', '513-1541785', '/image/c99ee9c4153d6e917808774fcff4a634.jpg',
        '/image/c99ee9c4153d6e917808774fcff4a634.jpg', '/image/logo-white.png', '30', '176');
