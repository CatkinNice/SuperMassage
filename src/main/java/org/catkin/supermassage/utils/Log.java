package org.catkin.supermassage.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author cakin
 *
 */
public class Log {
	private final static String SUPER_MASSAGE = "org.catkin.supermassage";
	private static Logger logger = LoggerFactory.getLogger(SUPER_MASSAGE);
	
	public static void info(String msg) {
		logger.info(msg);
	}
	
	public static void info(Throwable t) {
		logger.info(t.getMessage(), t);
	}
	
	public static void info(String msg, Throwable t) {
		logger.info(msg, t);
	}
	
	public static void error(String msg) {
		logger.error(msg);
	}
	
	public static void error(Throwable t) {
		logger.error(t.getMessage(), t);
	}
	
	public static void error(String msg, Throwable t) {
		logger.error(msg, t);
	}
}
