package com.qh.api.constenum;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserType
 * @Description 用户类型
 * @author chenyuezhi
 * @Date 2017年11月3日 上午11:09:55
 * @version 1.0.0
 */
public enum UserType {
    user(0), // 普通用户
    platform(1), // 游戏平台
    center(2), // 运营中心
    member(3), // 综合会员
    agent(4), // 代理机构，
    cust(5);// 游戏客户

    /**** 用户类型描述 ****/
    private static final Map<Integer, String> descMap = new HashMap<>(8);
    static {
        descMap.put(user.id(), "普通用户");
        descMap.put(platform.id(), "游戏平台");
        descMap.put(center.id(), "运营中心");
        descMap.put(member.id(), "综合会员");
        descMap.put(agent.id(), "代理机构");
        descMap.put(cust.id(), "游戏客户");
    }
    public static Map<Integer,String> desc(){
        return descMap;
    }
    
    
    private static final Map<Integer, UserType> childMap = new HashMap<>(8);
    static {
        childMap.put(platform.id(), center);
        childMap.put(center.id(), member);
        childMap.put(member.id(), agent);
        childMap.put(agent.id(), agent);
    }

    public static UserType getChild(Integer userType) {
        return childMap.get(userType);
    }

    public static String getChildDesc(Integer userType) {
        return descMap.get(getChild(userType).id);
    }
    
    /**** 用户类型描述 ****/
    private static final Map<Integer, UserType> userTypeMap = new HashMap<>(8);
    static {
        userTypeMap.put(user.id(), user);
        userTypeMap.put(platform.id(), platform);
        userTypeMap.put(center.id(), center);
        userTypeMap.put(member.id(), member);
        userTypeMap.put(agent.id(), agent);
        userTypeMap.put(cust.id(), cust);
    }

    public static UserType getUserType(Integer userType) {
        return userTypeMap.get(userType);
    }

    private int id;

    private UserType(int id) {
        this.id = id;
    }

    /**
     * @return
     */
    public int id() {
        return this.id;
    }

    public static Map<Integer,String> addDesc() {
        // TODO Auto-generated method stub
        return null;
    }

}
