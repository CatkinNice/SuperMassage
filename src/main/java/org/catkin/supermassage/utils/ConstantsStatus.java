package org.catkin.supermassage.utils;

public interface ConstantsStatus {
	/**
	 * 操作成功
	 */
	String SUCCESS = "OK";
	
	/**
	 * 店铺支付
	 */
	int ORDER_PAYTYPE_STORE = 0;
	
	/**
	 * 在线支付
	 */
	int ORDER_PAYTYPE_ONLINE = 1;

	/**
	 * 订单未支付
	 */
	int ORDER_USESTATUS_NOTPAY = 0;
	
	/**
	 * 订单未使用
	 */
	int ORDER_USESTATUS_USE = 1;
	
	/**
	 * 订单已预约
	 */
	int ORDER_USESTATUS_PANL = 1;
	
	/**
	 * 订单已使用
	 */
	int ORDER_USESTATUS_USED = 3;
	
	/**
	 * 订单未删除
	 */
	int ORDER_DELETED_NO = 0;
	
	/**
	 * 订单用户删除
	 */
	int ORDER_DELETED_YES = 1;
	
	/**
	 * 包间装修
	 */
	int ROOM_USESTATUS_FIX = -1;
	
	/**
	 * 包间空闲
	 */
	int ROOM_USESTATUS_AVAILABLE = 0;
	
	/**
	 * 包间使用中
	 */
	int ROOM_USESTATUS_UNAVAILABLE = 1;
	
	/**
	 * 包间占用休息
	 */
	int ROOM_USESTATUS_BREAK = 2;
	
	/**
	 * 包间休息结束
	 */
	int ROOM_USESTATUS_BREAKUP = 3;
	
	/**
	 * 员工休假
	 */
	int STAFF_WOKESTATUS_LEAVE = -1;
	
	/**
	 * 员工空闲
	 */
	int STAFF_WOKESTATUS_UNWANTED = 0;
	
	/**
	 * 员工预约中
	 */
	int STAFF_WOKESTATUS_PLAN = 1;
	
	/**
	 * 员工服务中
	 */
	int STAFF_WOKESTATUS_RUN = 2;
	
	/**
	 * 店铺用户
	 */
	int USER_TYPE_STORE = 0;
	
	/**
	 * APP用户
	 */
	int USER_TYPE_APP = 1;
	
	/**
	 * 微信用户
	 */
	int USER_TYPE_WECAT = 2;
}
