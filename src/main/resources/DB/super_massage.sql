-- MySQL dump 10.13  Distrib 5.6.13, for Win64 (x86_64)
--
-- Host: localhost    Database: super_massage
-- ------------------------------------------------------
-- Server version	5.6.13

DROP DATABASE IF EXISTS super_massage;

CREATE DATABASE super_massage DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE super_massage;

# DROP TABLE IF EXISTS `t_store`;
CREATE TABLE `t_store` (
  `id` bigint(20) NOT NULL COMMENT '店铺ID',
  `account` varchar(50) NOT NULL COMMENT '店铺登录帐号',
  `name` varchar(50) NOT NULL COMMENT '店铺名称',
  `pwd` varchar(50) NOT NULL default '' COMMENT '店铺管理密码',
  `long_lat_itude` varchar(50) default '' COMMENT '店铺经纬度',
  `address` varchar(200) default '' COMMENT '店铺地址',
  `phone` varchar(15) default '' COMMENT '电话',
  `remark` varchar(500) default '' COMMENT '备注',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺';


# DROP TABLE IF EXISTS `t_store_buy`;
CREATE TABLE `t_store_buy` (
  `id` bigint(20) NOT NULL COMMENT '购买ID',
  `store_id` bigint(20) NOT NULL COMMENT '店铺ID',
  `staff_num` int(11) NOT NULL DEFAULT '0' COMMENT '员工数量',
  `price` float NOT NULL COMMENT '购买价格',
  `buy_time` datetime NOT NULL COMMENT '购买时间',
  `start_time` date NOT NULL COMMENT '服务开始时间',
  `end_time` date NOT NULL COMMENT '服务结束时间',
  PRIMARY KEY (`id`),
  KEY `FK_storeBuy_store` (`store_id`),
  CONSTRAINT `FK_storeBuy_store` FOREIGN KEY (`store_id`) REFERENCES `t_store` (`id`) ON DELETE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺使用购买';

# DROP TABLE IF EXISTS `t_rooms`;
CREATE TABLE `t_rooms` (
  `id` bigint(20) NOT NULL COMMENT '包间ID',
  `store_id` bigint(20) NOT NULL COMMENT '店铺ID',
  `name` varchar(20) NOT NULL COMMENT '包间名称',
  `bed_num` tinyint(1) NOT NULL COMMENT '服务位数量',
  `remark` varchar(100) COMMENT '包间描述',
  `end_time` datetime COMMENT '使用结束时间',
  `use_status` tinyint(1) NOT NULL COMMENT '使用状态（-1：装修，0：空闲，1：使用，2：休息，3：休息结束）',
  PRIMARY KEY (`id`),
  KEY `FK_rooms_store` (`store_id`),
  CONSTRAINT `FK_rooms_store` FOREIGN KEY (`store_id`) REFERENCES `t_store` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺包间';


# DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL COMMENT '用户ID',
  `account` varchar(20) NOT NULL COMMENT '用户帐号',
  `pwd` varchar(50) NOT NULL COMMENT '用户密码',
  `name` varchar(50) COMMENT '用户昵称',
  `type` tinyint(1) COMMENT '用户类型（0：店铺用户，1：APP用户，2：微信用户）',
  `gender` bit(1) COMMENT '性别（0：男，1：女）',
  `age` tinyint(1) COMMENT '年龄',
  `icon` varchar(50) COMMENT '用户头像',
  `bank_id` varchar(50) COMMENT '支付帐号',
  `remark` varchar(100) COMMENT '个性签名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';

# DROP TABLE IF EXISTS `t_packages`;
CREATE TABLE `t_packages` (
  `id` bigint(20) NOT NULL COMMENT '商品ID',
  `store_id` bigint(20) NOT NULL COMMENT '店铺ID',
  `name` varchar(100) NOT NULL COMMENT '商品名字',
  `store_price` float NOT NULL COMMENT '商品店铺价格',
  `app_price` float NOT NULL COMMENT '商品APP价格',
  `timed` tinyint(2) NOT NULL COMMENT '服务时长（分）',
  `remark` varchar(500) COMMENT '商品介绍',
  PRIMARY KEY (`id`),
  KEY `FK_packages_store` (`store_id`),
  CONSTRAINT `FK_packages_store` FOREIGN KEY (`store_id`) REFERENCES `t_store` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品(服务)';


# DROP TABLE IF EXISTS `t_staff`;
CREATE TABLE `t_staff` (
  `id` bigint(20) NOT NULL COMMENT '员工ID',
  `store_id` bigint(20) NOT NULL COMMENT '店铺ID',
  `name` varchar(20) NOT NULL COMMENT '员工名字',
  `icon` varchar(20) COMMENT '员工头像',
  `gender` bit(1) COMMENT '性别（男：0，女：1）',
  `age` tinyint(1) COMMENT '年龄',
  `experience` tinyint(1) NOT NULL DEFAULT '1' COMMENT '工作经验(年)',
  `job` varchar(20) NOT NULL COMMENT '岗位',
  `remark` varchar(500) COMMENT '备注',
  `woke_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '服务状态（-1：休假，0:空闲，1:预约，2：服务）',
  PRIMARY KEY (`id`),
  KEY `FK_staff_store` (`store_id`),
  CONSTRAINT `FK_staff_store` FOREIGN KEY (`store_id`) REFERENCES `t_store` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工';


# DROP TABLE IF EXISTS `t_staff_packages`;
CREATE TABLE `t_staff_packages` (
  `staff_id` bigint(20) NOT NULL COMMENT '员工ID',
  `package_id` bigint(20) NOT NULL COMMENT '商品ID',
  PRIMARY KEY (`staff_id`,`package_id`),
  KEY `FK_sp_packages` (`package_id`),
  CONSTRAINT `FK_sp_packages` FOREIGN KEY (`package_id`) REFERENCES `t_packages` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_sp_staff` FOREIGN KEY (`staff_id`) REFERENCES `t_staff` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工服务关系';


# DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` varchar(25) NOT NULL COMMENT '订单ID',
  `store_id` bigint(20) NOT NULL COMMENT '店铺ID',
  `user_id` bigint(20) NOT NULL COMMENT '客户ID',
  `package_id` bigint(20) NOT NULL COMMENT '商品ID',
  `package_name` varchar(100) NOT NULL COMMENT '商品名字',
  `package_timed` tinyint(2) NOT NULL COMMENT '商品时长（分）',
  `store_price` float NOT NULL COMMENT '订单店铺价格',
  `app_price` float NOT NULL COMMENT '订单APP价格',
  `pay_id` varchar(100) COMMENT '支付单号（在线支付使用）',
  `pay_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '支付类型（0：到店支付，1：在线支付）',
  `use_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '订单状态（0：未支付，1：未使用，2：已使用）',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除状态（0：正常，1：用户删除）',
  PRIMARY KEY (`id`),
  KEY `FK_order_store` (`store_id`),
  KEY `FK_order_user` (`user_id`),
  KEY `FK_order_packages` (`package_id`),
  CONSTRAINT `FK_order_store` FOREIGN KEY (`store_id`) REFERENCES `t_store` (`id`) ON DELETE NO ACTION,
  CONSTRAINT `FK_order_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE NO ACTION,
  CONSTRAINT `FK_order_packages` FOREIGN KEY (`package_id`) REFERENCES `t_packages` (`id`) ON DELETE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单';


# DROP TABLE IF EXISTS `t_consume`;
CREATE TABLE `t_consume` (
  `order_id` varchar(25) NOT NULL COMMENT '订单ID',
  `store_id` bigint(20) NOT NULL COMMENT '店铺ID',
  `package_timed` tinyint(2) NOT NULL COMMENT '服务时长（分）', 
  `plan_staff_id` bigint(20) COMMENT '预约员工ID',
  `used_staff_id` bigint(20) COMMENT '服务员工ID',
  `plan_staff_name` varchar(20) COMMENT '预约员工名字',
  `used_staff_name` varchar(20) COMMENT '服务员工名字',
  `plan_time` datetime COMMENT '预约时间',
  `used_time` datetime COMMENT '消费时间',
  `room_id` bigint(20) COMMENT '使用包间ID',
  PRIMARY KEY (`order_id`),
  KEY `FK_consume_store` (`store_id`),
  KEY `FK_consumePlan_staff` (`plan_staff_id`),
  KEY `FK_consumeUsed_staff` (`used_staff_id`),
  KEY `FK_consume_rooms` (`room_id`),
  CONSTRAINT `FK_consume_order` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`id`),
  CONSTRAINT `FK_consume_store` FOREIGN KEY (`store_id`) REFERENCES `t_store` (`id`),
  CONSTRAINT `FK_consumePlan_staff` FOREIGN KEY (`plan_staff_id`) REFERENCES `t_staff` (`id`) ON DELETE NO ACTION,
  CONSTRAINT `FK_consumeUsed_staff` FOREIGN KEY (`used_staff_id`) REFERENCES `t_staff` (`id`) ON DELETE NO ACTION,
  CONSTRAINT `FK_consume_rooms` FOREIGN KEY (`room_id`) REFERENCES `t_rooms` (`id`) ON DELETE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消费记录';