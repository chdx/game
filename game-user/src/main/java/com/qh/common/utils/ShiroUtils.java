package com.qh.common.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.qh.api.constenum.UserType;
import com.qh.system.domain.UserDO;

public class ShiroUtils {
	public static Subject getSubjct() {
		return SecurityUtils.getSubject();
	}
	public static UserDO getUser() {
		return (UserDO)getSubjct().getPrincipal();
	}
	public static Integer getUserId() {
		return getUser().getUserId();
	}
	public static String getUsername() {
		return getUser().getUsername();
	}
	public static void logout() {
		getSubjct().logout();
	}
	
	
	public static boolean ifPlatform(){
		return ifPlatform();
	}
	/***
	 * 
	 * @Description 是否为游戏平台
	 * @param user
	 * @return
	 */
	public static boolean ifPlatform(UserDO user){
		return UserType.platform.id() == user.getUserType();
	}
	
	
	public static boolean ifCenter(){
		return ifCenter(getUser());
	}
	/**
	 * @Description 是否为运营中心
	 * @param user
	 * @return
	 */
	public static boolean ifCenter(UserDO user) {
		return UserType.center.id() == user.getUserType();
	}
	
	
	public static boolean ifMember(){
		return ifMember(getUser());
	}
	/**
	 * @Description 是否为会员中心
	 * @param user
	 * @return
	 */
	public static  boolean ifMember(UserDO user) {
		return UserType.member.id() == user.getUserType();
	}
	
	
	public static boolean ifAgent(){
		return ifAgent(getUser());
	}
	/**
	 * @Description 是否为机构代理
	 * @param user
	 * @return
	 */
	public static  boolean ifAgent(UserDO user) {
		return UserType.agent.id() == user.getUserType();
	}
	
	
	public static boolean ifCust(){
		return ifCust(getUser());
	}
	/**
	 * @Description 是否为游戏客户
	 * @param user
	 * @return
	 */
	public static  boolean ifCust(UserDO user) {
		return UserType.cust.id() == user.getUserType();
	}
}
