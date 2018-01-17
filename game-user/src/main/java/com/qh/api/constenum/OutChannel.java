package com.qh.api.constenum;

import java.util.HashMap;
import java.util.Map;

import com.qh.common.config.Constant;

/**
 * 
 * @ClassName: OutChannel
 * @Description: 支付通道枚举类 微信WAP支付：wap 微信公众号支付：gzh 微信扫码：wx QQ钱包扫码：qq
 *               支付宝扫码：ali 快捷支付：q 网银支付：wy 代付：acp
 * @author chyzh
 * @date 2017年10月24日 下午8:02:11
 *
 */
public enum OutChannel {
	/***聚富微信扫码收款**/
	jfwx,
	/***聚富支付宝扫码收款***/
	jfali,
	/***微信WAP支付***/
	wap,
	/***微信公众号***/
	gzh,
	/***微信扫码**/
	wx,
	/***QQ钱包扫码**/
	qq, 
	/***支付宝扫码***/
	ali,
	/***快捷支付***/
	q,
	/***网银支付****/
	wy,
	/***代付**/
	acp,
	/***商铺收款***/
	sp,
	/***人工充值***/
	man;
	/****支付渠道****/
	private static final Map<String,String> descMap = new HashMap<>(16);
	static{
		descMap.put(jfwx.name(), Constant.pay_name +  "微信扫码");
		descMap.put(jfali.name(), Constant.pay_name + "支付宝扫码");
		descMap.put(acp.name(), "代付");
		descMap.put(ali.name(), "支付宝扫码");
		descMap.put(wy.name(), "网银");
		descMap.put(gzh.name(), "公众号");
		descMap.put(q.name(), "快捷");
		descMap.put(qq.name(), "QQ钱包扫码");
		descMap.put(sp.name(), "商铺收款");
		descMap.put(wap.name(), "微信WAP");
		descMap.put(wx.name(), "微信扫码");
	}

	private static final Map<String,String> adminDescMap = new HashMap<>(4);
	static{
		adminDescMap.put(man.name(), "人工充值");
	}
	
	public static Map<String, String> desc() {
		return descMap;
	}

	public static String getDesc(String key){
		return descMap.get(key);
	}

	/***当前支付公司渠道*******/
	private static final Map<String,String> jfDescMap = new HashMap<>(4);
	static{
		jfDescMap.put(jfwx.name(), Constant.pay_name +  "微信扫码");
		jfDescMap.put(jfali.name(), Constant.pay_name + "支付宝扫码");
	}
	
	public static Map<String, String> jfDesc() {
		return jfDescMap;
	}

	/****所有通道*****************/;
	private static final Map<String,String> allDescMap = new HashMap<>(16);
	static{
		allDescMap.putAll(descMap);
		allDescMap.putAll(jfDescMap);
		allDescMap.putAll(adminDescMap);
	}
	/**
	 * @Description 返回所有的通道
	 * @return
	 */
	public static final Map<String,String> all() {
		return allDescMap;
	}


	
	/**
	 * @Description 返回所有商户可选通道
	 * @return
	 */
	private static final Map<String,String> merchAllDescMap = new HashMap<>(16);
	static{
		merchAllDescMap.putAll(descMap);
		merchAllDescMap.putAll(jfDescMap);
	}
	public static final Map<String,String> merchAll() {
		return merchAllDescMap;
	}
}
