package com.qh.api.constenum;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName FeeType
 * @Description 费用类型
 * @author chenyuezhi
 * @Date 2017年11月14日 下午3:24:48
 * @version 1.0.0
 */
public enum FeeType {
	/****平台下单收入 手续费用***/
	platIn(0),
	
	/****平台代付收入 手续费用***/
	platAcpIn(1), 
	
	/****商户下单收入 余额增加***/
	merchIn(2), 
	
	/****商户代付支出 余额扣减****/
	merchAcpOut(3),
	
	/****商户代付失败 余额返还**/
	merchAcpFail(4),
	
	/****商户代付未通过 余额返还**/
	merchAcpNopass(5),
	
	/****商户代理下单收入 手续费用***/
	agentIn(6), 
	
	/****商户代理代付收入 手续费用***/
	agentAcpIn(7),
	
	/****商户支付预付费 手续费用****/
	merchPreHand(8),
	
	/****商户充值 余额增加******/
	merchCharge(9),
	
	/****用户提现 余额减少***/
	withdrawOut(10),
	
	/****用户提现失败 余额返还**/
	withdrawFail(11),
	
	/****用户提现未通过 余额返还**/
	withdrawNopass(12),
	
	/****用户提现收入 手续费用***/
	platWithdrawIn(13);
	
	/**** 费用类型描述 ****/
	private static final Map<Integer, String> descMap = new HashMap<>(8);
	static {
		descMap.put(platIn.id(), "平台下单收入");
		descMap.put(platAcpIn.id(), "平台代付收入");
		descMap.put(merchIn.id(), "商户下单收入");
		descMap.put(merchAcpOut.id(), "商户代付支出");
		descMap.put(merchAcpFail.id(), "商户代付失败返还");
		descMap.put(merchAcpNopass.id(), "商户代付审核返还");
		descMap.put(agentIn.id(), "商户代理下单收入");
		descMap.put(agentAcpIn.id(), "商户代理代付收入");
		descMap.put(merchPreHand.id(), "商户预付手续费");
		descMap.put(merchCharge.id(), "商户充值收入");
		descMap.put(withdrawOut.id(), "提现支出");
		descMap.put(withdrawFail.id(), "提现失败返还");
		descMap.put(withdrawNopass.id(), "提现未通过返还");
		descMap.put(platWithdrawIn.id(), "平台提现收入");
	}

	
	/**** 商户费用类型描述 ****/
	private static final Map<Integer, String> merchDescMap = new HashMap<>(8);
	static {
		merchDescMap.put(merchIn.id(), descMap.get(merchIn.id()));
		merchDescMap.put(merchAcpOut.id(), descMap.get(merchAcpOut.id()));
		merchDescMap.put(merchAcpFail.id(), descMap.get(merchAcpFail.id()));
		merchDescMap.put(merchAcpNopass.id(), descMap.get(merchAcpNopass.id()));
		merchDescMap.put(merchPreHand.id(), descMap.get(merchPreHand.id()));
		merchDescMap.put(merchCharge.id(), descMap.get(merchCharge.id()));
		merchDescMap.put(withdrawOut.id(), descMap.get(withdrawOut.id()));
		merchDescMap.put(withdrawFail.id(), descMap.get(withdrawFail.id()));
		merchDescMap.put(withdrawNopass.id(), descMap.get(withdrawNopass.id()));
	}
	
	/**** 代理费用类型描述 ****/
	private static final Map<Integer, String> agentDescMap = new HashMap<>(8);
	static {
		agentDescMap.put(agentIn.id(), "商户代理下单收入");
		agentDescMap.put(agentAcpIn.id(), "商户代理代付收入");
		agentDescMap.put(withdrawOut.id(), descMap.get(withdrawOut.id()));
		agentDescMap.put(withdrawFail.id(), descMap.get(withdrawFail.id()));
		agentDescMap.put(withdrawNopass.id(), descMap.get(withdrawNopass.id()));
	}
	
	private int id;

	private FeeType(int id) {
		this.id = id;
	}

	public int id() {
		return id;
	}

	public static Map<Integer, String> desc() {
		return descMap;
	}
	public static Map<Integer, String> merchDesc() {
		return merchDescMap;
	}
	public static Map<Integer, String> agentDesc() {
		return agentDescMap;
	}
}
