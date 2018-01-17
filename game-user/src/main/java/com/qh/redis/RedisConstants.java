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
    
    /***用户缓存**/
    public static final String cache_user = "user_";
    
    /***配置缓存**/
    public static final String cache_config = "cfg_";
    
    /***父类配置缓存**/
    public static final String cache_config_parent = "cfg_p_";
    
    /***配置缓存**/
    public static final String cache_payConfig = "payCfg_";
    
    /***支付配置缓存****/
    public static final String cache_payConfigCompany = "payCfgCmp_";
    
    /***聚富商户****/
    public static final String cache_merchant = "merch_";
    
    /***余额账户缓存 ---平台资金账户***/
    public static final String cache_bal_foundAcct = "bal_foundAcct_";
    
    /***余额账户缓存 ---聚富商户***/
    public static final String cache_bal_merch = "bal_merch_";
    
    /***扫码通道支付金额占用缓存*****/
    public static final String cache_monAmount_occupy = "monAmount_occupy_";
    
    /***扫码通道支付金额订单号*****/
    public static final String cache_monAmount_orderNo = "monAmount_orderNo_";
    
    /***扫码通道支付金额业务订单号*****/
    public static final String cache_qr_businessNo = "qr_businessNo_";
    
    /***扫码通道支付金额占用 lock*******/
    public static final String lock_monAmount_occupy = "lock_monAmount_";
    
    /***同步余额账户缓存 ---聚富商户***/
    public static final String lock_bal_merch = "lock_merch_";
    
    /***余额账户缓存 ---聚富代理***/
    public static final String cache_bal_agent = "bal_agent_";
    
    /***同步余额账户缓存 ---聚富代理***/
    public static final String lock_bal_agent = "lock_agent_";
    
    /***同步余额账户缓存 ---平台资金账户***/
    public static final String lock_bal_foundAcct = "lock_foundAcct_";
    
    /***订单列表*****/
    public static final String cache_order = "ord_";
    
    /***排序订单列表****/
    public static final String cache_sort_order = "sort_ord_";
    
    /***商户充值列表****/
    public static final String cache_charge = "charge_";
    
    /***代付订单列表****/
    public static final String cache_order_acp = "ord_acp_";
    
    
    /***排序代付列表****/
    public static final String cache_sort_acp_order = "sort_acp_";
    
    /***银行列表***/
    public static final String cache_banks = "banks_";
    
    /***聚富支付二维码收款列表***/
    public static final String cache_qrs = "qrs_";
    
    /***订单列表 同步*****/
    public static final String lock_order = "lock_ord_";
    
    /***充值列表 同步****/
    public static String lock_charge = "lock_charge";
    
    /***订单通知列表同步***/
    public static final String lock_event_order = "lock_evtord_";
    
    /***订单通知列表同步***/
    public static final String lock_event = "lock_event_";
    
    /***代付订单列表 同步****/
    public static final String lock_order_acp = "lock_ord_acp_";
    
    /***代付通知列表同步***/
    public static final String lock_event_order_acp = "lock_evtacp_";
    
    /***支付订单清算***/
    public static final String lock_clear = "lock_clear_";
    
    
    /***订单渠道***/
    public static final String channel_order = "chl_ord";
    
    /***订单渠道***/
    public static final String channel_order_notify = "chl_ord_not";
    
    /***订单数据保存**/
    public static final String channel_order_data = "chl_ord_data";
    
    /***商户充值保存*******/
    public static final String channel_charge_data = "chl_charge_data";
    
    /***代付订单下单***/
    public static final String channel_order_acp = "chl_ord_acp";
    
    /***订单渠道***/
    public static final String channel_order_acp_notify = "chl_ord_acp_not";
    
    /***代付订单未通过***/
    public static final String channel_order_acp_nopass = "chl_ord_acp_nopass";
    
    /***代付订单数据保存**/
    public static final String channel_order_acp_data = "chl_ord_acp_data";
    
    /***过期事件通知***/
    public static final String channel_keyevent_expired = "__keyevent@0__:expired";
    
    /***支付通知队列**/
    public static final String cache_keyevent_ord = "evtord";
    
    /***代付通知队列**/
    public static final String cache_keyevent_acp = "evtacp";

    /**聚富支付网关最近连接时间**/
    public static final String cache_qr_last_login_time = "qr_last_login_time";
    
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
