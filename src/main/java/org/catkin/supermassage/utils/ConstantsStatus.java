package org.catkin.supermassage.utils;

public interface ConstantsStatus {
	/**
	 * 操作成功
	 */
	String SUCCESS = "OK";
	
	/**
	 * 订单常量
	 */
	interface Order {
		/**
		 * 店铺支付
		 */
		int PAY_TYPE_STORE = 0;
		
		/**
		 * 在线支付
		 */
		int PAY_TYPE_ONLINE = 1;

		/**
		 * 订单未支付
		 */
		int USE_STATUS_NOTPAY = 0;
		
		/**
		 * 订单未使用(已支付)
		 */
		int USE_STATUS_USE = 1;
		
		/**
		 * 订单已预约
		 */
		int USE_STATUS_PANL = 2;
		
		/**
		 * 订单预约过期
		 */
		int USE_STATUS_EXPPANL = 3;
		
		/**
		 * 订单已使用
		 */
		int USE_STATUS_USED = 4;
		
		/**
		 * 订单未删除
		 */
		int DELETED_NO = 0;
		
		/**
		 * 订单用户删除
		 */
		int DELETED_YES = 1;
	}
	
	/**
	 * 包间常量
	 */
	interface Rooms {
		/**
		 * 包间装修
		 */
		int USE_STATUS_FIX = -1;
		
		/**
		 * 包间空闲
		 */
		int USE_STATUS_IDLE = 0;
		
		/**
		 * 包间使用中
		 */
		int USE_STATUS_USE = 1;
		
		/**
		 * 包间占用休息
		 */
		int USE_STATUS_BREAK = 2;
		
		/**
		 * 包间休息结束
		 */
		int USE_STATUS_BREAKUP = 3;
	}
	
	/**
	 * 员工常量
	 */
	interface Staff {
		/**
		 * 员工休假
		 */
		int WOKE_STATUS_LEAVE = -1;
		
		/**
		 * 员工空闲
		 */
		int WOKE_STATUS_IDLE = 0;
		
		/**
		 * 员工预约中
		 */
		int WOKE_STATUS_PLAN = 1;
		
		/**
		 * 员工服务中
		 */
		int WOKE_STATUS_RUN = 2;
	}
	
	/**
	 * 用户常量
	 */
	interface User {
		/**
		 * 店铺用户
		 */
		int TYPE_STORE = 0;
		
		/**
		 * APP用户
		 */
		int TYPE_APP = 1;
		
		/**
		 * 微信用户
		 */
		int TYPE_WECAT = 2;
	}
	
}
