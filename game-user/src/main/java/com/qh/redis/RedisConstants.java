package com.qh.redis;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: RedisConstants
 * @Description: 常用的常量
 * @author chyzh
 * @date 2017年10月25日 下午9:11:21
 */
public class RedisConstants {
	
    /** 默认超时时间（毫秒） */  
    public static final long DEFAULT_TIME_OUT = 1000; 
    
    /***连接符**/
	public static final String link_symbol = "_";
    
	/***redis唯一号****/
	public static final String key_idNo = "idNo";
	
	/***登录用户****/
	public static final String cache_login_user = "login_user_";
	
    /***用户缓存**/
    public static final String cache_user = "user_";
    
    /***配置缓存**/
    public static final String cache_config = "cfg_";
    
    /***父类配置缓存**/
    public static final String cache_config_parent = "cfg_p_";
    
    /***支付配置缓存**/
    public static final String cache_payConfig = "payCfg_";
    
    /****平台缓存*****/
    public static final String cache_platform_ = "platf_";
    
    /****运营中心缓存**/
    public static final String cache_center_ = "cent_";
    
    /****会员中心缓存****/
    public static final String cache_member_ = "memb_";
    
    /****机构代理******/
    public static final String cache_agent_ = "agent_";
    
    /****游戏客户***/
    public static final String cache_cust = "cust_";
    
    /****机构代理余额*****/
    public static final String cache_bal_agent = "bal_agent_";
    
    /***过期事件通知***/
    public static final String channel_keyevent_expired = "__keyevent@0__:expired";
    
    /***事件通知间隔时间  10 分钟****/
    public static final int keyevent_10 = 10;
    
    /***事件通知间隔时间  20 分钟****/
    public static final int keyevent_20 = 20;
    
    /***事件通知间隔时间  40 分钟****/
    public static final int keyevent_40 = 40;
    
    /***事件通知间隔时间  80 分钟****/
    public static final int keyevent_80 = 80;
    
    /***事件通知间隔时间  160 分钟****/
    public static final int keyevent_160 = 160;
    
    /***事件通知间隔时间  320 分钟****/
    public static final int keyevent_320 = 320;
    
    public static final Map<Integer,Integer> evtMinuteMap = new HashMap<>();

    static{
    	evtMinuteMap.put(keyevent_10, keyevent_20);
    	evtMinuteMap.put(keyevent_20, keyevent_40);
    	evtMinuteMap.put(keyevent_40, keyevent_80);
    	evtMinuteMap.put(keyevent_80, keyevent_160);
    	evtMinuteMap.put(keyevent_160, keyevent_320);
    	evtMinuteMap.put(keyevent_320, 0);
    }
    
}
