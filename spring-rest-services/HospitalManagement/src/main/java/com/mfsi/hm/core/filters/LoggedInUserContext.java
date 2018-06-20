/**
 * 
 */
package com.mfsi.hm.core.filters;

import com.mfsi.hm.biztier.vos.UserVO;

/**
 * @author shah
 *
 */
public class LoggedInUserContext {

	private static final ThreadLocal<UserVO> context = new ThreadLocal<UserVO>();
	
	public static void setUser(UserVO user){
		context.set(user);
	}
	
	public static UserVO getUser(){
		return context.get();
	}
	
	public static void clear(){
		context.remove();
	}
}
