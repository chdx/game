package com.qh.game.constenum;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName GameUserStatus
 * @Description 游戏用户状态
 * @author chenyuezhi
 * @Date 2018年1月19日 下午4:36:44
 * @version 1.0.0
 */
public enum GameUserStatus {
    normal(0),//正常
    freeze(1);//冻结(不可交易)
    private static final Map<Integer,String> descMap = new HashMap<>();
    static{
        descMap.put(normal.id, "正常");
        descMap.put(freeze.id, "冻结");
    }
    public static final Map<Integer,String> desc(){
        return descMap;
    }
    
    
    private Integer id;
    
    public int id(){
        return this.id;
    }
    
    private GameUserStatus(int id){
        this.id = id;
    }
}
