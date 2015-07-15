package com.inlog.commons;

/**
 * Local thread to store incoming user as user name.
 * User name should be available in X-AUTH-USER-HEADER and since this is state less
 * it should be available in all the calls that we make
 * 
 * @author Mayur
 *
 */
public class UserThread {
	private static final ThreadLocal<String> localThread = new ThreadLocal<String>();

	public static void setThreadLocal(String username) {
		localThread.set(username);
	}

	public static String getUserName() {
		return localThread.get();
	}
}
