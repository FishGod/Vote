/*
Navicat MySQL Data Transfer

Source Server         : wjc
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : toupiao

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-10-11 11:33:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_option
-- ----------------------------
DROP TABLE IF EXISTS `tb_option`;
CREATE TABLE `tb_option` (
  `vid` int(11) NOT NULL DEFAULT '0',
  `oname` varchar(255) NOT NULL DEFAULT '',
  `number` int(11) DEFAULT NULL,
  KEY `fk_vote` (`vid`),
  CONSTRAINT `fk_vote` FOREIGN KEY (`vid`) REFERENCES `tb_vote` (`vid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_vote
-- ----------------------------
DROP TABLE IF EXISTS `tb_vote`;
CREATE TABLE `tb_vote` (
  `vid` int(11) NOT NULL AUTO_INCREMENT,
  `vname` varchar(255) NOT NULL DEFAULT '',
  `vtype` enum('0','1') NOT NULL DEFAULT '0',
  PRIMARY KEY (`vid`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(225) NOT NULL,
  `username` varchar(225) NOT NULL,
  `password` varchar(225) NOT NULL,
  `power` enum('0','1') NOT NULL DEFAULT '1',
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
