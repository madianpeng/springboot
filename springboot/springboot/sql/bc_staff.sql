/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : springboot

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-05-20 18:05:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bc_staff
-- ----------------------------
DROP TABLE IF EXISTS `bc_staff`;
CREATE TABLE `bc_staff` (
  `id` varchar(50) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `haspda` varchar(1) DEFAULT NULL,
  `deltag` varchar(1) DEFAULT NULL,
  `station` varchar(40) DEFAULT NULL,
  `standard` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bc_staff
-- ----------------------------
INSERT INTO `bc_staff` VALUES ('MD0001', '张三', '13812345678', '有', '0', '庐阳区', '5');
INSERT INTO `bc_staff` VALUES ('MD0002', '徐钧', '13812345688', '有', '0', '庐阳区', '14');
INSERT INTO `bc_staff` VALUES ('MD0003', '张勇', '13812345689', '有', '0', '庐阳区', '15');
INSERT INTO `bc_staff` VALUES ('MD0004', '迟育红', '13812345690', '有', '0', '庐阳区', '16');
INSERT INTO `bc_staff` VALUES ('MD0005', '赵家荣', '13812345691', '有', '0', '庐阳区', '17');
INSERT INTO `bc_staff` VALUES ('MD0006', '柏立田', '13812345692', '有', '0', '庐阳区', '18');
INSERT INTO `bc_staff` VALUES ('MD0007', '柏百', '13812345693', '有', '0', '庐阳区', '19');
INSERT INTO `bc_staff` VALUES ('MD0008', '京津冀', '13812345694', '有', '0', '庐阳区', '20');
INSERT INTO `bc_staff` VALUES ('MD0009', '李露露', '13812345695', '有', '0', '庐阳区', '21');
INSERT INTO `bc_staff` VALUES ('MD0010', '施俊', '13812345696', '有', '0', '庐阳区', '22');
INSERT INTO `bc_staff` VALUES ('MD0011', '陈曙光', '13812345697', '有', '0', '庐阳区', '23');
INSERT INTO `bc_staff` VALUES ('MD0012', '张权', '13812345680', '有', '0', '庐阳区', '6');
INSERT INTO `bc_staff` VALUES ('MD0013', '程昊', '13812345698', '有', '0', '庐阳区', '24');
INSERT INTO `bc_staff` VALUES ('MD0014', '杨敏', '13812345699', '有', '0', '庐阳区', '25');
INSERT INTO `bc_staff` VALUES ('MD0015', '朱圣峰', '13812345700', '有', '0', '庐阳区', '26');
INSERT INTO `bc_staff` VALUES ('MD0016', '张传兵', '13812345701', '有', '0', '庐阳区', '27');
INSERT INTO `bc_staff` VALUES ('MD0017', '张艳艳', '13812345702', '有', '0', '庐阳区', '28');
INSERT INTO `bc_staff` VALUES ('MD0018', '凌倩', '13812345703', '有', '0', '庐阳区', '29');
INSERT INTO `bc_staff` VALUES ('MD0019', '管兵兵', '13812345704', '有', '0', '庐阳区', '30');
INSERT INTO `bc_staff` VALUES ('MD0020', '曹慧敏', '13812345705', '有', '0', '庐阳区', '31');
INSERT INTO `bc_staff` VALUES ('MD0021', '杨琴', '13812345706', '有', '0', '庐阳区', '32');
INSERT INTO `bc_staff` VALUES ('MD0022', '姜猛', '13812345707', '有', '0', '庐阳区', '33');
INSERT INTO `bc_staff` VALUES ('MD0023', '江选友', '13812345681', '有', '0', '庐阳区', '7');
INSERT INTO `bc_staff` VALUES ('MD0024', '孙子龙', '13812345708', '有', '0', '庐阳区', '34');
INSERT INTO `bc_staff` VALUES ('MD0025', '祝玺', '13812345709', '有', '0', '庐阳区', '35');
INSERT INTO `bc_staff` VALUES ('MD0026', '方可', '13812345710', '有', '0', '庐阳区', '36');
INSERT INTO `bc_staff` VALUES ('MD0027', '袁深圳', '13812345711', '有', '0', '庐阳区', '37');
INSERT INTO `bc_staff` VALUES ('MD0028', '周克美', '13812345682', '有', '0', '庐阳区', '8');
INSERT INTO `bc_staff` VALUES ('MD0029', '陈雪茹', '13812345683', '有', '0', '庐阳区', '9');
INSERT INTO `bc_staff` VALUES ('MD0030', '李茹君', '13812345684', '有', '0', '庐阳区', '10');
INSERT INTO `bc_staff` VALUES ('MD0031', '高舒', '13812345685', '有', '0', '庐阳区', '11');
INSERT INTO `bc_staff` VALUES ('MD0032', '胡腾蛟', '13812345686', '有', '0', '庐阳区', '12');
INSERT INTO `bc_staff` VALUES ('MD0033', '李多平', '13812345687', '有', '0', '庐阳区', '13');
