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
}
