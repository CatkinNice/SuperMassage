-- MySQL dump 10.13  Distrib 5.6.13, for Win64 (x86_64)
--
-- Host: localhost    Database: super_massage
-- ------------------------------------------------------
-- Server version	5.6.13

--
-- Table structure for table `t_order`
--

DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` bigint(20) NOT NULL COMMENT '订单ID',
  `store_id` bigint(20) NOT NULL COMMENT '商店ID',
  `user_id` bigint(20) NOT NULL COMMENT '客户ID',
  `total_store_price` float NOT NULL COMMENT '订单店铺价格',
  `total_app_price` float NOT NULL COMMENT '订单APP价格',
  `pay_id` varchar(100) NOT NULL DEFAULT '' COMMENT '支付单号（在线支付使用）',
  `pay_type` bit(1) NOT NULL DEFAULT b'0' COMMENT '支付类型（0：到店支付，1：在线支付）',
  `pay_status` bit(1) NOT NULL DEFAULT b'0' COMMENT '支付状态（0：未支付，1：已支付）',
  PRIMARY KEY (`id`),
  KEY `FK_order_store` (`store_id`),
  KEY `FK_order_user` (`user_id`),
  CONSTRAINT `FK_order_store` FOREIGN KEY (`store_id`) REFERENCES `t_store` (`id`) ON DELETE NO ACTION,
  CONSTRAINT `FK_order_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE NO ACTION
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order`
--


--
-- Table structure for table `t_order_item`
--

DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item` (
  `id` bigint(20) NOT NULL COMMENT '订单项ID',
  `package_id` bigint(20) NOT NULL COMMENT '商品ID',
  `plan_staff_id` bigint(20) DEFAULT NULL COMMENT '预约员工ID',
  `used_staff_id` bigint(20) DEFAULT NULL COMMENT '服务员工ID',
  `plan_time` time DEFAULT NULL COMMENT '预约时间',
  `used_time` time DEFAULT NULL COMMENT '消费时间',
  PRIMARY KEY (`id`),
  KEY `FK_orderItemUsed_staff` (`used_staff_id`),
  KEY `FK_orderItemPlan_staff` (`plan_staff_id`),
  KEY `FK_orderItem_packages` (`package_id`),
  CONSTRAINT `FK_orderItemPlan_staff` FOREIGN KEY (`plan_staff_id`) REFERENCES `t_staff` (`id`) ON DELETE NO ACTION,
  CONSTRAINT `FK_orderItemUsed_staff` FOREIGN KEY (`used_staff_id`) REFERENCES `t_staff` (`id`) ON DELETE NO ACTION,
  CONSTRAINT `FK_orderItem_packages` FOREIGN KEY (`package_id`) REFERENCES `t_packages` (`id`) ON DELETE NO ACTION
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order_item`
--


--
-- Table structure for table `t_packages`
--

DROP TABLE IF EXISTS `t_packages`;
CREATE TABLE `t_packages` (
  `id` bigint(20) NOT NULL COMMENT '产品ID',
  `store_id` bigint(20) NOT NULL COMMENT '店铺ID',
  `name` varchar(100) NOT NULL COMMENT '产品名字',
  `store_price` float NOT NULL COMMENT '产品店铺价格',
  `app_price` float NOT NULL COMMENT '产品APP价格',
  `timed` int(4) NOT NULL COMMENT '服务时长（分）',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '产品介绍',
  PRIMARY KEY (`id`),
  KEY `FK_packages_store` (`store_id`),
  CONSTRAINT `FK_packages_store` FOREIGN KEY (`store_id`) REFERENCES `t_store` (`id`) ON DELETE CASCADE
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_packages`
--


--
-- Table structure for table `t_staff`
--

DROP TABLE IF EXISTS `t_staff`;
CREATE TABLE `t_staff` (
  `id` bigint(20) NOT NULL COMMENT '员工ID',
  `store_id` bigint(20) NOT NULL COMMENT '店铺ID',
  `name` varchar(20) NOT NULL COMMENT '员工名字',
  `icon` varchar(20) NOT NULL DEFAULT '' COMMENT '员工头像',
  `sex` bit(1) NOT NULL DEFAULT b'0' COMMENT '性别（男：0，女：1）',
  `age` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '年龄',
  `experience` tinyint(2) NOT NULL DEFAULT '1' COMMENT '工作经验(年)',
  `job` varchar(20) NOT NULL COMMENT '岗位',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `woke_status` bit(1) NOT NULL DEFAULT b'0' COMMENT '服务状态（0：空闲，1：服务中）',
  PRIMARY KEY (`id`),
  KEY `FK_staff_store` (`store_id`),
  CONSTRAINT `FK_staff_store` FOREIGN KEY (`store_id`) REFERENCES `t_store` (`id`) ON DELETE CASCADE
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_staff`
--


--
-- Table structure for table `t_staff_packages`
--

DROP TABLE IF EXISTS `t_staff_packages`;
CREATE TABLE `t_staff_packages` (
  `staff_id` bigint(20) NOT NULL COMMENT '员工ID',
  `package_id` bigint(20) NOT NULL COMMENT '商品ID',
  PRIMARY KEY (`staff_id`,`package_id`),
  KEY `FK_sp_packages` (`package_id`),
  CONSTRAINT `FK_sp_packages` FOREIGN KEY (`package_id`) REFERENCES `t_packages` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_sp_staff` FOREIGN KEY (`staff_id`) REFERENCES `t_staff` (`id`) ON DELETE CASCADE
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_staff_packages`
--


--
-- Table structure for table `t_store`
--

DROP TABLE IF EXISTS `t_store`;
CREATE TABLE `t_store` (
  `id` bigint(20) NOT NULL COMMENT '店铺ID',
  `name` varchar(50) NOT NULL COMMENT '店铺名称',
  `pwd` varchar(50) NOT NULL DEFAULT '' COMMENT '店铺管理密码',
  `long_lat_itude` varchar(50) NOT NULL DEFAULT '' COMMENT '店铺经纬度',
  `address` varchar(200) NOT NULL DEFAULT '' COMMENT '店铺地址',
  `phone` varchar(15) NOT NULL DEFAULT '' COMMENT '电话',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_store`
--


--
-- Table structure for table `t_store_buy`
--

DROP TABLE IF EXISTS `t_store_buy`;
CREATE TABLE `t_store_buy` (
  `id` bigint(20) NOT NULL COMMENT '购买ID',
  `store_id` bigint(20) NOT NULL COMMENT '店铺ID',
  `staff_num` tinyint(4) NOT NULL DEFAULT '0' COMMENT '员工数量',
  `price` float NOT NULL COMMENT '购买价格',
  `buy_time` time NOT NULL COMMENT '购买时间',
  `start_time` time NOT NULL COMMENT '服务开始时间',
  `end_time` time NOT NULL COMMENT '服务结束时间',
  PRIMARY KEY (`id`),
  KEY `FK_storeBuy_store` (`store_id`),
  CONSTRAINT `FK_storeBuy_store` FOREIGN KEY (`store_id`) REFERENCES `t_store` (`id`) ON DELETE NO ACTION
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_store_buy`
--


--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL COMMENT '用户ID',
  `account` varchar(20) NOT NULL COMMENT '用户帐号',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户昵称',
  `sex` bit(1) NOT NULL DEFAULT b'0' COMMENT '性别（男：0，女：1）',
  `age` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '年龄',
  `icon` varchar(50) NOT NULL DEFAULT '' COMMENT '用户头像',
  `pwd` varchar(50) NOT NULL DEFAULT '' COMMENT '用户密码',
  `bank_id` varchar(50) NOT NULL DEFAULT '' COMMENT '支付帐号',
  `remark` varchar(100) NOT NULL DEFAULT '' COMMENT '个性签名',
  PRIMARY KEY (`id`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--