/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50621
 Source Host           : localhost:3306
 Source Schema         : bbs

 Target Server Type    : MySQL
 Target Server Version : 50621
 File Encoding         : 65001

 Date: 09/08/2019 17:22:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for block
-- ----------------------------
DROP TABLE IF EXISTS `block`;
CREATE TABLE `block`  (
  `bId` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `bName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`bId`) USING BTREE,
  UNIQUE INDEX `bName`(`bName`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of block
-- ----------------------------
INSERT INTO `block` VALUES (9, '军事');
INSERT INTO `block` VALUES (10, '测试');

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `pId` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `pTitle` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pContext` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pTime` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `uId` int(10) UNSIGNED NOT NULL,
  `bId` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`pId`) USING BTREE,
  UNIQUE INDEX `pTitle`(`pTitle`, `uId`, `bId`) USING BTREE,
  INDEX `uId`(`uId`) USING BTREE,
  INDEX `bId`(`bId`) USING BTREE,
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`uId`) REFERENCES `user` (`uId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `post_ibfk_2` FOREIGN KEY (`bId`) REFERENCES `block` (`bId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES (9, ' 伊朗局势紧张', '今日伊朗击落一架美无人机', '2019-08-09 16:04:32', 1, 9);

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply`  (
  `rId` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `rContext` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rTime` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `uId` int(10) UNSIGNED NOT NULL,
  `pId` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`rId`) USING BTREE,
  INDEX `reply_ibfk_1`(`uId`) USING BTREE,
  INDEX `pId`(`pId`) USING BTREE,
  CONSTRAINT `reply_ibfk_1` FOREIGN KEY (`uId`) REFERENCES `user` (`uId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reply_ibfk_2` FOREIGN KEY (`pId`) REFERENCES `post` (`pId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of reply
-- ----------------------------
INSERT INTO `reply` VALUES (17, ' 666', '2019-08-09 16:05:57', 1, 9);
INSERT INTO `reply` VALUES (18, ' 吊', '2019-08-09 16:06:04', 1, 9);
INSERT INTO `reply` VALUES (19, ' time1', '2019-08-09 17:14:22', 1, 9);
INSERT INTO `reply` VALUES (20, ' time 2', '2017-10-01 17:14:26', 1, 9);
INSERT INTO `reply` VALUES (21, ' time 3\\', '2019-06-01 17:14:30', 1, 9);
INSERT INTO `reply` VALUES (22, ' time 4', '2019-08-06 17:14:34', 1, 9);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uId` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uPass` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `state` binary(1) NOT NULL DEFAULT 0,
  `flag` binary(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`uId`) USING BTREE,
  UNIQUE INDEX `uName`(`uName`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'liu', '123', 0x30, 0x31);
INSERT INTO `user` VALUES (2, 'liujijiang', '123', 0x30, 0x31);
INSERT INTO `user` VALUES (3, 'asd', '123', 0x30, 0x31);
INSERT INTO `user` VALUES (4, '123', 'fda', 0x30, 0x31);
INSERT INTO `user` VALUES (5, 'fds', 'fds', 0x30, 0x31);
INSERT INTO `user` VALUES (6, 'fd', 'fds', 0x30, 0x31);

SET FOREIGN_KEY_CHECKS = 1;
