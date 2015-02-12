package com.natraj.util;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
/**
 * 
 * Util of for all common tasks
 * 
 * @author aman
 *
 */
public class CommonUtil {

	/**
	 * to get current spring security session
	 * @return
	 */
	public static HttpSession getCurrentSession() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession(true);
	}
}
