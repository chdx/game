package com.qh.game.constenum;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName GameIntStatus
 * @Description 游戏用户积分状态  未激活(提示绑定银行卡提现) 1激活 2冻结(不可交易、不可以提现)  默认激活1
 * @author chenyuezhi
 * @Date 2018年1月19日 下午4:37:13
 * @version 1.0.0
 */
public enum GameIntStatus {
    inactive(0),//未激活
    active(1),//激活
    freeze(2);//冻结(不可交易、不可以提现)
    private static final Map<Integer,String> descMap = new HashMap<>();
    static{
        descMap.put(inactive.id, "未激活");
        descMap.put(active.id, "激活");
        descMap.put(freeze.id, "冻结");
    }
    public static final Map<Integer,String> desc(){
        return descMap;
    }
    
    private Integer id;
    
    public int id(){
        return this.id;
    }
    
    private GameIntStatus(int id){
        this.id = id;
    }
}
