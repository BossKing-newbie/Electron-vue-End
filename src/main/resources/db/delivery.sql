/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : delivery

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 08/07/2020 12:17:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员id',
  `admin_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `admin_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `admin_phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('000001', 'admin_000001', '000001', '0000001');

-- ----------------------------
-- Table structure for delivery_info
-- ----------------------------
DROP TABLE IF EXISTS `delivery_info`;
CREATE TABLE `delivery_info`  (
  `order_form_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '订单号',
  `order_delivery` json NOT NULL COMMENT '物流信息状态json数组',
  PRIMARY KEY (`order_form_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of delivery_info
-- ----------------------------
INSERT INTO `delivery_info` VALUES ('IsGxDOHh', '[{\"content\": \"成功预约\", \"timestamp\": \"2020-07-08 10:11:07\"}]');
INSERT INTO `delivery_info` VALUES ('Z95BpOjR', '[{\"content\": \"成功预约\", \"timestamp\": \"2020-07-07 22:53:00\"}]');
INSERT INTO `delivery_info` VALUES ('bijtd84T', '[{\"content\": \"成功预约\", \"timestamp\": \"2020-07-08 00:32:33\"}]');

-- ----------------------------
-- Table structure for order_form
-- ----------------------------
DROP TABLE IF EXISTS `order_form`;
CREATE TABLE `order_form`  (
  `order_form_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号',
  `order_form_products_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品id',
  `order_form_user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `order_form_time` datetime(0) NOT NULL COMMENT '下单时间，格式YYYY-MM-DD HH:MM:SS',
  `order_form_start_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发货地址',
  `order_form_end_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货地址',
  `order_form_weight` double(255, 0) NOT NULL COMMENT '重量',
  `order_form_money` double(255, 0) NOT NULL COMMENT '价格，若预约则为起步价，若订单已进行则为交付价格',
  `order_form_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单状态',
  `order_form_way` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '付款方法',
  PRIMARY KEY (`order_form_number`) USING BTREE,
  INDEX `order_form_number`(`order_form_number`) USING BTREE,
  INDEX `产品信息id`(`order_form_products_id`) USING BTREE,
  INDEX `用户id`(`order_form_user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_form
-- ----------------------------
INSERT INTO `order_form` VALUES ('bijtd84T', 'same_day', '724574109', '2020-07-08 00:32:34', '广东省广州市荔湾区荔湾大厦', '广东省广州市海珠区海珠街道', 0, 18, '预约中', '未付款');
INSERT INTO `order_form` VALUES ('IsGxDOHh', 'same_day', 'seven_King', '2020-07-08 10:11:07', '广东省广州市荔湾区荔湾大厦', '广东省佛山市顺德区大良街道', 0, 20, '预约中', '未付款');
INSERT INTO `order_form` VALUES ('Z95BpOjR', 'next_day', '724574109', '2020-07-07 22:53:01', '广东省汕头市龙湖区陈厝合永和街', '广东省广州市荔湾区荔湾广场', 0, 14, '已签收', '未付款');

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `order_form_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '订单编号',
  `order_sender` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '寄件人姓名',
  `order_sender_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '寄件人联系电话',
  `order_recipient` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '收件人姓名',
  `order_recipient_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '收件人联系电话',
  PRIMARY KEY (`order_form_number`) USING BTREE,
  UNIQUE INDEX `order_form_number_index`(`order_form_number`) USING BTREE COMMENT '订单编号索引'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('IsGxDOHh', '郑嘉雯', '15816633018', '纪佳金', '13078163530');
INSERT INTO `order_info` VALUES ('Z95BpOjR', '纪佳金', '15816633018', '郑嘉雯', '13078163530');
INSERT INTO `order_info` VALUES ('bijtd84T', '纪佳金', '13168597846', '郑嘉雯', '13078163530');

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`  (
  `products_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品信息表ID',
  `products_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品名称',
  `products_price` double NOT NULL COMMENT '底价',
  `products_weight` double NOT NULL COMMENT '初始重量',
  `products_addprice` double NOT NULL COMMENT '续重价格',
  PRIMARY KEY (`products_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES ('next_day', '次日达', 12, 0.5, 2);
INSERT INTO `products` VALUES ('same_day', '当日达', 18, 0.5, 2);
INSERT INTO `products` VALUES ('seventy_two', '72小时达', 8, 0.5, 2);

-- ----------------------------
-- Table structure for reserve_form
-- ----------------------------
DROP TABLE IF EXISTS `reserve_form`;
CREATE TABLE `reserve_form`  (
  `reserve_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '预约订单id，与order_form订单号对应',
  `reserve_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '上门预约时间',
  PRIMARY KEY (`reserve_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reserve_form
-- ----------------------------
INSERT INTO `reserve_form` VALUES ('IsGxDOHh', '2020-07-08 13:00~14:00');
INSERT INTO `reserve_form` VALUES ('Z95BpOjR', '2020-07-08 9:00~10:00');
INSERT INTO `reserve_form` VALUES ('bijtd84T', '2020-07-08 10:00~11:00');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` varbinary(20) NOT NULL COMMENT '用户账号',
  `user_password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `user_phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号码',
  `user_describe` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限',
  `user_integral` int(255) NOT NULL COMMENT '用户积分',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (0x373234353734313039, '$2a$10$CWls59VhaYjJzNUWsIacd.z6rPfcXRUDFUxOvTqVCSD1pcj/tG5wW', '13078163530', '普通会员', 0);
INSERT INTO `user` VALUES (0x626F73734B696E67, '$2a$10$rmOYL873gDabjK4qGWHlluFR84081CkB1U0XnfzMSVa6wAkw6u32e', '13078163530', '普通会员', 0);
INSERT INTO `user` VALUES (0x736576656E4B696E67, '$2a$10$UItPT0nKwQcG..zgrPt4cuV.xkajM/sv8RhwRHs3.CwKJp8fZC1nC', '13078163530', '普通会员', 0);
INSERT INTO `user` VALUES (0x736576656E5F4B696E67, '$2a$10$VMR1jVyk32lkz6QBaSISxOhg5knVgKpCE92V8diCKVguTEuUmhUTG', '15816633018', '普通会员', 0);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户账号',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户昵称',
  `user_sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户性别',
  `user_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户地址',
  `user_avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户头像地址',
  `user_province` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户地址编号',
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `index_user_id`(`user_id`) USING BTREE COMMENT '用户账号的索引'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('724574109', '大佬湿', 'man', '广东省-汕头市-龙湖区/陈厝合永和街金和九巷', 'http://qbskwf43q.bkt.clouddn.com/t2uPW-20170060309-79973244496460857.jpg', '440000-440500-440507');
INSERT INTO `user_info` VALUES ('bossKing', '大佬金', 'man', '广东省-佛山市-禅城区/佛山科学技术学院江湾一路18号', 'http://qbskwf43q.bkt.clouddn.com/pKDZB-20170060309-微信图片_20200317104521.jpg', '440000-440600-440604');
INSERT INTO `user_info` VALUES ('sevenKing', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user_info` VALUES ('seven_King', '郑嘉雯', 'woman', '广东省-汕头市-龙湖区/陈厝合永和街金和九巷十一号', 'http://qbskwf43q.bkt.clouddn.com/EuQqJ-20170060309-微信图片_20200306094721.jpg', '440000-440500-440507');

SET FOREIGN_KEY_CHECKS = 1;
