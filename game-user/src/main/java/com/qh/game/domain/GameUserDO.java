package com.qh.game.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;



/**
 * 用户
 * 
 * @author chyzh
 * @email 3048427407@qq.com
 * @date 2018-01-18 16:58:32
 */
public class GameUserDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//用户Id
	private Integer userId;
	//用户名
	private String username;
	//显示名称
	private String name;
	//用户类型
	private Integer userType;
	//用户状态
	private Integer userStatus;
	//积分状态
	private Integer intStatus;
	//邀请码
	private String inviteCode;
	//积分账号
	private String intNum;
	//手续分成费率
	private Map<String,BigDecimal> handRate;
	//结算分成比例
	private Map<String,BigDecimal> proportion;
	//平台Id
	private Integer platformId;
	//运营中心Id
	private Integer centerId;
	//会员中心Id
	private Integer memberId;
	//代理机构Id
	private Integer agentId;
	//父类Id
	private Integer parentId;

	/**
	 * 设置：用户Id
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户Id
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置：用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：用户名
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：显示名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：显示名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：用户类型
	 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	/**
	 * 获取：用户类型
	 */
	public Integer getUserType() {
		return userType;
	}
	/**
	 * 设置：用户状态
	 */
	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}
	/**
	 * 获取：用户状态
	 */
	public Integer getUserStatus() {
		return userStatus;
	}
	/**
	 * 设置：
	 */
	public void setIntStatus(Integer intStatus) {
		this.intStatus = intStatus;
	}
	/**
	 * 获取：
	 */
	public Integer getIntStatus() {
		return intStatus;
	}
	/**
	 * 设置：邀请码
	 */
	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
	/**
	 * 获取：邀请码
	 */
	public String getInviteCode() {
		return inviteCode;
	}
	/**
	 * 设置：积分账号
	 */
	public void setIntNum(String intNum) {
		this.intNum = intNum;
	}
	/**
	 * 获取：积分账号
	 */
	public String getIntNum() {
		return intNum;
	}
	/**
	 * 设置：手续分成费率
	 */
	public void setHandRate(Map<String,BigDecimal> handRate) {
		this.handRate = handRate;
	}
	/**
	 * 获取：手续分成费率
	 */
	public Map<String,BigDecimal> getHandRate() {
		return handRate;
	}
	/**
	 * 设置：结算分成比例
	 */
	public void setProportion(Map<String,BigDecimal> proportion) {
		this.proportion = proportion;
	}
	/**
	 * 获取：结算分成比例
	 */
	public Map<String,BigDecimal> getProportion() {
		return proportion;
	}
	/**
	 * 设置：平台Id
	 */
	public void setPlatformId(Integer platformId) {
		this.platformId = platformId;
	}
	/**
	 * 获取：平台Id
	 */
	public Integer getPlatformId() {
		return platformId;
	}
	/**
	 * 设置：运营中心Id
	 */
	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}
	/**
	 * 获取：运营中心Id
	 */
	public Integer getCenterId() {
		return centerId;
	}
	/**
	 * 设置：会员中心Id
	 */
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	/**
	 * 获取：会员中心Id
	 */
	public Integer getMemberId() {
		return memberId;
	}
	/**
	 * 设置：代理机构Id
	 */
	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}
	/**
	 * 获取：代理机构Id
	 */
	public Integer getAgentId() {
		return agentId;
	}
	/**
	 * 设置：父类Id
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：父类Id
	 */
	public Integer getParentId() {
		return parentId;
	}
}
