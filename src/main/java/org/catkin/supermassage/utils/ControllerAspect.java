package org.catkin.supermassage.utils;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Aspect
@Component
public class ControllerAspect {

	@Around("within(org.catkin.supermassage.controller.*Controller)")
	public Object around(ProceedingJoinPoint jp) {
		Log.info(getLogInfo(jp));
		Object result = null;
		try {
			result = jp.proceed();
		} catch (LogicException e) {
			Log.error(e);
			throw e;
		} catch (Throwable t) {
			Log.error(t);
		}
		return result;
	}

	private String getLogInfo(JoinPoint jp) {
		StringBuilder info = new StringBuilder("Url:");
		try {
			// 方法名
			String methodName = jp.getSignature().getName();
			// 类
			Class<?> clz = jp.getSignature().getDeclaringType();
			// @RequestMapping注解
			RequestMapping mapping =  clz.getAnnotation(RequestMapping.class);
			if (mapping != null && mapping.value().length > 0) {
				info.append(mapping.value()[0]);
			}
			
			// 参数
			String param = ", param:【";
			Object [] args = jp.getArgs();
			Class<?>[] clzs = new Class<?>[args.length];
			
			for (int i = 0; i < args.length; i++) {
				if (args[i] != null) {
					clzs[i] = args[i].getClass();
					param += args[i] + ",";
				} else {
					clzs[i] = Serializable.class;
					param += "null,";
				}
			}

			// 方法
			try {
				Method method = clz.getMethod(methodName, clzs);
				mapping =  method.getAnnotation(RequestMapping.class);
				
				if (mapping != null) {
					if (mapping.value().length > 0) {
						info.append(mapping.value()[0]);
					}
					
					if (mapping.method().length > 0) {
						info.append(", method:").append(mapping.method()[0]);
					}
				}
			} catch (Exception e) {
			}
			
			if (args.length > 0) {
				info.append(param);
				info.deleteCharAt(info.length() - 1);
				info.append("】");
			}
		} catch (Exception ex) {
			Log.error(ex);
		}
		return info.toString();
	}
	
}
