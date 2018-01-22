package com.qh.api.constenum;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName GameType
 * @Description 游戏种类
 * @author chenyuezhi
 * @Date 2018年1月22日 上午10:19:47
 * @version 1.0.0
 */
public enum GameType {
    /***世界杯***/
    FIFAWC;
    
    /**** 游戏种类描述 ****/
    private static final Map<String, String> descMap = new HashMap<>(8);
    static {
        descMap.put(FIFAWC.name(), "世界杯");
    }
}
