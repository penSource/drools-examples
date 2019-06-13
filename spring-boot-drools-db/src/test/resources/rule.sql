/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost
 Source Database       : test

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : utf-8

 Date: 07/31/2017 19:11:43 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `rule`
-- ----------------------------
DROP TABLE IF EXISTS `rule`;
CREATE TABLE `rule` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT,
	`rule_key` varchar(100) NOT NULL,
  `rule_type` varchar(100) NOT NULL,
  `content` varchar(2048) NOT NULL,
  `create_time` datetime(0) NOT NULL DEFAULT now(),
  `last_modify_time` datetime(0) DEFAULT CURRENT_TIMESTAMP,
  `version` bigint(22) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rule_key` (`rule_key`),
	INDEX `UK_rule_type` (`rule_type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `rule`
-- ----------------------------
BEGIN;
INSERT INTO `rule` VALUES ('1','address','rule_address', 'package plausibcheck.adress\n\nimport com.neo.drools.model.Address;\nimport com.neo.drools.model.fact.AddressCheckResult;\n\nrule \"Postcode 6 numbers\"\n\n    when\n        address : Address(postcode != null, postcode matches \"([0-9]{6})\")\n        checkResult : AddressCheckResult();\n    then\n        checkResult.setPostCodeResult(true);\n		System.out.println(\"规则6中打印日志：校验通过!\");\nend', now(), now(), '1');
INSERT INTO `rule` VALUES ('2','rule_test_888','rule_regex', 'package plausibcheck.adress\n\nimport com.neo.drools.model.Address;\nimport com.neo.drools.model.fact.AddressCheckResult;\n\nrule \"Postcode 6 numbers\"\n\n    when\n        address : Address(postcode != null, postcode matches \"([0-9]{6})\")\n        checkResult : AddressCheckResult();\n    then\n        checkResult.setPostCodeResult(true);\n		System.out.println(\"规则6中打印日志：校验通过!\");\nend', now(), now(), '1');
INSERT INTO `rule` VALUES ('3','rule_test_999','rule_regex', 'package plausibcheck.adress\n\nimport com.neo.drools.model.Address;\nimport com.neo.drools.model.fact.AddressCheckResult;\n\nrule \"Postcode 6 numbers\"\n\n    when\n        address : Address(postcode != null, postcode matches \"([0-9]{6})\")\n        checkResult : AddressCheckResult();\n    then\n        checkResult.setPostCodeResult(true);\n		System.out.println(\"规则6中打印日志：校验通过!\");\nend', now(), now(), '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
