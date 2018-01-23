package com.qh.game.service;

import com.qh.game.domain.GameCustDO;

import java.util.List;
import java.util.Map;

/**
 * 客户
 * 
 * @author chyzh
 * @email 3048427407@qq.com
 * @date 2018-01-18 16:58:32
 */
public interface GameCustService {
	
	GameCustDO get(Integer userId);
	
	List<GameCustDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(GameCustDO gameCust);
	
	int update(GameCustDO gameCust);
	
	int remove(Integer userId);
	
	int batchRemove(Integer[] userIds);
}
