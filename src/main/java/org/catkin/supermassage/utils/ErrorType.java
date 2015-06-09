package org.catkin.supermassage.utils;

public enum ErrorType {
	/**
	 * 服务地址未找到
	 */
	error404,
	/**
	 * 后台系统错误或参数错误导致程序出错，正常情况下时bug
	 */
	error500,
	/**
	 * token校验出错
	 */
	errorCheckToken,
	/**
	 * 服务参数不符规范
	 */
	errorRequestData,
	/**
	 * 店铺员工数不足
	 */
	errorStaffOver, 
	/**
	 * 存在同名店铺
	 */
	errorSameStore, 
	/**
	 * 帐号已存在
	 */
	errorSameAccount, 
	/**
	 * 帐号不存在
	 */
	errorNotAccount, 
	/**
	 * 密码错误
	 */
	errorPassword,
	/**
	 * 预约时间不可用
	 */
	errorPlanTime, 
	/**
	 * 订单未支付
	 */
	errorOrderNotPay,
}
